package com.tha103.gogoyu.consumer.model;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.planning.model.Planning;

import util.HibernateUtil;

public class ConsumerHibernateDAO implements ConsumerDAO_interface {

	private SessionFactory factory;

	public ConsumerHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}




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
	public byte[] getPicture(Integer cusId) {
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
	public Consumer getCusAccount(String cusAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Consumer consumer = session
					.createQuery("from Consumer where cus_account = :cusAccount", Consumer.class)
					.setParameter("cusAccount", cusAccount).uniqueResult();
			session.getTransaction().commit();
			return consumer;
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

	public void updFromBackend(Integer cusId, String cusName, String cusAccount, String cusMail, String cusPhone,
			String cusAddress, Integer cusSex) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query=session.createQuery("update Consumer set cusName=?0, cusAccount=?1, cusMail=?2, cusPhone=?3, "
					+ "cusAddress=?4, cusSex=?5 where cusId=?6");
			query.setParameter(0, cusName);
			query.setParameter(1, cusAccount);
			query.setParameter(2, cusMail);
			query.setParameter(3, cusPhone);
			query.setParameter(4, cusAddress);
			query.setParameter(5, cusSex);
			query.setParameter(6, cusId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	public static void main(String[] args) {
		ConsumerHibernateDAO dao = new ConsumerHibernateDAO(HibernateUtil.getSessionFactory());
		dao.findByPK(10);
	}






}

