package cn.gzsxt.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pojo.TbItem;
import cn.gzsxt.service.ItemService;


@Controller
@RequestMapping("/item")
public class ItemAction {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable("itemId") Long itemId) {
		System.out.println("查询前");
		TbItem item = itemService.getItemById(itemId);
		System.out.println("查询后");
		return item;
	}
}
