package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.room.model.RoomDAO_interface;
import com.tha103.gogoyu.room.model.RoomHibernateDAO;
import com.tha103.gogoyu.room.model.RoomServiceHibernate;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;

import util.HibernateUtil;

public class Room_ordServiceHibernate {
	private Room_ordDAO_interface dao;

	public Room_ordServiceHibernate() {
		dao = new Room_ordHibernateDAO(HibernateUtil.getSessionFactory());
	}

	public Integer addFromShopping(Integer compId, Integer planId, Integer roomId, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission, BigDecimal profit, Integer people, Date checkInTime,
			Date checkOutTime, Integer ordStatus) {

		Room_ord roomOrd = new Room_ord();
		roomOrd.setCompId(compId);
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

	public Room_ord add(Integer planId, Integer roomId, Integer cusId, Integer amount, BigDecimal totalPrice,
			BigDecimal commission, BigDecimal profit, Integer people, Date checkInTime, Date checkOutTime,
			Integer ordStatus, String remark, Integer score, String comments, Timestamp commentsTime) {
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

	public Room_ord update(Integer planId, Integer roomId, Integer cusId, Integer amount, BigDecimal totalPrice,
			BigDecimal commission, BigDecimal profit, Integer people, Date checkInTime, Date checkOutTime,
			Integer ordStatus, String remark, Integer score, String comments, Timestamp commentsTime) {
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

	public Room_ord getRoomOrd(Integer room_ordId) {
		return dao.findByPK(room_ordId);
	}

	public Room_ord getBycusID(Integer room_ordId) {
		return dao.findByPK(room_ordId);
	}

	public List<Room_ord> getAll() {
		return dao.getAll();
	}

	public Map<Room_ord, List<String>> getRoomOrdVo(Integer cartId, Integer cusId) {
		return dao.getRoomOrdVo(cartId, cusId);
	}

	public Map<Room_ord, List<String>> getRoomOrdByCompId(Integer compId, Integer beginCount, String ordOrReview) {
		return dao.getRoomOrdByCompId(compId, beginCount, ordOrReview);
	}

	public Map<Room_ord, List<String>> gettripIdComment(Integer roomId) {
		return dao.gettripIdComment(roomId);
	}

	public Map<Room_ord, List<String>> getRoomOrdByCusId(Integer CusId) {
		return dao.getRoomOrdByCusId(CusId);
	}

	public void updateStatusAndRemark(String remark, Integer roomOrdId, BigDecimal profit, BigDecimal commission,
			BigDecimal totalPrice, Timestamp ordTime, Integer people) {
		dao.updateStatusAndRemark(remark, roomOrdId, profit, commission, totalPrice, ordTime, people);
	}

	public Integer updateCommentAndScore(Integer roomOrd, Integer score, String comment, Timestamp commentsTime) {

		return dao.updateCommentAndScore(roomOrd, score, comment, commentsTime);
	}

	public Map<Room_ord, List<String>> getOneRoomOrd(Integer roomOrdId, Integer compId) {
		return dao.getRoomOrdByCompIdOrdId(roomOrdId, compId);
	}

	public Map<Room_ord, List<Object>> getRoomOrdList(Integer roomOrdId, Date checkInTime, Date checkOutTime , Long diffInDays ) {
		return dao.getRoomOrdList(roomOrdId, checkInTime, checkOutTime ,diffInDays );
	}

	public void updateAmount(Integer amount, Integer roomOrdId) {
		dao.updateAmount(amount, roomOrdId);
	}

	public void updateCartNum(Integer plan_id, Integer roomOrdId) {
		dao.updateCartNum(plan_id, roomOrdId);
	}

	public Integer queryProduct(Integer roomId, Integer cusId, Date checkInTime, Date checkOutTime) {
		return dao.queryProduct(roomId, cusId, checkInTime, checkOutTime);
	}

}
