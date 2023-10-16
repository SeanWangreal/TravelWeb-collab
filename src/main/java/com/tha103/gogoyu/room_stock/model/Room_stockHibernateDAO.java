package com.tha103.gogoyu.room_stock.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.room.model.Room;

import util.HibernateUtil;

public class Room_stockHibernateDAO implements Room_stockDAO_interface {
	private SessionFactory factory;
	
	public Room_stockHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}
	@Override
	public int add(Room_stock roomStock) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(roomStock);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Room_stock roomStock) {
		try {
			getSession().beginTransaction();
			getSession().update(roomStock);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer roomStockId) {
		try {
			getSession().beginTransaction();
			Room_stock roomStock = getSession().get(Room_stock.class, roomStockId);
			if (roomStock != null) {
				getSession().delete(roomStock);
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
	public Room_stock findByPK(Integer roomStockId) {
		try {
			getSession().beginTransaction();
			Room_stock roomStock = getSession().get(Room_stock.class, roomStockId);
			getSession().getTransaction().commit();
			return roomStock;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room_stock> getAll() {
		try {
			getSession().beginTransaction();
			List<Room_stock> list = getSession().createQuery("from Room_stock", Room_stock.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

}
