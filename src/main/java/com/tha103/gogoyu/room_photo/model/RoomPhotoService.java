package com.tha103.gogoyu.room_photo.model;

import java.util.List;

public interface RoomPhotoService {
	public Room_photo addRoomPhoto(Integer roomPhotoId,Integer roomId,byte[] photo);
	public Room_photo updateRoomPhoto(Integer roomPhotoId,Integer roomId,byte[] photo);
	public void deleteRoomPhoto(Integer roomPhotoId);
	public Room_photo getRoomPhoto(Integer roomPhotoId);
	public List<Room_photo> getAll();
	
}
