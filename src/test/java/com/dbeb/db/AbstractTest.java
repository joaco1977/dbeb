package com.dbeb.db;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;

import com.asimplemodule.dbeb.util.HibernateUtil;

public abstract class AbstractTest {
	
	
	Session session;
	
	@Before
	public void init() {
		
		session = HibernateUtil.getSession();
		session.beginTransaction();
	}
	
	
	@After
	public void clear() {
		session.getTransaction().rollback();
		HibernateUtil.closeSession();
	}
	
}
