package com.tha103.gogoyu.room_collect.model;

import java.util.List;

public interface Room_collectDAO_interface {
	public int insert(Room_collect roomCollect);
	public int update(Room_collect roomCollect);
	public int delete(Integer cusId,Integer roomId);
	public Room_collect findByPrimaryKey(Integer cusId,Integer roomId);
	public List<Room_collect> getAll();
}
