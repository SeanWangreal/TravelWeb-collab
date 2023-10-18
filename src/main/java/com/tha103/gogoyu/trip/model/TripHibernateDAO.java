package com.tha103.gogoyu.trip.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.scene.model.Scene;

import util.HibernateUtil;

public class TripHibernateDAO implements TripDAO_interface{
	private SessionFactory factory;

	public TripHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	// Session �� not thread-safe嚗���隞交迨�寞��典����憓��芣�寞�交�寞�鋆∪�澆��
	// 隞仿�踹��隢�瘙��瑁�蝺��梁�其����� Session
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(Trip trip_id) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(trip_id);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Trip Trip) {
		try {
			getSession().beginTransaction();
			getSession().update(Trip);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer tripVO) {
		try {
			getSession().beginTransaction();
			Trip trip = getSession().get(Trip.class, tripVO);
			if (trip != null) {
				getSession().delete(trip);
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
	public Trip findByPK(Integer trip_VO) {
		try {
			getSession().beginTransaction();
			Trip trip = getSession().get(Trip.class, trip_VO);
			getSession().getTransaction().commit();
			return trip;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Trip> getAll() {
		try {
			getSession().beginTransaction();
			List<Trip> list = getSession().createQuery("from trip", Trip.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

}
