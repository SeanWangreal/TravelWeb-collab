package com.tha103.gogoyu.trip.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.tha103.gogoyu.room.model.RoomService;

import util.HibernateUtil;

public class TripServiceHibernate implements TripService {
	private TripDAO_interface dao;

	public TripServiceHibernate() {
		dao = new TripHibernateDAO(HibernateUtil.getSessionFactory());
	}
	@Override
	public Trip addTrip(Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Timestamp startTime, Timestamp endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto) {

		Trip trip = new Trip();
		trip.setCompId(compId);
		trip.setTripName(tripName);
		trip.setAmount(amount);
		trip.setPrice(price);
		trip.setPeople(people);
		trip.setStartTime(startTime);
		trip.setEndTime(endTime);
		trip.setContent(content);
		trip.setState(state);
		trip.setTaipeiCity(taipeiCity);
		trip.setNewtaipeiCity(newtaipeiCity);
		trip.setTaoyuanCity(taoyuanCity);
		trip.setTaichungCity(taichungCity);
		trip.setTainanCity(tainanCity);
		trip.setKaohsiungCity(kaohsiungCity);
		trip.setHsinchuCounty(hsinchuCounty);
		trip.setMiaoliCounty(miaoliCounty);
		trip.setChanghuaCounty(changhuaCounty);
		trip.setNantouCounty(nantouCounty);
		trip.setYunlinCounty(yunlinCounty);
		trip.setChiayiCounty(chiayiCounty);
		trip.setPingtungCounty(pingtungCounty);
		trip.setYilanCity(yilanCity);
		trip.setHualienCity(hualienCity);
		trip.setTaitungCounty(taitungCounty);
		trip.setKinmenCounty(kinmenCounty);
		trip.setLienchiangCounty(lienchiangCounty);
		trip.setKeelungCity(keelungCity);
		trip.setHsinchuCity(hsinchuCity);
		trip.setChiayiCity(chiayiCity);
		trip.setPenghuCounty(penghuCounty);
		trip.setMainPhoto(mainPhoto);
		dao.add(trip);

		return trip;
	}
	@Override
	public Trip updateTrip(Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Timestamp startTime, Timestamp endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto) {

		Trip trip = new Trip();

		trip.setCompId(compId);
		trip.setTripName(tripName);
		trip.setAmount(amount);
		trip.setPrice(price);
		trip.setPeople(people);
		trip.setStartTime(startTime);
		trip.setEndTime(endTime);
		trip.setContent(content);
		trip.setState(state);
		trip.setTaipeiCity(taipeiCity);
		trip.setNewtaipeiCity(newtaipeiCity);
		trip.setTaoyuanCity(taoyuanCity);
		trip.setTaichungCity(taichungCity);
		trip.setTainanCity(tainanCity);
		trip.setKaohsiungCity(kaohsiungCity);
		trip.setHsinchuCounty(hsinchuCounty);
		trip.setMiaoliCounty(miaoliCounty);
		trip.setChanghuaCounty(changhuaCounty);
		trip.setNantouCounty(nantouCounty);
		trip.setYunlinCounty(yunlinCounty);
		trip.setChiayiCounty(chiayiCounty);
		trip.setPingtungCounty(pingtungCounty);
		trip.setYilanCity(yilanCity);
		trip.setHualienCity(hualienCity);
		trip.setTaitungCounty(taitungCounty);
		trip.setKinmenCounty(kinmenCounty);
		trip.setLienchiangCounty(lienchiangCounty);
		trip.setKeelungCity(keelungCity);
		trip.setHsinchuCity(hsinchuCity);
		trip.setChiayiCity(chiayiCity);
		trip.setPenghuCounty(penghuCounty);
		trip.setMainPhoto(mainPhoto);
		dao.update(trip);

		return trip;
	}
	@Override
	public void delete(Integer tripId) {
		dao.delete(tripId);
	}
	@Override
	public Trip getOne(Integer tripId) {
		return dao.findByPK(tripId);
	}
	@Override
	public List<Trip> getAll() {
		return dao.getAll();
	}
}
