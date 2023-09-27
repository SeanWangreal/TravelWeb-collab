package com.tha103.gogoyu.room_thumbup.model;

import java.util.List;

public interface Room_thumbupDAO_interface {
	public void insert(Room_thumbup room_thumbup);
	public void update(Room_thumbup room_thumbup);
	public void delete(Integer room_ord_id, Integer cus_id);
	public Room_thumbup findByPrimaryKey(Integer room_ord_id, Integer cus_id);
	public List<Room_thumbup> getAll();
}
