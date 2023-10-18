package com.tha103.gogoyu.planning.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import util.HibernateUtil;


public class PlanningHibernateDAO implements PlanningDAO_interface {
	private SessionFactory factory;
	//透過service帶session的建構子使用多型透過interface呼叫的hibernateDAO
		public PlanningHibernateDAO(SessionFactory factory) {
			this.factory = factory;  //取得sessionfactory並回傳
		}
		
		private Session getSession() {
			return factory.getCurrentSession(); //取得CurrentSession，後續只要呼叫getSession()就可以開始交易
		}
	@Override
	public int add(Planning planning) {
	
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(planning);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Planning planning) {
		
		try {
			getSession().beginTransaction();
			getSession().update(planning);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer planId) {
	
		try {
			getSession().beginTransaction();
			Planning planning = getSession().get(Planning.class, planId);
			if (planning != null) {
				getSession().delete(planning);
			}
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Planning findByPK(Integer planId) {
	
		try {
			getSession().beginTransaction();
			Planning planning = getSession().get(Planning.class, planId);
			getSession().getTransaction().commit();
			return planning;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	
	
	
	@Override
	public Integer findByPlanId(Integer cart_id , Integer cus_id) {
		
		
		try {
				getSession().beginTransaction();
				Query query = getSession().createQuery("select planId from Planning where cartId = :cart_id and cusId = :cus_id");
				query.setParameter("cart_id", cart_id);
				query.setParameter("cus_id", cus_id);
				Integer planId = (Integer) query.uniqueResult();
				
				getSession().getTransaction().commit();
				return planId;
		
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	


	@Override
	public List<Planning> getAll() {
	
		try {
			getSession().beginTransaction();
			List<Planning> list = getSession().createQuery("from Planning", Planning.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		PlanningHibernateDAO dao = new PlanningHibernateDAO();
//		Date date = new Date();
//		Timestamp time_s = new Timestamp(date.getTime());
//		// �啣�
//		Planning planning01 = new Planning();
//		planning01.setCusId(1);
//		planning01.setPlanName("1");
//		planning01.setCartId("1");
//		dao.add(planning01);

//		// 靽格��
//		Planning planning02 = new Planning();
//		planning02.setPlanId(6);
//		planning02.setCusId(1);
//		planning02.setCartId("1");
//		planning02.setPlanName("1");	
//		dao.update(planning02);

//		// �芷��
//		dao.delete(1);

//		// �亥岷�桃�
//		Planning planning03 = dao.findByPK(2);
//		System.out.print(planning03.getPlanId() + ",");
//		System.out.print(planning03.getCusId() + ",");
//		System.out.println(planning03.getPlanName());
//		System.out.println("---------------------");

//		// �亥岷�券��
//		List<Planning> list = dao.getAll();
//		for(Planning aPlanning : list) {
//			System.out.print(aPlanning.getPlanId() + ",");
//			System.out.print(aPlanning.getCusId() + ",");
//			System.out.print(aPlanning.getPlanName());
//			System.out.println();
//		}
	}

