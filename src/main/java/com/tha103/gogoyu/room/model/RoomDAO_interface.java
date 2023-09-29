package com.tha103.gogoyu.room.model;

import java.util.*;


public interface RoomDAO_interface {
	public void insert(Room room);
	public void update(Room room);
	public void delete(Integer roomId);
	public Room findByPrimaryKey(Integer roomId);
	public List<Room> getAll();

}
