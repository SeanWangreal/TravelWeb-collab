package com.tha103.gogoyu.scene.model;


import java.math.BigDecimal;

public class Scene implements java.io.Serializable {
	private Integer scene_id;
	private String scene_name;
	private String open_time;
	private String ticket_price;
	private String trans_info;
	private String parking;
	private String address;
	private BigDecimal lon;
	private BigDecimal lat;
	private String feature;
	private String picture;

	public Scene() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Scene(Integer scene_id, String scene_name, String open_time, String ticket_price, String trans_info,
			String parking, String address, BigDecimal lon,BigDecimal lat, String feature, String picture) {
		super();
		this.scene_id = scene_id;
		this.scene_name = scene_name;
		this.open_time = open_time;
		this.ticket_price = ticket_price;
		this.trans_info = trans_info;
		this.parking = parking;
		this.address = address;
		this.lon = lon;
		this.lat = lat;
		this.feature = feature;
		this.picture = picture;
	}

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}

	public Integer getScene_id() {
		return scene_id;
	}

	public void setScene_id(Integer scene_id) {
		this.scene_id = scene_id;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(String ticket_price) {
		this.ticket_price = ticket_price;
	}

	public String getTrans_info() {
		return trans_info;
	}

	public void setTrans_info(String trans_info) {
		this.trans_info = trans_info;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFeature() {
		return feature;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
