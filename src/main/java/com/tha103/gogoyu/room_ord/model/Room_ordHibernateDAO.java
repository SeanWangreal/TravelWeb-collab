package com.tha103.gogoyu.room_ord.model;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class Room_ordHibernateDAO implements Room_ordDAO_interface{

	@Override
	public int add(Room_ord roomOrd) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(roomOrd);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Room_ord roomOrd) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(roomOrd);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer roomOrdId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_ord roomOrd = session.get(Room_ord.class, roomOrdId);
			if (roomOrd != null) {
				session.delete(roomOrd);
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
	public Room_ord findByPK(Integer roomOrdId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_ord roomOrd = session.get(Room_ord.class, roomOrdId);
			session.getTransaction().commit();
			return roomOrd;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room_ord> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Room_ord> list = session.createQuery("from room_ord", Room_ord.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
