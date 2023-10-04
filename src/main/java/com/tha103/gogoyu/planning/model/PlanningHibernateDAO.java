package com.tha103.gogoyu.planning.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;


public class PlanningHibernateDAO implements PlanningDAO_interface {

	@Override
	public int add(Planning planning) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(planning);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Planning planning) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(planning);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer planId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Planning planning = session.get(Planning.class, planId);
			if (planning != null) {
				session.delete(planning);
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
	public Planning findByPK(Integer planId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Planning planning = session.get(Planning.class, planId);
			session.getTransaction().commit();
			return planning;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Planning> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Planning> list = session.createQuery("from Planning", Planning.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	public static void main(String[] args) {
		PlanningHibernateDAO dao = new PlanningHibernateDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
//		// 新增
//		Planning planning01 = new Planning();
//		planning01.setCusId(2);
//		planning01.setPlanName("測試新增2");
//		dao.add(planning01);

//		// 修改
//		Planning planning02 = new Planning();
//		planning02.setPlanId(6);
//		planning02.setCusId(1);
//		planning02.setPlanName("測試修改1");	
//		dao.update(planning02);

//		// 刪除
//		dao.delete(7);

//		// 查詢單筆
//		Planning planning03 = dao.findByPK(1);
//		System.out.print(planning03.getPlanId() + ",");
//		System.out.print(planning03.getCusId() + ",");
//		System.out.println(planning03.getPlanName());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Planning> list = dao.getAll();
//		for(Planning aPlanning : list) {
//			System.out.print(aPlanning.getPlanId() + ",");
//			System.out.print(aPlanning.getCusId() + ",");
//			System.out.print(aPlanning.getPlanName());
//			System.out.println();
//		}
	}
}
