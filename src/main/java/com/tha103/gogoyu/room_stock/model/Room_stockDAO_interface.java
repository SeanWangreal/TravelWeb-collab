package com.tha103.gogoyu.room_stock.model;


import java.util.List;



public interface Room_stockDAO_interface {
	int add(Room_stock roomStock);
	int update(Room_stock roomStock);
	int delete(Integer roomStockId);
	void addFirstTime(Room_stock roomStock);
	Room_stock findByPK(Integer roomStockId);
	List<Room_stock> getAll();
	List<Room_stock> getStockByRoomId(Integer roomId);
}
