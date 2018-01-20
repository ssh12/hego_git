package cn.gzsxt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gzsxt.mapper.TbItemDescMapper;
import cn.gzsxt.mapper.TbItemMapper;
import cn.gzsxt.pojo.TbItem;
import cn.gzsxt.pojo.TbItemDesc;
import cn.gzsxt.pojo.TbItemExample;
import cn.gzsxt.service.ItemService;
import cn.gzsxt.util.IDUtils;
import cn.gzsxt.vo.EUDataGriadResult;
import cn.gzsxt.vo.EgoResult;

@Service
public class ItemServiceimpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
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

	@Override
	public void saveItem(TbItem item, String desc) {
		// 商品表的信息
		//其他属性，已经由表单提交到set进入
		long itemId = IDUtils.genItemId();
		Date date = new Date();
		item.setId(itemId);
		item.setCreated(date);
		item.setUpdated(date);
		// 商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		// 保存商品信息到商品表
		itemMapper.insert(item);
		// 商品描述的信息
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public EgoResult geTbItemDesc(Long id) {
		TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(id);
		EgoResult egoResult = new EgoResult(tbItemDesc);
		return egoResult;
	}

	@Override
	public void upDateItem(TbItem item, String desc) {
		Long id = item.getId();
		Date date = new Date();
		item.setUpdated(date);
		itemMapper.updateByPrimaryKeySelective(item);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(date);
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void deleteItem(Long id) {
		itemMapper.deleteByPrimaryKey(id);
		itemDescMapper.deleteByPrimaryKey(id);
		//删除图片
	}

	@Override
	public void instockItem(Long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		// 商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 2);
		itemMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public void reshelfItem(Long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		// 商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		itemMapper.updateByPrimaryKeySelective(item);
	}

}
