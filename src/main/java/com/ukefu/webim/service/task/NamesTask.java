package com.ukefu.webim.service.task;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.callout.CallOutUtils;
import com.ukefu.util.client.NettyClients;
import com.ukefu.util.es.SearchTools;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.SkillExtentionRepository;
import com.ukefu.webim.web.model.CallOutConfig;
import com.ukefu.webim.web.model.SkillExtention;

public class NamesTask implements Runnable{
	
	private CallCenterAgent agent ;
	public NamesTask(CallCenterAgent agent) {
		this.agent = agent ;
	}
	@Override
	public void run() {
		if(agent!=null) {
			/**
			 * 更新状态
			 */
			agent.setWorkstatus(UKDataContext.WorkStatusEnum.PREVIEW.toString());
			/**
			 * 根据策略拉取名单 ，
			 * 1、拨打时间
			 * 2、允许或禁止拨打
			 * 3、优先拨打新名单/老名单/预约名单/未拨打成功的名单
			 */
			Page<UKDataBean> names = SearchTools.agentapsearch(this.agent.getOrgi(), agent.getUserid(), 0, 1) ;
			if(names.getTotalElements() == 0) {
				names = SearchTools.agentsearch(this.agent.getOrgi(), true, agent.getUserid() , 0, 1) ;
			}
			/**
			 * 找到名单，生成拨打任务，工作界面上，坐席只能看到自己的名单
			 */
			agent.setForecastvalue(null);
			agent.setForecast(false);
			if(names!=null && names.getContent().size() > 0) {
				UKDataBean name = names.getContent().get(0) ;
				CallOutUtils.processNames(name, agent, agent.getOrgi(), (int)(names.getTotalElements() - 1)) ;
			}else {
				/**
				 * 检查启用预测式外呼功能
				 */
				boolean tip = true ; 
				CallOutConfig config = CallOutUtils.initCallOutConfig(agent.getOrgi()) ;
				if(config!=null && config.isForecast()) {
					SkillExtentionRepository callCenterSkillRes = UKDataContext.getContext().getBean(SkillExtentionRepository.class) ;
					List<SkillExtention> callCenterSkillList = callCenterSkillRes.findByExtentionAndOrgi(agent.getExtno() , agent.getOrgi()) ;
					if(callCenterSkillList.size() > 0) {
						agent.setWorkstatus(UKDataContext.WorkStatusEnum.CALLOUT.toString());
						agent.setForecastvalue(agent.getSkill());
						agent.setForecast(true);
						NettyClients.getInstance().sendCallCenterMessage(agent.getExtno(), "docallout", agent);
						tip = false ;
					}
				}
				if(tip){
					agent.setWorkstatus(UKDataContext.WorkStatusEnum.IDLE.toString());
					NettyClients.getInstance().sendCallCenterMessage(agent.getExtno(), "error", "nonames");
					NettyClients.getInstance().sendCallCenterMessage(agent.getExtno(), "docallout", agent);
				}
			}
			
			CacheHelper.getCallCenterAgentCacheBean().put(agent.getUserid(), agent, agent.getOrgi());
		}
	}

}
