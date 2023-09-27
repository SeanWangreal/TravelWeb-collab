package com.tha103.gogoyu.room_stock.model;

import java.util.*;


public interface Room_stockDAO_interface {
	public void insert(Room_stock room_stock);
	public void update(Room_stock room_stock);
	public void delete(Integer room_stock_id);
	public Room_stock findByPrimaryKey(Integer room_stock_id);
	public List<Room_stock> getAll();
	
}
