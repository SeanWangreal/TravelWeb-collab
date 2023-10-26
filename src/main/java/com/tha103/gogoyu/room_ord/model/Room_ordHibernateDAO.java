package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.room_photo.model.Room_photo;
import com.tha103.gogoyu.trip.model.Trip;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;
import com.tha103.gogoyu.company.model.Company;
import util.HibernateUtil;

public class Room_ordHibernateDAO implements Room_ordDAO_interface {
	private SessionFactory factory;

	// 透過service帶session的建構子使用多型透過interface呼叫的hibernateDAO
	public Room_ordHibernateDAO(SessionFactory factory) {
		this.factory = factory; // 取得sessionfactory並回傳
	}

	private Session getSession() {
		return factory.getCurrentSession(); // 取得CurrentSession，後續只要呼叫getSession()就可以開始交易
	}

	@Override
	public int add(Room_ord roomOrd) { // 此傳入的是"無"pk的(這樣才能透過自增主鍵新增)
		try {
			getSession().beginTransaction();// 開始交易
			getSession().save(roomOrd); // 傳入一個roomOrd並且建立在table內
			getSession().getTransaction().commit();// 交易完成
			return 1; // 回傳int型別pk去判斷是否有成功，假如不回傳-1就代表成功commit
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();// 發生例外執行交易取消
			return -1; // 代表失敗rollback
		}

	}

	@Override
	public int update(Room_ord roomOrd) { // 此傳入的是"有"pk的(這樣才能更新)
		try {
			getSession().beginTransaction();// 開始交易
			getSession().update(roomOrd);// 傳入一個roomOrd並在table內更新該欄位
			getSession().getTransaction().commit();// 交易完成
			return 1; // 回傳int 1，假如不回傳-1就代表成功commit
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();// 發生例外執行交易取消
		}
		return -1;// 代表失敗rollback
	}

	@Override
	public int delete(Integer roomOrdId) { // 傳入一個pk，只需要提供pk即可將該筆資料刪除，不需要整個VO
		try {
			getSession().beginTransaction();
			Room_ord roomOrd = getSession().get(Room_ord.class, roomOrdId); // 在刪除前，要先查詢是否有該pk資料
//			if (roomOrd != null) {  //判斷假如有東西
			getSession().delete(roomOrd); // 依照傳入pk刪除該datarow
//			}
			getSession().getTransaction().commit();// 交易完成
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();// 發生例外執行交易取消
		}
		return -1;
	}

	@Override
	public Room_ord findByPK(Integer roomOrdId) {
		try {
			getSession().beginTransaction();
			Room_ord roomOrd = getSession().get(Room_ord.class, roomOrdId);// 傳入pk查詢該datarow資料
			getSession().getTransaction().commit(); // 交易完成
			return roomOrd; // 回傳該table所查詢的"該"pk的物件
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();// 發生例外執行交易取消
		}
		return null;
	}

	@Override
	public List<Room_ord> getAll() {

		try {
			getSession().beginTransaction();
			List<Room_ord> list = getSession().createQuery("from Room_ord", Room_ord.class).list(); // 用hql傳入於哪個table，回傳整個該table物件並集合成一個list
			getSession().getTransaction().commit(); // 交易完成
			return list; // 回傳該table所有的物件
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();// 發生例外執行交易取消
		}
		return null; // 如果該table沒有東西就回傳null
	}


