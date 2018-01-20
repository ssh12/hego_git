package cn.gzsxt.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.service.PictureService;
import cn.gzsxt.vo.PictureResult;

@Controller
@RequestMapping("/pic")
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public PictureResult upLoadImage(MultipartFile uploadFile) {
		PictureResult pictureResult = pictureService.uploadFile(uploadFile);
		//返回图片的成功与保存位置url
		return pictureResult;
	}
	
}
