package cn.gzsxt.service;

import cn.gzsxt.pojo.TbItem;
import cn.gzsxt.pojo.TbItemDesc;
import cn.gzsxt.vo.EUDataGriadResult;
import cn.gzsxt.vo.EgoResult;

public interface ItemService {
	public TbItem getItemById(Long id);
	public EUDataGriadResult getAllItem(int page,int rewNum);
	public void saveItem(TbItem item,String desc);
	public void upDateItem(TbItem item,String desc);
	public EgoResult geTbItemDesc(Long id);
	public void deleteItem(Long id);
	public void instockItem(Long id);
	public void reshelfItem(Long id);
}
