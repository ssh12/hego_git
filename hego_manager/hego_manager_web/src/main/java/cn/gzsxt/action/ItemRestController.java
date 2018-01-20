package cn.gzsxt.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pojo.TbItem;
import cn.gzsxt.pojo.TbItemDesc;
import cn.gzsxt.service.ItemService;
import cn.gzsxt.vo.EgoResult;

@Controller
@RequestMapping("/rest/item")
public class ItemRestController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/query/item/desc/{id}")
	@ResponseBody
	public EgoResult queryItemDesc(@PathVariable Long id) {
		EgoResult result = itemService.geTbItemDesc(id);
		return result;
		
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public EgoResult upDataItem(TbItem item, String desc) {
		itemService.upDateItem(item, desc);
		return EgoResult.ok();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public EgoResult deleteItem(@RequestParam("ids") Long ids) {
		itemService.deleteItem(ids);
		return EgoResult.ok();
	}
	
	@RequestMapping("/instock")
	@ResponseBody
	public EgoResult instockItem(@RequestParam("ids") Long ids) {//下架
		itemService.instockItem(ids);
		return EgoResult.ok();
	}
	
	@RequestMapping("/reshelf")
	@ResponseBody
	public EgoResult reshelfItem(@RequestParam("ids") Long ids) {//上架
		itemService.reshelfItem(ids);
		return EgoResult.ok();
	}
	
}
