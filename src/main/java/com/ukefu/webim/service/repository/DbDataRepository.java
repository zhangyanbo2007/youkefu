package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.User;

/**
 * Bug：DbDataRepository用于序列化 WorkOrders 和 OrdersComment ， 申明的 却是 User , 居然能存的进去？ 参见 MultiUpdateEventHandler.java
 * @author admin
 *
 */
public interface DbDataRepository  extends JpaRepository<User, String> {
	
}
