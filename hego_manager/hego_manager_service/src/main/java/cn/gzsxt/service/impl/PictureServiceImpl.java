package cn.gzsxt.service.impl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.gzsxt.service.PictureService;
import cn.gzsxt.util.DateUtil;
import cn.gzsxt.util.FtpUtil;
import cn.gzsxt.util.IDUtils;
import cn.gzsxt.vo.PictureResult;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	
	@Value("${FTP_PORT}")
	private int FTP_PORT;
	
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;//    /home/ftpuser/image
	
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;//   http://192.168.59.130
	
	
	@Override
	public PictureResult uploadFile(MultipartFile uploadFile) {
		String fileSavePath = null;
		try {
			fileSavePath = saveImageFile(uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PictureResult result = null;
		if(fileSavePath != null && !"".equals(fileSavePath)) {
			//上传成功
			result = new PictureResult(0, fileSavePath);
		}else {
			//上传失败
			result = new PictureResult(1, IMAGE_BASE_URL + "/errorImage.png", "图片上传失败!");
		}
		return result;
	}
	
	private String saveImageFile(MultipartFile uploadFile) throws IOException {
		// 判断是否文件上传
		if(StringUtils.isEmpty(uploadFile)) {
			return null;
		}
		// 文件以天为单位存放     FTP_BASE_PATH=/home/ftpuser/image + filePath
		String filePath = "/" + DateUtil.getYearStr(new Date())
						+ "/" + DateUtil.getMonthStr(new Date())
						+ "/" + DateUtil.getDayStr(new Date());
		// 原来需要上传的图片的名称[包含文件后缀]
		String fileName = uploadFile.getOriginalFilename();
		//保存图片名字 为当前时间+文件后缀
		String saveFileName = IDUtils.genImageName() + fileName.substring(fileName.lastIndexOf("."));
		
		boolean file = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, filePath, saveFileName, uploadFile.getInputStream());
/*		if(file) {
			System.out.println("上传成功");
		}else {
			System.out.println("上传失败");
		}	*/	
		
		return IMAGE_BASE_URL + filePath +"/" +saveFileName;
	}

}
