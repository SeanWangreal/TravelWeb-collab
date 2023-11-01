package com.tha103.gogoyu.trip.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.scene.model.Scene;
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
	
	public Map<Trip, String> searchTrip(String site, Date startTime, Date endTime, Integer number) {
		Map<Trip, String> map = new LinkedHashMap<Trip, String>();
		try {
			String SQL = null;
			getSession().beginTransaction();
//			@SuppressWarnings("unchecked")
			switch (site) {
			case "台北市":SQL ="Taipei_City";break;
			case "新北市":SQL ="NewTaipei_City";break;
			case "桃園市":SQL ="Taoyuan_City";break;
			case "台中市":SQL ="Taichung_City";break;
			case "台南市":SQL ="Tainan_City";break;
			case "高雄市":SQL ="Kaohsiung_City";break;
			case "新竹縣":SQL ="Hsinchu_County";break;
			case "苗栗縣":SQL ="Miaoli_County";break;
			case "彰化縣":SQL ="Changhua_County";break;
			case "南投縣":SQL ="Nantou_County";break;
			case "雲林縣":SQL ="Yunlin_County";break;
			case "嘉義縣":SQL ="Chiayi_County";break;
			case "屏東縣":SQL ="Pingtung_County";break;
			case "宜蘭市":SQL ="Yilan_City";break;
			case "花蓮市":SQL ="Hualien_City";break;
			case "台東縣":SQL ="Taitung_County";break;
			case "金門縣":SQL ="Kinmen_County";break;
			case "連江縣":SQL ="Lienchiang_County";break;
			case "基隆市":SQL ="Keelung_City";break;
			case "新竹市":SQL ="Hsinchu_City";break;
			case "嘉義市":SQL ="Chiayi_City";break;
			case "澎湖縣":SQL ="Penghu_County";break;
			}
			List<Trip> tripList = new ArrayList<Trip>();
			tripList = getSession().createQuery("from Trip where " 
			+ SQL 
			+ " = 1 AND start_time >= :startTime AND end_time <= :endTime AND people >= :number AND amount > 0 AND state = 1", Trip.class)
					.setParameter("startTime", startTime)
					.setParameter("endTime", endTime)
					.setParameter("number", number)
					.list();
			
			for(Trip trip : tripList) {
				Double avgScore = getSession()
					      .createQuery("select avg(score) from Trip_ord where tripId = :tripId ", Double.class)
					      .setParameter("tripId", trip.getTripId()).uniqueResult();
				 if (avgScore != null) {
				     Double averageScore = Math.round(avgScore * 10.0) / 10.0;
				     map.put(trip, averageScore.toString());
				    } else {
				     map.put(trip, "N/A");
				    }
			}
			
			getSession().getTransaction().commit();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return map;
	}
	
	public List<Trip> getHotTrip() {
		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Trip> query1 = getSession().createNativeQuery(
					"SELECT * FROM trip WHERE trip_id IN (SELECT trip_id FROM (SELECT trip_id, count(trip_id) FROM trip_ord GROUP BY trip_id ORDER BY 2 DESC LIMIT 3) as xxx);",
					Trip.class);
			List<Trip> list = query1.list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	public List<Object> getTripProdutDetail(Integer tripId) {
		try {
			getSession().beginTransaction();
			
			Trip trip = getSession().get(Trip.class, tripId);
			
			List<Integer> tripPhotoIdList = getSession().createQuery("select tripPhotoId from Trip_photo where trip_id =:trip_id", Integer.class)
					.setParameter("trip_id", tripId).list();
			List<Itinerary> itineraryList = getSession().createQuery("from Itinerary where trip_id =:trip_id", Itinerary.class)
					.setParameter("trip_id", tripId).list();
			List<Scene> sceneList = new ArrayList<Scene>();
			for(Itinerary itinerary : itineraryList) {
				Integer SceneId = itinerary.getSceneId();
				Scene scene = getSession().get(Scene.class, SceneId);
				sceneList.add(scene);
			}
			Integer compId = trip.getCompId();
			Company company = getSession().get(Company.class, compId);
			
			List<String> site = new ArrayList<String>();
			if(trip.getTaipeiCity() == (byte)1) {
				site.add("台北市");
			}
			if(trip.getNewtaipeiCity() == (byte)1) {
				site.add("新北市");
			}
			if(trip.getTaoyuanCity() == (byte)1) {
				site.add("桃園市");
			}
			if(trip.getTaichungCity() == (byte)1) {
				site.add("台中市");
			}
			if(trip.getTainanCity() == (byte)1) {
				site.add("台南市");
			}
			if(trip.getKaohsiungCity() == (byte)1) {
				site.add("高雄市");
			}
			if(trip.getHsinchuCounty() == (byte)1) {
				site.add("新竹縣");
			}
			if(trip.getMiaoliCounty() == (byte)1) {
				site.add("苗栗縣");
			}
			if(trip.getChanghuaCounty() == (byte)1) {
				site.add("彰化縣");
			}
			if(trip.getNantouCounty() == (byte)1) {
				site.add("南投縣");
			}
			if(trip.getYunlinCounty() == (byte)1) {
				site.add("雲林縣");
			}
			if(trip.getChiayiCounty() == (byte)1) {
				site.add("嘉義縣");
			}
			if(trip.getPingtungCounty() == (byte)1) {
				site.add("屏東縣");
			}
			if(trip.getYilanCity() == (byte)1) {
				site.add("宜蘭市");
			}
			if(trip.getHualienCity() == (byte)1) {
				site.add("花蓮市");
			}
			if(trip.getTaitungCounty() == (byte)1) {
				site.add("台東縣");
			}
			if(trip.getKinmenCounty() == (byte)1) {
				site.add("金門縣");
			}
			if(trip.getLienchiangCounty() == (byte)1) {
				site.add("連江縣");
			}
			if(trip.getKeelungCity() == (byte)1) {
				site.add("基隆市");
			}
			if(trip.getHsinchuCity() == (byte)1) {
				site.add("新竹市");
			}
			if(trip.getChiayiCity() == (byte)1) {
				site.add("嘉義市");
			}
			if(trip.getPenghuCounty() == (byte)1) {
				site.add("澎湖縣");
			}
			
			List<Object> list = new ArrayList<Object>();
			list.add(trip);
			list.add(tripPhotoIdList);
			list.add(itineraryList);
			list.add(sceneList);
			list.add(company);
			list.add(site);
			
			getSession().getTransaction().commit();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	public List<Scene> scenesMaps(Integer tripId){
		List<Scene> sceneList = new ArrayList<Scene>();
		try {
			getSession().beginTransaction();
			List<Itinerary> itineraryList = getSession().createQuery("from Itinerary where trip_id =:trip_id", Itinerary.class)
					.setParameter("trip_id", tripId).list();
			
			for(Itinerary itinerary : itineraryList) {
				Integer SceneId = itinerary.getSceneId();
				Scene scene = getSession().get(Scene.class, SceneId);
				sceneList.add(scene);
			}
			getSession().getTransaction().commit();
			return sceneList;
		} catch(Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	public static void main (String[] args ) {
//		String site = "新北市";
//		Date startTime = java.sql.Date.valueOf("2023-10-04");
//		Date endTime = java.sql.Date.valueOf("2023-10-05");
//		Integer number = 1;
//		TripServiceHibernate svc = new TripServiceHibernate();
//		List<Trip> list = svc.searchTrip(site, startTime, endTime, number);
//		for(Trip trip : list) {
//			System.out.println(trip.getTripName());
//		}
		
	}
}
