package com.tha103.gogoyu.room.model;

import java.util.*;


public interface RoomDAO_interface {
	int add(Room room);
	int update(Room room);
	int delete(Integer roomId);
	Room findByPK(Integer roomId);
	List<Room> getAll();

}
