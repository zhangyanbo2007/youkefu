package com.ukefu.webim.service.task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.ReporterRepository;
import com.ukefu.webim.service.resource.OutputTextFormat;
import com.ukefu.webim.service.resource.Resource;
import com.ukefu.webim.web.model.JobDetail;

public class Fetcher implements Runnable {
	
	private JobDetail job = null;

	private AtomicInteger activeThreads = new AtomicInteger(0);
	private AtomicInteger pages = new AtomicInteger(0); // total pages fetched
	private AtomicInteger errors = new AtomicInteger(0); // total pages fetched
	private Resource resource = null ;
	private int processpages = 0 ;
	private boolean round ;
	/**
	 * 构建任务信息
	 * @param job
	 */
	public Fetcher(JobDetail job) throws Exception{
		this.job = job;
		try {
			if(job!=null && job.getTasktype()!=null){
				resource = Resource.getResource(job);
			}
			/**
			 * 初始化资源
			 */
			
			if(resource!=null){
				resource.begin();
			}
			this.job.setLastindex(job.getStartindex()) ;
			this.pages = new AtomicInteger((int)job.getReport().getPages()); // total pages fetched
			processpages = this.pages.intValue() ;
			job.getReport().setDataid(this.job.getId());
		}catch (Exception e1) {
			String msg = "TaskID:"+job.getId() + " TaskName:"+job.getName()+" TaskType:"+job.getTasktype()+" Date:"+new Date()+" Exception:"+e1.getMessage() ;
			job.setExceptionMsg(ExceptionUtils.getMessage(e1));
			if(StringUtils.isBlank(job.getMemo())) {
				job.setMemo(msg);
			}
			/**
			 * 设置错误代码
			 */
			e1.printStackTrace();
			throw new Exception(msg , e1);
		}
	}

	public void run() {
		job.getReport().setThreads(1);
		job.getReport().setStarttime(new java.util.Date());
		try {
			synchronized (activeThreads) {
				activeThreads.incrementAndGet(); // count threads
			}
			reportStatus();
			OutputTextFormat obj;
			while (job.isFetcher() && resource != null && (obj = resource.next()) != null) {
				try {
					while (job.isPause() && job.isFetcher()) {
						Thread.sleep(1000);
						if(resource.isAvailable()) {
							break ;
						}
					}
					/**
					 * 校验任务的 结束时间
					 */
					if(!StringUtils.isBlank(this.job.getEndtime())){
						Date current = UKTools.dateFormate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) +" "+ this.job.getEndtime() );
						if(current.before(new Date())) {
							this.job.setMemo("任务已超过结束时间");
							break ;
						}
					}
					if (obj!= null && this.job.isFetcher()){
						output(obj);
					}
				} catch (Throwable t) { // unexpected exception
					// unblock
					job.setExceptionMsg(t.getMessage());
					t.printStackTrace();
					errors.incrementAndGet();
					break;
				}
			}
			/**
	         * 
	         */
			if(StringUtils.isBlank(this.job.getMemo())) {
				this.job.setMemo("true");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			job.setExceptionMsg(e.getMessage());
		} finally {
			this.job.getReport().setErrors(this.job.getReport().getErrors()+errors.intValue()) ;
			if(resource!=null){
				/**
				 * end中包含了 Close 方法
				 */
				try {
					reportStatus();
					this.resource.end(this.pages.intValue()==processpages) ;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.job.getReport().setOrgi(this.job.getOrgi());
	    	this.job.getReport().setDataid(this.job.getId());
	    	this.job.getReport().setTitle(this.job.getName() + "_" + UKTools.dateFormate.format(new Date()));
	    	
	    	this.job.getReport().setUserid(this.job.getCreater());
			this.job.getReport().setUsername(this.job.getUsername());
			this.job.getReport().setEndtime(new Date());
			this.job.getReport().setTotal(this.pages.intValue());
			this.job.getReport().setAmount(String.valueOf((this.job.getReport().getEndtime().getTime() - this.job.getReport().getStart())/ 1000f));
			
			UKDataContext.getContext().getBean(ReporterRepository.class).save(this.job.getReport()) ;
			synchronized (activeThreads) {
				activeThreads.decrementAndGet(); // count threads
			}
		}
	}
	
	private void output(OutputTextFormat object)throws Exception {
		try {
			this.reportStatus();
			OutputTextFormat outputTextFormat = resource.getText(object);
			if(outputTextFormat==null){
				return ;
			}else{
				pages.incrementAndGet();
				resource.process(outputTextFormat, job) ;
				job.setStartindex(job.getStartindex()+1) ;
			}
			reportStatus();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void reportStatus() throws IOException {
		if(this.job!=null && this.job.getReport()!=null){
			this.job.getReport().setPages(this.pages.intValue()) ;
			this.job.getReport().setThreads(activeThreads.intValue()) ;
			this.job.getReport().setStatus(new StringBuffer().append("已处理：").append(this.job.getReport().getPages()).append(", 错误：").append(this.job.getReport().getErrors()).append("，处理速度：").append(job.getReport().getSpeed()).append("条/秒，线程数：").append(this.job.getReport().getThreads()).append(this.job.getReport().getDetailmsg()!=null ? "，详细信息："+this.job.getReport().getDetailmsg() : "").toString());
			CacheHelper.getJobCacheBean().put(job.getId(), job , job.getOrgi());
		}
	}

	public boolean isRound() {
		return round;
	}

	public void setRound(boolean round) {
		this.round = round;
	}
}