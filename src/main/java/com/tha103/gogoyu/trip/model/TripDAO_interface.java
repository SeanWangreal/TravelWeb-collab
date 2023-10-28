package com.tha103.gogoyu.trip.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;

public interface TripDAO_interface {
	int add(Trip Trip,LinkedList<byte[]> allPhoto,List<Itinerary> itineraryList);
	int update(Trip Trip);
	int delete(Integer tripId);
	Trip findByPK(Integer tripId);
	List<Trip> findTripByCompId(Integer compId);
	List<Trip> getAll();
	Set<Trip_photo> getAllPhotoByTripId(Integer tripId);
	Set<Itinerary> getItineraryByTripId(Integer tripId);
	int deleteAllPhoto(Integer tripId);
	byte[] getMainPhoto(Integer tripId);
	public Integer updateAmount(Integer amount , Integer tripId) ;
	List<Trip> searchTrip(String site, Date startTime, Date endTime, Integer number);
}
