package com.tha103.gogoyu.room_photo.model;

import java.io.Serializable;
import java.sql.Timestamp;
 
public class Room_photo implements Serializable {
	private Integer room_photo_id;
	private Integer room_id;
	private byte[] photo;
	private Timestamp upload_time;
	
	public Room_photo() {
		super();
	}

	
	public Room_photo(Integer room_photo_id, Integer room_id, byte[] photo, Timestamp upload_time) {
		super();
		this.room_photo_id = room_photo_id;
		this.room_id = room_id;
		this.photo = photo;
		this.upload_time = upload_time;
	}
	
	public Integer getRoom_photo_id() {
		return room_photo_id;
	}

	public void setRoom_photo_id(Integer room_photo_id) {
		this.room_photo_id = room_photo_id;
	}

	public Integer getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
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
