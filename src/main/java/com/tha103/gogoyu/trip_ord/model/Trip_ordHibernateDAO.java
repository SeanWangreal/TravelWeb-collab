package com.tha103.gogoyu.trip_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.room_ord.model.Room_ord;
import com.tha103.gogoyu.room_ord.model.Room_ordServiceHibernate;
import com.tha103.gogoyu.trip.model.Trip;

import util.HibernateUtil;

public class Trip_ordHibernateDAO implements Trip_ordDAO_Interface{
	private SessionFactory factory;

	//透過service帶session的建構子使用多型透過interface呼叫的hibernateDAO
	public Trip_ordHibernateDAO(SessionFactory factory) {
		this.factory = factory;  //取得sessionfactory並回傳
	}
	
	private Session getSession() {
		return factory.getCurrentSession(); //取得CurrentSession，後續只要呼叫getSession()就可以開始交易
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
	public List<Trip_ord> getTripOrdVo(Integer cartId, Integer cusId) {

		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Trip_ord> query1 = getSession().createNativeQuery(
					"SELECT * from trip_ord where plan_id in (select plan_id from planning where cart_id= :cartId and cus_id = :cusId)", Trip_ord.class);
						query1.setParameter("cartId", cartId);
						query1.setParameter("cusId", cusId);
			List<Trip_ord> list1 = query1.list();
			getSession().getTransaction().commit();
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	public static void main(String[] args) {
		Trip_ordServiceHibernate n = new Trip_ordServiceHibernate();
		List<Trip_ord>a = n.getTripOrdVo(1,1);
		for(Trip_ord a1 : a) {
			System.out.println(a1.getTripOrdId());
			
		}
	}
//	public static void main(String[] args) {
//		Trip_ordDAO_Interface dao=new Trip_ordHibernateDAO();
//		Date date = new Date();
//		Timestamp nowTime = new Timestamp(date.getTime());
		
		//insert
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
		
		//findByPrimaryKey
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
		
		//getAll
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
