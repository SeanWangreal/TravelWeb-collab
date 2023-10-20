package com.tha103.gogoyu.room_collect.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Room_collectService {

	private Room_collectDAO_interface dao;

	public Room_collectService() {
		dao = new Room_collectHibernateDAO();
	}
	
	private Date date = new Date();
	private Timestamp time_s = new Timestamp(date.getTime());

	public Room_collect addRoomCollect(Integer cusId, Integer roomId) {

		Room_collect room_collect = new Room_collect();

		room_collect.setCusId(cusId);
		room_collect.setRoomId(roomId);
		room_collect.setCollectTime(time_s);
		dao.insert(room_collect);

		return room_collect;
	}

	public Room_collect updateRoomCollect(Integer cusId, Integer roomId) {

		Room_collect room_collect = new Room_collect();

		room_collect.setCusId(cusId);
		room_collect.setRoomId(roomId);
		room_collect.setCollectTime(time_s);
		dao.update(room_collect);

		return room_collect;
	}

	public void deleteRoomCollect(Integer cus_id, Integer roomId) {
		dao.delete(cus_id, roomId);
	}

	public Room_collect getOneRoomCollect(Integer cus_id, Integer roomId) {
		return dao.findByPrimaryKey(cus_id, roomId);
	}

	public List<Room_collect> getAll() {
		return dao.getAll();
	}
}
