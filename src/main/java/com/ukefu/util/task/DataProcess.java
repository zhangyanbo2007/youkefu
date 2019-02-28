package com.ukefu.util.task;


public abstract class DataProcess {
	protected DSDataEvent event ;
	public DataProcess(final DSDataEvent event){
		this.event = event ;
	}
	public abstract void process() ;
}
