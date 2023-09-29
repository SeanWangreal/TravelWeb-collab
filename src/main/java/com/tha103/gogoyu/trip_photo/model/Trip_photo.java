package com.tha103.gogoyu.trip_photo.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trip_photo")
public class Trip_photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trip_photo_id")
	private Integer tripPhotoId;

	@Column(name = "trip_id")
	private Integer tripId;

	@Column(name = "photo", columnDefinition = "longblob")
	private byte[] photo;

	@Column(name = "upload_time")
	private Timestamp uploadTime;

	public Trip_photo() {
		super();
	}

	public Trip_photo(Integer tripPhotoId, Integer tripId, byte[] photo, Timestamp uploadTime) {
		super();
		this.tripPhotoId = tripPhotoId;
		this.tripId = tripId;
		this.photo = photo;
		this.uploadTime = uploadTime;
	}

	public Integer getTripPhotoId() {
		return tripPhotoId;
	}

	public void setTripPhotoId(Integer tripPhotoId) {
		this.tripPhotoId = tripPhotoId;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
}
