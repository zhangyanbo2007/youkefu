package com.ukefu.webim.web.handler.apps.entim;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.service.repository.ChatMessageRepository;
import com.ukefu.webim.service.repository.IMGroupRepository;
import com.ukefu.webim.service.repository.IMGroupUserRepository;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.service.repository.RecentUserRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.IMGroup;
import com.ukefu.webim.web.model.IMGroupUser;
import com.ukefu.webim.web.model.RecentUser;
import com.ukefu.webim.web.model.User;

@Controller
@RequestMapping("/ent/im")
public class EntIMController extends Handler{
	
	@Autowired
	private OrganRepository organRes ;
	
	@Autowired
	private UserRepository userRes ;
	
	@Autowired
	private IMGroupRepository imGroupRes ;
	
	@Autowired
	private IMGroupUserRepository imGroupUserRes ;
	
	@Autowired
	private ChatMessageRepository chatMessageRes; 
	
	@Autowired
	private RecentUserRepository recentUserRes ;
	
    @RequestMapping("/index")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public ModelAndView index(HttpServletRequest request , HttpServletResponse response) {
    	ModelAndView view = request(super.createEntIMTempletResponse("/apps/entim/index")) ; 
		view.addObject("organList", organRes.findByOrgi(super.getOrgi(request))) ;
		view.addObject("userList", userRes.findByOrgiAndDatastatus(super.getOrgi(request) , false)) ;
		
		view.addObject("groupList", imGroupRes.findByCreaterAndOrgi(super.getUser(request).getId(), super.getOrgi(request)));
		
		view.addObject("joinGroupList", imGroupUserRes.findByUserAndOrgi(super.getUser(request), super.getOrgi(request))) ;
		
		view.addObject("recentUserList", recentUserRes.findByCreaterAndOrgi(super.getUser(request).getId(), super.getOrgi(request))) ;
		
        return view;
    }
    
    @RequestMapping("/skin")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public ModelAndView skin(HttpServletRequest request , HttpServletResponse response) {
    	ModelAndView view = request(super.createEntIMTempletResponse("/apps/entim/skin")) ; 
		
        return view;
    }
    
    @RequestMapping("/point")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public ModelAndView point(HttpServletRequest request , HttpServletResponse response) {
    	ModelAndView view = request(super.createEntIMTempletResponse("/apps/entim/point")) ; 
    	view.addObject("recentUserList", recentUserRes.findByCreaterAndOrgi(super.getUser(request).getId(), super.getOrgi(request))) ;
        return view;
    }
    
    @RequestMapping("/chat")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public ModelAndView chat(HttpServletRequest request , HttpServletResponse response , @Valid String userid) {
    	ModelAndView view = request(super.createEntIMTempletResponse("/apps/entim/chat")) ; 
    	view.addObject("entimuser", userRes.findByIdAndOrgi(userid, super.getOrgi(request))) ;
    	view.addObject("contextid", UKTools.genNewID(super.getUser(request).getId(), userid)) ;
    	view.addObject("online", NettyClients.getInstance().getEntIMClientsNum(userid) > 0) ;
    	view.addObject("chatMessageList", chatMessageRes.findByContextidAndUseridAndOrgi(userid , super.getUser(request).getId(), super.getOrgi(request), new PageRequest(0, 20, Sort.Direction.DESC, "createtime"))) ;
    	
    	RecentUser recentUser = recentUserRes.findByCreaterAndUserAndOrgi(super.getUser(request).getId() , new User(userid), super.getOrgi(request)) ;
    	/**
    	 * 我的最近联系人
    	 */
    	if(recentUser == null){
    		recentUser = new RecentUser();
    		recentUser.setOrgi(super.getOrgi(request));
    		recentUser.setCreater(super.getUser(request).getId());
    		recentUser.setUser(new User(userid));
    	}else{
    		recentUser.setNewmsg(0);
    	}
    	recentUserRes.save(recentUser) ;
    	/**
    	 * 对方的最近联系人
    	 */
    	recentUser = recentUserRes.findByCreaterAndUserAndOrgi(userid , super.getUser(request), super.getOrgi(request)) ;
    	if(recentUser == null){
    		recentUser = new RecentUser();
    		recentUser.setOrgi(super.getOrgi(request));
    		recentUser.setCreater(userid);
    		recentUser.setUser(super.getUser(request));
    		recentUserRes.save(recentUser) ;
    	}
        return view;
    }
    
