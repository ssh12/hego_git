package cn.gzsxt.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.service.ItemCatService;
import cn.gzsxt.vo.EUTreeNode;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService ItemService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getAllItemCatByParentId(@RequestParam(value="id",defaultValue="0") long parentId){
		List<EUTreeNode> treeNodeList = ItemService.getAllItemCat(parentId);
		return treeNodeList;
	}
	
}
