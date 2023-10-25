package com.tha103.gogoyu.adm_meb.model;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class AdminHibernateDAO implements AdminDAO_interface {

	@Override
	public int add(Admin admMeb) {
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
	public int update(Admin admMeb) {
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
			Admin admMeb = session.get(Admin.class, admId);
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
	public Admin findByPrimaryKey(Integer admId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Admin admMeb = session.get(Admin.class, admId);
			session.getTransaction().commit();
			return admMeb;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Admin> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Admin> list = session.createQuery("from Adm_meb", Admin.class).list();
			session.getTransaction().commit();
			return list;
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
