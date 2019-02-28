package com.ukefu.webim.web.handler.admin.callcenter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.BlackListRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.BlackEntity;

@Controller
@RequestMapping("/admin/callcenter")
public class CallCenterBlackController extends Handler{
	
	@Autowired
	private BlackListRepository blackRes ;
	
	@RequestMapping(value = "/black")
    @Menu(type = "callcenter" , subtype = "callcenterblack" , access = false , admin = true)
    public ModelAndView black(ModelMap map , HttpServletRequest request , @Valid String hostid) {
		map.addAttribute("blackList" , blackRes.findByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, "createtime")));
		return request(super.createRequestPageTempletResponse("/admin/callcenter/black/index"));
    }
	
	@RequestMapping(value = "/black/add")
    @Menu(type = "callcenter" , subtype = "black" , access = false , admin = true)
    public ModelAndView blackadd(ModelMap map , HttpServletRequest request , @Valid String hostid) {
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/black/add"));
    }
	
	@RequestMapping(value = "/black/save")
    @Menu(type = "callcenter" , subtype = "black" , access = false , admin = true)
    public ModelAndView blacksave(ModelMap map , HttpServletRequest request , @Valid String phones) {
		if(!StringUtils.isBlank(phones)){
			String[] ps = phones.split("[ ,，\t\n]") ;
			for(String ph : ps){
				if(ph.length() >= 3){
					int count = blackRes.countByPhoneAndOrgi(ph.trim(), super.getOrgi(request)) ;
					if(count == 0){
						BlackEntity be = new BlackEntity();
						be.setPhone(ph.trim());
						be.setChannel(UKDataContext.ChannelTypeEnum.PHONE.toString());
						be.setOrgi(super.getOrgi(request));
						be.setCreater(super.getUser(request).getId());
						blackRes.save(be) ;
					}
				}
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/black.html"));
    }
	
	@RequestMapping(value = "/black/edit")
    @Menu(type = "callcenter" , subtype = "black" , access = false , admin = true)
    public ModelAndView blackedit(ModelMap map , HttpServletRequest request , @Valid String id) {
		map.addAttribute("black" , blackRes.findByIdAndOrgi(id, super.getOrgi(request)));
    	return request(super.createRequestPageTempletResponse("/admin/callcenter/black/edit"));
    }
	
	@RequestMapping(value = "/black/update")
    @Menu(type = "callcenter" , subtype = "black" , access = false , admin = true)
    public ModelAndView pbxhostupdate(ModelMap map , HttpServletRequest request , @Valid BlackEntity black) {
		if(!StringUtils.isBlank(black.getId())){
			BlackEntity oldBlack = blackRes.findByIdAndOrgi(black.getId(), super.getOrgi(request)) ;
			if(oldBlack!=null){
				oldBlack.setPhone(black.getPhone());
				oldBlack.setChannel(UKDataContext.ChannelTypeEnum.PHONE.toString());
				oldBlack.setOrgi(super.getOrgi(request));
				blackRes.save(oldBlack);
			}
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/black.html"));
    }
	
	@RequestMapping(value = "/black/delete")
    @Menu(type = "callcenter" , subtype = "black" , access = false , admin = true)
    public ModelAndView blackdelete(ModelMap map , HttpServletRequest request , @Valid String id) {
		if(!StringUtils.isBlank(id)){
			blackRes.delete(id);
		}
		return request(super.createRequestPageTempletResponse("redirect:/admin/callcenter/black.html"));
    }
}
