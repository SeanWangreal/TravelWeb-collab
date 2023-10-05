package com.tha103.gogoyu.room_stock.model;

import java.util.*;


public interface Room_stockDAO_interface {
	
	int add(Room_stock roomStock);
	int update(Room_stock roomStock);
	int delete(Integer roomStockId);
	Room_stock findByPK(Integer roomStockId);
	List<Room_stock> getAll();
}
