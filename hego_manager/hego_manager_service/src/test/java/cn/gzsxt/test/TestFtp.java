package cn.gzsxt.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class TestFtp {
	public static void main(String[] args) throws Exception {

		FTPClient ftpClient = new FTPClient();
		
		ftpClient.connect("192.168.59.130");
		ftpClient.login("ftpuser", "ftpuser");
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\yz784007841\\Desktop\\微信截图_20180115133009.png"));
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.enterLocalPassiveMode();
		ftpClient.storeFile("2.jpg", inputStream);
		inputStream.close();

		ftpClient.logout();
	}

}
