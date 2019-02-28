package com.ukefu.util;

import java.util.Date;

import com.ukefu.webim.web.model.JobDetail;

public class TaskTools {
	public static Date updateTaskNextFireTime(JobDetail jobDetail){
		Date nextFireDate = new Date();
		Date date = new Date();
		if(jobDetail!=null && jobDetail.getCronexp()!=null && jobDetail.getCronexp().length()>0){
			try {
				nextFireDate = (CronTools.getFinalFireTime(jobDetail.getCronexp(), jobDetail.getNextfiretime()!=null ? jobDetail.getNextfiretime() : date)) ;
			} catch (Exception e) {
				nextFireDate = new Date(System.currentTimeMillis() + 1000*60*60*24) ; 	//一旦任务的 Cron表达式错误，将下次执行时间自动设置为一天后，避免出现任务永远无法终止的情况
				e.printStackTrace();
			}
		}
		return nextFireDate ;
	}
	
}
