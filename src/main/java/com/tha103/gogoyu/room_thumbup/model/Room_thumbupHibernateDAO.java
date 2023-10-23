package com.tha103.gogoyu.room_thumbup.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import util.HibernateUtil;

public class Room_thumbupHibernateDAO implements Room_thumbupDAO_interface{

	@Override
	public int insert(Room_thumbup roomThumbup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(roomThumbup);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Room_thumbup roomThumbup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(roomThumbup);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Integer roomOrdId, Integer cusId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_thumbup roomLike = session.get(Room_thumbup.class, new Room_thumbup.DoublePk(roomOrdId, cusId));
			if (roomLike != null) {
				session.delete(roomLike);
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
	public Room_thumbup findByPrimaryKey(Integer roomOrdId, Integer cusId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_thumbup roomLike = session.get(Room_thumbup.class, new Room_thumbup.DoublePk(roomOrdId, cusId));
			session.getTransaction().commit();
			return roomLike;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Room_thumbup> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Room_thumbup> list = session.createQuery("from Room_thumbup", Room_thumbup.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Room_thumbupHibernateDAO hDao = new Room_thumbupHibernateDAO();
		Room_thumbup roomThumbup = new Room_thumbup();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
		roomThumbup.setCusId(74);
		roomThumbup.setRoomOrdId(13);
		roomThumbup.setThumbupTime(time_s);
//		System.out.println(hDao.insert(tripThumbup));
//		System.out.println(hDao.update(tripThumbup));
//		System.out.println(hDao.delete(13, 1));
//		System.out.println(hDao.findByPrimaryKey(13, 2));
		System.out.println(hDao.getAll());
	} 
	

}
