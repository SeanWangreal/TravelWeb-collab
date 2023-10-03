package com.tha103.gogoyu.trip_thumbup.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;

import util.HibernateUtil;

public class Trip_thumbupHibernateDAO implements Trip_thumbupDAO_interface {
	private static DataSource ds = null;
	
	@Override
	public int insert(Trip_thumbup tripThumbup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(tripThumbup);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Trip_thumbup tripThumbup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(tripThumbup);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Integer tripOrdId, Integer cusId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip_thumbup tripLike = session.get(Trip_thumbup.class, new Trip_thumbup.DoublePk(tripOrdId, cusId));
			session.delete(tripLike);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Trip_thumbup findByPrimaryKey(Integer tripOrdId, Integer cusId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip_thumbup tripLike = session.get(Trip_thumbup.class, new Trip_thumbup.DoublePk(tripOrdId, cusId));
			session.getTransaction().commit();
			return tripLike;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Trip_thumbup> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Trip_thumbup> list = session.createQuery("from Trip_thumbup",Trip_thumbup.class).list();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Trip_thumbupHibernateDAO hDao = new Trip_thumbupHibernateDAO();
		Trip_thumbup tripThumbup = new Trip_thumbup();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
		tripThumbup.setCusId(1);
		tripThumbup.setTripOrdId(13);
		tripThumbup.setThumbupTime(time_s);
		System.out.println(hDao.insert(tripThumbup));
		
	}
}
