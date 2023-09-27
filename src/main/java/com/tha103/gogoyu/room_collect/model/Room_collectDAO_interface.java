package com.tha103.gogoyu.room_collect.model;

import java.util.List;

public interface Room_collectDAO_interface {
	public void insert(Room_collect room_collect);
	public void update(Room_collect room_collect);
	public void delete(Integer cus_id,Integer room_id);
	public Room_collect findByPrimaryKey(Integer cus_id,Integer room_id);
	public List<Room_collect> getAll();
}
