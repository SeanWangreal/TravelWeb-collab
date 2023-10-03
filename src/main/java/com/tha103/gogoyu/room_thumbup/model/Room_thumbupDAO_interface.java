package com.tha103.gogoyu.room_thumbup.model;

import java.util.List;

public interface Room_thumbupDAO_interface {
	public int insert(Room_thumbup roomThumbup);
	public int update(Room_thumbup roomThumbup);
	public int delete(Integer roomOrdId, Integer cusId);
	public Room_thumbup findByPrimaryKey(Integer roomOrdId, Integer cusId);
	public List<Room_thumbup> getAll();
}
