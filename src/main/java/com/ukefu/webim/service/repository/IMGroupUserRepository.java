package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.IMGroup;
import com.ukefu.webim.web.model.IMGroupUser;
import com.ukefu.webim.web.model.User;

public abstract interface IMGroupUserRepository extends
		JpaRepository<IMGroupUser, String> {
	
	
	public abstract IMGroupUser findByIdAndOrgi(String id , String orgi);

	public List<IMGroupUser> findByImgroupAndOrgi(IMGroup imgroup , String orgi);
	
	public IMGroupUser findByImgroupAndUserAndOrgi(IMGroup imgroup , User user, String orgi);
	
	public List<IMGroupUser> findByUserAndOrgi(User user, String orgi);
	
}
