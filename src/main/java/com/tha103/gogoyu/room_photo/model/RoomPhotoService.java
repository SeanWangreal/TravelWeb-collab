package com.tha103.gogoyu.room_photo.model;

import java.util.List;

public interface RoomPhotoService {
	public int addRoomPhoto(Integer roomId,byte[] photo);
	public int updateRoomPhoto(Integer roomPhotoId ,byte[] photo);
	public void deleteRoomPhoto(Integer roomPhotoId);
	public byte[] getRoomPhoto(Integer roomPhotoId);
	public List<Room_photo> getAll();
	
}
