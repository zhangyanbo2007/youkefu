package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.User;

public interface BaseRepository extends JpaRepository<User,String>{
}
