package com.tha103.gogoyu.room.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.tha103.gogoyu.room_ord.model.Room_ord;
import com.tha103.gogoyu.room_photo.model.Room_photo;


public interface RoomDAO_interface {
	int add(Room room, LinkedList<byte[]> allPhoto);
	int update(Room room);
	int delete(Integer roomId);
	Room findByPK(Integer roomId);
	List<Room> findRoomByCompId(Integer compId);
	List<Room> getAll();
	byte[] getMainPhoto(Integer roomId);
	int deleteAllPhoto(Integer roomId);
	Set<Room_photo> getAllPhoto(Integer roomId);
	List<List> getHotRoomDetail();
//	List<Room> searchRoom(String comp_address,Date checkIn,Date checkOut,Integer number);
	Map<Room, List<String>> searchRoom(String comp_address,Date checkIn,Date checkOut,Integer number);
	List<Object> getRoomProdutDetail(Integer roomId);
	
}
