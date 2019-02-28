package com.ukefu.webim.web.handler.apps.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.DataDicRepository;
import com.ukefu.webim.service.repository.OrganRepository;
import com.ukefu.webim.service.repository.PublishedReportRepository;
import com.ukefu.webim.service.repository.ReportCubeService;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.PublishedReport;
import com.ukefu.webim.web.model.ReportFilter;

@Controller
@RequestMapping("/apps/view")
public class ReportViewController extends Handler{
	
	@Value("${web.upload-path}")
    private String path;
	
	@Value("${uk.im.server.port}")  
    private Integer port; 
	
	@Autowired
	private DataDicRepository dataDicRes;
	
	@Autowired
	private PublishedReportRepository publishedReportRes;
	
	@Autowired
	private ReportCubeService reportCubeService;
	
	@Autowired
	private OrganRepository organRepository; 
    @RequestMapping("/index")
    @Menu(type = "setting" , subtype = "report" , admin= true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , @Valid String dicid , @Valid String id) throws Exception {
    	Page<PublishedReport> publishedReportList = null ;
    	if(!StringUtils.isBlank(dicid) && !"0".equals(dicid)){
        	map.put("dataDic", dataDicRes.findByIdAndOrgi(dicid, super.getOrgi(request))) ;
    	}
    	map.put("reportList", publishedReportList = publishedReportRes.findByOrgi(super.getOrgi(request) , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	if(publishedReportList!=null && publishedReportList.getContent().size() > 0) {
    		PublishedReport publishedReport = publishedReportList.getContent().get(0);
    		if(!StringUtils.isBlank(id)) {
    			for(PublishedReport report : publishedReportList) {
    				if(report.getId().equals(id)) {
    					publishedReport = report ; break ;
    				}
    			}
    		}
    		map.put("report", publishedReport) ;
    		
    		if(publishedReport!=null) {
				map.addAttribute("publishedReport", publishedReport);
				map.addAttribute("report", publishedReport.getReport());
				map.addAttribute("reportModels", publishedReport.getReport().getReportModels());
				List<ReportFilter> listFilters = publishedReport.getReport().getReportFilters();
				if(!listFilters.isEmpty()) {
					Map<String,ReportFilter> filterMap = new HashMap<String,ReportFilter>();
					for(ReportFilter rf:listFilters) {
						filterMap.put(rf.getId(), rf);
					}
					for(ReportFilter rf:listFilters) {
						if(!StringUtils.isBlank(rf.getCascadeid())) {
							rf.setChildFilter(filterMap.get(rf.getCascadeid()));
						}
					}
				}
				map.addAttribute("reportFilters", reportCubeService.fillReportFilterData(listFilters, request));
			}
    		
    	} 
		map.addAttribute("organList", organRepository.findByOrgi(super.getOrgi(request)));
    	map.put("dataDicList", dataDicRes.findByOrgi(super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/apps/business/view/index"));
    }
}