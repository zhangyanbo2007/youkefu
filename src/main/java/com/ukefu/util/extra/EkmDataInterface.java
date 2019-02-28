package com.ukefu.util.extra;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;

import com.ukefu.webim.web.model.EkmComments;
import com.ukefu.webim.web.model.EkmKnowledgeMaster;
import com.ukefu.webim.web.model.User;

public interface EkmDataInterface {
	
	public Page<EkmKnowledgeMaster> findByOrgi(ModelMap map,HttpServletRequest request, String orgi, User user, PageRequest page);
	
	public void getKnowledgeDetail(ModelMap map,HttpServletRequest request,String knowledgeid,String orgi,User user ,int p, int ps) ;
	
	public void doKnowledgeComments(ModelMap map,HttpServletRequest request,String knowledgeid,EkmComments ekmComments ,User user,String orgi) ;
	
	public void searchKnowledgeListByKnowledgeTypeAndKnowbase(ModelMap map,HttpServletRequest request,User user,String orgi, String knowledgetype, String knowbaseid,int p,int ps) ;
	
}
