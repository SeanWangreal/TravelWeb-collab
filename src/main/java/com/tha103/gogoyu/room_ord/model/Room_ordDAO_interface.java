package com.tha103.gogoyu.room_ord.model;

import java.util.*;


public interface Room_ordDAO_interface {
	public void insert(Room_ord room_ord);
	public void update(Room_ord room_ord);
	public void delete(Integer room_ord_id);
	public Room_ord findByPrimaryKey(Integer room_ord_id);
	public List<Room_ord> getAll();
	

}
