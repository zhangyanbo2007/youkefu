package com.ukefu.webim.web.handler.admin.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hazelcast.com.eclipsesource.json.JsonObject;
import com.ukefu.util.Menu;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.web.handler.Handler;

@Controller
@RequestMapping("/admin/monitor")
public class HazelcastMonitorController extends Handler{
	
    @RequestMapping("/hazelcast")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , HttpServletResponse response) throws SQLException {
    	Map<String , Object> jsonObjectMap = new HashMap< String , Object>();
    	
    	jsonObjectMap.put("agentUser" , convert(CacheHelper.getAgentUserCacheBean().getStatics())) ;
    	jsonObjectMap.put("agentStatus" , convert(CacheHelper.getAgentStatusCacheBean().getStatics())) ;
    	
    	jsonObjectMap.put("apiUser" , convert(CacheHelper.getApiUserCacheBean().getStatics())) ;
    	jsonObjectMap.put("imrCache" , convert(CacheHelper.getIMRCacheBean().getStatics())) ;
    	jsonObjectMap.put("onlineUser" , convert(CacheHelper.getOnlineUserCacheBean().getStatics())) ;
    	jsonObjectMap.put("systemCache" , convert(CacheHelper.getSystemCacheBean().getStatics())) ;
    	
    	map.addAttribute("systemStatics", new Gson().toJson(jsonObjectMap)) ;
    	
    	
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json; charset=utf-8");
        return request(super.createRequestPageTempletResponse("/admin/system/monitor/hazelcast"));
    }
    /**
     * 转换统计数据
     * @param json
     * @return
     */
    private Map<String , Object> convert(JsonObject json){
    	Map<String , Object> values = new HashMap<String , Object>();
    	for(String key : json.names()){
    		values.put(key, json.get(key)) ;
    	}
    	return values;
    }
}