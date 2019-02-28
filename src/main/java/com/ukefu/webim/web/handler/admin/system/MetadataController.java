package com.ukefu.webim.web.handler.admin.system;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.util.UKeFuList;
import com.ukefu.util.metadata.DatabaseMetaDataHandler;
import com.ukefu.util.metadata.UKColumnMetadata;
import com.ukefu.util.metadata.UKTableMetaData;
import com.ukefu.webim.service.hibernate.BaseService;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.service.repository.SysDicRepository;
import com.ukefu.webim.service.repository.TablePropertiesRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.TableProperties;
import com.ukefu.webim.web.model.UKeFuDic;
import com.ukefu.webim.web.model.User;

@Controller
@RequestMapping("/admin/metadata")
public class MetadataController extends Handler{
	
	@Autowired
	private MetadataRepository metadataRes ;
	
	@Autowired
	private BaseService<?> service ;
	
	@Autowired
	private SysDicRepository sysDicRes ;
	
	@Autowired
	private TablePropertiesRepository tablePropertiesRes ;
	
	@Autowired
	@PersistenceContext
	private EntityManager em;

    @RequestMapping("/index")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView index(ModelMap map , HttpServletRequest request ,String msg) throws SQLException {
    	map.addAttribute("metadataList", metadataRes.findAll(new PageRequest(super.getP(request), super.getPs(request)))) ;
    	map.addAttribute("msg", msg);
        return request(super.createAdminTempletResponse("/admin/system/metadata/index"));
    }
    
