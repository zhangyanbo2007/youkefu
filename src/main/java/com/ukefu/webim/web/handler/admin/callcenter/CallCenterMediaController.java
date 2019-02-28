package com.ukefu.webim.web.handler.admin.callcenter;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.MediaRepository;
import com.ukefu.webim.service.repository.PbxHostRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Media;

@Controller
@RequestMapping("/admin/callcenter")
public class CallCenterMediaController extends Handler{
	
	@Autowired
	private PbxHostRepository pbxHostRes ;
	
	@Autowired
	private MediaRepository mediaRes ;
	
	@Value("${web.upload-path}")
    private String path;
	
	@RequestMapping(value = "/media")
    @Menu(type = "callcenter" , subtype = "callcentermedia" , access = false , admin = true)
    public ModelAndView media(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		if(!StringUtils.isBlank(hostid)){
			map.addAttribute("pbxHost" , pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request)));
			map.addAttribute("mediaList" , mediaRes.findByHostidAndOrgi(hostid, super.getOrgi(request)));
		}
		return request(super.createRequestPageTempletResponse("/admin/callcenter/media/index"));
    }
	
	@RequestMapping(value = "/media/add")
    @Menu(type = "callcenter" , subtype = "media" , access = false , admin = true)
    public ModelAndView mediaadd(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/media/add"));
    }
	
	@RequestMapping(value = "/media/save")
    @Menu(type = "callcenter" , subtype = "media" , access = false , admin = true)
    public ModelAndView mediasave(ModelMap map , HttpServletRequest request , @RequestParam(value = "mediafile", required = false) MultipartFile mediafile) throws IOException {
		Media media = new Media();
		media.setName(request.getParameter("name"));
		media.setHostid(request.getParameter("hostid"));
		if(!StringUtils.isBlank(media.getName())){
			int count = mediaRes.countByNameAndOrgi(media.getName(), super.getOrgi(request)) ;
			if(count == 0){
				String fileName = "media/"+media.getId()+ mediafile.getOriginalFilename().substring(mediafile.getOriginalFilename().lastIndexOf(".")) ;
				
				media.setOrgi(super.getOrgi(request));
				media.setCreater(super.getUser(request).getId());
				media.setFilelength((int) mediafile.getSize());
				media.setContent(mediafile.getContentType());
				media.setFilename(fileName);
				
				if(mediafile!=null && mediafile.getOriginalFilename().lastIndexOf(".") > 0){
		    		File logoDir = new File(path , "media");
		    		if(!logoDir.exists()){
		    			logoDir.mkdirs() ;
		    		}
		    		FileCopyUtils.copy(mediafile.getBytes(), new File(path , fileName));
		    	}
				
				mediaRes.save(media) ;
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/media.html?hostid="+media.getHostid()));
    }
	
	@RequestMapping(value = "/media/edit")
    @Menu(type = "callcenter" , subtype = "media" , access = false , admin = true)
    public ModelAndView mediaedit(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		map.addAttribute("media" , mediaRes.findByIdAndOrgi(id, super.getOrgi(request)));
		map.put("pbxHost", pbxHostRes.findByIdAndOrgi(hostid, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/media/edit"));
    }
	
	@RequestMapping(value = "/media/update")
    @Menu(type = "callcenter" , subtype = "media" , access = false , admin = true)
    public ModelAndView pbxhostupdate(ModelMap map , HttpServletRequest request , @RequestParam(value = "mediafile", required = false) MultipartFile mediafile) throws IOException {
		Media media = new Media();
		media.setName(request.getParameter("name"));
		media.setHostid(request.getParameter("hostid"));
		media.setId(request.getParameter("id"));
		if(!StringUtils.isBlank(media.getId())){
			Media oldMedia = mediaRes.findByIdAndOrgi(media.getId(), super.getOrgi(request)) ;
			if(oldMedia!=null){
				if(mediafile!=null && mediafile.getSize() > 0){
					File wavFile = new File(path , oldMedia.getFilename());
		    		if(!wavFile.exists()){
		    			wavFile.deleteOnExit();
		    		}
					
					String fileName = "media/"+media.getId()+mediafile.getOriginalFilename().substring(mediafile.getOriginalFilename().lastIndexOf(".")) ;
					oldMedia.setFilename(fileName);
					
					if(mediafile!=null && mediafile.getOriginalFilename().lastIndexOf(".") > 0){
			    		File mediaDir = new File(path , "media");
			    		if(!mediaDir.exists()){
			    			mediaDir.mkdirs() ;
			    		}
			    		FileCopyUtils.copy(mediafile.getBytes(), new File(path , fileName));
			    	}
				}
				oldMedia.setName(media.getName());
				mediaRes.save(oldMedia);
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/media.html?hostid="+media.getHostid()));
    }
	
	@RequestMapping(value = "/media/delete")
    @Menu(type = "callcenter" , subtype = "media" , access = false , admin = true)
    public ModelAndView mediadelete(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String hostid) {
		if(!StringUtils.isBlank(id)){
			mediaRes.delete(id);
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/media.html?hostid="+hostid));
    }
	@RequestMapping(value = "/play")
    @Menu(type = "callcenter" , subtype = "play" , access = false)
    public ModelAndView play(ModelMap map , HttpServletRequest request ,@Valid final String id ,@Valid final String hostid) {
		map.addAttribute("media", mediaRes.findByIdAndOrgi(id, super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/media/play"));
    }
}
