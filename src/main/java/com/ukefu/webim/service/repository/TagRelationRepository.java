package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.TagRelation;

public abstract interface TagRelationRepository  extends JpaRepository<TagRelation, String>
{
	
	public abstract TagRelation findByUseridAndTagid(String userid , String tagid);
	
	public abstract List<TagRelation> findByUserid(String userid);
}

