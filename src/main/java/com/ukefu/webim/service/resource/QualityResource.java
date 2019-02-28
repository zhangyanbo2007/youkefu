package com.ukefu.webim.service.resource;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.es.SearchTools;
import com.ukefu.webim.service.es.WorkOrdersRepository;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.service.repository.QualityActivityTaskRepository;
import com.ukefu.webim.service.repository.QualityAgentRepository;
import com.ukefu.webim.service.repository.QualityFilterRepository;
import com.ukefu.webim.service.repository.QualityFormFilterItemRepository;
import com.ukefu.webim.service.repository.QualityFormFilterRepository;
import com.ukefu.webim.service.repository.QualityMissionHisRepository;
import com.ukefu.webim.service.repository.StatusEventRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.QualityActivityTask;
import com.ukefu.webim.web.model.QualityAgent;
import com.ukefu.webim.web.model.QualityFilter;
import com.ukefu.webim.web.model.QualityFormFilter;
import com.ukefu.webim.web.model.QualityFormFilterItem;
import com.ukefu.webim.web.model.QualityMissionHis;
import com.ukefu.webim.web.model.StatusEvent;
import com.ukefu.webim.web.model.User;
import com.ukefu.webim.web.model.WorkOrders;

public class QualityResource extends Resource{
	
	private JobDetail jobDetail;
	private UserRepository userRes;
	
	private QualityFormFilter qcFormFilter = null;
	private QualityFormFilterRepository qcFormFilterRes;
	
	private QualityFormFilterItemRepository qcFormFilterItemRes;
	
	private QualityFilter qcFilter;
	private QualityFilterRepository qcFilterRes;
	
	private List<QualityAgent> qcAgentList;
	private QualityAgent qcAgent;
	
	private QualityActivityTask qcActTask;
	private QualityActivityTaskRepository qcActTaskRes;
	
	private List<?> dataList ;
	
	//语音通话
	private StatusEventRepository statusEventRes;
	//工单
	private WorkOrdersRepository workOrdersRes ;
	//会话质检
	private AgentServiceRepository agentServiceRes;
	
	private QualityMissionHisRepository qcMissionHisRes;
	
	private AtomicInteger assignorganInt = new AtomicInteger() /***分配到坐席***/, assignInt = new AtomicInteger() /***分配到部门***/ , assignAiInt = new AtomicInteger() /***分配到AI***/ ,atomInt = new AtomicInteger() ;
	
	public QualityResource(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
		this.qcFilterRes = UKDataContext.getContext().getBean(QualityFilterRepository.class);
		this.qcFormFilterRes = UKDataContext.getContext().getBean(QualityFormFilterRepository.class);
		this.qcFormFilterItemRes = UKDataContext.getContext().getBean(QualityFormFilterItemRepository.class);
		this.qcActTaskRes = UKDataContext.getContext().getBean(QualityActivityTaskRepository.class);
		this.statusEventRes = UKDataContext.getContext().getBean(StatusEventRepository.class);
		this.workOrdersRes = UKDataContext.getContext().getBean(WorkOrdersRepository.class);
		this.agentServiceRes = UKDataContext.getContext().getBean(AgentServiceRepository.class);
		this.qcMissionHisRes = UKDataContext.getContext().getBean(QualityMissionHisRepository.class);
		this.userRes = UKDataContext.getContext().getBean(UserRepository.class);
	}

