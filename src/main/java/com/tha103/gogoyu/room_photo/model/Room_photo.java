package com.tha103.gogoyu.room_photo.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "room_photo")
public class Room_photo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_photo_id", updatable = false)
	private Integer roomPhotoId;
	
	@Column(name="room_id")
	private Integer roomId;
	
	@Column(name = "photo", columnDefinition = "longblob")
	private byte[] photo;
	
	@Column(name="upload_time", insertable = false, updatable = false)
	private Timestamp uploadTime;


	public Room_photo() {
		super();
	}


	public Room_photo(Integer roomPhotoId, Integer roomId, byte[] photo, Timestamp uploadTime) {
		super();
		this.roomPhotoId = roomPhotoId;
		this.roomId = roomId;
		this.photo = photo;
		this.uploadTime = uploadTime;
	}

	public Integer getRoomPhotoId() {
		return roomPhotoId;
	}

	public void setRoomPhotoId(Integer roomPhotoId) {
		this.roomPhotoId = roomPhotoId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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
