package com.tha103.gogoyu.itinerary.model;

import java.sql.Timestamp;

public class Itinerary implements java.io.Serializable {
	private Integer itinerary_id;
	private Integer trip_id;
	private Integer scene_id;
	private Timestamp begin_time;

	public Itinerary(Integer itinerary_id, Integer trip_id, Integer scene_id, Timestamp begin_time) {
		super();
		this.itinerary_id = itinerary_id;
		this.trip_id = trip_id;
		this.scene_id = scene_id;
		this.begin_time = begin_time;
	}

	public Itinerary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getItinerary_id() {
		return itinerary_id;
	}

	public void setItinerary_id(Integer itinerary_id) {
		this.itinerary_id = itinerary_id;
	}

	public Integer getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}

	public Integer getScene_id() {
		return scene_id;
	}

	public void setScene_id(Integer scene_id) {
		this.scene_id = scene_id;
	}

	public Timestamp getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Timestamp begin_time) {
		this.begin_time = begin_time;
	}

}
