package com.tha103.gogoyu.itinerary.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ItineraryDAO_interface {
	int add(Itinerary Itinerary);
	int update(Itinerary Itinerary);
	int delete(Integer itinerary_id);
	void deleteAllByTripId(Integer tripId);
	Itinerary findByPK(Integer itinerary_id);
	List<Itinerary> getAllByTripId(Integer tripId);
	List<Itinerary> getAll();
	void deleteAllByTripIdAndAdd(Integer tripId, List<Itinerary> Itinerary);
	
//    List<Itinerary> getByCompositeQuery(Map<String, String> map);
//	List<Itinerary> getAll(int currentPage);
//	long getTotal();
}
