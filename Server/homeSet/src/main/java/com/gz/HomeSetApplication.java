package com.gz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.gz.mapper")
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.gz.model","com.gz.bean", "com.gz.rest","com.gz.service","com.gz.mapper","com.gz.interceptor","com.gz.aspect"})
//开启定时任务
@EnableScheduling
//开启异步调用方法
//@EnableAsync
public class HomeSetApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(HomeSetApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(HomeSetApplication.class, args);
		
	}
}
