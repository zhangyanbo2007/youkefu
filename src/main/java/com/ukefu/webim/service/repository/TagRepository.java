package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Tag;

public abstract interface TagRepository  extends JpaRepository<Tag, String>
{
	
	public abstract Tag findByOrgiAndTag(String orgi , String tag);
	
	public abstract int countByOrgiAndTagAndTagtype(String orgi , String tag , String tagtype);
	
	public abstract List<Tag> findByOrgiAndTagAndTagtype(String orgi , String tag , String tagtype);
	
	public abstract Tag findByOrgiAndId(String orgi , String id);
	
	public abstract Page<Tag> findByOrgiAndTagtype(String orgi , String tagtype ,Pageable paramPageable);
	
	public abstract List<Tag> findByOrgi(String orgi);
	
	public abstract List<Tag> findByOrgiAndTagtype(String orgi , String tagtype);
}

