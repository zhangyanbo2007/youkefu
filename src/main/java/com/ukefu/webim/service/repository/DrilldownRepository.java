package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.DrillDown;

public abstract interface DrilldownRepository extends JpaRepository<DrillDown, String> {

}
