package com.ukefu.util.task;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.dsl.Disruptor;

public class DSQueueProcessHandler {
	private  Disruptor<DSDataEvent> disruptor ;
	private Executor executor = Executors.newCachedThreadPool();
	private static DSQueueProcessHandler dsQueneProcess = new DSQueueProcessHandler() ;
	private DSDataEventProducer producer ;
	static{
		try {
			dsQueneProcess.process();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @throws InterruptedException
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	private void process() throws InterruptedException{
        disruptor = new Disruptor<DSDataEvent>(new DSDataEventFactory(), 1024, executor);

        disruptor.handleEventsWith(new DSDataEventHandler());

        disruptor.start();
        
        producer = new DSDataEventProducer(disruptor.getRingBuffer());
	}
	
	public DSData doTask(DSData dsData){
		producer.onData(dsData) ;
		return dsData ;
	}
	
	public static DSQueueProcessHandler getInstance(){
		return dsQueneProcess ;
	}
}
