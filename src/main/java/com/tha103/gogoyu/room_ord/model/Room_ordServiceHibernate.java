package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.room.model.RoomDAO_interface;
import com.tha103.gogoyu.room.model.RoomHibernateDAO;
import com.tha103.gogoyu.room.model.RoomServiceHibernate;

import util.HibernateUtil;

public class Room_ordServiceHibernate {
	private Room_ordDAO_interface dao;

	public Room_ordServiceHibernate() {
		dao = new Room_ordHibernateDAO(HibernateUtil.getSessionFactory());//servlet透過service利用多型(daointerface)呼叫d	ao
	}

	public Room_ord add(Integer planId, Integer roomId,Room room,Consumer cusumer, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission, Integer people, Timestamp checkInTime, Timestamp checkOutTime,
			Integer ordStatus, Timestamp ordTime, String remark, Integer score, String comments,
			Timestamp commentsTime) {

		Room_ord roomOrd = new Room_ord();
		roomOrd.setPlanId(planId);
		roomOrd.setRoomId(roomId);
		roomOrd.setRoom(room);
		roomOrd.setCusumer(cusumer);
		roomOrd.setCusId(cusId);
		roomOrd.setAmount(amount);
		roomOrd.setCommission(commission);
		roomOrd.setCheckInTime(checkInTime);
		roomOrd.setCheckOutTime(checkOutTime);
		roomOrd.setOrdStatus(ordStatus);
		roomOrd.setOrdTime(ordTime);
		roomOrd.setRemark(remark);
		roomOrd.setScore(score);
		roomOrd.setComments(comments);
		roomOrd.setCommentsTime(commentsTime);
		
		dao.add(roomOrd);

		return roomOrd;
	}
	
	
	
	
	
	
//	public Room updateStatus(Integer roomId,Integer roomStatus) {
//		Room room = this.getOneRoom(roomId);
//		room.setRoomStatus(roomStatus);
//		dao.update(room);
//		return room;
//	}

	public Room_ord update(Integer planId, Integer roomId,Room room,Consumer cusumer, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission, Integer people, Timestamp checkInTime, Timestamp checkOutTime,
			Integer ordStatus, Timestamp ordTime, String remark, Integer score, String comments,
			Timestamp commentsTime) {

		Room_ord roomOrd = new Room_ord();
		roomOrd.setPlanId(planId);
		roomOrd.setRoomId(roomId);
		roomOrd.setRoom(room);
		roomOrd.setCusumer(cusumer);
		roomOrd.setCusId(cusId);
		roomOrd.setAmount(amount);
		roomOrd.setCommission(commission);
		roomOrd.setCheckInTime(checkInTime);
		roomOrd.setCheckOutTime(checkOutTime);
		roomOrd.setOrdStatus(ordStatus);
		roomOrd.setOrdTime(ordTime);
		roomOrd.setRemark(remark);
		roomOrd.setScore(score);
		roomOrd.setComments(comments);
		roomOrd.setCommentsTime(commentsTime);
		
		dao.update(roomOrd);

		return roomOrd;
	}
	
	public void delete(Integer room_ordId) {
		dao.delete(room_ordId);
	}

	public Room_ord getByRoom_ordID(Integer room_ordId) { 
		return dao.findByPK(room_ordId);
	}
	
	public Room_ord getByPlanID(Integer room_ordId) { //取得planning的屬性
		return dao.findByPK(room_ordId); 
	}
	
	public Room_ord getBycusID(Integer room_ordId) {  //取得consumer的屬性
		return dao.findByPK(room_ordId);
	}
	
	

	public List<Room_ord> getAll() {
		return dao.getAll();
	}

//	@Override
//	public List<Room> getRoomByCompId(Integer compId) {
//		return dao.findRoomByCompId(compId);
//	}
//	public static void main(String[] args) {
//		RoomServiceHibernate hi = new RoomServiceHibernate();
//		System.out.println(hi.getAll());
//	}
	
}
