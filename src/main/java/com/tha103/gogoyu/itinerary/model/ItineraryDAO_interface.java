package com.tha103.gogoyu.itinerary.model;

import java.util.List;
import java.util.Map;

public interface ItineraryDAO_interface {
	int add(Itinerary Itinerary);

	int update(Itinerary Itinerary);

	int delete(Integer itinerary_id);

	Itinerary findByPK(Integer itinerary_id);

	List<Itinerary> getAll();
//    List<Itinerary> getByCompositeQuery(Map<String, String> map);
//	List<Itinerary> getAll(int currentPage);
//	long getTotal();
}
