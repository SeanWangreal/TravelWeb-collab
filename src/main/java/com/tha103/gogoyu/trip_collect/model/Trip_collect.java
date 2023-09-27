package com.tha103.gogoyu.trip_collect.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Trip_collect implements Serializable{
	private Integer cus_id;
	private Integer trip_id;
	private Timestamp collect_time;
	public Trip_collect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trip_collect(Integer cus_id, Integer trip_id, Timestamp collect_time) {
		super();
		this.cus_id = cus_id;
		this.trip_id = trip_id;
		this.collect_time = collect_time;
	}
	
	public Integer getCus_id() {
		return cus_id;
	}
	
	public void setCus_id(Integer cus_id) {
		this.cus_id = cus_id;
	}
	
	public Integer getTrip_id() {
		return trip_id;
	}
	
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}

	public Timestamp getCollect_time() {
		return collect_time;
	}

	public void setCollect_time(Timestamp collect_time) {
		this.collect_time = collect_time;
	}
	
}