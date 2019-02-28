package com.ukefu.webim.config.web;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池 ， 作业调度平台
 * @author iceworld
 *
 */
@Configuration
public class ExecutorConfig {
	private static int CORE_POOL_SIZE = 5;
	private static int MAX_POOL_SIZE = 200;

	/**
	 * 作业平台使用的线程池
	 * @return
	 */
	@Bean(name = "uKeFuTaskExecutor")
	public ThreadPoolTaskExecutor taskExecutor() {

		ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
		// 线程池维护线程的最少数量
		poolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
		// 线程池维护线程的最大数量
		poolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
		// 线程池所使用的缓冲队列
		poolTaskExecutor.setQueueCapacity(200);
		// 线程池维护线程所允许的空闲时间
		poolTaskExecutor.setKeepAliveSeconds(30);
		poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return poolTaskExecutor;
	}
}
