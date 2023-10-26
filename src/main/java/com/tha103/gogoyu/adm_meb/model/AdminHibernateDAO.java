package com.tha103.gogoyu.adm_meb.model;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import util.HibernateUtil;

public class AdminHibernateDAO implements AdminDAO_interface {

	@Override
	public int add(Adm_meb admMeb) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(admMeb);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Adm_meb admMeb) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(admMeb);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer admId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Adm_meb admMeb = session.get(Adm_meb.class, admId);
			if (admMeb != null) {
				session.delete(admMeb);
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
	public Adm_meb findByPrimaryKey(Integer admId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Adm_meb admMeb = session.get(Adm_meb.class, admId);
			session.getTransaction().commit();
			return admMeb;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Adm_meb> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Adm_meb> list = session.createQuery("from Adm_meb", Adm_meb.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public Adm_meb findByAccount(String adm_account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			Adm_meb admMeb = session.get(Adm_meb.class, adm_account);
			Query query = session.createQuery("from Adm_meb where admAccount = ?0", Adm_meb.class)
													.setParameter(0, adm_account);
			Adm_meb admMeb=(Adm_meb)query.getSingleResult();
			session.getTransaction().commit();
			return admMeb;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

//	public static void main(String[] args) {
//			Adm_mebHibernateDAO dao = new Adm_mebHibernateDAO();
		
		//新增
//		Adm_meb admMeb = new Adm_meb();
//		admMeb.setAdmName("1");
//		admMeb.setAdmAccount("1");
//		admMeb.setAdmPassword("1");
//		dao.add(admMeb);
		
		//修改
//		Adm_meb admMeb = new Adm_meb();
//		admMeb.setAdmName("2");
//		admMeb.setAdmAccount("2");
//		admMeb.setAdmPassword("2");
//		admMeb.setAdmId(6);
//		dao.update(admMeb);
			
		//刪除
//		dao.delete(6);
		
		// 查詢
//		Adm_meb admMeb = dao.findByPrimaryKey(5);
//			System.out.print(admMeb.getAdmId() + ",");
//			System.out.print(admMeb.getAdmName() + ",");
//			System.out.print(admMeb.getAdmAccount() + ",");
//			System.out.print(admMeb.getAdmPassword() + ",");
//			System.out.println("---------------------");
		
		// getAll
//		List<Adm_meb> list = dao.getAll();
//		for (Adm_meb admMeb : list) {
//			System.out.print(admMeb.getAdmId() + ",");
//			System.out.print(admMeb.getAdmName() + ",");
//			System.out.print(admMeb.getAdmAccount() + ",");
//			System.out.print(admMeb.getAdmPassword() + ",");
//		}
//	}
	
}
