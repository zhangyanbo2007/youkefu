package com.ukefu.webim.config.web;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.ukefu.util.event.UserDataEvent;
import com.ukefu.webim.util.disruptor.UserDataEventFactory;
import com.ukefu.webim.util.disruptor.UserEventHandler;
import com.ukefu.webim.util.disruptor.multiupdate.MultiUpdateEventFactory;
import com.ukefu.webim.util.disruptor.multiupdate.MultiUpdateEventHandler;

@Component
public class DisruptorConfigure {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Bean(name="disruptor")   
    public Disruptor<UserDataEvent> disruptor() {   
		Executor executor = Executors.newCachedThreadPool();
    	 UserDataEventFactory factory = new UserDataEventFactory();
    	 Disruptor<UserDataEvent> disruptor = new Disruptor<UserDataEvent>(factory, 1024, executor, ProducerType.MULTI , new BlockingWaitStrategy());
    	 disruptor.setDefaultExceptionHandler(new UKeFuExceptionHandler());
    	 disruptor.handleEventsWith(new UserEventHandler());
    	 disruptor.start();
         return disruptor;   
    }  
    
    @SuppressWarnings({ "unchecked", "deprecation" })
	@Bean(name="multiupdate")   
    public Disruptor<UserDataEvent> multiupdate() {   
    	Executor executor = Executors.newCachedThreadPool();
    	 MultiUpdateEventFactory factory = new MultiUpdateEventFactory();
    	 Disruptor<UserDataEvent> disruptor = new Disruptor<UserDataEvent>(factory, 1024, executor, ProducerType.MULTI , new BlockingWaitStrategy());
    	 disruptor.handleEventsWith(new MultiUpdateEventHandler());
    	 disruptor.setDefaultExceptionHandler(new UKeFuExceptionHandler());
    	 disruptor.start();
         return disruptor;   
    }  
}
