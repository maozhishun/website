package com.gz.init;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;

public class SysInit implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n--------------初始化系统常量---开始-------------------");

		System.out.println("\n--------------初始化系统常量---结束-------------------");
	}

	/**
	 * 有界队列线程池
	 * 初始线程数0(这个主要是用于io)
	 * 最大线程数10
	 * 过期时间 2秒
	 * 队列容量5
	 */
	public static ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(0, 10, 2L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

}
