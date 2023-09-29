package com.tha103.gogoyu.room_ord.model;

import java.util.*;
 

public interface Room_ordDAO_interface {
	public void insert(Room_ord roomOrd);
	public void update(Room_ord roomOrd);
	public void delete(Integer roomOrdId);
	public Room_ord findByPrimaryKey(Integer roomOrdId);
	public List<Room_ord> getAll();
	

}
