package com.tha103.gogoyu.room_stock.model;


import java.sql.Date;
import java.util.List;
import java.util.Map;



public interface Room_stockDAO_interface {
	int add(Room_stock roomStock);
	int update(Room_stock roomStock);
	int delete(Integer roomStockId);
	void addFirstTime(Room_stock roomStock);
	Room_stock findByPK(Integer roomStockId);
	List<Room_stock> getAll();
	List<Room_stock> getAllByToday(Integer roomId);
	List<Room_stock> getStockByRoomId(Integer roomId);
	void updateAll(List<Room_stock> oldRoomStock, List<Room_stock> deleteRoomStock, List<Room_stock> newRoomStock);
	Integer searchMinRoomStockByTime(Integer roomId, Date checkIn, Date checkOut);
	Integer updateRoomStock(Integer roomId, Integer amount, Date checkInTime, Date checkOutTime);
}
