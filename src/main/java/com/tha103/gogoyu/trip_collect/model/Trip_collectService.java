package com.tha103.gogoyu.trip_collect.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Trip_collectService {

	private Trip_collectDAO_interface dao;

	public Trip_collectService() {
		dao = new Trip_collectHibernateDAO();
	}
	
	private Date date = new Date();
	private Timestamp time_s = new Timestamp(date.getTime());

	public Trip_collect addTripCollect(Integer cusId, Integer tripId) {

		Trip_collect trip_collect = new Trip_collect();

		trip_collect.setCusId(cusId);
		trip_collect.setTripId(tripId);
		trip_collect.setCollectTime(time_s);
		dao.insert(trip_collect);

		return trip_collect;
	}

	public Trip_collect updateTripCollect(Integer cusId, Integer tripId) {

		Trip_collect trip_collect = new Trip_collect();

		trip_collect.setCusId(cusId);
		trip_collect.setTripId(tripId);
		trip_collect.setCollectTime(time_s);
		dao.update(trip_collect);

		return trip_collect;
	}

	public void deleteTripCollect(Integer cus_id, Integer trip_id) {
		dao.delete(cus_id, trip_id);
	}

	public Trip_collect getOneTripCollect(Integer cus_id, Integer trip_id) {
		return dao.findByPrimaryKey(cus_id, trip_id);
	}

	public List<Trip_collect> getAll() {
		return dao.getAll();
	}
}