	public Map <Room_ord , List<String>> getRoomOrdVo(Integer cartId, Integer cusId) {

		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Room_ord> query1 = getSession().createNativeQuery(
					"SELECT * from room_ord where plan_id in (select plan_id from Planning where cart_id= :cartId and cus_id = :cusId)", Room_ord.class);
						query1.setParameter("cartId", cartId);
						query1.setParameter("cusId", cusId);
			List<Room_ord> list1 = query1.list();
			
			Map <Room_ord , List<String>> map  = new LinkedHashMap<Room_ord , List<String>>();
			for (Room_ord ord: list1) {
				List<String> info = new ArrayList<String>();
//				Trip trip = getSession().get(Trip.class, ord.getTripId());
				Integer compId =getSession().get(Room.class, ord.getRoomId()).getCompId();
					info.add(getSession().get(Company.class, compId).getCompName());//String compName
					map.put(ord,info);
			}
			
			
			getSession().getTransaction().commit();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	
	
	
	public Map<Room_ord,List<String>> getRoomOrdByCusId(Integer CusId){
		try {
			getSession().beginTransaction();
			Map<Room_ord,List<String>> map = new LinkedHashMap<Room_ord, List<String>>();
			List<Room_ord> list = getSession().createQuery("from Room_ord where cus_id = :CusId and ord_status != 0 order by ord_time desc", Room_ord.class)
					.setParameter("CusId", CusId)
					.list();
			for (Room_ord ord : list) {
				List<String> info = new ArrayList<String>();
				Room room =getSession().get(Room.class, ord.getRoomId());
				Consumer consumer =getSession().get(Consumer.class, ord.getCusId());
				Company company = getSession().get(Company.class , ord.getCompId());
				info.add(room.getRoomName());
				info.add(room.getRoomType().toString());
				info.add(consumer.getCusName());
				map.put(ord, info);
			}
			getSession().getTransaction().commit();
			return map; 
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null; 
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Map<Room_ord, List<String>> gettripIdComment(Integer RoomId) {
		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Room_ord> query1 = getSession().createNativeQuery(
			"select * from room_ord where room_id = :room_id and ord_status =1 and comments is not null",Room_ord.class);
			query1.setParameter("room_id", RoomId);
			List<Room_ord> list1 = query1.list();
			
			Map<Room_ord,List<String>> map  = new LinkedHashMap<Room_ord,List<String>>();
			for (Room_ord ord: list1) {
				List<String> info = new ArrayList<String>();
					info.add(getSession().get(Company.class, ord.getCompId()).getCompName());//String compName
					info.add(getSession().get(Consumer.class, ord.getCusId()).getCusName());//String cusName					
					map.put(ord,info);
			}
			
			getSession().getTransaction().commit();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	public Integer updateCommentAndScore(Integer roomOrd , Integer score , String comment ,Timestamp commentsTime) {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery("UPDATE Room_ord SET score = :score, comments = :comments  , commentsTime = :commentsTime WHERE roomOrdId = :roomOrdId");
			query.setParameter("comments", comment);
			query.setParameter("score", score);
			query.setParameter("roomOrdId", roomOrd);
			query.setParameter("commentsTime", commentsTime);
			query.executeUpdate();
			
			getSession().getTransaction().commit();
			return 1 ;
		}catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1 ;
	}
	




@Override
public Map<Room_ord, List<String>> getRoomOrdByCompId(Integer compId, Integer beginCount, String ordOrReview) {
	try {
		getSession().beginTransaction();
		List<Room_ord> list = null;
		Long total = null;
		if ("ord".equals(ordOrReview)) {
			NativeQuery<Room_ord> query = getSession().createNativeQuery(
					"select * from room_ord where comp_id = :compId and ord_status != 0 order by ord_time desc  limit :beginCount , 5 ",
					Room_ord.class);
			query.setParameter("compId", compId);
			query.setParameter("beginCount", beginCount);
			list = query.list();
			total = getSession()
					.createQuery("select count(*) from Room_ord where comp_id = :compId and ord_status != 0",
							Long.class)
					.setParameter("compId", compId).uniqueResult();
		} else if ("review".equals(ordOrReview)) {
			NativeQuery<Room_ord> query = getSession().createNativeQuery(
					"select * from room_ord where comp_id = :compId and ord_status != 0 and score is not null order by ord_time desc  limit :beginCount , 5 ",
					Room_ord.class);
			query.setParameter("compId", compId);
			query.setParameter("beginCount", beginCount);
			list = query.list();
			total = getSession().createQuery(
					"select count(*) from Room_ord where comp_id = :compId and ord_status != 0 and score is not null",
					Long.class).setParameter("compId", compId).uniqueResult();
		}

		Map<Room_ord, List<String>> map = new LinkedHashMap<Room_ord, List<String>>();
//		List<Room_ord> list = getSession().createQuery("from Room_ord where comp_id = :compId and ord_status != 0 order by ord_time desc", Room_ord.class)
//				.setParameter("compId", compId)
//				.list();
		for (Room_ord ord : list) {
			List<String> info = new ArrayList<String>();
			Room room = getSession().get(Room.class, ord.getRoomId());
			Consumer consumer = getSession().get(Consumer.class, ord.getCusId());
			info.add(room.getRoomName());
			info.add(room.getRoomType().toString());
			info.add(consumer.getCusName());
			info.add(total.toString());
			map.put(ord, info);
		}
		getSession().getTransaction().commit();
		return map;
	} catch (Exception e) {
		e.printStackTrace();
		getSession().getTransaction().rollback();
	}
	return null;
}

@Override
public Map<Room_ord, List<String>> getRoomOrdByCompIdOrdId(Integer roomOrdId, Integer compId) {
	try {
		getSession().beginTransaction();
		NativeQuery<Room_ord> query = getSession().createNativeQuery(
					"select * from room_ord where comp_id = :compId and ord_status != 0 and room_ord_id = :roomOrdId",
					Room_ord.class);
			query.setParameter("compId", compId);
			query.setParameter("roomOrdId", roomOrdId);
		List<Room_ord> list = query.list();
		Map<Room_ord, List<String>> map = new LinkedHashMap<Room_ord, List<String>>();
//		List<Room_ord> list = getSession().createQuery("from Room_ord where comp_id = :compId and ord_status != 0 order by ord_time desc", Room_ord.class)
//				.setParameter("compId", compId)
//				.list();
		for (Room_ord ord : list) {
			List<String> info = new ArrayList<String>();
			Room room = getSession().get(Room.class, ord.getRoomId());
			Consumer consumer = getSession().get(Consumer.class, ord.getCusId());
			info.add(room.getRoomName());
			info.add(room.getRoomType().toString());
			info.add(consumer.getCusName());
			map.put(ord, info);
		}
		getSession().getTransaction().commit();
		return map;
	} catch (Exception e) {
		e.printStackTrace();
		getSession().getTransaction().rollback();
	}
	return null;
}

}
