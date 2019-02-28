package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CallMonitorPerformance;


/*
 * 坐席绩效表 -- JPA接口
 */
public interface CallMonitorPerformanceRepository extends JpaRepository<CallMonitorPerformance, String> {

}
