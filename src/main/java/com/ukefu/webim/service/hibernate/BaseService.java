package com.ukefu.webim.service.hibernate;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService<T> {

	private SessionFactory hibernateFactory;

	@Autowired
	public BaseService(EntityManagerFactory factory) {
		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		this.hibernateFactory = factory.unwrap(SessionFactory.class);
	}
	
	
	public void save(Object t){
		Session session = hibernateFactory.openSession() ; 
		try{
			session.save(t) ;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.flush();
			session.close();	
		}
	}
	
	public void delete(Object object){
		Session session = hibernateFactory.openSession() ; 
		try{
			session.delete(session.merge(object));
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.flush();
			session.close();	
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list(String bean){
		List<T> dataList = null ;
		Session session = hibernateFactory.openSession() ; 
		try{
			dataList = session.createCriteria(Class.forName(bean)).list() ;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.flush();
			session.close();	
		}
		return dataList;
	}
}