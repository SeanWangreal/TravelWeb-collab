package com.tha103.gogoyu.room_ord.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class Room_ordHibernateDAO implements Room_ordDAO_interface {
	private SessionFactory factory;

	//透過service帶session的建構子使用多型透過interface呼叫的hibernateDAO
	public Room_ordHibernateDAO(SessionFactory factory) {
		this.factory = factory;  //取得sessionfactory並回傳
	}
	
	private Session getSession() {
		return factory.getCurrentSession(); //取得CurrentSession，後續只要呼叫getSession()就可以開始交易
	}

	@Override
	public int add(Room_ord roomOrd) { //此傳入的是"無"pk的(這樣才能透過自增主鍵新增)
		try {
			getSession().beginTransaction();//開始交易
			Integer id = (Integer) getSession().save(roomOrd);  //傳入一個roomOrd並且建立在table內
			getSession().getTransaction().commit();//交易完成
			return id;  //回傳int型別pk去判斷是否有成功，假如不回傳-1就代表成功commit
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();//發生例外執行交易取消
		}
		return -1; //代表失敗rollback
	}

	@Override
	public int update(Room_ord roomOrd) {  //此傳入的是"有"pk的(這樣才能更新)
		try {
			getSession().beginTransaction();//開始交易
			getSession().update(roomOrd);//傳入一個roomOrd並在table內更新該欄位
			getSession().getTransaction().commit();//交易完成
			return 1; //回傳int 1，假如不回傳-1就代表成功commit
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();//發生例外執行交易取消
		}
		return -1;//代表失敗rollback
	}

	@Override
	public int delete(Integer roomOrdId) { //傳入一個pk，只需要提供pk即可將該筆資料刪除，不需要整個VO
		try {
			getSession().beginTransaction();
//			Room_ord roomOrd = getSession().get(Room_ord.class, roomOrdId); //在刪除前，要先查詢是否有該pk資料
//			if (roomOrd != null) {  //判斷假如有東西
				getSession().delete(roomOrdId);  //依照傳入pk刪除該datarow
//			}
			getSession().getTransaction().commit();//交易完成
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();//發生例外執行交易取消
		}
		return -1;
	}

	@Override
	public Room_ord findByPK(Integer roomOrdId) {
		try {
			getSession().beginTransaction();
			Room_ord roomOrd = getSession().get(Room_ord.class, roomOrdId);//傳入pk查詢該datarow資料
			getSession().getTransaction().commit(); //交易完成 
			return roomOrd; //回傳該table所查詢的"該"pk的物件
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();//發生例外執行交易取消
		}
		return null;
	}

	@Override
	public List<Room_ord> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Room_ord> list = session.createQuery("from Room_ord", Room_ord.class).list(); //用hql傳入於哪個table，回傳整個該table物件並集合成一個list
			session.getTransaction().commit(); //交易完成
			return list; //回傳該table所有的物件
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();//發生例外執行交易取消
		}
		return null; //如果該table沒有東西就回傳null
	}

}
