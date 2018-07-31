package com.gz.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 拦截器配置类:添加拦截器，增加拦截地址
 * @author admin
 *
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//该接口最好赋予管理员权限才可以访问，防止泄露用户信息
		registry.addInterceptor(new GZInterceptor()).addPathPatterns("/homeset/proposer/listProposerInfo")
													.addPathPatterns("");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
