package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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


	public Room_ordHibernateDAO(SessionFactory factory) {
		this.factory = factory; // 
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(Room_ord roomOrd) { 
		try {
			getSession().beginTransaction();
			getSession().save(roomOrd); 
			getSession().getTransaction().commit();
			return 1; 
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

	public Integer updateAmount(Integer amount, Integer roomOrdId) {

		System.out.println(amount);
		try {
			getSession().beginTransaction();
			Query query = getSession()
					.createQuery("update Room_ord set amount = :amount  where roomOrdId = :roomOrdId");
			query.setParameter("roomOrdId", roomOrdId);
			query.setParameter("amount", amount);

			query.executeUpdate();

			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	public Map<Room_ord, List<String>> getRoomOrdVo(Integer cartId, Integer cusId) {

		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Room_ord> query1 = getSession().createNativeQuery(
					"SELECT * from room_ord where plan_id in (select plan_id from Planning where cart_id= :cartId and cus_id = :cusId) and ord_status = 0",
					Room_ord.class);
			query1.setParameter("cartId", cartId);
			query1.setParameter("cusId", cusId);
			List<Room_ord> list1 = query1.list();

			Map<Room_ord, List<String>> map = new LinkedHashMap<Room_ord, List<String>>();
			for (Room_ord ord : list1) {
				List<String> info = new ArrayList<String>();
				Integer compId = getSession().get(Room.class, ord.getRoomId()).getCompId();
				String roomId = getSession().get(Room.class, ord.getRoomId()).getRoomName();
				info.add(getSession().get(Company.class, compId).getCompName());// String compName
				info.add(roomId);

				Room room = getSession().get(Room.class, ord.getRoomId());
				Double avgScore = getSession()
						.createQuery("select avg(score) from Room_ord where roomId = :roomId ", Double.class)
						.setParameter("roomId", room.getRoomId()).uniqueResult();

				if (avgScore != null) {
					Double averageScore = Math.round(avgScore * 10.0) / 10.0;
					info.add(averageScore.toString());
				} else {
					info.add("N/A");
				}

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

	
	
	
	public Integer queryProduct(Integer roomId , Integer cusId , Date checkInTime , Date checkOutTime) {
		try {
			getSession().beginTransaction();
			Room_ord query = getSession().createQuery("from Room_ord where roomId = :roomId and cusId = :cusId and checkInTime = :checkInTime and checkOutTime = :checkOutTime  and ordStatus = 0" , Room_ord.class)
													.setParameter("roomId", roomId)
													.setParameter("cusId", cusId)
													.setParameter("checkInTime", checkInTime)
													.setParameter("checkOutTime", checkOutTime)
													.uniqueResult();
			
			getSession().getTransaction().commit();
			System.out.println(query+"1232132131");
			return  query == null ? 1 : -1 ;
			
		}catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
			return -2 ;
		}
	
	}
	
	
	
	
	
	
	
	public Map<Room_ord, List<String>> getRoomOrdByCusId(Integer CusId) {
		try {
			getSession().beginTransaction();
			Map<Room_ord, List<String>> map = new LinkedHashMap<Room_ord, List<String>>();
			List<Room_ord> list = getSession()
					.createQuery("from Room_ord where cus_id = :CusId and ord_status = 1 order by ord_time desc",
							Room_ord.class)
					.setParameter("CusId", CusId).list();
			for (Room_ord ord : list) {
				List<String> info = new ArrayList<String>();
				Room room = getSession().get(Room.class, ord.getRoomId());
				Consumer consumer = getSession().get(Consumer.class, ord.getCusId());
				Company company = getSession().get(Company.class, ord.getCompId());
				info.add(room.getRoomName());
				info.add(room.getRoomType().toString());
				info.add(consumer.getCusName());
				info.add(company.getCompName());
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

	public Map<Room_ord, List<Object>> getRoomOrdList(Integer roomOrdId ,Date checkInTime ,Date checkOutTime ) {
		try {
			getSession().beginTransaction();
			Map<Room_ord, List<Object>> map = new LinkedHashMap<Room_ord, List<Object>>();
			@SuppressWarnings("unchecked")
			NativeQuery<Room_ord> query1 = getSession()
					.createNativeQuery("select * from Room_ord where room_ord_id = :roomOrdId", Room_ord.class)
					.setParameter("roomOrdId", roomOrdId);
			List<Room_ord> list1 = query1.list();

			System.out.println(list1);
			for (Room_ord Room : list1) {
				List<Object> info = new ArrayList<Object>();
				info.add(getSession().get(Company.class, Room.getCompId()).getCompName());// String compName
				info.add(getSession().get(Room.class, Room.getRoomId()).getRoomName());// String 房型名
				String room = null;
				Integer roomType = getSession().get(Room.class, Room.getRoomId()).getRoomType();
				switch (roomType) {
				case 1:
					room = "單人房";
					break;
				case 2:
					room = "雙人房";
					break;
				case 3:
					room = "三人房";
					break;
				case 4:
					room = "四人房";
					break;
				}
				info.add(room);// String 房型
				info.add(getSession().get(Company.class, Room.getCompId()).getPrincipalName());// String Principalname
				info.add(getSession().get(Company.class, Room.getCompId()).getPrincipalPhone());// String PrincipalPhone
//						
				BigDecimal totalPrice = getSession().get(Room.class, Room.getRoomId()).getPrice()
						.multiply(new BigDecimal(Room.getAmount()));
				info.add(totalPrice);
				BigDecimal commission = totalPrice.multiply(new BigDecimal(0.1));
				info.add(commission);
				BigDecimal profit = totalPrice.subtract(commission);
				info.add(profit);
				
				
					Long out = checkOutTime.getTime();
				   long ONE_DAY = 1 * 24 * 60 * 60 * 1000L;
				   out -= ONE_DAY;
				   Date newDate = new Date(out);
				  
				Room roomId= getSession().get(Room.class, Room.getRoomId());
				Integer roomstock = getSession()
						.createQuery("select min(stock) from Room_stock where ( stockDate between :checkInTime and  :checkOutTime ) and roomId = :roomId ", Integer.class)
						.setParameter("checkInTime",  checkInTime)
						.setParameter("checkOutTime", newDate)
						.setParameter("roomId", roomId.getRoomId()).uniqueResult();
				info.add(roomstock);
				
			
				

				map.put(Room, info);
			}
			System.out.println(map);
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
					"select * from room_ord where room_id = :room_id and ord_status =1 and comments is not null",
					Room_ord.class);
			query1.setParameter("room_id", RoomId);
			List<Room_ord> list1 = query1.list();

			Map<Room_ord, List<String>> map = new LinkedHashMap<Room_ord, List<String>>();
			for (Room_ord ord : list1) {
				List<String> info = new ArrayList<String>();
				info.add(getSession().get(Company.class, ord.getCompId()).getCompName());// String compName
				info.add(getSession().get(Consumer.class, ord.getCusId()).getCusName());// String cusName
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

	public Integer updateCommentAndScore(Integer roomOrd, Integer score, String comment, Timestamp commentsTime) {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery(
					"UPDATE Room_ord SET score = :score, comments = :comments  , commentsTime = :commentsTime WHERE roomOrdId = :roomOrdId");
			query.setParameter("comments", comment);
			query.setParameter("score", score);
			query.setParameter("roomOrdId", roomOrd);
			query.setParameter("commentsTime", commentsTime);
			query.executeUpdate();

			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	public Integer updateStatusAndRemark(String remark, Integer roomOrdId, BigDecimal profit, BigDecimal commission,
			BigDecimal totalPrice, Timestamp ordTime, Integer people) {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery(
					"update Room_ord set ordStatus =1 , remark = :remark , profit = :profit , commission = :commission , totalPrice = :totalPrice  , ordTime = :ordTime  , people = :people where roomOrdId = :roomOrdId");
			query.setParameter("remark", remark);
			query.setParameter("roomOrdId", roomOrdId);
			query.setParameter("profit", profit);
			query.setParameter("commission", commission);
			query.setParameter("totalPrice", totalPrice);
			query.setParameter("ordTime", ordTime);
			query.setParameter("people", people);

			query.executeUpdate();

			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
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

	public Integer updateCartNum(Integer planId, Integer roomOrdId) {
		try {
			getSession().beginTransaction();
			Query query = getSession()
					.createQuery("update Room_ord set planId = :planId  where roomOrdId = :roomOrdId");
			query.setParameter("roomOrdId", roomOrdId);
			query.setParameter("planId", planId);

			query.executeUpdate();

			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

}
