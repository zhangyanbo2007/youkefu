package com.ukefu.webim.service.task;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownLoadFileTask implements Runnable{
	
	private String urlStr ;
	private String fileName ;
	private String savePath ;
	
	public DownLoadFileTask( String urlStr , String fileName , String savePath) {
		this.urlStr = urlStr;
		this.fileName = fileName;
		this.savePath = savePath;
	}

	@Override
	public void run() {
		try {
			downLoadFromUrl(urlStr, fileName, savePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从输入流中获取字节数组
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
		byte[] buffer = new byte[1024];  
		int len = 0;  
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		while((len = inputStream.read(buffer)) != -1) {  
			bos.write(buffer, 0, len);  
		}  
		bos.close();  
		return bos.toByteArray();  
	}  
	
	/**
	 * 从网络Url中下载文件
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
		URL url = new URL(urlStr);  
		URLConnection conn = url.openConnection();  
                //设置超时间为6秒
		conn.setConnectTimeout(6*1000);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
 
		//得到输入流
		InputStream inputStream = conn.getInputStream();  
		//获取自己数组
		byte[] getData = readInputStream(inputStream);    
 
		//文件保存位置
		File saveDir = new File(savePath);
		if(!saveDir.exists()){
			saveDir.mkdir();
		}
		File file = new File(saveDir+File.separator+fileName);    
		FileOutputStream fos = new FileOutputStream(file);     
		fos.write(getData); 
		if(fos!=null){
			fos.close();  
		}
		if(inputStream!=null){
			inputStream.close();
		}
		System.out.println("info:"+url+" download success"); 
 
	}

}
