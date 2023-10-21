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
		dao = new Room_ordHibernateDAO(HibernateUtil.getSessionFactory());//servlet�z�Lservice�Q�Φh��(daointerface)�I�sd	ao
	}

	
	//for�[�J�ʪ����ɡA�ҥH�O�S���U���q��~�঳���ݩʡA�Ҧpcomment...
	public Integer addFromShopping(Integer planId, Integer roomId, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission,BigDecimal profit, Integer people, Timestamp checkInTime, Timestamp checkOutTime,
			Integer ordStatus) { 

		Room_ord roomOrd = new Room_ord();
		roomOrd.setPlanId(planId);
		roomOrd.setRoomId(roomId);
		roomOrd.setCusId(cusId);
		roomOrd.setAmount(amount);
		roomOrd.setTotalPrice(totalPrice);
		roomOrd.setCommission(commission);
		roomOrd.setProfit(profit);
		roomOrd.setPeople(people);
		roomOrd.setCheckInTime(checkInTime);
		roomOrd.setCheckOutTime(checkOutTime);
		roomOrd.setOrdStatus(ordStatus);
	
		

		return dao.add(roomOrd);
	}
	
	
	
	
	
	
	
	public Room_ord add(Integer planId, Integer roomId, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission,BigDecimal profit, Integer people, Timestamp checkInTime, Timestamp checkOutTime,
			Integer ordStatus, String remark, Integer score, String comments,
			Timestamp commentsTime) {

		Room_ord roomOrd = new Room_ord();
		roomOrd.setPlanId(planId);
		roomOrd.setRoomId(roomId);
		roomOrd.setCusId(cusId);
		roomOrd.setAmount(amount);
		roomOrd.setTotalPrice(totalPrice);
		roomOrd.setCommission(commission);
		roomOrd.setProfit(profit);
		roomOrd.setCheckInTime(checkInTime);
		roomOrd.setCheckOutTime(checkOutTime);
		roomOrd.setOrdStatus(ordStatus);
		roomOrd.setRemark(remark);
		roomOrd.setScore(score);
		roomOrd.setComments(comments);
		roomOrd.setCommentsTime(commentsTime);
		
		dao.add(roomOrd);

		return roomOrd;
	}
	
	
	
	
	


	public Room_ord update(Integer planId, Integer roomId, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission,BigDecimal profit, Integer people, Timestamp checkInTime, Timestamp checkOutTime,
			Integer ordStatus, String remark, Integer score, String comments,
			Timestamp commentsTime) {

		Room_ord roomOrd = new Room_ord();
		roomOrd.setPlanId(planId);
		roomOrd.setRoomId(roomId);
		roomOrd.setCusId(cusId);
		roomOrd.setAmount(amount);
		roomOrd.setCommission(commission);
		roomOrd.setProfit(profit);
		roomOrd.setCheckInTime(checkInTime);
		roomOrd.setCheckOutTime(checkOutTime);
		roomOrd.setOrdStatus(ordStatus);
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
	

	public Room_ord getRoomOrd(Integer room_ordId) {  //���oconsumer���ݩ�
		return dao.findByPK(room_ordId);
	}
	
	
	
	public Room_ord getBycusID(Integer room_ordId) {  //���oconsumer���ݩ�
		return dao.findByPK(room_ordId);
	}
	
	

	public List<Room_ord> getAll() {
		return dao.getAll();
	}

	
	public List<Room_ord> getRoomOrdVo(Integer cartId , Integer cusId){
		return dao.getRoomOrdVo(cartId , cusId);
	}
	
	
	
	
	

}
