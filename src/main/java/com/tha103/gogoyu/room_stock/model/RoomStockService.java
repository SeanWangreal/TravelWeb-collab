package com.tha103.gogoyu.room_stock.model;

import java.util.List;
import java.sql.Date;

public interface RoomStockService {
	public int addRoomStock(Integer roomId,Date stockDate,Integer stock);
	public int updateRoomStock(Integer roomStockId ,Integer stock);
	public void deleteRoomStock(Integer roomStockId);
	public Room_stock getRoomStock(Integer roomStockId);
	public List<Room_stock> getAll();
	public List<Room_stock> getStockByRoomId(Integer roomId);
	public List<Room_stock> getStockByTodayByRoomId(Integer roomId);
	public void addFirstTime(Integer roomId,Date stockDate,Integer stock);
	
}
