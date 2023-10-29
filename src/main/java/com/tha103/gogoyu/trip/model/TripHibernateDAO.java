package com.tha103.gogoyu.trip.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;
import com.tha103.gogoyu.trip.model.TripServiceHibernate;


public class TripHibernateDAO implements TripDAO_interface{
	private SessionFactory factory;

	public TripHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(Trip trip,LinkedList<byte[]> allPhoto,List<Itinerary> itineraryList) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(trip);
			for (int i = 0; i < allPhoto.size();i++) {
				Trip_photo tripPhoto = new Trip_photo();
				tripPhoto.setTripId(id);
				tripPhoto.setPhoto(allPhoto.get(i));
				getSession().save(tripPhoto);
			}
			for (Itinerary it: itineraryList) {
				it.setTripId(id);
				getSession().save(it);
			}
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
	
	
	
	public Integer updateAmount(Integer amount , Integer tripId) {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery("update Trip set  amount= :amount  where tripId = :tripId");
			query.setParameter("tripId", tripId);
			query.setParameter("amount", amount);
	

			query.executeUpdate();
			
			getSession().getTransaction().commit();
			return 1 ;
		}catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1 ;
	}
	
	public List<Trip> searchTrip(String site, Date startTime, Date endTime, Integer number) {
		try {
			getSession().beginTransaction();
//			@SuppressWarnings("unchecked")
			NativeQuery<Trip> query1 = getSession().createNativeQuery(
					"SELECT * FROM trip WHERE :site = 1 AND start_time >= :startTime AND end_time <= :endTime AND people <= :number", Trip.class)
			.setParameter("site", site)
			.setParameter("startTime", startTime)
			.setParameter("endTime", endTime)
			.setParameter("number", number);
			List<Trip> list = query1.list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	public static void main (String[] args ) {
		String site = "newTaipei_city";
		Date startTime = java.sql.Date.valueOf("2023-10-04");
		Date endTime = java.sql.Date.valueOf("2023-10-04");
		Integer number = 3;
		TripServiceHibernate svc = new TripServiceHibernate();
		System.out.println(svc.searchTrip(site, startTime, endTime, number));
	}
}
