package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.OnlineUserHis;

public abstract interface OnlineUserHisRepository extends JpaRepository<OnlineUserHis, String>
{
	public abstract OnlineUserHis findByIdAndOrgi(String paramString, String orgi);

	public abstract List<OnlineUserHis> findByUseridAndOrgi(String userid, String orgi);

	public abstract List<OnlineUserHis> findBySessionidAndOrgi(String paramString, String orgi);
}
