package cn.gzsxt.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pojo.TbItem;
import cn.gzsxt.service.ItemService;
import cn.gzsxt.vo.EUDataGriadResult;
import cn.gzsxt.vo.EgoResult;


@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable("itemId") Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	//localhost/item/list?page=2&rowNum=5
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGriadResult getItemList(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="30")Integer rowNum) {
		EUDataGriadResult result = itemService.getAllItem(page, rowNum);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public EgoResult saveItemWithItemDesc(TbItem item, String desc) {
		itemService.saveItem(item, desc);
		return EgoResult.ok();
	}
	
	
}