    @RequestMapping("/edit")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView edit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.addAttribute("metadata", metadataRes.findById(id)) ;
    	map.addAttribute("propertiesList", tablePropertiesRes.findByDbtableid(id)) ;
    	return request(super.createRequestPageTempletResponse("/admin/system/metadata/edit"));
    }
    
    @RequestMapping("/update")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView update(ModelMap map , HttpServletRequest request , @Valid MetadataTable metadata) throws SQLException {
    	MetadataTable table = metadataRes.findById(metadata.getId()) ;
    	table.setName(metadata.getName());
    	table.setFromdb(metadata.isFromdb());
    	table.setListblocktemplet(metadata.getListblocktemplet());
    	table.setPreviewtemplet(metadata.getPreviewtemplet());
    	metadataRes.save(table);
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html"));
    }
    
    @RequestMapping("/addsql")
    @Menu(type = "admin" , subtype = "addsql" , admin = true)
    public ModelAndView addsql(ModelMap map , HttpServletRequest request) {
    	return request(super.createRequestPageTempletResponse("/admin/system/metadata/addsql"));
    }
    @RequestMapping("/addsqlsave")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView addsqlsave(ModelMap map , HttpServletRequest request , final @Valid String datasql , final @Valid String name) throws Exception {
    	if(!StringUtils.isBlank(datasql) && !StringUtils.isBlank(name)){
    		final User user = super.getUser(request);
	    	Session session = (Session) em.getDelegate();
	    	session.doWork(
	    		    new Work() {
	    		        public void execute(Connection connection) throws SQLException 
	    		        {
	    		        	try{
	    		        		int count = metadataRes.countByTablename(name) ;
    				    		if(count == 0){
    			 		    		MetadataTable metaDataTable = new MetadataTable();
    				  				//当前记录没有被添加过，进行正常添加
    				  				metaDataTable.setTablename(name);
    				  				metaDataTable.setOrgi(user.getOrgi());
    				  				metaDataTable.setId(UKTools.md5(metaDataTable.getTablename()));
    				  				metaDataTable.setTabledirid("0");
    				  				metaDataTable.setCreater(user.getId());
    				  				metaDataTable.setCreatername(user.getUsername());
    				  				metaDataTable.setName(name);
    				  				metaDataTable.setDatasql(datasql);
    				  				metaDataTable.setTabletype("2");
    				  				metaDataTable.setUpdatetime(new Date());
    				  				metaDataTable.setCreatetime(new Date());
    				  				metadataRes.save(processMetadataTable( DatabaseMetaDataHandler.getSQL(connection, name, datasql) , metaDataTable));
    				    		}
	    			    	}catch(Exception ex){
	    			    		ex.printStackTrace();
	    			    	}finally{
	    			    		connection.close();
	    			    	}
	    		        }
	    		    }
	    		);
	    	
    	}
    	
        return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html"));
    }
    
    @RequestMapping("/editsql")
    @Menu(type = "admin" , subtype = "editsql" , admin = true)
    public ModelAndView editsql(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.addAttribute("metadata", metadataRes.findById(id)) ;
    	return request(super.createRequestPageTempletResponse("/admin/system/metadata/editsql"));
    }
    
    

    @RequestMapping("/updatesql")
    @Menu(type = "admin" , subtype = "updatesql" , admin = true)
    public ModelAndView updatesql(ModelMap map , HttpServletRequest request , @Valid MetadataTable metadata) throws SQLException {
    	MetadataTable table = metadataRes.findById(metadata.getId()) ;
    	if(table!=null) {
	    	table.setName(metadata.getName());
	    	table.setDatasql(metadata.getDatasql());
	    	metadataRes.save(table);
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html"));
    }
    
    @RequestMapping("/properties/edit")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView propertiesedit(ModelMap map , HttpServletRequest request , @Valid String id) {
    	map.addAttribute("tp", tablePropertiesRes.findById(id)) ;
    	map.addAttribute("sysdicList", sysDicRes.findByParentid("0")) ;
    	map.addAttribute("dataImplList", UKeFuDic.getInstance().getDic("com.dic.data.impl")) ;
    	
    	return request(super.createRequestPageTempletResponse("/admin/system/metadata/tpedit"));
    }
    
    @RequestMapping("/properties/update")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView propertiesupdate(ModelMap map , HttpServletRequest request , @Valid TableProperties tp) throws SQLException {
    	TableProperties tableProperties = tablePropertiesRes.findById(tp.getId()) ;
    	tableProperties.setName(tp.getName());
    	tableProperties.setSeldata(tp.isSeldata());
    	tableProperties.setSeldatacode(tp.getSeldatacode());
    	
    	tableProperties.setReffk(tp.isReffk());
    	tableProperties.setReftbid(tp.getReftbid());
    	
    	tableProperties.setDefaultvaluetitle(tp.getDefaultvaluetitle());
    	tableProperties.setDefaultfieldvalue(tp.getDefaultfieldvalue());
    	
    	tableProperties.setModits(tp.isModits());
    	tableProperties.setPk(tp.isPk()) ;
    	
    	tableProperties.setSystemfield(tp.isSystemfield());
    	
    	tableProperties.setPlugin(tp.getPlugin());
    	
    	tableProperties.setImpfield(tp.isImpfield());
    	
    	tableProperties.setSortindex(tp.getSortindex());
    	
    	tablePropertiesRes.save(tableProperties);
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/table.html?id="+tableProperties.getDbtableid()));
    }
    @RequestMapping("/properties/update/mul")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView editMultiple(ModelMap map , HttpServletRequest request , @Valid String[] ids, @Valid String tbid) {
    	String[] porids = ids;
    	map.addAttribute("porids", porids);
    	map.addAttribute("tbid", tbid);
    	return request(super.createRequestPageTempletResponse("/admin/system/metadata/editmultiple"));
    }
    
    @RequestMapping("/properties/update/mul/save")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView updateMultiple(ModelMap map , HttpServletRequest request , @Valid TableProperties table, @Valid String[] porids, @Valid String tbid) throws SQLException {
    	
    	if(porids != null && porids.length > 0) {
    		List<TableProperties> proList = new ArrayList<TableProperties>();
    		for(String proid : porids) {
        		TableProperties tableProperties = tablePropertiesRes.findById(proid) ;
        		if(tableProperties != null) {
        			tableProperties.setImpfield(table.isImpfield());
        			proList.add(tableProperties);
        		}
        	}
    		if(proList.size() > 0) {
    			tablePropertiesRes.save(proList);
    		}
    	}
    	
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/table.html?id="+tbid));
    }
    @RequestMapping("/delete")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView delete(ModelMap map , HttpServletRequest request , @Valid String id) throws SQLException {
    	MetadataTable table = metadataRes.findById(id) ;
    	metadataRes.delete(table);
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html"));
    }
    
    @RequestMapping("/sync")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView sync(ModelMap map , HttpServletRequest request , @Valid String id) throws SQLException {
    	final MetadataTable table = metadataRes.findById(id) ;
    	Session session = (Session) em.getDelegate();
		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				try {
					MetadataTable metaDataTable = new MetadataTable();
					//当前记录没有被添加过，进行正常添加
	  				metaDataTable.setTablename(table.getTablename());
	  				metaDataTable.setOrgi(table.getOrgi());
	  				metaDataTable.setId(UKTools.md5(metaDataTable.getTablename()));
	  				metaDataTable.setTabledirid("0");
	  				metaDataTable.setCreater(table.getCreater());
	  				metaDataTable.setCreatername(table.getCreatername());
	  				metaDataTable.setName(table.getName());
	  				metaDataTable.setUpdatetime(new Date());
	  				metaDataTable.setCreatetime(new Date());
					processMetadataTable( DatabaseMetaDataHandler.getTable(connection, metaDataTable.getTablename()) , metaDataTable) ;
					for(TableProperties temp : metaDataTable.getTableproperty()) {
						boolean found = false ;
						for(TableProperties tp : table.getTableproperty()) {
							if(temp.getFieldname().equals(tp.getFieldname())) {
								found = true ;
								break ;
							}
						}
						if(found == false) {
							temp.setDbtableid(table.getId());
							tablePropertiesRes.save(temp) ;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
		    		connection.close();
		    	}
			}
		});
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/table.html?id="+id));
    }
    
    @RequestMapping("/batdelete")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView batdelete(ModelMap map , HttpServletRequest request , @Valid String[] ids) throws SQLException {
    	String msg = "bat_delete_success";
    	if(ids!=null && ids.length>0){
    		metadataRes.delete(metadataRes.findAll(Arrays.asList(ids)) );
    	}else {
    		msg = "bat_delete_faild";
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html?msg="+msg));
    }
    
    @RequestMapping("/properties/delete")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView propertiesdelete(ModelMap map , HttpServletRequest request , @Valid String id , @Valid String tbid) throws SQLException {
    	TableProperties prop = tablePropertiesRes.findById(id) ;
    	tablePropertiesRes.delete(prop);
        return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/table.html?id="+ (!StringUtils.isBlank(tbid) ? tbid : prop.getDbtableid())));
    }
    
    @RequestMapping("/properties/batdelete")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView propertiesbatdelete(ModelMap map , HttpServletRequest request , @Valid String[] ids, @Valid String tbid) throws SQLException {
    	String msg = "bat_delete_success";
    	if(ids!=null && ids.length>0){
    		tablePropertiesRes.delete(tablePropertiesRes.findAll(Arrays.asList(ids)) );
    	}else {
    		msg = "bat_delete_faild";
    	}
        return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/table.html?id="+ tbid+"&msg="+msg));
    }
    
    @RequestMapping("/table")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView table(ModelMap map , HttpServletRequest request , @Valid String id, @Valid String msg) throws SQLException {
    	map.addAttribute("propertiesList", tablePropertiesRes.findByDbtableid(id)) ;
    	map.addAttribute("tbid", id) ;
    	map.addAttribute("table", metadataRes.findById(id)) ;
    	map.addAttribute("msg", msg);
        return request(super.createAdminTempletResponse("/admin/system/metadata/table"));
    }
    
    @RequestMapping("/imptb")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView imptb(final ModelMap map , HttpServletRequest request) throws Exception {
    	this.search(map, request);
    	Session session = (Session) em.getDelegate();
		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				try {
					map.addAttribute("tablesList",
							DatabaseMetaDataHandler.getTables(connection));
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
		    		connection.close();
		    	}
			}
		});
		
		return request(super
				.createRequestPageTempletResponse("/admin/system/metadata/tablelist"));
    }
    
    public void search(ModelMap map ,HttpServletRequest request) {
    	List<MetadataTable> metaList = metadataRes.findByOrgi(super.getOrgi(request));
		if(metaList != null && !metaList.isEmpty()) {
			StringBuffer str = new StringBuffer();
			for(MetadataTable MetadataTable : metaList) {
				str.append(MetadataTable.getName());
				str.append(",");
			}
			map.addAttribute("metadataTable", str);
		}
    }
    
    @RequestMapping("/imptbsave")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView imptb(ModelMap map , HttpServletRequest request , final @Valid String[] tables) throws Exception {
    	final User user = super.getUser(request) ;
    	if(tables!=null && tables.length > 0){
	    	Session session = (Session) em.getDelegate();
	    	session.doWork(
	    		    new Work() {
	    		        public void execute(Connection connection) throws SQLException 
	    		        {
	    		        	try{
	    				    	for(String table : tables){
	    				    		int count = metadataRes.countByTablename(table) ;
	    				    		if(count == 0){
	    			 		    		MetadataTable metaDataTable = new MetadataTable();
	    				  				//当前记录没有被添加过，进行正常添加
	    				  				metaDataTable.setTablename(table);
	    				  				metaDataTable.setOrgi(user.getOrgi());
	    				  				metaDataTable.setId(UKTools.md5(metaDataTable.getTablename()));
	    				  				metaDataTable.setTabledirid("0");
	    				  				metaDataTable.setCreater(user.getId());
	    				  				metaDataTable.setCreatername(user.getUsername());
	    				  				metaDataTable.setName(table);
	    				  				metaDataTable.setUpdatetime(new Date());
	    				  				metaDataTable.setCreatetime(new Date());
	    				  				metadataRes.save(processMetadataTable( DatabaseMetaDataHandler.getTable(connection, metaDataTable.getTablename()) , metaDataTable));
	    				    		}
	    				    	}
	    			    	}catch(Exception ex){
	    			    		ex.printStackTrace();
	    			    	}finally{
	    			    		connection.close();
	    			    	}
	    		        }
	    		    }
	    		);
	    	
    	}
    	
        return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html"));
    }
    
    public MetadataTable processMetadataTable(UKTableMetaData metaData , MetadataTable table){
    	table.setTableproperty(new ArrayList<TableProperties>()); 
    	if(metaData!=null){
	    	for(UKColumnMetadata colum : metaData.getColumnMetadatas()){
	    		TableProperties tablePorperties = new TableProperties(colum.getName().toLowerCase() , colum.getTypeName() , colum.getColumnSize() , metaData.getName().toLowerCase()) ;
				tablePorperties.setOrgi(table.getOrgi()) ;
				
				tablePorperties.setDatatypecode(0);
				tablePorperties.setLength(colum.getColumnSize());
				tablePorperties.setDatatypename(getDataTypeName(colum.getTypeName()));
				tablePorperties.setName(colum.getTitle().toLowerCase());
				if(tablePorperties.getFieldname().equals("create_time") || tablePorperties.getFieldname().equals("createtime") || tablePorperties.getFieldname().equals("update_time")){
					tablePorperties.setDatatypename(getDataTypeName("datetime"));
				}
				if(colum.getName().startsWith("field")){
					tablePorperties.setFieldstatus(false);
				}else{
					tablePorperties.setFieldstatus(true);
				}
				table.getTableproperty().add(tablePorperties) ;
			}
	    	table.setTablename(table.getTablename().toLowerCase());//转小写
    	}
    	return table ;
    }
    
    public String getDataTypeName(String type){
    	String typeName = "text" ;
    	if(type.indexOf("varchar")>=0){
    		typeName = "text" ;
    	}else if(type.equalsIgnoreCase("date") || type.equalsIgnoreCase("datetime")){
    		typeName = type.toLowerCase() ;
    	}else if(type.equalsIgnoreCase("int") || type.equalsIgnoreCase("float")  || type.equalsIgnoreCase("number")){
    		typeName = "number" ;
    	}
    	return typeName ;
    }
    
    @RequestMapping("/clean")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView clean(ModelMap map , HttpServletRequest request,@Valid String id) throws SQLException, BeansException, ClassNotFoundException {
    	if(!StringUtils.isBlank(id)) {
    		MetadataTable table = metadataRes.findById(id) ;
    		if(table.isFromdb() && !StringUtils.isBlank(table.getListblocktemplet())) {
    			SysDic dic = UKeFuDic.getInstance().getDicItem(table.getListblocktemplet()) ;
    			if(dic!=null) {
	    			Object bean = UKDataContext.getContext().getBean(Class.forName(dic.getCode())) ;
	    			if(bean instanceof ElasticsearchRepository) {
	    				ElasticsearchRepository<?, ?> jpa = (ElasticsearchRepository<?, ?>)bean ;
	    				jpa.deleteAll(); 
	    			}
    			}
    		}
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html"));
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping("/synctoes")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView synctoes(ModelMap map , HttpServletRequest request,@Valid String id) throws SQLException, BeansException, ClassNotFoundException {
    	if(!StringUtils.isBlank(id)) {
    		MetadataTable table = metadataRes.findById(id) ;
    		if(table.isFromdb() && !StringUtils.isBlank(table.getListblocktemplet())) {
    			SysDic dic = UKeFuDic.getInstance().getDicItem(table.getListblocktemplet()) ;
    			
    			if(dic!=null) {
	    			Object bean = UKDataContext.getContext().getBean(Class.forName(dic.getCode())) ;
	    			if(bean instanceof ElasticsearchRepository) {
	    				ElasticsearchRepository jpa = (ElasticsearchRepository)bean ;
	    				if(!StringUtils.isBlank(table.getPreviewtemplet())) {
	    					SysDic jpaDic = UKeFuDic.getInstance().getDicItem(table.getPreviewtemplet()) ;
	    					List dataList = service.list(jpaDic.getCode()) ;
	    					List values = new UKeFuList();
	    					for(Object object : dataList) {
	    						values.add(object) ;
	    					}
	    					if(dataList.size() > 0) {
	    						jpa.save(values) ;
	    					}
	    				}
	    			}
    			}
    		}
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html"));
    }
    
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping("/synctodb")
    @Menu(type = "admin" , subtype = "metadata" , admin = true)
    public ModelAndView synctodb(ModelMap map , HttpServletRequest request,@Valid String id) throws SQLException, BeansException, ClassNotFoundException {
    	if(!StringUtils.isBlank(id)) {
    		MetadataTable table = metadataRes.findById(id) ;
    		if(table.isFromdb() && !StringUtils.isBlank(table.getListblocktemplet())) {
    			SysDic dic = UKeFuDic.getInstance().getDicItem(table.getListblocktemplet()) ;
    			
    			if(dic!=null) {
	    			Object bean = UKDataContext.getContext().getBean(Class.forName(dic.getCode())) ;
	    			if(bean instanceof ElasticsearchRepository) {
	    				ElasticsearchRepository jpa = (ElasticsearchRepository)bean ;
	    				if(!StringUtils.isBlank(table.getPreviewtemplet())) {
	    					Iterable dataList = jpa.findAll();
	    					for(Object object : dataList) {
	    						service.delete(object);
	    						service.save(object);
	    					}
	    				}
	    			}
    			}
    		}
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/admin/metadata/index.html"));
    }
    
}