package com.ukefu.webim.web.handler.apps.report;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.util.task.DSData;
import com.ukefu.util.task.DSDataEvent;
import com.ukefu.util.task.ExcelImportProecess;
import com.ukefu.util.task.export.ExcelExporterProcess;
import com.ukefu.webim.service.repository.ColumnPropertiesRepository;
import com.ukefu.webim.service.repository.DataDicRepository;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.service.repository.PublishedReportRepository;
import com.ukefu.webim.service.repository.ReportCubeService;
import com.ukefu.webim.service.repository.ReportFilterRepository;
import com.ukefu.webim.service.repository.ReportModelRepository;
import com.ukefu.webim.service.repository.ReportRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.ColumnProperties;
import com.ukefu.webim.web.model.DataDic;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.PublishedReport;
import com.ukefu.webim.web.model.Report;
import com.ukefu.webim.web.model.ReportFilter;
import com.ukefu.webim.web.model.ReportModel;

@Controller
@RequestMapping("/apps/report")
public class ReportController extends Handler{
	
	@Value("${web.upload-path}")
    private String path;
	
	@Value("${uk.im.server.port}")  
    private Integer port; 
	
	@Autowired
	private DataDicRepository dataDicRes;
	
	@Autowired
	private ReportRepository reportRes;
	
	@Autowired
	private PublishedReportRepository publishedReportRes;
	
	@Autowired
	private ReportFilterRepository reportFilterRepository;
	
	@Autowired
	private ColumnPropertiesRepository columnPropertiesRepository;
	
	@Autowired
	private ReportModelRepository reportModelRes;
	
	@Autowired
	private MetadataRepository metadataRes ;
	
	@Autowired
	private ReportCubeService reportCubeService;
	
