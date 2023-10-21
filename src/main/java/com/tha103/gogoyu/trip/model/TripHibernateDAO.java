package com.tha103.gogoyu.trip.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.scene.model.Scene;

import util.HibernateUtil;

public class TripHibernateDAO implements TripDAO_interface{
	@Override
	public int add(Trip trip_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(trip_id);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Trip Trip) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(Trip);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer tripVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip trip = session.get(Trip.class, tripVO);
			if (trip != null) {
				session.delete(trip);
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
	public Trip findByPK(Integer trip_VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip trip = session.get(Trip.class, trip_VO);
			session.getTransaction().commit();
			return trip;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Trip> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Trip> list = session.createQuery("from trip", Trip.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	public List<Trip> getHotTrip() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Trip> query1 = session.createNativeQuery("SELECT * FROM trip WHERE trip_id IN (SELECT trip_id FROM (SELECT trip_id, count(trip_id) FROM trip_ord GROUP BY trip_id ORDER BY 2 DESC LIMIT 3) as xxx);", Trip.class);
			List<Trip> list = query1.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