    @RequestMapping("/group")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public ModelAndView group(HttpServletRequest request , HttpServletResponse response , @Valid String id) {
    	ModelAndView view = request(super.createEntIMTempletResponse("/apps/entim/group/index")) ; 
    	IMGroup imGroup = imGroupRes.findById(id);
    	view.addObject("imGroup", imGroup) ;
    	view.addObject("imGroupUserList", imGroupUserRes.findByImgroupAndOrgi(imGroup, super.getOrgi(request))) ;
    	view.addObject("contextid", id) ;
    	view.addObject("chatMessageList", chatMessageRes.findByContextidAndOrgi(id, super.getOrgi(request), new PageRequest(0, 20, Sort.Direction.DESC, "createtime"))) ;
        return view;
    }
    
    @RequestMapping("/group/user")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public ModelAndView user(HttpServletRequest request , HttpServletResponse response , @Valid String id) {
    	ModelAndView view = request(super.createEntIMTempletResponse("/apps/entim/group/user")) ; 
    	IMGroup imGroup = imGroupRes.findById(id);
    	view.addObject("imGroup", imGroup) ;
    	view.addObject("organList", organRes.findByOrgi(super.getOrgi(request))) ;
		view.addObject("userList", userRes.findByOrgiAndDatastatus(super.getOrgi(request) , false)) ;
		
		view.addObject("imGroupUserList", imGroupUserRes.findByImgroupAndOrgi(imGroup, super.getOrgi(request))) ;
		
        return view;
    }
    
    @RequestMapping("/group/seluser")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public void seluser(HttpServletRequest request , HttpServletResponse response , @Valid String id , @Valid String user) {
    	IMGroup imGroup = new IMGroup();
    	imGroup.setId(id);
    	User curUser = new User();
    	curUser.setId(user);
    	IMGroupUser imGroupUser = imGroupUserRes.findByImgroupAndUserAndOrgi(imGroup, curUser, super.getOrgi(request)) ;
    	if(imGroupUser == null){
    		imGroupUser = new IMGroupUser() ;
    		imGroupUser.setImgroup(imGroup);
    		imGroupUser.setUser(curUser);
    		imGroupUser.setOrgi(super.getUser(request).getOrgi());
    		imGroupUser.setCreater(super.getUser(request).getId());
    		imGroupUserRes.save(imGroupUser) ;
    	}
    }
    
    @RequestMapping("/group/rmuser")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public void rmluser(HttpServletRequest request , HttpServletResponse response , @Valid String id , @Valid String user) {
    	IMGroup imGroup = new IMGroup();
    	imGroup.setId(id);
    	User curUser = new User();
    	curUser.setId(user);
    	IMGroupUser imGroupUser = imGroupUserRes.findByImgroupAndUserAndOrgi(imGroup, curUser, super.getOrgi(request)) ;
    	if(imGroupUser != null){
    		imGroupUserRes.delete(imGroupUser);
    	}
    }
    
    @RequestMapping("/group/tipmsg")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public ModelAndView tipmsg(HttpServletRequest request , HttpServletResponse response , @Valid String id , @Valid String tipmsg) {
    	ModelAndView view = request(super.createRequestPageTempletResponse("/apps/entim/group/tipmsg")) ; 
    	IMGroup imGroup = imGroupRes.findById(id) ;
    	if(imGroup != null){
    		imGroup.setTipmessage(tipmsg);
    		imGroupRes.save(imGroup) ;
    	}
    	view.addObject("imGroup", imGroup) ;
    	return view ;
    }
    
    
    @RequestMapping("/group/save")
    @Menu(type = "im" , subtype = "entim" , access = false)
    public ModelAndView groupsave(HttpServletRequest request , HttpServletResponse response , @Valid IMGroup group) {
    	ModelAndView view = request(super.createRequestPageTempletResponse("/apps/entim/group/grouplist")) ; 
    	if(!StringUtils.isBlank(group.getName()) && imGroupRes.countByNameAndOrgi(group.getName() , super.getOrgi(request)) == 0){
	    	group.setOrgi(super.getUser(request).getOrgi());
	    	group.setCreater(super.getUser(request).getId());
	    	imGroupRes.save(group) ;
    	
	    	IMGroupUser imGroupUser = new IMGroupUser() ;
	    	imGroupUser.setOrgi(super.getUser(request).getOrgi());
	    	imGroupUser.setUser(super.getUser(request));
	    	imGroupUser.setImgroup(group);
	    	imGroupUser.setAdmin(true);
	    	imGroupUser.setCreater(super.getUser(request).getId());
	    	imGroupUserRes.save(imGroupUser) ;
    	}
    	view.addObject("groupList", imGroupRes.findByCreaterAndOrgi(super.getUser(request).getId(), super.getOrgi(request))) ;
		
		view.addObject("joinGroupList", imGroupUserRes.findByUserAndOrgi(super.getUser(request), super.getOrgi(request))) ;
    	
        return view;
    }
}