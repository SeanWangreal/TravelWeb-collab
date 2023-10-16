package com.tha103.gogoyu.room_stock.model;

import java.sql.Date;
import java.util.List;

import com.tha103.gogoyu.room.model.RoomHibernateDAO;

import util.HibernateUtil;

public class RoomStockServiceHibernate implements RoomStockService {
	private Room_stockDAO_interface dao;

	public RoomStockServiceHibernate() {
		dao = new Room_stockHibernateDAO(HibernateUtil.getSessionFactory());
	}
	@Override
	public int addRoomStock(Integer roomId, Date stockDate, Integer stock) {
		Room_stock roomStock = new Room_stock();
		roomStock.setRoomId(roomId);
		roomStock.setStockDate(stockDate);
		roomStock.setStock(stock);
		return dao.add(roomStock);
	}

	@Override
	public int updateRoomStock(Integer roomStockId, Date stockDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteRoomStock(Integer roomStockId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Room_stock getRoomStock(Integer roomStockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room_stock> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
