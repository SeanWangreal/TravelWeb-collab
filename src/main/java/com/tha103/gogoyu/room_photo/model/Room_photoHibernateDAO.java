package com.tha103.gogoyu.room_photo.model;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class Room_photoHibernateDAO implements Room_photoDAO_interface {

	@Override
	public int add(Room_photo roomPhoto) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(roomPhoto);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Room_photo roomPhoto) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(roomPhoto);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer roomPhotoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_photo roomPhoto = session.get(Room_photo.class, roomPhotoId);
			if (roomPhoto != null) {
				session.delete(roomPhoto);
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
	public Room_photo findByPK(Integer roomPhotoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room_photo roomPhoto = session.get(Room_photo.class, roomPhotoId);
			session.getTransaction().commit();
			return roomPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room_photo> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Room_photo> list = session.createQuery("from room_photo", Room_photo.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
