package com.tha103.gogoyu.trip_photo.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Trip_photo implements Serializable {
	private Integer trip_photo_id;
	private Integer trip_id;
	private byte[] photo;
	private Timestamp upload_time;

	public Trip_photo() {
		super();
	}

	public Trip_photo(Integer trip_photo_id, Integer trip_id, byte[] photo, Timestamp upload_time) {
		super();
		this.trip_photo_id = trip_photo_id;
		this.trip_id = trip_id;
		this.photo = photo;
		this.upload_time = upload_time;
	}

	public Integer getTrip_photo_id() {
		return trip_photo_id;
	}

	public void setTrip_photo_id(Integer trip_photo_id) {
		this.trip_photo_id = trip_photo_id;
	}

	public Integer getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Timestamp getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
	}
}
