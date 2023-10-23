package com.tha103.gogoyu.room_stock.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
	public int updateRoomStock(Integer roomStockId, Integer stock) {
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

	@Override
	public List<Room_stock> getStockByTodayByRoomId(Integer roomId) {
		return dao.getAllByToday(roomId);
	}

	@Override
	public void updateAllRoomStock(int roomId, Map<Integer, Integer> oldMap, List<Integer> deleteIdList,
			Map<Date, Integer> newStockMap) {
		List<Room_stock> oldRoomStock = new ArrayList<Room_stock>();
		List<Room_stock> deleteRoomStock = new ArrayList<Room_stock>();
		List<Room_stock> newRoomStock = new ArrayList<Room_stock>();
		if (oldMap != null) {
			for (Integer oldRoomStockId : oldMap.keySet()) {
				Room_stock roomStock = this.getRoomStock(oldRoomStockId);
				roomStock.setRoomStockId(oldRoomStockId);
				roomStock.setStock(oldMap.get(oldRoomStockId));
				oldRoomStock.add(roomStock);
			}
		}
		if (deleteIdList != null) {
			for (Integer deleteRoomStockId : deleteIdList) {
				Room_stock deleteRoom = this.getRoomStock(deleteRoomStockId);
				deleteRoomStock.add(deleteRoom);
			}
		}
		if (newStockMap != null) {
			for (Date newRoomDate : newStockMap.keySet()) {
				Room_stock roomStock = new Room_stock();
				roomStock.setRoomId(roomId);
				roomStock.setStockDate(newRoomDate);
				roomStock.setStock(newStockMap.get(newRoomDate));
				newRoomStock.add(roomStock);
			}
		}
		dao.updateAll(oldRoomStock, deleteRoomStock, newRoomStock);

	}

}
