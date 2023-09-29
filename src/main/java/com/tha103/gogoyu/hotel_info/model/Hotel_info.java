package com.tha103.gogoyu.hotel_info.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Hotel_info")
public class Hotel_info implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "hotel_info_id", updatable = false)
	private Integer hotelInfoId;

	@Column(name = "restaurant")
	private byte restaurant;

	@Column(name = "room_service")
	private byte roomService;

	@Column(name = "allday_counter")
	private byte alldayCounter;

	@Column(name = "spa")
	private byte spa;

	@Column(name = "gym")
	private byte gym;

	@Column(name = "garden")
	private byte garden;

	@Column(name = "terrace")
	private byte terrace;

	@Column(name = "no_smoking")
	private byte noSmoking;

	@Column(name = "freewifi")
	private byte freewifi;

	@Column(name = "heater")
	private byte heater;

	@Column(name = "beach")
	private byte beach;

	@Column(name = "pool")
	private byte pool;

	@Column(name = "chargingstation")
	private byte chargingstation;

	@Column(name = "parking")
	private byte parking;

	public Hotel_info() {
		super();
	}

	public Hotel_info(Integer hotel_info_id, byte restaurant, byte room_service, byte allday_counter, byte spa,
			byte gym, byte garden, byte terrace, byte no_smoking, byte freewifi, byte heater, byte beach, byte pool,
			byte chargingstation, byte parking) {
		super();
		this.hotelInfoId = hotel_info_id;
		this.restaurant = restaurant;
		this.roomService = room_service;
		this.alldayCounter = allday_counter;
		this.spa = spa;
		this.gym = gym;
		this.garden = garden;
		this.terrace = terrace;
		this.noSmoking = no_smoking;
		this.freewifi = freewifi;
		this.heater = heater;
		this.beach = beach;
		this.pool = pool;
		this.chargingstation = chargingstation;
		this.parking = parking;
	}

	public Integer getHotelInfoId() {
		return hotelInfoId;
	}

	public void setHotelInfoId(Integer hotelInfoId) {
		this.hotelInfoId = hotelInfoId;
	}

	public byte getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(byte restaurant) {
		this.restaurant = restaurant;
	}

	public byte getRoomService() {
		return roomService;
	}

	public void setRoomService(byte roomService) {
		this.roomService = roomService;
	}

	public byte getAlldayCounter() {
		return alldayCounter;
	}

	public void setAlldayCounter(byte alldayCounter) {
		this.alldayCounter = alldayCounter;
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

	public byte getNoSmoking() {
		return noSmoking;
	}

	public void setNoSmoking(byte noSmoking) {
		this.noSmoking = noSmoking;
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
