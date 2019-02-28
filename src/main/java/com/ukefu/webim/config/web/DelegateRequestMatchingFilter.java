package com.ukefu.webim.config.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.User;

public class DelegateRequestMatchingFilter implements Filter {
    private RequestMatcher[] ignoredRequests;

    public DelegateRequestMatchingFilter(RequestMatcher... matcher) {
        this.ignoredRequests = matcher;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
         HttpServletRequest request = (HttpServletRequest) req;
         boolean matchAnyRoles = false ;
         for(RequestMatcher anyRequest : ignoredRequests ){
        	 if(anyRequest.matches(request)){
        		 matchAnyRoles = true ;
        	 }
         }
         User user = (User) request.getSession().getAttribute(UKDataContext.USER_SESSION_NAME) ;
         if(matchAnyRoles){
        	 if(user !=null && "0".equals(user.getUsertype())){
        		 chain.doFilter(req,resp);
        	 }else{
	        	 //重定向到 无权限执行操作的页面
	        	 HttpServletResponse response = (HttpServletResponse) resp ;
	        	 response.sendRedirect("/?msg=security");
        	 }
         }else{
        	 try{
        		 chain.doFilter(req,resp);
        	 }catch(ClientAbortException ex){
        		 //Tomcat异常，不做处理
        	 }
         }
    }

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}