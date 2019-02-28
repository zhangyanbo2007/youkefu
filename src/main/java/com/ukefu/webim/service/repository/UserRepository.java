package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.User;

public abstract interface UserRepository extends JpaRepository<User, String>
{
  public abstract User findByIdAndOrgi(String paramString, String orgi);
  
  
  public abstract User findById(String id);
  
  public abstract User findByEmailAndDatastatus(String email,boolean datastatus);
  
  public abstract User findByMobileAndDatastatus(String mobile,boolean datastatus);
  
  public abstract User findByUsernameAndDatastatus(String username, boolean datastatus);
  
  public abstract User findByUsernameAndPasswordAndDatastatus(String username, String password,boolean datastatus);
  
  public abstract User findByMobileAndPasswordAndDatastatus(String mobile, String password,boolean datastatus);
  
  public abstract User findByUsernameAndOrgi(String paramString, String orgi);
  
  public abstract User findByUsernameAndPassword(String paramString1, String password);
  
  public abstract User findBySuperuser(boolean superuser);
  
  public abstract Page<User> findByOrgi(String orgi , Pageable paramPageable);
  
  public abstract List<User> findByOrgi(String orgi);
  
  public abstract Page<User> findByDatastatusAndOrgi(boolean datastatus , String orgi, Pageable paramPageable);
  
  public abstract Page<User> findByDatastatusAndOrgiAndUsernameLike(boolean datastatus , String orgi ,String username ,Pageable paramPageable);
  
  public abstract Page<User> findByIdAndOrgi(String id , String orgi,Pageable paramPageable);
  
  public abstract List<User> findByOrganAndOrgiAndDatastatus(String paramString, String orgi,boolean b);
  
  public abstract List<User> findByOrganAndDatastatusAndOrgi(String paramString , boolean datastatus, String orgi);
  
  public abstract List<User> findByOrgiAndDatastatus(String orgi , boolean datastatus);
  
  public abstract Page<User> findByOrgiAndAgentAndDatastatus(String orgi , boolean agent ,boolean status, Pageable paramPageable);
  
  public abstract List<User> findByOrgiAndAgentAndDatastatus(String orgi , boolean agent,boolean status);
  
  public abstract long countByOrgiAndAgent(String orgi , boolean agent) ;
  
  public abstract int countByOrgiAndOrgan(String orgi , String organ) ;
  
  public abstract List<User> findAll(Specification<User> spec) ;
  

	public abstract Page<User> findByDatastatusAndOrgiAndOrgid(boolean b, String orgi, String orgid,
			Pageable pageRequest);
	
	
	public abstract List<User> findByOrgiAndDatastatusAndOrgid(String orgi, boolean b, String orgid);
	
	
	public abstract Page<User> findByDatastatusAndOrgiAndOrgidAndUsernameLike(boolean b, String orgi, String orgid,
			String string, Pageable pageRequest);


	public abstract Page<User> findByOrganInAndAgentAndDatastatus(List<String> organIdList, boolean b,boolean status,Pageable pageRequest);

	public abstract List<User> findByOrganInAndAgentAndDatastatus(List<String> organIdList, boolean b,boolean status);
	
	public abstract List<User> findByIdInAndOrgiAndDatastatus(List<String> usersids, String orgi,boolean b);


	public abstract List<User> findByIdInAndOrganInAndDatastatus(List<String> usersids, List<String> organIdList,boolean status);


	public abstract Page<User> findByOrganInAndDatastatus(List<String> organIdList, boolean b, Pageable pageRequest);

	public abstract List<User> findByOrganInAndDatastatus(List<String> organIdList, boolean b);
	
	public abstract Page<User> findByOrgiAndDatastatus(String orgi, boolean b, Pageable pageRequest);


	public abstract Page<User> findByOrganInAndDatastatusAndUsernameLike(List<String> organIdList, boolean b,
			String username, Pageable pageRequest);


	public abstract Page<User> findByOrganInAndAgentAndDatastatusAndUsertypeIsNull(List<String> organIdList, boolean agent,
			boolean datastatus, Pageable pageRequest);


	public abstract Page<User> findByOrgiAndAgentAndDatastatusAndUsertypeIsNull(String orgi, boolean agent, boolean datastatus,
			Pageable pageRequest);
	
	public abstract Page<User> findByOrgidAndAgentAndDatastatusAndUsertype(String orgid, boolean agent, boolean datastatus,String type,
			Pageable pageRequest);


	public abstract List<User> findByOrgidAndAgentAndDatastatus(String orgid, boolean agent, boolean datastatus);
	
	public abstract List<User> findByOrgiAndOrganAndDatastatus(String orgi, String organ, boolean datastatus);
	
	public abstract List<User> findByOrgiAndCallcenterAndDatastatusAndOrgan(String orgi, boolean callcenter, boolean datastatus,String organ);
	
	public abstract List<User> findByOrgiAndCallcenterAndDatastatus(String orgi, boolean callcenter, boolean datastatus);
	
	public abstract Page<User> findByDatastatusAndOrganAndOrgi(boolean b,String organ, String orgi, Pageable pageRequest);
}
