package com.tha103.gogoyu.room_photo.model;

import java.util.*;


public interface Room_photoDAO_interface {
	public void insert(Room_photo room_photo);
	public void update(Room_photo room_photo);
	public void delete(Integer room_photo_id);
	public Room_photo findByPrimaryKey(Integer room_photo_id);
	public List<Room_photo> getAll();
}
