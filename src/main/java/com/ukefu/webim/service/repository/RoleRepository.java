package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Role;

public abstract interface RoleRepository
  extends JpaRepository<Role, String>
{
  public abstract Role findByIdAndOrgi(String paramString, String orgi);
  
  public abstract List<Role> findByOrgi(String orgi);
  
  public abstract List<Role> findByOrgiAndOrgid(String orgi,String orgid);
  
  public abstract Role findByNameAndOrgi(String paramString, String orgi);
  
  public abstract Role findByNameAndOrgiAndOrgid(String paramString, String orgi,String orgid);
}

