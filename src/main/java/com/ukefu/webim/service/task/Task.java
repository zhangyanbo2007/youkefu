package com.ukefu.webim.service.task;

import java.util.Date;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.TaskTools;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.ReporterRepository;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.Reporter;

public class Task implements Runnable{
	private JobDetail jobDetail ;
	private JobDetailRepository jobDetailRes ;
	
	public Task(JobDetail jobDetail , JobDetailRepository jobDetailRes){
		this.jobDetail = jobDetail ;
		this.jobDetailRes = jobDetailRes ;
	}
	@Override
	public void run() {
		try{
			/**
			 * 首先从  等待执行的队列中找到优先级最高的任务，然后将任务放入到  执行队列
			 */
			if(jobDetail!=null){
				/**
				 * 开始启动执行线程
				 */
				jobDetail.setTaskfiretime(new Date());
				jobDetail.setTaskstatus(UKDataContext.TaskStatusType.RUNNING.getType()) ;
				jobDetail.setMemo(null);
				jobDetailRes.save(jobDetail) ;
				/**
				 * 任务开始执行
				 */
				if(true){
					if(jobDetail.getReport()==null){
						jobDetail.setReport(new Reporter());
						UKDataContext.getContext().getBean(ReporterRepository.class).save(jobDetail.getReport()) ;
					}
					if (jobDetail.isFetcher()) {
						UKDataContext.localJobDetailMap.put(jobDetail.getId(), jobDetail) ;
						do {
							new Fetcher(jobDetail).run();
						}while(jobDetail.isFetcher() && jobDetail.getReport()!=null && jobDetail.getReport().isRound()) ;//启用多轮，每轮处理本轮数据
					}
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			/**
			 * 任务开始执行，执行完毕后 ，任务状态回执为  NORMAL
			 */
			if(jobDetail.getCronexp()!=null && jobDetail.getCronexp().length() > 0 && jobDetail.isPlantask() && !"operation".equals(jobDetail.getCrawltaskid())){
				jobDetail.setNextfiretime(TaskTools.updateTaskNextFireTime(jobDetail));
			}
			jobDetail.setStartindex(0) ;	//将分页位置设置为从头开始，对数据采集有效，对RivuES增量采集无效
			jobDetail.setFetcher(true) ;
			jobDetail.setPause(false) ;
			jobDetail.setTaskstatus(UKDataContext.TaskStatusType.NORMAL.getType()) ;
			jobDetail.setCrawltaskid(null);
			
			
//			jobDetail.setLastdate(new Date()) ;
			jobDetailRes.save(jobDetail) ;
			UKDataContext.localJobDetailMap.remove(jobDetail.getId()) ;
			/**
			 * 存储历史信息
			 */
			CacheHelper.getJobCacheBean().delete(this.jobDetail.getId(), this.jobDetail.getOrgi()) ;
		}
	}
}
