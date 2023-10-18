package com.tha103.gogoyu.trip.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface TripService {
	public Trip addTrip(Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Timestamp startTime, Timestamp endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto) ;
	
	public Trip updateTrip(Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Timestamp startTime, Timestamp endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto) ;
	
	public void delete(Integer tripId);
	public Trip getOne(Integer tripId);
	public List<Trip> getAll();
}
