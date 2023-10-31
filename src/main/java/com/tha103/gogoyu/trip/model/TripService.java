package com.tha103.gogoyu.trip.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.scene.model.Scene;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;

public interface TripService {
	public Trip addTrip(Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Date startTime, Date endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto,LinkedList<byte[]> allPhoto,List<Itinerary> itineraryList) ;
	
	public Trip updateTrip(Integer tripId,Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Date startTime, Date endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto) ;
	public void deleteTrip(Integer tripId);
	public Trip getOneTrip(Integer tripId);
	public List<Trip> getAll();
	public List<Trip> getTripByCompId(Integer compId);
	public byte[] getMainPhoto(Integer roomId);
	public Set<Trip_photo> getAllPhoto(Integer tripId);
	public Trip updateStatus(Integer tripid, Integer status);
	public Set<Itinerary> getItineraryByTripId(Integer tripId);
	int deleteAllPhoto(Integer tripId);
	public Map<Trip, String> searchTrip(String site, Date startTime, Date endTime, Integer number);
	public List<Trip> getHotTrip();
	public List<Object> getTripProdutDetail(Integer tripId);
	public List<Scene> scenesMaps(Integer tripId);
}
