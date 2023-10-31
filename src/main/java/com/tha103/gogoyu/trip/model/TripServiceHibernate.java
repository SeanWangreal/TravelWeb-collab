package com.tha103.gogoyu.trip.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.scene.model.Scene;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;

import util.HibernateUtil;

public class TripServiceHibernate implements TripService {
	private TripDAO_interface dao;

	public TripServiceHibernate() {
		dao = new TripHibernateDAO(HibernateUtil.getSessionFactory());
	}
	@Override
	public Trip addTrip(Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Date startTime, Date endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto,LinkedList<byte[]> allPhoto,List<Itinerary> itineraryList) {
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
		return this.getOneTrip(dao.add(trip, allPhoto, itineraryList));
	}
	@Override
	public Trip updateTrip(Integer tripId,Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Date startTime, Date endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto) {
		Trip trip = this.getOneTrip(tripId);
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
	public void deleteTrip(Integer tripId) {
		dao.delete(tripId);
	}
	@Override
	public Trip getOneTrip(Integer tripId) {
		return dao.findByPK(tripId);
	}
	@Override
	public List<Trip> getAll() {
		return dao.getAll();
	}
	@Override
	public Set<Trip_photo> getAllPhoto(Integer tripId) {
		return dao. getAllPhotoByTripId(tripId);
	}
	@Override
	public Trip updateStatus(Integer tripid, Integer state) {
		Trip trip = this.getOneTrip(tripid);
		trip.setState(state);
		dao.update(trip);
		return trip;
		
	}
	public Trip getTrip(Integer tripid) {

		return dao.findByPK(tripid);
		
	}
	@Override
	public List<Trip> getTripByCompId(Integer compId) {
		return dao.findTripByCompId(compId);
	}
	@Override
	public byte[] getMainPhoto(Integer tripId) {
		return dao.getMainPhoto(tripId);
	}
	
	@Override
	public int deleteAllPhoto(Integer tripId) {
		return dao.deleteAllPhoto(tripId);
	}
	@Override
	public Set<Itinerary> getItineraryByTripId(Integer tripId) {
		return dao.getItineraryByTripId(tripId);
	}
	
	
	
	public void updateAmount(Integer amount , Integer tripId) {
		dao.updateAmount(amount , tripId);
	}
	
	public List<Trip> searchTrip(String site, Date startTime, Date endTime, Integer number){
		return dao.searchTrip(site, startTime, endTime, number);
	}
	
	public List<Trip> getHotTrip(){
		return dao.getHotTrip();
	}
	
	public List<Object> getTripProdutDetail(Integer tripId){
		return dao.getTripProdutDetail(tripId);
	}
	
	public static void main (String[] args ) {

	}
	@Override
	public List<Scene> scenesMaps(Integer tripId) {
		return dao.scenesMaps(tripId);
	}
	
}
