package com.tha103.gogoyu.trip.model;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;


public class TripHibernateDAO implements TripDAO_interface{
	private SessionFactory factory;

	public TripHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(Trip trip) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(trip);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Trip trip) {
		try {
			getSession().beginTransaction();
			getSession().update(trip);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer tripId) {
		try {
			getSession().beginTransaction();
			Trip trip = getSession().get(Trip.class, tripId);
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
	public Trip findByPK(Integer tripId) {
		try {
			getSession().beginTransaction();
			Trip trip = getSession().get(Trip.class, tripId);
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
			List<Trip> list = getSession().createQuery("from Trip", Trip.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public Set<Trip_photo> getAllPhotoByTripId(Integer tripId) {
		try {
			getSession().beginTransaction();
			Trip trip = getSession().get(Trip.class, tripId);
			Set<Trip_photo> set = trip.getTripPhoto();
			for (Trip_photo tp: set) {
				getSession().get(Trip_photo.class, tp.getTripPhotoId());
			}
			getSession().getTransaction().commit();
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Trip> findTripByCompId(Integer compId) {
		try {
			getSession().beginTransaction();
			List<Trip> list = getSession()
					.createQuery("from Trip where comp_id = :comp_id order by state desc, trip_id desc", Trip.class)
					.setParameter("comp_id", compId).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public int deleteAllPhoto(Integer tripId) {
		try {
			getSession().beginTransaction();
			Trip trip = getSession().get(Trip.class, tripId);
			for (Trip_photo p : trip.getTripPhoto()) {
				getSession().delete(p);
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
	public byte[] getMainPhoto(Integer tripId) {
		try {
			getSession().beginTransaction();
			byte[] mainPhoto = getSession().createQuery("select mainPhoto from Trip where trip_id = :trip_id",byte[].class)
					.setParameter("trip_id", tripId)
					.uniqueResult();
			getSession().getTransaction().commit();
			return mainPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public Set<Itinerary> getItineraryByTripId(Integer tripId) {
		try {
			getSession().beginTransaction();
			Trip trip = getSession().get(Trip.class, tripId);
			Set<Itinerary> set = trip.getItinerary();
			for (Itinerary it: set) {
				getSession().get(Itinerary.class, it.getItineraryId());
			}
			getSession().getTransaction().commit();
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

}
