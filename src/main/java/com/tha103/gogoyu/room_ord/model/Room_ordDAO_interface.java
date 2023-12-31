package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;
 

public interface Room_ordDAO_interface {
//	public void insert(Room_ord roomOrd);
//	public void update(Room_ord roomOrd);
//	public void delete(Integer roomOrdId);
//	public Room_ord findByPrimaryKey(Integer roomOrdId);
//	public List<Room_ord> getAll();
	int add(Room_ord roomOrd);
	int update(Room_ord roomOrd);
	int delete(Integer roomOrdId);
	Room_ord findByPK(Integer roomOrdId);
	List<Room_ord> getAll();
	public Map <Room_ord , List<String>> getRoomOrdVo(Integer cartId , Integer cusId);
	public Map<Room_ord,List<String>> gettripIdComment(Integer roomId);
	public Integer updateCommentAndScore(Integer roomOrd , Integer score , String comment ,Timestamp commentsTime);
	public Map<Room_ord,List<String>> getRoomOrdByCusId(Integer CusId);
	public  Map<Room_ord,List<String>> getRoomOrdByCompId(Integer compId,Integer beginCount, String ordOrReview);
	public  Map<Room_ord,List<String>> getRoomOrdByCompIdOrdId(Integer roomOrdId, Integer compId);
	public Integer updateStatusAndRemark(String remark , Integer tripOrdId ,BigDecimal profit , BigDecimal commission ,BigDecimal totalPrice ,Timestamp ordTime , Integer people);
	public Map <Room_ord , List <Object>> getRoomOrdList(Integer roomOrdIdDate , Date checkInTime ,Date checkOutTime , Long diffInDays );
	public Integer updateAmount(Integer amount ,Integer roomOrdId);
	public Integer updateCartNum(Integer planId , Integer roomOrdId);
	public Integer queryProduct(Integer roomId , Integer cusId , Date checkInTime , Date checkOutTime); 
}
