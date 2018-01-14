package cn.gzsxt.service;

import java.util.List;

import cn.gzsxt.vo.EUTreeNode;

public interface ItemCatService {
	public List<EUTreeNode> getAllItemCat(Long parentId);
}