	@Override
	public void begin() throws Exception {
		
		if(!StringUtils.isBlank(this.jobDetail.getFilterid())) {//筛选表单id是否为空，作为数据的筛选依据
			qcFormFilter = this.qcFormFilterRes.findByIdAndOrgi(this.jobDetail.getFilterid(), this.jobDetail.getOrgi());
			final List<QualityFormFilterItem> qcFormFilterItemList = this.qcFormFilterItemRes.findByOrgiAndQcformfilterid(this.jobDetail.getOrgi(), this.jobDetail.getFilterid());
			if(qcFormFilter != null) {
				
				if(isRecovery()) {
					
				}else {
					final String orgi = this.jobDetail.getOrgi();
					User user = userRes.findByIdAndOrgi(this.jobDetail.getCreater(), orgi);
					if(UKDataContext.QcFormFilterTypeEnum.CALLEVENT.toString().equals(qcFormFilter.getFiltertype())) {
						//语音质检
						dataList = SearchTools.searchQualityStatusEvent(orgi, qcFormFilterItemList,user);
					}else if(UKDataContext.QcFormFilterTypeEnum.WORKORDERS.toString().equals(qcFormFilter.getFiltertype())) {
						//工单质检
						dataList = SearchTools.searchQualityWorkOrders(orgi, qcFormFilterItemList,user);
					}else if(UKDataContext.QcFormFilterTypeEnum.AGENTSERVICE.toString().equals(qcFormFilter.getFiltertype())) {
						//会话质检
						dataList = SearchTools.searchQualityAgentService(orgi, qcFormFilterItemList,user);
					}
				}
			}
			
			this.qcAgentList = UKDataContext.getContext().getBean(QualityAgentRepository.class).findByActidAndOrgi(this.jobDetail.getId(), this.jobDetail.getOrgi());
			
			if(this.qcAgentList != null && this.qcAgentList.size() > 0) {
				this.qcAgent = this.qcAgentList.remove(0);
			}
			
			this.jobDetail.setExecnum(this.jobDetail.getExecnum() + 1);
			
			if(this.isRecovery() && !StringUtils.isBlank(this.jobDetail.getExectype()) 
					&& (this.jobDetail.getExectype().equals("filterid") || this.jobDetail.getExectype().equals("filterskill") || this.jobDetail.getExectype().equals("taskid") || this.jobDetail.getExectype().equals("taskskill"))) {
				if(this.jobDetail.getExectype().equals("filterid") || this.jobDetail.getExectype().equals("filterskill")) {
					this.qcFilter = this.qcFilterRes.findByIdAndOrgi(this.jobDetail.getExectarget(), this.jobDetail.getOrgi());
				}else if(this.jobDetail.getExectype().equals("taskid") || this.jobDetail.getExectype().equals("taskskill")) {
					this.qcActTask = this.qcActTaskRes.findByIdAndOrgi(this.jobDetail.getExectarget(), this.jobDetail.getOrgi());
				}
			}else {
				
				qcActTask = new QualityActivityTask();
				qcActTask.setName(this.jobDetail.getName() + "_" + UKTools.dateFormate.format(new Date()));
				qcActTask.setOrgi(this.jobDetail.getOrgi());
				qcActTask.setOrgan(this.jobDetail.getOrgan());
				qcActTask.setCreater(this.jobDetail.getCreater());
				qcActTask.setCreatetime(new Date());
				if(this.isRecovery()) {
					qcActTask.setExectype(UKDataContext.ActivityExecType.RECOVERY.toString());
				}else {
					qcActTask.setExectype(UKDataContext.ActivityExecType.DEFAULT.toString());
				}
				qcActTask.setFilterid(this.qcFormFilter.getId());
				qcActTask.setActid(this.jobDetail.getId());
				qcActTask.setExecnum(this.jobDetail.getExecnum());
				
				
				this.qcActTaskRes.save(qcActTask);
				
				qcFilter = new QualityFilter();
				
				qcFormFilter.setExecnum(qcFormFilter.getExecnum() + 1);
				UKTools.copyProperties(qcActTask, qcFilter);
				qcFilter.setName(this.qcFormFilter.getName() + "_" + UKTools.dateFormate.format(new Date()));
				qcFilter.setExecnum(qcFormFilter.getExecnum());
				this.qcFilterRes.save(qcFilter);
			}
		}
		
	}

	@Override
	public void end(boolean clear) throws Exception {
		
		/**
		 * FormFilter的执行信息更新，执行次数
		 */
		if(qcFormFilterRes!=null && this.qcFormFilter != null) {
			this.qcFormFilter.setFilternum(this.qcFormFilter.getFilternum()+1);
			qcFormFilterRes.save(this.qcFormFilter) ;
		}
		
		if(this.qcActTask!=null) {
			if(this.isRecovery()) {
				if(!StringUtils.isBlank(this.jobDetail.getExecto())) {
					this.qcActTask.setReorgannum(this.atomInt.intValue());
				}else {
					this.qcActTask.setRenum(this.atomInt.intValue());
				}
			}else {
				this.qcActTask.setAssigned(this.assignInt.intValue());
				this.qcActTask.setAssignedorgan(this.assignorganInt.intValue());
				this.qcActTask.setAssignedai(this.assignAiInt.intValue());
				this.qcActTask.setNamenum(this.assignInt.intValue() + this.assignorganInt.intValue() + this.assignAiInt.intValue());
				this.qcActTask.setNotassigned(this.dataList.size() - this.assignInt.intValue() - this.assignorganInt.intValue() - this.assignAiInt.intValue());
			}
			this.qcActTaskRes.save(this.qcActTask) ;
		}
		if(this.qcFilter!=null) {
			if(this.isRecovery()) {
				if(!StringUtils.isBlank(this.jobDetail.getExecto())) {
					this.qcFilter.setReorgannum(this.atomInt.intValue());
				}else {
					this.qcFilter.setRenum(this.atomInt.intValue());
				}
			}else {
				this.qcFilter.setAssigned(this.assignInt.intValue());
				this.qcFilter.setAssignedorgan(this.assignorganInt.intValue());
				this.qcFilter.setAssignedai(this.assignAiInt.intValue());
				this.qcFilter.setNotassigned(this.dataList.size() - this.assignInt.intValue() - this.assignorganInt.intValue() - this.assignAiInt.intValue());
			}
			this.qcFilterRes.save(this.qcFilter) ;
		}
		
		/**
		 * 更新任务状态，记录生成的任务信息
		 */
		this.jobDetail.setExecmd(null);
		this.jobDetail.setExectype(null);
		this.jobDetail.setExectarget(null);
		this.jobDetail.setExecto(null);
	}

