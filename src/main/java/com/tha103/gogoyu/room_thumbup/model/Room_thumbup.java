package com.tha103.gogoyu.room_thumbup.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Room_thumbup implements Serializable {
	private Integer room_ord_id;
	private Integer cus_id;
	private Timestamp thumbup_time;

	public Room_thumbup() {
		super();
	}

	public Room_thumbup(Integer room_ord_id, Integer cus_id, Timestamp thumbup_time) {
		super();
		this.room_ord_id = room_ord_id;
		this.cus_id = cus_id;
		this.thumbup_time = thumbup_time;
	}

	public Integer getRoom_ord_id() {
		return room_ord_id;
	}

	public void setRoom_ord_id(Integer room_ord_id) {
		this.room_ord_id = room_ord_id;
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