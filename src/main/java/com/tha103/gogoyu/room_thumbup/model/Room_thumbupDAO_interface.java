package com.tha103.gogoyu.room_thumbup.model;

import java.util.List;

public interface Room_thumbupDAO_interface {
	public void insert(Room_thumbup roomThumbup);
	public void update(Room_thumbup roomThumbup);
	public void delete(Integer roomOrdId, Integer cusId);
	public Room_thumbup findByPrimaryKey(Integer roomOrdId, Integer cusId);
	public List<Room_thumbup> getAll();
}
