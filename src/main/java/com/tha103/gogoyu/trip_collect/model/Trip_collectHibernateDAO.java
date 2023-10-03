package com.tha103.gogoyu.trip_collect.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

public class Trip_collectHibernateDAO implements Trip_collectDAO_interface{
	@Override
	public int insert(Trip_collect tripCollect) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(tripCollect);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Trip_collect tripCollect) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(tripCollect);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Integer cusId, Integer tripId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip_collect tripCollect = session.get(Trip_collect.class, new Trip_collect.DoublePk(cusId, tripId));
			if (tripCollect != null) {
				session.delete(tripCollect);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Trip_collect findByPrimaryKey(Integer cusId, Integer tripId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip_collect tripCollect = session.get(Trip_collect.class, new Trip_collect.DoublePk(cusId, tripId));
			session.getTransaction().commit();
			return tripCollect;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Trip_collect> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Trip_collect> list = session.createQuery("from Trip_collect", Trip_collect.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Trip_collectHibernateDAO hDao = new Trip_collectHibernateDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
		Trip_collect tripCollect = new Trip_collect(1,2,time_s);
//		System.out.println(hDao.insert(tripCollect));
		System.out.println(hDao.update(tripCollect));
//		System.out.println(hDao.delete(13, 1));
//		System.out.println(hDao.findByPrimaryKey(13, 2));
		System.out.println(hDao.getAll());
	}
}
