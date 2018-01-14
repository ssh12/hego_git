package cn.gzsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gzsxt.mapper.TbItemMapper;
import cn.gzsxt.pojo.TbItem;
import cn.gzsxt.pojo.TbItemExample;
import cn.gzsxt.service.ItemService;
import cn.gzsxt.vo.EUDataGriadResult;

@Service
public class ItemServiceimpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(Long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

	@Override
	public EUDataGriadResult getAllItem(int page, int rowNum) {
		TbItemExample itemExample = new TbItemExample();
		//使用分页帮助插件
		//获取第page页，rowNum条内容，默认查询总数count
		PageHelper.startPage(page, rowNum);
		
		//在执行sql之前
		//紧跟着的第一个select方法会被分页
		List<TbItem> itemList = itemMapper.selectByExample(itemExample);
		//使用pageinfo对象拿到itemlist分页之后的信息
		PageInfo<TbItem> info = new PageInfo<>(itemList);
		
		EUDataGriadResult result = new EUDataGriadResult();
		result.setTotal(info.getTotal());
		result.setRows(itemList);
		
		return result;
	}

}