	@Override
	public JobDetail getJob() {
		// TODO Auto-generated method stub
		return this.jobDetail;
	}

	@Override
	public void process(OutputTextFormat meta, JobDetail job) throws Exception {
		
		if(meta.getObject() != null) {
			this.qcAgent.getDisnames().incrementAndGet() ;
			if(this.isRecovery()) {
				
			}else {
				QualityMissionHis qcMissionHis = new QualityMissionHis();
				if(meta.getObject() instanceof StatusEvent) {
					//通话质检
					StatusEvent statusEvent = (StatusEvent)meta.getObject();
					statusEvent.setQualitydistime(new Date());
					statusEvent.setQualitytype(UKDataContext.QcFormFilterTypeEnum.CALLEVENT.toString());
					statusEvent.setAssuser(this.jobDetail.getCreater());
					statusEvent.setTemplateid(this.jobDetail.getTemplateid());
					statusEvent.setQualityactid(this.jobDetail.getId());
					statusEvent.setQualityfilterid(this.jobDetail.getFilterid());
					statusEvent.setQualitystatus(UKDataContext.QualityStatus.NO.toString());
					
					if("agent".equals(this.qcAgent.getDistype())) {
						statusEvent.setQualitydisorgan(this.qcAgent.getOrgan());
						statusEvent.setQualitydisuser(this.qcAgent.getDistarget());
						this.assignInt.incrementAndGet() ;
						qcMissionHis.setQualitydisorgan(this.qcAgent.getOrgan());
						qcMissionHis.setQualitydisuser(this.qcAgent.getDistarget());
						statusEvent.setQualitydistype(UKDataContext.QualityDisStatusType.DISAGENT.toString());
					}else if("skill".equals(this.qcAgent.getDistype())) {
						statusEvent.setQualitydisorgan(this.qcAgent.getDistarget());
						this.assignorganInt.incrementAndGet() ;
						qcMissionHis.setQualitydisorgan(this.qcAgent.getDistarget());
						statusEvent.setQualitydistype(UKDataContext.QualityDisStatusType.DISORGAN.toString());
					}
					this.statusEventRes.save(statusEvent);
					qcMissionHis.setDataid(statusEvent.getId());
					qcMissionHis.setQualitytype(UKDataContext.QcFormFilterTypeEnum.CALLEVENT.toString());
				}else if(meta.getObject() instanceof WorkOrders) {
					//工单质检
					WorkOrders workOrders = (WorkOrders)meta.getObject();
					workOrders.setQualitydistime(new Date());
					workOrders.setQualitytype(UKDataContext.QcFormFilterTypeEnum.WORKORDERS.toString());
					workOrders.setAssuser(this.jobDetail.getCreater());
					workOrders.setTemplateid(this.jobDetail.getTemplateid());
					workOrders.setQualitystatus(UKDataContext.QualityStatus.NO.toString());
					workOrders.setQualityactid(this.jobDetail.getId());
					workOrders.setQualityfilterid(this.jobDetail.getFilterid());
					
					if("agent".equals(this.qcAgent.getDistype())) {
						workOrders.setQualitydisorgan(this.qcAgent.getOrgan());
						workOrders.setQualitydisuser(this.qcAgent.getDistarget());
						this.assignInt.incrementAndGet() ;
						qcMissionHis.setQualitydisorgan(this.qcAgent.getOrgan());
						qcMissionHis.setQualitydisuser(this.qcAgent.getDistarget());
						workOrders.setQualitydistype(UKDataContext.QualityDisStatusType.DISAGENT.toString());
					}else if("skill".equals(this.qcAgent.getDistype())) {
						workOrders.setQualitydisorgan(this.qcAgent.getDistarget());
						this.assignorganInt.incrementAndGet() ;
						qcMissionHis.setQualitydisorgan(this.qcAgent.getDistarget());
						workOrders.setQualitydistype(UKDataContext.QualityDisStatusType.DISORGAN.toString());
					}
					this.workOrdersRes.save(workOrders);
					qcMissionHis.setDataid(workOrders.getId());
					qcMissionHis.setQualitytype(UKDataContext.QcFormFilterTypeEnum.WORKORDERS.toString());
				}else if(meta.getObject() instanceof AgentService) {
					//会话质检
					AgentService agentService = (AgentService)meta.getObject();
					agentService.setQualitydistime(new Date());
					agentService.setQualitytype(UKDataContext.QcFormFilterTypeEnum.AGENTSERVICE.toString());
					agentService.setAssuser(this.jobDetail.getCreater());
					agentService.setTemplateid(this.jobDetail.getTemplateid());
					agentService.setQualitystatus(UKDataContext.QualityStatus.NO.toString());
					agentService.setQualityactid(this.jobDetail.getId());
					agentService.setQualityfilterid(this.jobDetail.getFilterid());
					
					if("agent".equals(this.qcAgent.getDistype())) {
						agentService.setQualitydisorgan(this.qcAgent.getOrgan());
						agentService.setQualitydisuser(this.qcAgent.getDistarget());
						this.assignInt.incrementAndGet() ;
						qcMissionHis.setQualitydisorgan(this.qcAgent.getOrgan());
						qcMissionHis.setQualitydisuser(this.qcAgent.getDistarget());
						agentService.setQualitydistype(UKDataContext.QualityDisStatusType.DISAGENT.toString());
					}else if("skill".equals(this.qcAgent.getDistype())) {
						agentService.setQualitydisorgan(this.qcAgent.getDistarget());
						this.assignorganInt.incrementAndGet() ;
						qcMissionHis.setQualitydisorgan(this.qcAgent.getDistarget());
						agentService.setQualitydistype(UKDataContext.QualityDisStatusType.DISORGAN.toString());
					}
					this.agentServiceRes.save(agentService);
					qcMissionHis.setDataid(agentService.getId());
					qcMissionHis.setQualitytype(UKDataContext.QcFormFilterTypeEnum.AGENTSERVICE.toString());
				}
				if(!StringUtils.isBlank(qcMissionHis.getDataid())) {
					qcMissionHis.setFormfilterid(this.jobDetail.getFilterid());
					qcMissionHis.setQualitytime(new Date());
					qcMissionHis.setAssuser(this.jobDetail.getCreater());
					qcMissionHis.setTemplateid(this.jobDetail.getTemplateid());
					qcMissionHis.setQualitystatus(UKDataContext.QualityStatus.NO.toString());
					qcMissionHis.setOrgi(this.jobDetail.getOrgi());
					qcMissionHis.setActid(this.jobDetail.getId());
					qcMissionHis.setOrgan(this.jobDetail.getOrgan());
					qcMissionHis.setFilterid(this.qcFilter.getId());
					qcMissionHis.setTaskid(this.qcActTask.getId());
					this.qcMissionHisRes.save(qcMissionHis);
				}
			}
		}
	}

