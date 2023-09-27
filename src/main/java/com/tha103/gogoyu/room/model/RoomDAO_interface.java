package com.tha103.gogoyu.room.model;

import java.util.*;


public interface RoomDAO_interface {
	public void insert(Room room);
	public void update(Room room);
	public void delete(Integer room_id);
	public Room findByPrimaryKey(Integer room_id);
	public List<Room> getAll();

}
