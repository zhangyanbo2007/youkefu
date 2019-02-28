package com.ukefu.webim.service.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.web.model.JobDetail;

/**
 * 定时清空 uk_jobdetail表 已完成的回收任务 tasktype = recovery
 * @author kashing
 *
 */
@Configuration
@EnableScheduling
public class CleanJobTask {
	
	@Autowired
	private JobDetailRepository jobDetailRepository ;
	//每月一号1点执行
	@Scheduled(cron= "0 0 1 1 * ?")
    public void task() {
		if(UKDataContext.getContext()!=null && UKDataContext.needRunTask()){
			Page<JobDetail> pageList = null;
			do {
				pageList = jobDetailRepository.findAll(new Specification<JobDetail>(){
					@Override
					public Predicate toPredicate(Root<JobDetail> root, CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						List<Predicate> list = new ArrayList<Predicate>();  

						list.add(cb.equal(root.get("tasktype").as(String.class), UKDataContext.TaskType.RECOVERY.toString()));
						list.add(cb.equal(root.get("taskstatus").as(String.class), UKDataContext.TaskStatusType.NORMAL.getType()));
						//3天前
						list.add(cb.lessThanOrEqualTo(root.get("createtime").as(Date.class), UKTools.getLastDay(3)));
						Predicate[] p = new Predicate[list.size()];  
						return cb.and(list.toArray(p));   
					}}, new PageRequest(0, 1000, Sort.Direction.ASC, new String[] { "createtime" }));
				if(pageList.getContent().size()  > 0 ) {
					jobDetailRepository.delete(pageList.getContent());
				}
			}while(pageList.getContent().size() > 0 );
		}
	}
}
