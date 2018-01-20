package cn.gzsxt.service;

import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.vo.PictureResult;

public interface PictureService {
	public PictureResult uploadFile(MultipartFile uploadFile);
}
