package cn.gzsxt.service;

import cn.gzsxt.pojo.TbItem;
import cn.gzsxt.vo.EUDataGriadResult;

public interface ItemService {
	public TbItem getItemById(Long id);
	public EUDataGriadResult getAllItem(int page,int rewNum);
}
