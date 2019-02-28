package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SNSAccount;

public abstract interface SNSAccountRepository
  extends JpaRepository<SNSAccount, String>
{
  public abstract SNSAccount findByIdAndOrgi(String paramString, String orgi);
  
  public abstract SNSAccount findBySnsid(String snsid);
  
  public abstract SNSAccount findBySnsidAndOrgi(String snsid, String orgi);
  
  public abstract int countByAppkeyAndOrgi(String appkey, String orgi);
  
  public abstract int countBySnsidAndOrgi(String snsid, String orgi);
  
  public abstract List<SNSAccount> findBySnstypeAndOrgi(String paramString , String orgi);
  
  public abstract List<SNSAccount> findBySnstype(String snsType);
  
  public abstract Page<SNSAccount> findBySnstypeAndOrgi(String paramString ,String orgi, Pageable page);
}
