package com.tha103.gogoyu.hotel_info.model;

public class Hotel_info implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer hotel_info_id;
	private byte restaurant;
	private byte room_service;
	private byte allday_counter;
	private byte spa;
	private byte gym;
	private byte garden;
	private byte terrace;
	private byte no_smoking;
	private byte freewifi;
	private byte heater;
	private byte beach;
	private byte pool;
	private byte chargingstation;
	private byte parking;
	public Hotel_info() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hotel_info(Integer hotel_info_id, byte restaurant, byte room_service, byte allday_counter, byte spa,
			byte gym, byte garden, byte terrace, byte no_smoking, byte freewifi, byte heater, byte beach, byte pool,
			byte chargingstation, byte parking) {
		super();
		this.hotel_info_id = hotel_info_id;
		this.restaurant = restaurant;
		this.room_service = room_service;
		this.allday_counter = allday_counter;
		this.spa = spa;
		this.gym = gym;
		this.garden = garden;
		this.terrace = terrace;
		this.no_smoking = no_smoking;
		this.freewifi = freewifi;
		this.heater = heater;
		this.beach = beach;
		this.pool = pool;
		this.chargingstation = chargingstation;
		this.parking = parking;
	}

	

	public int getHotel_info_id() {
		return hotel_info_id;
	}

	public void setHotel_info_id(int hotel_info_id) {
		this.hotel_info_id = hotel_info_id;
	}

	public byte getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(byte restaurant) {
		this.restaurant = restaurant;
	}

	public byte getRoom_service() {
		return room_service;
	}

	public void setRoom_service(byte room_service) {
		this.room_service = room_service;
	}

	public byte getAllday_counter() {
		return allday_counter;
	}

	public void setAllday_counter(byte allday_counter) {
		this.allday_counter = allday_counter;
	}

	public byte getSpa() {
		return spa;
	}

	public void setSpa(byte spa) {
		this.spa = spa;
	}

	public byte getGym() {
		return gym;
	}

	public void setGym(byte gym) {
		this.gym = gym;
	}

	public byte getGarden() {
		return garden;
	}

	public void setGarden(byte garden) {
		this.garden = garden;
	}

	public byte getTerrace() {
		return terrace;
	}

	public void setTerrace(byte terrace) {
		this.terrace = terrace;
	}

	public byte getNo_smoking() {
		return no_smoking;
	}

	public void setNo_smoking(byte no_smoking) {
		this.no_smoking = no_smoking;
	}

	public byte getFreewifi() {
		return freewifi;
	}

	public void setFreewifi(byte freewifi) {
		this.freewifi = freewifi;
	}

	public byte getHeater() {
		return heater;
	}

	public void setHeater(byte heater) {
		this.heater = heater;
	}

	public byte getBeach() {
		return beach;
	}

	public void setBeach(byte beach) {
		this.beach = beach;
	}

	public byte getPool() {
		return pool;
	}

	public void setPool(byte pool) {
		this.pool = pool;
	}

	public byte getChargingstation() {
		return chargingstation;
	}

	public void setChargingstation(byte chargingstation) {
		this.chargingstation = chargingstation;
	}

	public byte getParking() {
		return parking;
	}

	public void setParking(byte parking) {
		this.parking = parking;
	}

}
