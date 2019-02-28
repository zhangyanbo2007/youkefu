package com.ukefu.webim.web.handler.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.AttachmentRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.AttachmentFile;
import com.ukefu.webim.web.model.UploadStatus;

@Controller
@RequestMapping("/res")
public class MediaController extends Handler{
	
	@Value("${web.upload-path}")
    private String path;
	
	private String TEMPLATE_DATA_PATH = "WEB-INF/data/templates/";
	
	@Autowired
	private AttachmentRepository attachementRes;
	
    @RequestMapping("/image")
    @Menu(type = "resouce" , subtype = "image" , access = true)
    public void index(HttpServletResponse response, @Valid String id) throws IOException {
    	File file = new File(path ,id) ;
    	if(!StringUtils.isBlank(id) && !(id.endsWith(".png") || id.endsWith(".jpg"))){
	    	if(id.endsWith("_original") && !file.exists()){
	    		File orgFile = new File(path , id.substring(0 , id.indexOf("_original"))) ;
	    		if(orgFile.exists()){
	    			UKTools.processImage(file = new File(path , id), orgFile) ;
	    		}
	    	}else if(!StringUtils.isBlank(id) && file.exists() && !id.endsWith("_original")){
	    		File originalFile = new File( path , id+"_original") ;
	    		if(!originalFile.exists()){
	    			UKTools.processImage(new File( path , id+"_original"), file) ;
	    		}
	    	}else if(!StringUtils.isBlank(id) && !file.exists() && !id.endsWith("_original")){
	    		File destFile = new File(path , id+"_original") ;
	    		if(destFile.exists()){
	    			UKTools.processImage(new File(path + id), destFile) ;
	    		}
	    		file = new File(path , id) ;
	    	}
    	}
    	if(file.exists() && file.isFile()){
    		response.setHeader("Content-Type","image/png");
    		response.setContentType("image/png");
    		response.getOutputStream().write(FileUtils.readFileToByteArray(new File(path ,id)));
    	}
    }
    
