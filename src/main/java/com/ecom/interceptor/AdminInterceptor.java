package com.ecom.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ecom.pojo.User;

public class AdminInterceptor extends HandlerInterceptorAdapter{

	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		if(session.getAttribute("user") != null){
			if (u.getRole().equalsIgnoreCase("admin")){
			System.out.println("in interceptor and the user is admin");
			return true;
			}

				
		}
		
		System.out.println("The user is not admin");
		System.out.println(request.getContextPath());
		response.sendRedirect(request.getContextPath()+"/authorize/error");
		
		return false;
		
		
		
		
	}
	
}
