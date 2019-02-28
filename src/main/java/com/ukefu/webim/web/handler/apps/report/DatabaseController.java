package com.ukefu.webim.web.handler.apps.report;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.DatabaseRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Database;

@Controller
@RequestMapping("/apps/database")
public class DatabaseController extends Handler{

	@Autowired
	private DatabaseRepository databaseRes;
	
    @RequestMapping("/index")
    @Menu(type = "setting" , subtype = "database" , admin= true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , @Valid String dicid) {
    	map.put("databaseList", databaseRes.findByOrgi(super.getOrgi(request) , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	return request(super.createAppsTempletResponse("/apps/business/database/index"));
    }
    
    @RequestMapping("/add")
    @Menu(type = "setting" , subtype = "database" , admin= true)
    public ModelAndView quickreplyadd(ModelMap map , HttpServletRequest request , @Valid String dicid) {
        return request(super.createRequestPageTempletResponse("/apps/business/database/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "setting" , subtype = "database" , admin= true)
    public ModelAndView quickreplysave(ModelMap map , HttpServletRequest request , @Valid Database db) throws NoSuchAlgorithmException {
    	if(!StringUtils.isBlank(db.getName())){
    		db.setOrgi(super.getOrgi(request));
    		db.setCreatetime(new Date());
    		if(!StringUtils.isBlank(db.getPassword())) {
    			db.setPassword(UKTools.encryption(db.getPassword()));
    		}
    		db.setCreateuser(super.getUser(request).getId());
    		
			databaseRes.save(db) ;
    	}
        return request(super.createRequestPageTempletResponse("redirect:/apps/database/index.html"));
    }
    
    @RequestMapping("/delete")
    @Menu(type = "setting" , subtype = "database" , admin= true)
    public ModelAndView quickreplydelete(ModelMap map , HttpServletRequest request , @Valid Database db) {
    	if(db!=null && !StringUtils.isBlank(db.getId())){
    		databaseRes.delete(db);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/database/index.html"));
    }
    @RequestMapping("/edit")
    @Menu(type = "setting" , subtype = "database" , admin= true)
    public ModelAndView quickreplyedit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.put("database", databaseRes.findByIdAndOrgi(id, super.getOrgi(request))) ;
    	
        return request(super.createRequestPageTempletResponse("/apps/business/database/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "setting" , subtype = "database" , admin= true)
    public ModelAndView quickreplyupdate(ModelMap map , HttpServletRequest request , @Valid Database db) throws NoSuchAlgorithmException {
    	if(!StringUtils.isBlank(db.getId())){
    		Database temp = databaseRes.findByIdAndOrgi(db.getId() , super.getOrgi(request)) ;
    		
    		db.setOrgi(super.getOrgi(request));
    		db.setCreatetime(new Date());
    		db.setCreateuser(super.getUser(request).getId());
    		
    		if(!StringUtils.isBlank(db.getPassword())) {
    			db.setPassword(UKTools.encryption(db.getPassword()));
    		}else if(temp!=null && temp.getPassword()!=null){
    			db.setPassword(temp.getPassword());
    		}
			databaseRes.save(db) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/database/index.html"));
    }
    
    @RequestMapping("/test")
    @Menu(type = "setting" , subtype = "database" , admin= true)
    public ModelAndView test(ModelMap map , HttpServletRequest request , @Valid Database db) throws NoSuchAlgorithmException, SQLException {
    	if(!StringUtils.isBlank(db.getId())){
    		Connection conn = null ;
    		try {
    			Database database = databaseRes.findByIdAndOrgi(db.getId() , super.getOrgi(request)) ;
				if(database!=null && !StringUtils.isBlank(database.getDriverclazz())) {
					Class.forName(database.getDriverclazz()) ;
					conn = DriverManager.getConnection(database.getDatabaseurl() , database.getAccount() , !StringUtils.isBlank(database.getPassword())?UKTools.decryption(database.getPassword()):"") ;
					map.addAttribute("result", true) ;
				}
    		} catch (Exception e) {
    			e.printStackTrace();
    			map.addAttribute("result", false) ;
    		}finally{
    			if(conn!=null) {
    				conn.close();
    			}
    		}
    	}
    	return request(super.createRequestPageTempletResponse("/apps/business/database/test"));
    }
}