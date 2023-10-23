package com.tha103.gogoyu.room_collect.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class Room_collectHibernateDAO implements Room_collectDAO_interface {
	
	
	@Override
	public int insert(Room_collect roomCollect) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(roomCollect);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Room_collect roomCollect) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(roomCollect);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Integer cusId, Integer roomId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_collect roomCollect = session.get(Room_collect.class, new Room_collect.DoublePk(cusId, roomId));
			if (roomCollect != null) {
				session.delete(roomCollect);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Room_collect findByPrimaryKey(Integer cusId, Integer roomId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_collect roomCollect = session.get(Room_collect.class, new Room_collect.DoublePk(cusId, roomId));
			session.getTransaction().commit();
			return roomCollect;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Room_collect> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Room_collect> list = session.createQuery("from Room_collect", Room_collect.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Room_collectHibernateDAO hDao = new Room_collectHibernateDAO();
		Room_collect roomCollect = new Room_collect();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
		roomCollect.setCusId(74);
		roomCollect.setRoomId(3);
		roomCollect.setCollectTime(time_s);
//		System.out.println(hDao.insert(roomCollect));
//		System.out.println(hDao.update(roomCollect));
//		System.out.println(hDao.delete(13, 1));
//		System.out.println(hDao.findByPrimaryKey(13, 2));
		System.out.println(hDao.getAll());
	}

}
