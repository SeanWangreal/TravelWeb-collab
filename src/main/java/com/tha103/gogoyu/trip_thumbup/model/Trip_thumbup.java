package com.tha103.gogoyu.trip_thumbup.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Trip_thumbup implements Serializable {
	private Integer trip_ord_id;
	private Integer cus_id;
	private Timestamp thumbup_time;

	public Trip_thumbup() {
		super();
	}

	public Trip_thumbup(Integer trip_ord_id, Integer cus_id, Timestamp thumbup_time) {
		super();
		this.trip_ord_id = trip_ord_id;
		this.cus_id = cus_id;
		this.thumbup_time = thumbup_time;
	}

	public Integer getTrip_ord_id() {
		return trip_ord_id;
	}

	public void setTrip_ord_id(Integer trip_ord_id) {
		this.trip_ord_id = trip_ord_id;
	}

	public Integer getCus_id() {
		return cus_id;
	}

	public void setCus_id(Integer cus_id) {
		this.cus_id = cus_id;
	}

	public Timestamp getThumbup_time() {
		return thumbup_time;
	}

	public void setThumbup_time(Timestamp thumbup_time) {
		this.thumbup_time = thumbup_time;
	}
}
