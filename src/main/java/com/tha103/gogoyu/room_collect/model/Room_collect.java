package com.tha103.gogoyu.room_collect.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Room_collect implements Serializable{
	private Integer cus_id;
	private Integer room_id;
	private Timestamp collect_time;
	public Room_collect(Integer cus_id, Integer room_id, Timestamp collect_time) {
		super();
		this.cus_id = cus_id;
		this.room_id = room_id;
		this.collect_time = collect_time;
	}

	public Room_collect() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Integer getCus_id() {
		return cus_id;
	}
	
	public void setCus_id(Integer room_id) {
		this.room_id = room_id;
	}
	
	public Integer getRoom_id() {
		return room_id;
	}
	
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}

	public Timestamp getCollect_time() {
		return collect_time;
	}

	public void setCollect_time(Timestamp collect_time) {
		this.collect_time = collect_time;
	}
}
