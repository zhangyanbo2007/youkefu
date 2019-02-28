package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Words;

public interface WordsRepository extends JpaRepository<Words, String> {
	
	public abstract List<Words> findByOrgi(String orgi) ;
}
