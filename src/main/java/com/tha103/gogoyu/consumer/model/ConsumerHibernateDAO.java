package com.tha103.gogoyu.consumer.model;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class ConsumerHibernateDAO implements ConsumerDAO_interface {

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
	public Consumer findByPrimaryKey(Integer cusId) {
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

	public static void main(String[] args) {
		ConsumerHibernateDAO dao = new ConsumerHibernateDAO();
		
//		新增
//		Consumer consumer = new Consumer();
//		consumer.setCusId("1");
//		consumer.setCusName("1");
//		consumer.setAccount("1");
//		consumer.setAccount("1");
//		consumer.setAccount("1");
//		consumer.setAccount("1");
//		consumer.setAccount("1");
//		consumer.setAccount("1");
//		consumer.setAccount("1");
//		dao.add(admMeb);
		
//		修改
//		Adm_meb admMeb = new Adm_meb();
//		admMeb.setAdmName("2");
//		admMeb.setAdmAccount("2");
//		admMeb.setAdmPassword("2");
//		admMeb.setAdmId(6);
//		dao.update(admMeb);
			
//		刪除
//		dao.delete(6);
		
		// 查詢
//		Consumer consumer = dao.findByPrimaryKey(5);
//		System.out.print(consumer.getCusId() + ",");
//		System.out.print(consumer.getCusName() + ",");
//		System.out.print(consumer.getCusAccount() + ",");
//		System.out.print(consumer.getCusPassword() + ",");
//		System.out.print(consumer.getCusMail() + ",");
//		System.out.print(consumer.getCusPhone() + ",");
//		System.out.print(consumer.getCusAddress() + ",");
//		System.out.print(consumer.getCusSex() + ",");
//		System.out.print(consumer.getCusPhoto() + ",");
			System.out.println("---------------------");
		
			
		List<Consumer> list = dao.getAll();
		for (Consumer consumer : list) {
			System.out.print(consumer.getCusId() + ",");
			System.out.print(consumer.getCusName() + ",");
			System.out.print(consumer.getCusAccount() + ",");
			System.out.print(consumer.getCusPassword() + ",");
			System.out.print(consumer.getCusMail() + ",");
			System.out.print(consumer.getCusPhone() + ",");
			System.out.print(consumer.getCusAddress() + ",");
			System.out.print(consumer.getCusSex() + ",");
			System.out.print(consumer.getCusPhoto() + ",");
		}
		

	}
}
