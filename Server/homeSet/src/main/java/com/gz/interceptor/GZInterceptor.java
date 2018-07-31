package com.gz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 广尊通用拦截器
 * 应该在启动类添加拦截器地址的扫描，如果在此处添加，拦截器不会生效
 * @author admin
 *
 */
public class GZInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String adminId = null;
		adminId = null== request.getParameter("gzAdminId")? request.getHeader("gzAdminId"): request.getParameter("gzAdminId");
		System.out.println("==========="+adminId+"===========");
		if (adminId.equals("admin")) {
			return true;
		}else {
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().write("{\"code\":500, \"num\":1000, \"msg\":\"非广尊管理员，无权访问!!\"}");
			return false;
		}
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	

}