    @RequestMapping("/index")
    @Menu(type = "setting" , subtype = "report" , admin= true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , @Valid String dicid) {
    	if(!StringUtils.isBlank(dicid) && !"0".equals(dicid)){
        	map.put("dataDic", dataDicRes.findByIdAndOrgi(dicid, super.getOrgi(request))) ;
    		map.put("reportList", reportRes.findByOrgiAndDicid(super.getOrgi(request) , dicid , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	}else{
    		map.put("reportList", reportRes.findByOrgi(super.getOrgi(request) , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	}
    	map.put("dataDicList", dataDicRes.findByOrgi(super.getOrgi(request))) ;
    	return request(super.createAppsTempletResponse("/apps/business/report/index"));
    }
    
    @RequestMapping("/add")
    @Menu(type = "setting" , subtype = "reportadd" , admin= true)
    public ModelAndView quickreplyadd(ModelMap map , HttpServletRequest request , @Valid String dicid) {
    	if(!StringUtils.isBlank(dicid)){
    		map.addAttribute("dataDic", dataDicRes.findByIdAndOrgi(dicid, super.getOrgi(request))) ;
    	}
    	map.addAttribute("dataDicList", dataDicRes.findByOrgi(super.getOrgi(request))) ;
        return request(super.createRequestPageTempletResponse("/apps/business/report/add"));
    }
    
    @RequestMapping("/save")
    @Menu(type = "setting" , subtype = "report" , admin= true)
    public ModelAndView quickreplysave(ModelMap map , HttpServletRequest request , @Valid Report report) {
    	ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?dicid="+report.getDicid()));
    	if(!StringUtils.isBlank(report.getName())){
    		int count = reportRes.countByOrgiAndName(super.getOrgi(request), report.getName()) ;
    		if(count == 0) {
		    	report.setOrgi(super.getOrgi(request));
				report.setCreater(super.getUser(request).getId());
				report.setReporttype(UKDataContext.ReportType.REPORT.toString());
				report.setCode(UKTools.genID());
				reportRes.save(report) ;
    		}else {
    			view = request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?msg=rt_name_exist&dicid="+report.getDicid()));
    		}
    	}
        return view ;
    }
    
    @RequestMapping("/delete")
    @Menu(type = "setting" , subtype = "report" , admin= true)
    public ModelAndView quickreplydelete(ModelMap map , HttpServletRequest request , @Valid String id) {
    	Report report = reportRes.findOne(id) ;
    	if(report!=null){
    		reportRes.delete(report);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?dicid="+report.getDicid()));
    }
    @RequestMapping("/edit")
    @Menu(type = "setting" , subtype = "report" , admin= true)
    public ModelAndView quickreplyedit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	Report report = reportRes.findOne(id) ; 
    	map.put("report", report) ;
    	if(report!=null){
    		map.put("dataDic", dataDicRes.findByIdAndOrgi(report.getDicid(), super.getOrgi(request))) ;
    	}
    	map.addAttribute("dataDicList", dataDicRes.findByOrgi(super.getOrgi(request))) ;
        return request(super.createRequestPageTempletResponse("/apps/business/report/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "setting" , subtype = "report" , admin= true)
    public ModelAndView quickreplyupdate(ModelMap map , HttpServletRequest request , @Valid Report report) {
    	if(!StringUtils.isBlank(report.getId())){
    		Report temp = reportRes.findOne(report.getId()) ;
    		if(temp!=null) {
	    		temp.setName(report.getName());
	    		temp.setCode(report.getCode());
	    		temp.setDicid(report.getDicid());
	    		temp.setUpdatetime(new Date());
	    		temp.setDescription(report.getDescription());
	    		reportRes.save(temp) ;
    		}
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?dicid="+report.getDicid()));
    }
    
    @RequestMapping({"/addtype"})
	@Menu(type="apps", subtype="kbs")
	public ModelAndView addtype(ModelMap map , HttpServletRequest request , @Valid String dicid){
		map.addAttribute("dataDicList", dataDicRes.findByOrgi(super.getOrgi(request))) ;
		if(!StringUtils.isBlank(dicid)){
			map.addAttribute("dataDic", dataDicRes.findByIdAndOrgi(dicid, super.getOrgi(request))) ;
		}
		return request(super.createRequestPageTempletResponse("/apps/business/report/addtype"));
	}

    @RequestMapping("/type/save")
    @Menu(type = "apps" , subtype = "report")
    public ModelAndView typesave(HttpServletRequest request ,@Valid DataDic dataDic) {
    	List<DataDic> dicList = dataDicRes.findByOrgiAndName(super.getOrgi(request),dataDic.getName()) ;
    	if(dicList!=null && dicList.size() > 0){
    		return request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?dicid="+dataDic.getParentid()+"&msg=qr_type_exist"));
    	}else {
    		dataDic.setOrgi(super.getOrgi(request));
    		dataDic.setCreater(super.getUser(request).getId());
    		dataDic.setCreatetime(new Date());
    		dataDic.setTabtype(UKDataContext.QuickTypeEnum.PUB.toString());
    		dataDicRes.save(dataDic) ;
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?dicid="+dataDic.getId()));
    }
    
    @RequestMapping({"/edittype"})
	@Menu(type="apps", subtype="kbs")
	public ModelAndView edittype(ModelMap map , HttpServletRequest request , String id){
    	DataDic dataDic = dataDicRes.findByIdAndOrgi(id, super.getOrgi(request)) ;
    	map.addAttribute("dataDic", dataDic) ;
    	if(dataDic!=null) {
    		map.addAttribute("parentDataDic", dataDicRes.findByIdAndOrgi(dataDic.getParentid(), super.getOrgi(request))) ;
    	}
		map.addAttribute("dataDicList", dataDicRes.findByOrgi(super.getOrgi(request))) ;
		return request(super.createRequestPageTempletResponse("/apps/business/report/edittype"));
	}
	 
    @RequestMapping("/type/update")
    @Menu(type = "apps" , subtype = "report")
    public ModelAndView typeupdate(HttpServletRequest request ,@Valid DataDic dataDic) {
    	ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?dicid="+dataDic.getId()));
    	DataDic tempDataDic= dataDicRes.findByIdAndOrgi(dataDic.getId(), super.getOrgi(request)) ;
    	if(tempDataDic !=null){
    		//判断名称是否重复
    		List<DataDic> dicList = dataDicRes.findByOrgiAndNameAndIdNot(super.getOrgi(request) , dataDic.getName() , dataDic.getId()) ;
    		if(dicList!=null && dicList.size() > 0) {
    			view = request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?msg=qr_type_exist&dicid="+dataDic.getId()));
    		}else {
    			tempDataDic.setName(dataDic.getName());
    			tempDataDic.setDescription(dataDic.getDescription());
    			tempDataDic.setParentid(dataDic.getParentid());
	    		dataDicRes.save(tempDataDic) ;
    		}
    	}
    	return view ;
    }
    
    @RequestMapping({"/deletetype"})
	@Menu(type="apps", subtype="kbs")
	public ModelAndView deletetype(ModelMap map , HttpServletRequest request , @Valid String id){
    	ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?dicid="+id)); 
    	if(!StringUtils.isBlank(id)){
    		DataDic tempDataDic = dataDicRes.findByIdAndOrgi(id, super.getOrgi(request)) ;
    		int count = reportRes.countByOrgiAndDicid(super.getOrgi(request), id) ;
    		if(count == 0) {
    			dataDicRes.delete(tempDataDic);
    			view = request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?dicid="+tempDataDic.getParentid())); 
    		}else {
    			view = request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html?msg=report_exist&dicid="+id)); 
    		}
    	}
    	return view ;
	}
    
    @RequestMapping("/imp")
    @Menu(type = "setting" , subtype = "reportimp")
    public ModelAndView imp(ModelMap map , HttpServletRequest request , @Valid String type) {
    	map.addAttribute("type", type) ;
        return request(super.createRequestPageTempletResponse("/apps/business/report/imp"));
    }
    
    @RequestMapping("/impsave")
    @Menu(type = "setting" , subtype = "reportimpsave")
    public ModelAndView impsave(ModelMap map , HttpServletRequest request , @RequestParam(value = "cusfile", required = false) MultipartFile cusfile , @Valid String type) throws IOException {
    	DSDataEvent event = new DSDataEvent();
    	String fileName = "quickreply/"+UKTools.getUUID()+cusfile.getOriginalFilename().substring(cusfile.getOriginalFilename().lastIndexOf(".")) ;
    	File excelFile = new File(path , fileName) ;
    	if(!excelFile.getParentFile().exists()){
    		excelFile.getParentFile().mkdirs() ;
    	}
    	MetadataTable table = metadataRes.findByTablename("uk_report") ;
    	if(table!=null){
	    	FileUtils.writeByteArrayToFile(new File(path , fileName), cusfile.getBytes());
	    	event.setDSData(new DSData(table,excelFile , cusfile.getContentType(), super.getUser(request)));
	    	event.getDSData().setClazz(Report.class);
	    	event.setOrgi(super.getOrgi(request));
	    	if(!StringUtils.isBlank(type)){
	    		event.getValues().put("cate", type) ;
	    	}else{
	    		event.getValues().put("cate", UKDataContext.DEFAULT_TYPE) ;
	    	}
	    	event.getValues().put("type", UKDataContext.QuickTypeEnum.PUB.toString()) ;
	    	event.getValues().put("creater", super.getUser(request).getId()) ;
//	    	event.getDSData().setProcess(new QuickReplyProcess(reportRes));
//	    	reporterRes.save(event.getDSData().getReport()) ;
	    	new ExcelImportProecess(event).process() ;		//启动导入任务
    	}
    	
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html"+(!StringUtils.isBlank(type)? "?dicid="+type:"")));
    }
    
    @RequestMapping("/batdelete")
    @Menu(type = "setting" , subtype = "reportbatdelete")
    public ModelAndView batdelete(ModelMap map , HttpServletRequest request , HttpServletResponse response , @Valid String[] ids ,@Valid String type) throws IOException {
    	if(ids!=null && ids.length > 0){
    		Iterable<Report> topicList = reportRes.findAll(Arrays.asList(ids)) ;
    		reportRes.delete(topicList);
    	}
    	
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/index.html"+(!StringUtils.isBlank(type) ? "?dicid="+type:"")));
    }
    
    @RequestMapping("/expids")
    @Menu(type = "setting" , subtype = "reportexpids")
    public void expids(ModelMap map , HttpServletRequest request , HttpServletResponse response , @Valid String[] ids) throws IOException {
    	if(ids!=null && ids.length > 0){
    		Iterable<Report> topicList = reportRes.findAll(Arrays.asList(ids)) ;
    		MetadataTable table = metadataRes.findByTablename("uk_report") ;
    		List<Map<String,Object>> values = new ArrayList<Map<String,Object>>();
    		for(Report topic : topicList){
    			values.add(UKTools.transBean2Map(topic)) ;
    		}
    		
    		response.setHeader("content-disposition", "attachment;filename=UCKeFu-Report-"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls");  
    		if(table!=null){
    			ExcelExporterProcess excelProcess = new ExcelExporterProcess( values, table, response.getOutputStream()) ;
    			excelProcess.process();
    		}
    	}
    	
        return ;
    }
    
    @RequestMapping("/expall")
    @Menu(type = "setting" , subtype = "reportexpall")
    public void expall(ModelMap map , HttpServletRequest request , HttpServletResponse response,@Valid String type) throws IOException {
    	List<Report> reportList = reportRes.findByOrgiAndDicid(super.getOrgi(request) , type) ;
    	
    	MetadataTable table = metadataRes.findByTablename("uk_report") ;
		List<Map<String,Object>> values = new ArrayList<Map<String,Object>>();
		for(Report report : reportList){
			values.add(UKTools.transBean2Map(report)) ;
		}
		
		response.setHeader("content-disposition", "attachment;filename=UCKeFu-Report-"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls");  
		
		if(table!=null){
			ExcelExporterProcess excelProcess = new ExcelExporterProcess( values, table, response.getOutputStream()) ;
			excelProcess.process();
		}
        return ;
    }
    
    @RequestMapping("/pbreportindex")
    @Menu(type = "setting" , subtype = "pbreport" , admin= true)
    public ModelAndView pbreportindex(ModelMap map , HttpServletRequest request , @Valid String dicid) {
    	if(!StringUtils.isBlank(dicid) && !"0".equals(dicid)){
        	map.put("dataDic", dataDicRes.findByIdAndOrgi(dicid, super.getOrgi(request))) ;
    		map.put("reportList", publishedReportRes.findByOrgiAndDicid(super.getOrgi(request) , dicid , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	}else{
    		map.put("reportList", publishedReportRes.findByOrgi(super.getOrgi(request) , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	}
    	map.put("dataDicList", dataDicRes.findByOrgi(super.getOrgi(request))) ;
    	return request(super.createAppsTempletResponse("/apps/business/report/pbreportindex"));
    }
    @RequestMapping("/pbreportlist")
    @Menu(type = "setting" , subtype = "pbreport" , admin= true)
    public ModelAndView pbreportlist(ModelMap map , HttpServletRequest request , @Valid String dicid) {
    	if(!StringUtils.isBlank(dicid) && !"0".equals(dicid)){
        	map.put("dataDic", dataDicRes.findByIdAndOrgi(dicid, super.getOrgi(request))) ;
    		map.put("reportList", publishedReportRes.findByOrgiAndDicid(super.getOrgi(request) , dicid , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	}else{
    		map.put("reportList", publishedReportRes.findByOrgi(super.getOrgi(request) , new PageRequest(super.getP(request), super.getPs(request)))) ;
    	}
    	map.put("dataDicList", dataDicRes.findByOrgi(super.getOrgi(request))) ;
    	return request(super.createRequestPageTempletResponse("/apps/business/report/pbreportlist"));
    }
    
    @RequestMapping("/pbdelete")
    @Menu(type = "setting" , subtype = "pbreport" , admin= true)
    public ModelAndView pbdelete(ModelMap map , HttpServletRequest request , @Valid String id) {
    	PublishedReport report = publishedReportRes.findOne(id) ;
    	if(report!=null){
    		publishedReportRes.delete(report);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/pbreportindex.html?dicid="+report.getDicid()));
    }
    
    @RequestMapping("/unpub")
    @Menu(type = "setting" , subtype = "pbreport" , admin= true)
    public ModelAndView unpub(ModelMap map , HttpServletRequest request , @Valid String id) {
    	PublishedReport pubReport = publishedReportRes.findOne(id) ;
    	if(pubReport!=null){
    		Report report = pubReport.getReport() ;
    		List<ReportFilter> filters = report.getReportFilters() ;
    		for(ReportFilter filter : filters) {
    			if(reportFilterRepository.findById(filter.getId())==null){
    				reportFilterRepository.save(filter) ;
    			}
    		}
    		for(ReportModel model : report.getReportModels()) {
    			for(ColumnProperties col : model.getColproperties()) {
    				columnPropertiesRepository.save(col) ;
    			}
    			for(ColumnProperties pro : model.getProperties()) {
    				columnPropertiesRepository.save(pro) ;
    			}
    			for(ColumnProperties measure : model.getMeasures()) {
    				columnPropertiesRepository.save(measure) ;
    			}
    			for(ReportFilter filter : model.getFilters()) {
    				reportFilterRepository.save(filter) ;
    			}
    			if(reportModelRes.findById(model.getId()) == null) {
    				reportModelRes.save(model) ;
    			}
    		}
    		if(reportRes.findByIdAndOrgi(report.getId(), super.getOrgi(request)) == null) {
    			reportRes.save(report) ;
    		}
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/report/pbreportindex.html?dicid="+pubReport.getDicid()));
    }
    /**
     * 报表
     * @param map
     * @param request
     * @param id
     * @return
     * @throws Exception 
     */
    @RequestMapping("/view")
	@Menu(type = "report", subtype = "report")
	public ModelAndView view(ModelMap map, HttpServletRequest request, @Valid String id) throws Exception {
		if (!StringUtils.isBlank(id)) {
			PublishedReport publishedReport = publishedReportRes.findById(id);
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
		return request(super.createRequestPageTempletResponse("/apps/business/report/view"));
	}
}