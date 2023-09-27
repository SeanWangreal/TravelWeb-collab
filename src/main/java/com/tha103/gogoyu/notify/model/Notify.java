package com.tha103.gogoyu.notify.model;

import java.sql.Timestamp;

public class Notify implements java.io.Serializable{
	private Integer notify_id;
	private Integer cus_id;
	private Integer comp_id;
	private Integer room_ord_id;
	private Integer trip_ord_id;
	private String contents;
	private Boolean state;
	private Timestamp notify_time;
	
	public Notify() {
		super();
	}
	
	public Notify(Integer notify_id, Integer cus_id, Integer comp_id, Integer room_ord_id, Integer trip_ord_id,
			String contents, Boolean state, Timestamp notify_time) {
		super();
		this.notify_id = notify_id;
		this.cus_id = cus_id;
		this.comp_id = comp_id;
		this.room_ord_id = room_ord_id;
		this.trip_ord_id = trip_ord_id;
		this.contents = contents;
		this.state = state;
		this.notify_time = notify_time;
	}
	
	public Integer getNotify_id() {
		return notify_id;
	}
	
	public void setNotify_id(Integer notify_id) {
		this.notify_id = notify_id;
	}
	
	public Integer getCus_id() {
		return cus_id;
	}
	
	public void setCus_id(Integer cus_id) {
		this.cus_id = cus_id;
	}
	
	public Integer getComp_id() {
		return comp_id;
	}
	
	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}
	
	public Integer getRoom_ord_id() {
		return room_ord_id;
	}
	
	public void setRoom_ord_id(Integer room_ord_id) {
		this.room_ord_id = room_ord_id;
	}
	
	public Integer getTrip_ord_id() {
		return trip_ord_id;
	}
	
	public void setTrip_ord_id(Integer trip_ord_id) {
		this.trip_ord_id = trip_ord_id;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public Boolean getState() {
		return state;
	}
	
	public void setState(Boolean state) {
		this.state = state;
	}
	
	public Timestamp getNotify_time() {
		return notify_time;
	}
	
	public void setNotify_time(Timestamp notify_time) {
		this.notify_time = notify_time;
	}
}
	
	
	