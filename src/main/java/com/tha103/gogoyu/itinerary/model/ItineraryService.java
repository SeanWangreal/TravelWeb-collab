package com.tha103.gogoyu.itinerary.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface ItineraryService {
	public int add(Integer tripId, Integer sceneId, String sceneName, Timestamp beginTime);

	public int update(Integer itineraryId, Integer tripId, Integer sceneId, String sceneName, Timestamp beginTime);

	public void delete(Integer itineraryId);

	public Itinerary getOneItinerary(Integer itineraryId);

	public void deleteAllByTripIdAndAdd(Integer tripId, List<Integer> sceneIdList, List<String> sceneNameList,
			List<Timestamp> beginTimeList);

	public List<Itinerary> getAll();

	public List<Itinerary> getAllByTripId(Integer tripId);
}
