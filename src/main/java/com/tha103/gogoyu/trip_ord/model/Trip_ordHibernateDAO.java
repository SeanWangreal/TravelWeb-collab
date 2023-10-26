package com.tha103.gogoyu.trip_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.trip.model.Trip;

public class Trip_ordHibernateDAO implements Trip_ordDAO_Interface {
	private SessionFactory factory;

	// 透過service帶session的建構子使用多型透過interface呼叫的hibernateDAO
	public Trip_ordHibernateDAO(SessionFactory factory) {
		this.factory = factory; // 取得sessionfactory並回傳
	}

	private Session getSession() {
		return factory.getCurrentSession(); // 取得CurrentSession，後續只要呼叫getSession()就可以開始交易
	}

	@Override
	public Integer add(Trip_ord tripOrd) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(tripOrd);
			getSession().save(tripOrd);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}
	@Override
	public Integer update(Trip_ord tripOrd) {
		try {
			getSession().beginTransaction();
			getSession().update(tripOrd);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Integer delete(Integer tripOrdId) {
		try {
			getSession().beginTransaction();
			Trip_ord emp = getSession().get(Trip_ord.class, tripOrdId);
			if (emp != null) {
				getSession().delete(emp);
			}
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Trip_ord findByPrimaryKey(Integer tripOrdId) {
		try {
			getSession().beginTransaction();
			Trip_ord tripOrd = getSession().get(Trip_ord.class, tripOrdId);
			getSession().getTransaction().commit();
			return tripOrd;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Trip_ord> getAll() {
		try {
			getSession().beginTransaction();
			List<Trip_ord> list = getSession().createQuery("from Trip_ord", Trip_ord.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	
	@Override
	public Map<Trip_ord, List<String>> gettripIdComment(Integer tripId) {
		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Trip_ord> query1 = getSession().createNativeQuery(
			"select * from trip_ord where trip_id = :trip_id and ord_status =1 and comments is not null",Trip_ord.class);
			query1.setParameter("trip_id", tripId);
			List<Trip_ord> list1 = query1.list();
			
			Map<Trip_ord,List<String>> map  = new LinkedHashMap<Trip_ord,List<String>>();
			for (Trip_ord ord: list1) {
				List<String> info = new ArrayList<String>();
					info.add(getSession().get(Trip.class, ord.getTripId()).getTripName());//String tripName
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

	
	
	
	
	@Override
	public Map<Trip_ord, List<String>> getTripOrdVo(Integer cartId, Integer cusId) {
		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Trip_ord> query1 = getSession().createNativeQuery(
					"SELECT * from trip_ord where plan_id in (select plan_id from planning where cart_id= :cartId and cus_id = :cusId)",
					Trip_ord.class);
			query1.setParameter("cartId", cartId);
			query1.setParameter("cusId", cusId);
			List<Trip_ord> list1 = query1.list();
			
			Map<Trip_ord,List<String>> map  = new LinkedHashMap<Trip_ord,List<String>>();
			for (Trip_ord ord: list1) {
				List<String> info = new ArrayList<String>();
//				Trip trip = getSession().get(Trip.class, ord.getTripId());
					info.add(getSession().get(Trip.class, ord.getTripId()).getTripName());//String tripName
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

	
	
	public Integer updateCommentAndScore(Integer tripOrd , Integer score , String comment ,Timestamp commentsTime) {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery("UPDATE Trip_ord SET score = :score, comments = :comments  , commentsTime = :commentsTime WHERE tripOrdId = :tripOrdId");
			query.setParameter("comments", comment);
			query.setParameter("score", score);
			query.setParameter("tripOrdId", tripOrd);
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
	public Map<Trip_ord,List<String>> getTripOrdByCompId(Integer compId) {
		try {
			getSession().beginTransaction();
			List<Trip_ord> list = getSession()
					.createQuery("from Trip_ord where comp_id = :compId and ord_status != 0 order by ord_time desc", Trip_ord.class)
					.setParameter("compId", compId).list();
			Map<Trip_ord,List<String>> map = new LinkedHashMap();
			for (Trip_ord ord : list) {
				List<String> info = new ArrayList<String>();
				Trip room =getSession().get(Trip.class, ord.getTripId());
				Consumer consumer =getSession().get(Consumer.class, ord.getCusId());
				info.add(room.getTripName());
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
//	public static void main(String[] args) {
//		Trip_ordDAO_Interface dao=new Trip_ordHibernateDAO();
//		Date date = new Date();
//		Timestamp nowTime = new Timestamp(date.getTime());

	// insert
//		Trip_ord tripOrd1 = new Trip_ord();

//		//tripOrd1.setTripOrdId(6);
//		tripOrd1.setTripId(306);
//		tripOrd1.setPlanId(206);
//		tripOrd1.setCusId(106);
//		tripOrd1.setAmount(3);
//		tripOrd1.setTotalPrice(new BigDecimal(50000));
//		tripOrd1.setCommission(new BigDecimal(5000));
//		tripOrd1.setOrdStatus(0);
//		tripOrd1.setOrdTime(nowTime);
//		tripOrd1.setRemark("remark_insert");
//		tripOrd1.setScore(10);
//		tripOrd1.setComments("comments_insert");
//		tripOrd1.setCommentsTime(nowTime);
//		dao.insert(tripOrd1);

	// update
//		Trip_ord tripOrd2 = new Trip_ord();

//		tripOrd2.setTripId(306);
//		tripOrd2.setPlanId(206);
//		tripOrd2.setCusId(106);
//		tripOrd2.setAmount(5);
//		tripOrd2.setTotalPrice(new BigDecimal(60000));
//		tripOrd2.setCommission(new BigDecimal(6000));
//		tripOrd2.setOrdStatus(0);
//		tripOrd2.setOrdTime(nowTime);
//		tripOrd2.setRemark("remark_update");
//		tripOrd2.setScore(10);
//		tripOrd2.setComments("comments_update");
//		tripOrd2.setCommentsTime(nowTime);
//		tripOrd2.setTripOrdId(6);
//		dao.update(tripOrd2);

	// delete
//		dao.delete(6);

	// findByPrimaryKey
//		Trip_ord tripOrd3 = dao.findByPrimaryKey(5);
//		System.out.println("----------------------------------------------------------------");
//		System.out.print(tripOrd3.getTripOrdId() + ", ");
//		System.out.print(tripOrd3.getTripId() + ", ");
//		System.out.print(tripOrd3.getPlanId() + ", ");
//		System.out.print(tripOrd3.getCusId() + ", ");
//		System.out.print(tripOrd3.getAmount() + ", ");
//		System.out.print(tripOrd3.getTotalPrice() + ", ");
//		System.out.print(tripOrd3.getCommission()+", ");
//		System.out.print(tripOrd3.getOrdStatus()+", ");
//		System.out.print(tripOrd3.getOrdTime()+", ");
//		System.out.print(tripOrd3.getRemark()+", ");
//		System.out.print(tripOrd3.getScore()+", ");
//		System.out.print(tripOrd3.getComments()+", ");
//		System.out.println(tripOrd3.getCommentsTime()+", ");

	// getAll
//		List<Trip_ord> list = dao.getAll();
//		for (Trip_ord aTrip : list) {
//			System.out.println("----------------------------------------------------------------");
//			System.out.print(aTrip.getTripOrdId() + ", ");
//			System.out.print(aTrip.getTripId() + ", ");
//			System.out.print(aTrip.getPlanId() + ", ");
//			System.out.print(aTrip.getCusId() + ", ");
//			System.out.print(aTrip.getAmount() + ", ");
//			System.out.print(aTrip.getTotalPrice() + ", ");
//			System.out.print(aTrip.getCommission()+", ");
//			System.out.print(aTrip.getOrdStatus()+", ");
//			System.out.print(aTrip.getOrdTime()+", ");
//			System.out.print(aTrip.getRemark()+", ");
//			System.out.print(aTrip.getScore()+", ");
//			System.out.print(aTrip.getComments()+", ");
//			System.out.println(aTrip.getCommentsTime()+", ");
//		}
//	}
}
