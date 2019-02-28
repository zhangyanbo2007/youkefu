package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmKnowledgeTypeAuth;

public abstract interface EkmKnowledgeTypeAuthRepository  extends JpaRepository<EkmKnowledgeTypeAuth, String>
{
	//查询我的分类按钮授权
	public abstract EkmKnowledgeTypeAuth findByUseridAndKnowledgetypeidAndOrgi(String userid, String Knowledgetypeid ,String orgi );
	
	public abstract List<EkmKnowledgeTypeAuth> findByKnowledgetypeidAndOrgi(String knowledgetypeid,String orgi );
	
	public abstract List<EkmKnowledgeTypeAuth> findByUseridAndOrgi(String userid,String orgi );
	
	public abstract List<EkmKnowledgeTypeAuth> findByUseridAndKnowledgebaseidAndViewAndOrgi(String userid, String knowledgebaseid,boolean view,String orgi );
	
	public abstract EkmKnowledgeTypeAuth findByIdAndOrgi(String id , String orgi );
	
	public abstract List<EkmKnowledgeTypeAuth> findByUseridAndViewAndOrgi(String userid,boolean view,String orgi );
	
	//取消部门授权知识库时，同时删除该部门下的人员，对该知识库下的知识分类授权记录
	public abstract List<EkmKnowledgeTypeAuth> findByUseridAndKnowledgebaseidAndOrgi(String userid, String knowledgebaseid,String orgi );
	
	public abstract List<EkmKnowledgeTypeAuth> findByKnowledgebaseidAndOrgi(String knowledgebaseid,String orgi );
	
	public abstract List<EkmKnowledgeTypeAuth> findByOrgi(String orgi );
	
	public abstract List<EkmKnowledgeTypeAuth> findByKnowledgetypeidAndKnowledgebaseidAndOrgi(String knowledgetypeid, String Knowledgebaseid,String orgi );
}

