package com.tha103.gogoyu.room_photo.model;

import java.util.*;


public interface Room_photoDAO_interface {
	int add(Room_photo roomPhoto);
	int update(Room_photo roomPhoto);
	int delete(Integer roomPhotoId);
	Room_photo findByPK(Integer roomPhotoId);
	List<Room_photo> getAll();
}
