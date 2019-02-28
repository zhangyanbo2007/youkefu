package com.ukefu.webim.web.handler.admin.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.corundumstudio.socketio.SocketIOServer;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.SecretRepository;
import com.ukefu.webim.service.repository.SystemConfigRepository;
import com.ukefu.webim.service.repository.SystemMessageRepository;
import com.ukefu.webim.service.repository.TemplateRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Secret;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.SystemConfig;
import com.ukefu.webim.web.model.UKeFuDic;

@Controller
@RequestMapping("/admin/config")
public class SystemConfigController extends Handler{
	
	@Value("${uk.im.server.port}")  
    private Integer port;

	@Value("${web.upload-path}")
    private String path;
	
	@Autowired
	private SocketIOServer server ;
	
	@Autowired
	private SystemConfigRepository systemConfigRes ;
	
	
	@Autowired
	private SystemMessageRepository systemMessageRes ;
	
	@Autowired
	private SecretRepository secRes ;
	
	@Autowired
	private TemplateRepository templateRes ;
	
    @RequestMapping("/index")
    @Menu(type = "admin" , subtype = "config" , admin = true)
    public ModelAndView index(ModelMap map , HttpServletRequest request , @Valid String execute) throws SQLException {
    	map.addAttribute("server", server) ;
    	if(UKDataContext.model.get("im")!=null){
    		map.addAttribute("entim", UKDataContext.model.get("im")) ;
    	}
    	if(request.getSession().getAttribute(UKDataContext.UKEFU_SYSTEM_INFOACQ)!=null){
    		map.addAttribute("entim", request.getSession().getAttribute(UKDataContext.UKEFU_SYSTEM_INFOACQ)) ;
    	}
    	map.addAttribute("server", server) ;
    	map.addAttribute("imServerStatus", UKDataContext.getIMServerStatus()) ;
    	List<Secret> secretConfig = secRes.findByOrgi(super.getOrgi(request)) ;
    	if(secretConfig!=null && secretConfig.size() > 0){
    		map.addAttribute("secret", secretConfig.get(0)) ;
    	}
    	List<SysDic> dicList = UKeFuDic.getInstance().getDic(UKDataContext.UKEFU_SYSTEM_DIC) ;
    	SysDic callCenterDic = null , workOrderDic = null  , smsDic = null ;
    	for(SysDic dic : dicList){
    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_CALLCENTER)){
    			callCenterDic = dic ;
    		}
    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_WORKORDEREMAIL)){
    			workOrderDic = dic ;
    		}
    		if(dic.getCode().equals(UKDataContext.UKEFU_SYSTEM_SMSEMAIL)){
    			smsDic = dic ;
    		}
    	}
    	if(callCenterDic!=null){
    		map.addAttribute("templateList", templateRes.findByTemplettypeAndOrgi(callCenterDic.getId(), super.getOrgi(request))) ;
    	}
    	if(workOrderDic!=null){
    		map.addAttribute("workOrderList", templateRes.findByTemplettypeAndOrgi(workOrderDic.getId(), super.getOrgi(request))) ;
    	}
    	if(smsDic!=null){
    		map.addAttribute("smsList", templateRes.findByTemplettypeAndOrgi(smsDic.getId(), super.getOrgi(request))) ;
    	}
    	
    	map.addAttribute("sysMessageList", systemMessageRes.findByMsgtypeAndOrgi(UKDataContext.SystemMessageType.EMAIL.toString(), super.getOrgi(request))) ;
    	
    	map.addAttribute("smsMessageList", systemMessageRes.findByMsgtypeAndOrgi(UKDataContext.SystemMessageType.SMS.toString(), super.getOrgi(request))) ;
    	
    	if(!StringUtils.isBlank(execute) && execute.equals("false")){
    		map.addAttribute("execute", execute) ;
    	}
    	if(!StringUtils.isBlank(request.getParameter("msg"))){
    		map.addAttribute("msg", request.getParameter("msg")) ;
    	}
        return request(super.createAdminTempletResponse("/admin/config/index"));
    }
    
    @RequestMapping("/stopimserver")
    @Menu(type = "admin" , subtype = "stopimserver" , access = false , admin = true)
    public ModelAndView stopimserver(ModelMap map , HttpServletRequest request , @Valid String confirm) throws SQLException {
    	boolean execute = false ;
    	if(execute = UKTools.secConfirm(secRes, super.getOrgi(request), confirm)){
	    	server.stop();
	    	UKDataContext.setIMServerStatus(false);
    	}
        return request(super.createRequestPageTempletResponse("redirect:/admin/config/index.html?execute="+execute));
    }
    
    @RequestMapping("/startentim")
    @Menu(type = "admin" , subtype = "startentim" , access = false , admin = true)
    public ModelAndView startentim(ModelMap map , HttpServletRequest request) throws SQLException {
    	UKDataContext.model.put("im", true) ;
        return request(super.createRequestPageTempletResponse("redirect:/admin/config/index.html"));
    }
    
    @RequestMapping("/stopentim")
    @Menu(type = "admin" , subtype = "stopentim" , access = false , admin = true)
    public ModelAndView stopentim(ModelMap map , HttpServletRequest request) throws SQLException {
    	UKDataContext.model.remove("im") ;
        return request(super.createRequestPageTempletResponse("redirect:/admin/config/index.html"));
    }
    
    /**
     * 危险操作，请谨慎调用 ， WebLogic/WebSphere/Oracle等中间件服务器禁止调用
     * @param map
     * @param request
     * @return
     * @throws SQLException
     */
    @RequestMapping("/stop")
    @Menu(type = "admin" , subtype = "stop" , access = false , admin = true)
    public ModelAndView stop(ModelMap map , HttpServletRequest request , @Valid String confirm) throws SQLException {
    	boolean execute = false ;
    	if(execute = UKTools.secConfirm(secRes, super.getOrgi(request), confirm)){
	    	server.stop();
	    	UKDataContext.setIMServerStatus(false);
	    	System.exit(0);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/config/index.html?execute="+execute));
    }
    
    
    @RequestMapping("/save")
    @Menu(type = "admin" , subtype = "save" , admin = true)
    public ModelAndView save(ModelMap map , HttpServletRequest request , @Valid SystemConfig config ,BindingResult result , @RequestParam(value = "keyfile", required = false) MultipartFile keyfile , @RequestParam(value = "loginlogo", required = false) MultipartFile loginlogo , @RequestParam(value = "consolelogo", required = false) MultipartFile consolelogo , @RequestParam(value = "favlogo", required = false) MultipartFile favlogo , @Valid Secret secret) throws SQLException, IOException, NoSuchAlgorithmException {
    	/*SystemConfig systemConfig = systemConfigRes.findByOrgi(super.getOrgi(request)) ;
    	config.setOrgi(super.getOrgi(request));*/
    	SystemConfig systemConfig = systemConfigRes.findByOrgi(UKDataContext.SYSTEM_ORGI) ;
    	config.setOrgi(UKDataContext.SYSTEM_ORGI);
    	String msg = "0" ;
    	String jkspassword = null ;
    	if(StringUtils.isBlank(config.getJkspassword())){
    		config.setJkspassword(null);
    	}
    	if(systemConfig == null){
    		config.setCreater(super.getUser(request).getId());
    		config.setCreatetime(new Date());
    		systemConfig = config ;
    		jkspassword = UKTools.encryption(systemConfig.getJkspassword() );
    	}else{
    		UKTools.copyProperties(config,systemConfig);
    		if(StringUtils.isBlank(config.getJkspassword())){
    			jkspassword = systemConfig.getJkspassword();
        	}else {
        		jkspassword = UKTools.encryption(systemConfig.getJkspassword() );
        	}
    	}
    	if(config.isEnablessl()){
	    	if(keyfile!=null && keyfile.getBytes()!=null && keyfile.getBytes().length > 0 && keyfile.getOriginalFilename()!=null && keyfile.getOriginalFilename().length() > 0){
		    	FileUtils.writeByteArrayToFile(new File(path , "ssl/"+keyfile.getOriginalFilename()), keyfile.getBytes());
		    	systemConfig.setJksfile(keyfile.getOriginalFilename());
		    	File sslFilePath = new File(path , "ssl/https.properties") ;
		    	if(!sslFilePath.getParentFile().exists()) {
		    		sslFilePath.getParentFile().mkdirs() ;
		    	}
		    	Properties prop = new Properties();     
		    	FileOutputStream oFile = new FileOutputStream(sslFilePath);//true表示追加打开
		    	if(systemConfig.getJkspassword()!=null) {
		    		prop.setProperty("key-store-password", jkspassword) ;
		    	}
		    	prop.setProperty("key-store",systemConfig.getJksfile()) ;
		    	prop.store(oFile , "SSL Properties File");
		    	oFile.close();
	    	}
    	}else if(new File(path , "ssl").exists()){
    		File[] sslFiles = new File(path , "ssl").listFiles() ;
    		for(File sslFile : sslFiles){
    			sslFile.delete();
    		}
    	}
    	
    	if(loginlogo!=null && !StringUtils.isBlank(loginlogo.getOriginalFilename()) && loginlogo.getOriginalFilename().lastIndexOf(".") > 0) {
    		String logoFileName = "logo/"+UKTools.md5(loginlogo.getOriginalFilename())+loginlogo.getOriginalFilename().substring(loginlogo.getOriginalFilename().lastIndexOf(".")) ;
    		FileUtils.writeByteArrayToFile(new File(path ,logoFileName), loginlogo.getBytes());
    		systemConfig.setLoginlogo(logoFileName);
    	}
    	if(consolelogo!=null && !StringUtils.isBlank(consolelogo.getOriginalFilename()) && consolelogo.getOriginalFilename().lastIndexOf(".") > 0) {
    		String consoleLogoFileName = "logo/"+UKTools.md5(consolelogo.getOriginalFilename())+consolelogo.getOriginalFilename().substring(consolelogo.getOriginalFilename().lastIndexOf(".")) ;
    		FileUtils.writeByteArrayToFile(new File(path ,consoleLogoFileName), consolelogo.getBytes());
    		systemConfig.setConsolelogo(consoleLogoFileName);
    	}
    	if(favlogo!=null && !StringUtils.isBlank(favlogo.getOriginalFilename()) && consolelogo.getOriginalFilename().lastIndexOf(".") > 0) {
    		String favLogoFileName = "logo/"+UKTools.md5(favlogo.getOriginalFilename())+favlogo.getOriginalFilename().substring(favlogo.getOriginalFilename().lastIndexOf(".")) ;
    		FileUtils.writeByteArrayToFile(new File(path ,favLogoFileName), favlogo.getBytes());
    		systemConfig.setFavlogo(favLogoFileName);
    	}
    	
    	if(secret!=null && !StringUtils.isBlank(secret.getPassword())){
	    	List<Secret> secretConfig = secRes.findByOrgi(super.getOrgi(request)) ;
	    	String repassword = request.getParameter("repassword") ;
	    	if(!StringUtils.isBlank(repassword) && repassword.equals(secret.getPassword())){
		    	if(secretConfig!=null && secretConfig.size() > 0){
		    		Secret tempSecret = secretConfig.get(0) ;
		    		String oldpass = request.getParameter("oldpass") ;
		    		if(!StringUtils.isBlank(oldpass) && UKTools.md5(oldpass).equals(tempSecret.getPassword())){
		    			tempSecret.setPassword(UKTools.md5(secret.getPassword()));
		    			msg = "1" ;
		    			tempSecret.setEnable(true);
		    			secRes.save(tempSecret) ;
		    		}else{
			    		msg = "3" ;
			    	}
		    	}else{
		    		secret.setOrgi(super.getOrgi(request));
		    		secret.setCreater(super.getUser(request).getId());
		    		secret.setCreatetime(new Date());
		    		secret.setPassword(UKTools.md5(secret.getPassword()));
		    		secret.setEnable(true);
		    		msg = "1" ;
		    		secRes.save(secret) ;
		    	}
	    	}else{
	    		msg = "2" ;
	    	}
	    	map.addAttribute("msg", msg) ;
    	}
    	
    	if(!StringUtils.isBlank(jkspassword)) {
    		systemConfig.setJkspassword(jkspassword);
    	}
    	systemConfigRes.save(systemConfig) ;
    	
    	CacheHelper.getSystemCacheBean().put("systemConfig", systemConfig , super.getOrgi(request));
    	map.addAttribute("imServerStatus", UKDataContext.getIMServerStatus()) ;
    	
    	return request(super.createRequestPageTempletResponse("redirect:/admin/config/index.html?msg="+msg));
    }
}