    @RequestMapping("/voice")
    @Menu(type = "resouce" , subtype = "voice" , access = true)
    public void voice(HttpServletResponse response, @Valid String id) throws IOException {
    	File file = new File(path ,id) ;
//    	response.setContentType(Files.probeContentType(Paths.get(file.getAbsolutePath())));  
    	response.setHeader("Content-Type" , "application/octet-stream");
    	response.setHeader("Content-Lenght" , String.valueOf(file.length()));
    	response.setContentLengthLong(file.length());
        response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(file.getName(), "UTF-8"));  
        if(file.exists() && file.isFile()){
    		FileInputStream input = new FileInputStream(file) ;
    		OutputStream output = response.getOutputStream() ; 
    		byte[] data = new byte[1024] ;
    		int len = 0 ;
    		while((len = input.read(data))>0) {
    			output.write(data , 0  , len);
    		}
    		output.flush();
    		input.close();
    	}
    }
    
    @RequestMapping("/voice/{type}/{id}")
    @Menu(type = "resouce" , subtype = "voice" , access = true)
    public void voiceplay(HttpServletResponse response, HttpServletRequest request, @PathVariable String type, @PathVariable String id) throws IOException {
    	File file = new File(path ,type+"/"+id + request.getRequestURI().substring(request.getRequestURI().lastIndexOf("."))) ;
    	response.setHeader("Content-Type" , "application/octet-stream");  
    	response.setHeader("Content-Lenght" , String.valueOf(file.length()));
    	response.setContentLengthLong(file.length());
    	response.setContentType(Files.probeContentType(Paths.get(file.getAbsolutePath())));  
        response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(file.getName(), "UTF-8"));  
    	if(file.exists() && file.isFile()){
    		FileInputStream input = new FileInputStream(file) ;
    		OutputStream output = response.getOutputStream() ; 
    		byte[] data = new byte[1024] ;
    		int len = 0 ;
    		while((len = input.read(data))>0) {
    			output.write(data , 0  , len);
    		}
    		input.close();
    	}
    }
    
    @RequestMapping("/url")
    @Menu(type = "resouce" , subtype = "image" , access = true)
    public void url(HttpServletResponse response, @Valid String url) throws IOException {
    	byte[] data = new byte[1024] ;
    	int length = 0 ;
    	OutputStream out = response.getOutputStream();
    	if(!StringUtils.isBlank(url)){
    		InputStream input = new URL(url).openStream() ;
    		while((length = input.read(data) )> 0){
    			out.write(data, 0, length);
    		}
    		input.close();
    	}
    }
    
    @RequestMapping("/image/upload")
    @Menu(type = "resouce" , subtype = "imageupload" , access = false)
    public ModelAndView upload(ModelMap map,HttpServletRequest request , @RequestParam(value = "imgFile", required = false) MultipartFile imgFile) throws IOException {
    	ModelAndView view = request(super.createRequestPageTempletResponse("/public/upload")) ; 
    	UploadStatus upload = null ;
    	String fileName = null ;
    	if(imgFile!=null && imgFile.getOriginalFilename().lastIndexOf(".") > 0){
    		File uploadDir = new File(path , "upload");
    		if(!uploadDir.exists()){
    			uploadDir.mkdirs() ;
    		}
    		fileName = "upload/"+UKTools.md5(imgFile.getBytes())+imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf(".")).toLowerCase() ;
    		FileCopyUtils.copy(imgFile.getBytes(), new File(path , fileName));
    		
    		String fileURL =  request.getScheme()+"://"+request.getServerName()+"/res/image.html?id="+fileName ;
    		if(request.getServerPort() == 80){
    			fileURL = request.getScheme()+"://"+request.getServerName()+"/res/image.html?id="+fileName;
			}else{
				fileURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/res/image.html?id="+fileName;
			}
    		upload = new UploadStatus("0" , fileURL); //图片直接发送给 客户，不用返回
    	}else{
    		upload = new UploadStatus("请选择图片文件");
    	}
    	map.addAttribute("upload", upload) ;
        return view ; 
    }
    
    @RequestMapping("/file")
    @Menu(type = "resouce" , subtype = "file" , access = false)
    public void file(HttpServletResponse response,HttpServletRequest request, @Valid String id) throws IOException {
    	if(!StringUtils.isBlank(id)){
    		AttachmentFile attachmentFile = attachementRes.findByIdAndOrgi(id, super.getOrgi(request)) ;
    		if(attachmentFile!=null){
    			response.setContentType(attachmentFile.getFiletype());  
    	        response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(attachmentFile.getTitle(), "UTF-8"));  
    	        if(!StringUtils.isBlank(attachmentFile.getModel()) && attachmentFile.getModel().equals("webim")){
    	        	response.getOutputStream().write(FileUtils.readFileToByteArray(new File(path , "app/webim/"+attachmentFile.getFileid())));
    	        }else if(!StringUtils.isBlank(attachmentFile.getModel()) && attachmentFile.getModel().equals("workorders")){
    	        	response.getOutputStream().write(FileUtils.readFileToByteArray(new File(path , "app/workorders/"+attachmentFile.getFileid())));
    	        }else {
    	        	response.getOutputStream().write(FileUtils.readFileToByteArray(new File(path , "app/ekm/"+attachmentFile.getFileid())));
				}
    		}
    	}
    }
    @RequestMapping("/template")
    @Menu(type = "resouce" , subtype = "template" , access = false)
    public void template(HttpServletResponse response,HttpServletRequest request, @Valid String filename) throws IOException {
    	if(!StringUtils.isBlank(filename)){
    		InputStream is = MediaController.class.getClassLoader().getResourceAsStream(TEMPLATE_DATA_PATH+filename);
    		if(is!=null) {
    			response.setContentType("text/plain");  
    	        response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(filename, "UTF-8"));  
    	        int length ;
    	        byte[] data = new byte[1024] ;
    	        while((length = is.read(data)) > 0) {
    	        	response.getOutputStream().write(data , 0 , length);
    	        }
    	        is.close();
    		}
    	}
    	return ;
    }
    
}