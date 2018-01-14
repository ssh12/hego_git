package cn.gzsxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.mapper.TbItemCatMapper;
import cn.gzsxt.pojo.TbItemCat;
import cn.gzsxt.pojo.TbItemCatExample;
import cn.gzsxt.pojo.TbItemCatExample.Criteria;
import cn.gzsxt.service.ItemCatService;
import cn.gzsxt.vo.EUTreeNode;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EUTreeNode> getAllItemCat(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> itemCatList = itemCatMapper.selectByExample(example);
		// 把itemCatList转换为EUTreeNode对象List
		List<EUTreeNode> treeNodeList = new ArrayList<>();
		EUTreeNode node = null;
		for (TbItemCat tbItemCat : itemCatList) {
			node = new EUTreeNode();
			// 给node设置id,text,state
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());			
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			treeNodeList.add(node);
		}
		return treeNodeList;
	}

}
