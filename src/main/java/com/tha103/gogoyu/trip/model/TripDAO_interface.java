package com.tha103.gogoyu.trip.model;

import java.util.List;
import java.util.Set;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;


public interface TripDAO_interface {
	int add(Trip Trip);
	int update(Trip Trip);
	int delete(Integer tripId);
	Trip findByPK(Integer tripId);
	List<Trip> findTripByCompId(Integer compId);
	List<Trip> getAll();
	Set<Trip_photo> getAllPhotoByTripId(Integer tripId);
	Set<Itinerary> getItineraryByTripId(Integer tripId);
	int deleteAllPhoto(Integer tripId);
	byte[] getMainPhoto(Integer tripId);

}