	@Override
	public OutputTextFormat next() throws Exception {
		OutputTextFormat outputTextFormat = null;
		if(this.dataList!=null && this.qcAgent!=null) {
			synchronized (this.dataList) {
				if(atomInt.intValue() < this.dataList.size()) {
					if(this.isRecovery()) {
						Object object = this.dataList.get(atomInt.intValue()) ;
						outputTextFormat = new OutputTextFormat(this.jobDetail);
						if(this.qcFormFilter!=null) {
							outputTextFormat.setTitle(this.qcFormFilter.getName());
						}
						outputTextFormat.setObject(object);
						atomInt.incrementAndGet() ;
					}else if(this.dataList!=null) {
						if(this.qcAgent.getDisnames().intValue() >= this.qcAgent.getDisnum() ) {
							if(this.qcAgentList.size() > 0) {
								this.qcAgent = this.qcAgentList.remove(0) ;
							}else {
								this.qcAgent = null ;
							}
						}
						if(this.qcAgent != null) {
							Object object = this.dataList.get(atomInt.intValue()) ;
							outputTextFormat = new OutputTextFormat(this.jobDetail);
							if(this.qcFormFilter!=null) {
								outputTextFormat.setTitle(this.qcFormFilter.getName());
							}
							outputTextFormat.setObject(object);
		
							atomInt.incrementAndGet() ;
							
							/**
							 * 修改为平均分配的方式 ， 每个坐席或者部门评价分配
							 */
							this.qcAgentList.add(this.qcAgent) ;
							if(this.qcAgentList.size() > 0) {
								this.qcAgent = this.qcAgentList.remove(0) ;
							}
						}
					}
				}
			}
		}
		return outputTextFormat;
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public OutputTextFormat getText(OutputTextFormat object) throws Exception {
		// TODO Auto-generated method stub
		return object;
	}

	@Override
	public void rmResource() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTask() throws Exception {
		/**
		 * 更新任务状态，记录生成的任务信息
		 */
		this.jobDetail.setExecmd(null);
		this.jobDetail.setExectype(null);
		this.jobDetail.setExectarget(null);
		this.jobDetail.setExecto(null);
	}

	private boolean isRecovery() {
		return !StringUtils.isBlank(this.jobDetail.getExecmd()) && this.jobDetail.getExecmd().equals("recovery") ;
	}
}
