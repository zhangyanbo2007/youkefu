package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.StatusEventSatisf;

public interface StatusEventSatisfRepository extends JpaRepository<StatusEventSatisf, String> {

	public StatusEventSatisf findById(String id);
}
