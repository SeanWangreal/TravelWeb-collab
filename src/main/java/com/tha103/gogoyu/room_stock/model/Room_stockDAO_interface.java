package com.tha103.gogoyu.room_stock.model;

import java.util.*;


public interface Room_stockDAO_interface {
	public void insert(Room_stock roomStock);
	public void update(Room_stock roomStock);
	public void delete(Integer roomStockId);
	public Room_stock findByPrimaryKey(Integer roomStockId);
	public List<Room_stock> getAll();
	
}
