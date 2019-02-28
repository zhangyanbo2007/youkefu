package com.ukefu.webim.web.handler.resource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.es.ContactsRepository;
import com.ukefu.webim.web.handler.Handler;

@Controller
public class ContactsResourceController extends Handler{
	
	@Autowired
	private ContactsRepository contactsRes ;
	
	@RequestMapping("/res/contacts")
    @Menu(type = "res" , subtype = "contacts")
    public ModelAndView add(ModelMap map , HttpServletRequest request , @Valid String q) {
		if(q==null){
			q = "" ;
		}
    	map.addAttribute("contactsList", contactsRes.findByCreaterAndSharesAndOrgi(super.getUser(request).getId(),super.getUser(request).getId(),super.getOrgi(request), false , q , new PageRequest(0, 10))) ;
        return request(super.createRequestPageTempletResponse("/public/contacts"));
    }
}