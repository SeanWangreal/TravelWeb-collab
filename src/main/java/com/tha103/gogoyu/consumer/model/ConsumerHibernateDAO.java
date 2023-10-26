package com.tha103.gogoyu.consumer.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SharedSessionContract;

import com.tha103.gogoyu.trip_ord.model.Trip_ord;

import util.HibernateUtil;

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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(consumer);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Consumer consumer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(consumer);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer cusId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Consumer consumer = session.get(Consumer.class, cusId);
			if (consumer != null) {
				session.delete(consumer);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	@Override
	public Consumer findByPK(Integer cusId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Consumer consumer = session.get(Consumer.class, cusId);
			session.getTransaction().commit();
			return consumer;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Consumer> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Consumer> list = session.createQuery("from Consumer", Consumer.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
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
		
		
		
			
		
		

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			byte[] cusPhoto = session.createQuery("select cusPhoto from Consumer where cus_id = :cus_id", byte[].class)
					.setParameter("cus_id", cusId).uniqueResult();
			session.getTransaction().commit();
			return cusPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;


	}

	@Override
	public List<Consumer> getCusAccount(String cusAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Consumer> list = session
					.createQuery("from Consumer where cus_account = :cusAccount", Consumer.class)
					.setParameter("cusAccount", cusAccount).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	@Override
	public List<Consumer> getCusPassword(String cusPassword) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Consumer> list = session
					.createQuery("from Consumer where cus_password = :cusPassword", Consumer.class)
					.setParameter("cusPassword", cusPassword).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	

}

