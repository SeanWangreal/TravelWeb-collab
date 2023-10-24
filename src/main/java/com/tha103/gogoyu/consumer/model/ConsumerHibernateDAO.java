package com.tha103.gogoyu.consumer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.trip_ord.model.Trip_ord;

import util.HibernateUtil;
import util.Util;

public class ConsumerHibernateDAO implements ConsumerDAO_interface {
	// SessionFactory �� thread-safe嚗��臬恐���箏惇�扯�隢�瘙��瑁�蝺����梁��
	private SessionFactory factory;

	public ConsumerHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}



	// Session �� not thread-safe嚗���隞交迨�寞��典����憓��芣�寞�交�寞�鋆∪�澆��
	// 隞仿�踹��隢�瘙��瑁�蝺��梁�其����� Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(Consumer consumer) {
		return (Integer) getSession().save(consumer);
	}

	@Override
	public int update(Consumer consumer) {
		try {
			getSession().update(consumer);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer cusId) {
		Consumer cus = getSession().get(Consumer.class, cusId);
		if (cus != null) {
			getSession().delete(cus);
			return 1;
		} else {
			return -1;
		}

	}

	@Override
	public Consumer findByPK(Integer cusId) {
		return getSession().get(Consumer.class, cusId);
	}

	@Override
	public List<Consumer> getAll() {
		return getSession().createQuery("from consumer", Consumer.class).list();
	}

	@Override
	public byte[] getPicture(Integer cusId) throws Exception {
		
		try {
			getSession().beginTransaction();
			Consumer consumer = getSession().get(Consumer.class, cusId);	
			getSession().getTransaction().commit();
			return consumer.getCusPhoto();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
		
		
		
			
		
		
	}


