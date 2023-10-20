package com.tha103.gogoyu.itinerary.model;

import java.sql.Timestamp;
import java.util.List;

import util.HibernateUtil;

public class ItineraryServiceHibernate implements ItineraryService {
	private ItineraryDAO_interface dao;

	public ItineraryServiceHibernate() {
		dao = new ItineraryHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public int add(Integer tripId, Integer sceneId, String sceneName, Timestamp beginTime) {
		Itinerary itinerary = new Itinerary();
		itinerary.setTripId(tripId);
		itinerary.setSceneId(sceneId);
		itinerary.setSceneName(sceneName);
		itinerary.setBeginTime(beginTime);
		return dao.add(itinerary);
	}

	@Override
	public int update(Integer itineraryId, Integer tripId, Integer sceneId, String sceneName, Timestamp beginTime) {
		Itinerary itinerary =this.getOneItinerary(itineraryId);
		itinerary.setTripId(tripId);
		itinerary.setSceneId(sceneId);
		itinerary.setSceneName(sceneName);
		itinerary.setBeginTime(beginTime);
		return dao.update(itinerary);
	}

	@Override
	public void delete(Integer itineraryId) {
		dao.delete(itineraryId);
	}

	@Override
	public List<Itinerary> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Itinerary> getAllByTripId(Integer tripId) {
		return dao.getAllByTripId(tripId);
	}

	@Override
	public Itinerary getOneItinerary(Integer itineraryId) {
		return dao.findByPK(itineraryId);
	}

	@Override
	public void deleteAllByTripId(Integer tripId) {
		dao.deleteAllByTripId(tripId);
	}

}
