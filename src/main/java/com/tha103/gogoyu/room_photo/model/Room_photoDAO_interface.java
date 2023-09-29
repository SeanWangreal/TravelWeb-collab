package com.tha103.gogoyu.room_photo.model;

import java.util.*;


public interface Room_photoDAO_interface {
	public void insert(Room_photo roomPhoto);
	public void update(Room_photo roomPhoto);
	public void delete(Integer roomPhotoId);
	public Room_photo findByPrimaryKey(Integer roomPhotoId);
	public List<Room_photo> getAll();
}
