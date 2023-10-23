package com.tha103.gogoyu.room.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.tha103.gogoyu.room_photo.model.Room_photo;


public interface RoomDAO_interface {
	int add(Room room);
	int update(Room room);
	int delete(Integer roomId);
	Room findByPK(Integer roomId);
	List<Room> findRoomByCompId(Integer compId);
	List<Room> getAll();
	byte[] getMainPhoto(Integer roomId);
	int deleteAllPhoto(Integer roomId);
	Set<Room_photo> getAllPhoto(Integer roomId);
	List<Room> getHotRoom();
	List<Room> searchRoom(String comp_address,Date checkIn,Date checkOut,Integer number);
}
