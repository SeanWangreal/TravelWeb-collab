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
	public int updateRoomStock(Integer roomStockId,Integer stock) {
		Room_stock roomStock = this.getRoomStock(roomStockId);
		roomStock.setStock(stock);
		return dao.update(roomStock);
	}

	@Override
	public void deleteRoomStock(Integer roomStockId) {
		dao.delete(roomStockId);
	}

	@Override
	public Room_stock getRoomStock(Integer roomStockId) {
		return dao.findByPK(roomStockId);
	}

	@Override
	public List<Room_stock> getAll() {
		return dao.getAll();
	}
	@Override
	public List<Room_stock> getStockByRoomId(Integer roomId) {
		return dao.getStockByRoomId(roomId);
	}
	@Override
	public void addFirstTime(Integer roomId, Date stockDate, Integer stock) {
		Room_stock roomStock = new Room_stock();
		roomStock.setRoomId(roomId);
		roomStock.setStockDate(stockDate);
		roomStock.setStock(stock);
		dao.addFirstTime(roomStock);
		
	}


}
