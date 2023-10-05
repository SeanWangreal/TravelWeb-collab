package com.tha103.gogoyu.room_stock.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.gogoyu.room.model.Room;

import util.HibernateUtil;

public class Room_stockHibernateDAO implements Room_stockDAO_interface {

	@Override
	public int add(Room_stock roomStock) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(roomStock);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Room_stock roomStock) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(roomStock);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer roomStockId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_stock roomStock = session.get(Room_stock.class, roomStockId);
			if (roomStock != null) {
				session.delete(roomStock);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Room_stock findByPK(Integer roomStockId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_stock roomStock = session.get(Room_stock.class, roomStockId);
			session.getTransaction().commit();
			return roomStock;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room_stock> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Room_stock> list = session.createQuery("from room_stock", Room_stock.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
