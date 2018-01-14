package cn.gzsxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public EUDataGriadResult getAllItem(int page, int rewNum) {
		TbItemExample itemExample = new TbItemExample();
		//使用分页帮助插件
		
		
		return null;
	}

}
