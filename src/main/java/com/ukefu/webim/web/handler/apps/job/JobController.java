package com.ukefu.webim.web.handler.apps.job;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.JobTask;


@Controller
@RequestMapping("/apps/job")
public class JobController extends Handler {
	
	@Autowired
	private JobDetailRepository jobRes ;
	/**
	 * 跳转到修改执行时间的页面
	 * @param request
	 * @param orgi
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({"/setting"})
	@Menu(type="job", subtype="setting")
	public ModelAndView setting(ModelMap map , HttpServletRequest request , @Valid String id) throws Exception {
		JobDetail detail = jobRes.findByIdAndOrgi(id, super.getOrgi(request)) ;
		if(detail.getTaskinfo() != null && detail.getTaskinfo() != ""){
			ObjectMapper objectMapper = new ObjectMapper();  
			JobTask taskInfo = objectMapper.readValue(detail.getTaskinfo(), JobTask.class) ;
			map.put("taskinfo",taskInfo);
		}
		map.put("job", detail);
		return request(super.createRequestPageTempletResponse("/apps/business/job/setting"));
	}

	@RequestMapping({"/save"})
	@Menu(type="job", subtype="save")
	public ModelAndView save(HttpServletRequest request,
			@Valid JobTask taskinfo,@Valid Boolean plantask, @Valid String id) throws ParseException {
		JobDetail detail = jobRes.findByIdAndOrgi(id, super.getOrgi(request)) ;
		
		if(detail != null ){
			try {
				detail.setPlantask(plantask) ;
				ObjectMapper mapper = new ObjectMapper();  
				detail.setTaskinfo(mapper.writeValueAsString(taskinfo));
				DecimalFormat format = new DecimalFormat("00");
				detail.setStarttime(format.format(taskinfo.getRunBeginHour())+":"+format.format(taskinfo.getRunBeginMinute())+":"+format.format(taskinfo.getRunBeginSecond()));
				if(taskinfo.getIsRepeat() && taskinfo.getRepeatJustTime() > 0) {
					detail.setEndtime(format.format((taskinfo.getRunBeginHour() + taskinfo.getRepeatJustTime()))+":"+format.format(taskinfo.getRunBeginMinute())+":"+format.format(taskinfo.getRunBeginSecond()));
				}
				if(plantask!=null && plantask.booleanValue()) {
					detail.setCronexp(UKTools.convertCrond(taskinfo));
					/**
					 * 设定触发时间
					 */
					detail.setNextfiretime(new Date());
					detail.setNextfiretime(UKTools.updateTaskNextFireTime(detail));
					detail.setPlantask(true);
				}else {
					detail.setPlantask(false);
					detail.setCronexp(null);
					detail.setNextfiretime(null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			jobRes.save(detail) ;
		}
		return request(super.createRequestPageTempletResponse("/public/success"));
	}
}
