package com.tha103.gogoyu.trip_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class Trip_ordHibernateDAO implements Trip_ordDAO_Interface{
	
	@Override
	public void insert(Trip_ord tripOrd) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			Integer id = (Integer) session.save(tripOrd);
			session.save(tripOrd);
			session.getTransaction().commit();
//			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
//		return -1;
	}

	@Override
	public void update(Trip_ord tripOrd) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(tripOrd);
			session.getTransaction().commit();
//			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
//		return -1;
	}

	@Override
	public void delete(Integer tripOrdId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip_ord emp = session.get(Trip_ord.class, tripOrdId);
			if (emp != null) {
				session.delete(emp);
			}
			session.getTransaction().commit();
//			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
//		return -1;
		
	}

	@Override
	public Trip_ord findByPrimaryKey(Integer tripOrdId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip_ord tripOrd = session.get(Trip_ord.class, tripOrdId);
			session.getTransaction().commit();
			return tripOrd;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Trip_ord> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Trip_ord> list = session.createQuery("from Trip_ord", Trip_ord.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Trip_ordDAO_Interface dao=new Trip_ordHibernateDAO();
		Date date = new Date();
		Timestamp nowTime = new Timestamp(date.getTime());
		
		//insert
		Trip_ord tripOrd1 = new Trip_ord();
		
//		//tripOrd1.setTripOrdId(6);
//		tripOrd1.setTripId(306);
//		tripOrd1.setPlanId(206);
//		tripOrd1.setCusId(106);
//		tripOrd1.setAmount(3);
//		tripOrd1.setTotalPrice(new BigDecimal(50000));
//		tripOrd1.setCommission(new BigDecimal(5000));
//		tripOrd1.setOrdStatus(0);
//		tripOrd1.setOrdTime(nowTime);
//		tripOrd1.setRemark("remark_insert");
//		tripOrd1.setScore(10);
//		tripOrd1.setComments("comments_insert");
//		tripOrd1.setCommentsTime(nowTime);
//		dao.insert(tripOrd1);
		
		// update
		Trip_ord tripOrd2 = new Trip_ord();
		
//		tripOrd2.setTripId(306);
//		tripOrd2.setPlanId(206);
//		tripOrd2.setCusId(106);
//		tripOrd2.setAmount(5);
//		tripOrd2.setTotalPrice(new BigDecimal(60000));
//		tripOrd2.setCommission(new BigDecimal(6000));
//		tripOrd2.setOrdStatus(0);
//		tripOrd2.setOrdTime(nowTime);
//		tripOrd2.setRemark("remark_update");
//		tripOrd2.setScore(10);
//		tripOrd2.setComments("comments_update");
//		tripOrd2.setCommentsTime(nowTime);
//		tripOrd2.setTripOrdId(6);
//		dao.update(tripOrd2);
		
		// delete
//		dao.delete(6);
		
		//findByPrimaryKey
//		Trip_ord tripOrd3 = dao.findByPrimaryKey(5);
//		System.out.println("----------------------------------------------------------------");
//		System.out.print(tripOrd3.getTripOrdId() + ", ");
//		System.out.print(tripOrd3.getTripId() + ", ");
//		System.out.print(tripOrd3.getPlanId() + ", ");
//		System.out.print(tripOrd3.getCusId() + ", ");
//		System.out.print(tripOrd3.getAmount() + ", ");
//		System.out.print(tripOrd3.getTotalPrice() + ", ");
//		System.out.print(tripOrd3.getCommission()+", ");
//		System.out.print(tripOrd3.getOrdStatus()+", ");
//		System.out.print(tripOrd3.getOrdTime()+", ");
//		System.out.print(tripOrd3.getRemark()+", ");
//		System.out.print(tripOrd3.getScore()+", ");
//		System.out.print(tripOrd3.getComments()+", ");
//		System.out.println(tripOrd3.getCommentsTime()+", ");
		
		//getAll
		List<Trip_ord> list = dao.getAll();
		for (Trip_ord aTrip : list) {
			System.out.println("----------------------------------------------------------------");
			System.out.print(aTrip.getTripOrdId() + ", ");
			System.out.print(aTrip.getTripId() + ", ");
			System.out.print(aTrip.getPlanId() + ", ");
			System.out.print(aTrip.getCusId() + ", ");
			System.out.print(aTrip.getAmount() + ", ");
			System.out.print(aTrip.getTotalPrice() + ", ");
			System.out.print(aTrip.getCommission()+", ");
			System.out.print(aTrip.getOrdStatus()+", ");
			System.out.print(aTrip.getOrdTime()+", ");
			System.out.print(aTrip.getRemark()+", ");
			System.out.print(aTrip.getScore()+", ");
			System.out.print(aTrip.getComments()+", ");
			System.out.println(aTrip.getCommentsTime()+", ");
		}
	}
}
