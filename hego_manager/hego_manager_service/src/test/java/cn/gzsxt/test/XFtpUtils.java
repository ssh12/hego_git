package cn.gzsxt.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class XFtpUtils {

	/**
	 * 连接sftp服务器
	 */
	public ChannelSftp connect(String host, int port, String username, String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
		} catch (Exception e) {
			System.out.println("link failure !");
		}
		return sftp;
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException {
		return sftp.ls(directory);
	}

	public static void main(String[] args) {
		XFtpUtils sf = new XFtpUtils();
		String host = "192.168.59.130"; 			//主机
		int port = 22;								//端口
		String username = "ftpuser";					//
		String password = "ftpuser";					//
		String directory = "/home/ftpuser/";	//服务器image保存位置
		
		//上传文件    本地文件位置
		String uploadFile = "C:\\Users\\yz784007841\\Desktop\\微信截图_20180115133009.png";
		//服务器端的文件    即将被下载
		String downloadFile = "微信截图_20180115133009.png";
		//本地下载文件的    保存位置 与文件名
		String saveFile = "C:\\Users\\yz784007841\\Desktop\\2.jpg";
		//服务器端的文件    即将被删除
		String deleteFile = "1.jpg";
		
		//连接服务器   （连接通道 协议    sftp）
		ChannelSftp sftp = sf.connect(host, port, username, password);
		
		//上传文件
		sf.upload(directory, uploadFile, sftp);
		//下载文件
		//sf.download(directory, downloadFile, saveFile, sftp);
		//删除文件
		//sf.delete(directory, deleteFile, sftp);
		try {
			//sftp.rmdir("ss");
			sftp.cd(directory);
			//sftp.mkdir("ss");
			System.out.println("finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
