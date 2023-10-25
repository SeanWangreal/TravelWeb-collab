package com.tha103.gogoyu.consumer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.company.model.Company;

import util.HibernateUtil;
import util.Util;

public class ConsumerHibernateDAO implements ConsumerDAO_interface {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ConsumerHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}



	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
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
		try {
			getSession().beginTransaction();
			Consumer cus= getSession().get(Consumer.class, cusId);
			getSession().getTransaction().commit();
			return cus;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Consumer> getAll() {
		return getSession().createQuery("from consumer", Consumer.class).list();
	}

	@Override
	public byte[] getPicture(Integer cusId) throws Exception {
		Consumer consumer = getSession().get(Consumer.class, cusId);
	    if (consumer != null) {
	        return consumer.getCusPhoto(); // 假设你的 Consumer 类有名为 getPicture 的方法用于获取图片数据
	    } else {
	        throw new Exception("Consumer not found");
	    }

	}

}
