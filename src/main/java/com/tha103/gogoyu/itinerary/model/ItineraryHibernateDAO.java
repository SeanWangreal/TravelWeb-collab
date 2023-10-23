package com.tha103.gogoyu.itinerary.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class ItineraryHibernateDAO implements ItineraryDAO_interface {
	private SessionFactory factory;
	
	public  ItineraryHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(Itinerary Itinerary) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(Itinerary);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Itinerary Itinerary) {
		try {
			getSession().beginTransaction();
			getSession().update(Itinerary);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer itineraryId) {
		try {
			getSession().beginTransaction();
			Itinerary emp = getSession().get(Itinerary.class, itineraryId);
			if (emp != null) {
				getSession().delete(emp);
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
	public Itinerary findByPK(Integer itineraryId) {
		try {
			getSession().beginTransaction();
			Itinerary itinerary = getSession().get(Itinerary.class, itineraryId);
			getSession().getTransaction().commit();
			return itinerary;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Itinerary> getAll() {
		try {
			getSession().beginTransaction();
			List<Itinerary> list = getSession().createQuery("from Itinerary", Itinerary.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Itinerary> getAllByTripId(Integer tripId) {
		try {
			getSession().beginTransaction();
			List<Itinerary> list = getSession().createQuery("from Itinerary where trip_id = :tripId order by begin_time", Itinerary.class)
					.setParameter("tripId", tripId)
					.list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public void deleteAllByTripId(Integer tripId) {
		try {
			getSession().beginTransaction();
			List<Itinerary> list = getSession().createQuery("from Itinerary where trip_id = :tripId order by begin_time", Itinerary.class)
					.setParameter("tripId", tripId)
					.list();
			for (Itinerary li : list) {
				getSession().delete(li);				
			}
			getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
	}
	public static void main(String[] args) {
		ItineraryHibernateDAO dao = new ItineraryHibernateDAO(HibernateUtil.getSessionFactory());
//		System.out.println(dao.getAllByTripId(8));
		dao.deleteAllByTripId(8);
	}

	@Override
	public void deleteAllByTripIdAndAdd(Integer tripId, List<Itinerary> ItineraryList) {
		try {
			getSession().beginTransaction();
			List<Itinerary> list = getSession().createQuery("from Itinerary where trip_id = :tripId order by begin_time", Itinerary.class)
					.setParameter("tripId", tripId)
					.list();
			for (Itinerary li : list) {
				getSession().delete(li);				
			}
			for (int i = 0;i<ItineraryList.size();i++) {
				getSession().save(ItineraryList.get(i));
			}
			getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		
	}
}
