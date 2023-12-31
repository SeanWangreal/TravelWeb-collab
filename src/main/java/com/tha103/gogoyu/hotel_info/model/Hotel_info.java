package com.tha103.gogoyu.hotel_info.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.trip.model.Trip;

@Entity
@Table(name = "hotel_info")
public class Hotel_info implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotel_info_id", updatable = false)
	private Integer hotelInfoId;

	@Column(name = "restaurant", columnDefinition = "bit")
	private byte restaurant;

	@Column(name = "room_service", columnDefinition = "bit")
	private byte roomService;

	@Column(name = "allday_counter", columnDefinition = "bit")
	private byte alldayCounter;

	@Column(name = "spa", columnDefinition = "bit")
	private byte spa;

	@Column(name = "gym", columnDefinition = "bit")
	private byte gym;

	@Column(name = "garden", columnDefinition = "bit")
	private byte garden;

	@Column(name = "terrace", columnDefinition = "bit")
	private byte terrace;

	@Column(name = "no_smoking", columnDefinition = "bit")
	private byte noSmoking;

	@Column(name = "freewifi", columnDefinition = "bit")
	private byte freewifi;

	@Column(name = "heater", columnDefinition = "bit")
	private byte heater;

	@Column(name = "beach", columnDefinition = "bit")
	private byte beach;

	@Column(name = "pool", columnDefinition = "bit")
	private byte pool;

	@Column(name = "chargingstation", columnDefinition = "bit")
	private byte chargingstation;

	@Column(name = "parking", columnDefinition = "bit")
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
