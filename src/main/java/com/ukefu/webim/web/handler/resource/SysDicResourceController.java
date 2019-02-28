package com.ukefu.webim.web.handler.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.UKeFuDic;

@Controller
@RequestMapping("/res")
public class SysDicResourceController extends Handler{
	
    @RequestMapping("/dic")
    @Menu(type = "resouce" , subtype = "dic" , access = true)
    public ModelAndView index(ModelMap map , HttpServletResponse response, @Valid String id, @Valid String name, @Valid String attr , @Valid String style) throws IOException {
    	List<SysDic> itemList = new ArrayList<SysDic>() ;
    	SysDic sysDic = UKeFuDic.getInstance().getDicItem(id) ;
    	if(sysDic!=null){
	    	SysDic dic = UKeFuDic.getInstance().getDicItem(sysDic.getDicid()) ;
			List<SysDic> sysDicList = (List<SysDic>) UKeFuDic.getInstance().getSysDic(dic.getCode()) ;
	    	for(SysDic item : sysDicList){
	    		if(item.getParentid().equals(id)){
	    			itemList.add(item) ;
	    		}
	    	}
    	}
    	map.addAttribute("sysDicList", itemList) ;
    	map.addAttribute("name", name) ;
    	map.addAttribute("attr", attr) ;
    	map.addAttribute("style", style) ;
    	return request(super.createRequestPageTempletResponse("/public/select"));
    }
    
}