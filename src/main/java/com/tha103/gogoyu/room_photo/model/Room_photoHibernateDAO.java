package com.tha103.gogoyu.room_photo.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class Room_photoHibernateDAO implements Room_photoDAO_interface {
	private SessionFactory factory;

	public  Room_photoHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	@Override
	public int add(Room_photo roomPhoto) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(roomPhoto);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Room_photo roomPhoto) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			getSession().beginTransaction();
			getSession().update(roomPhoto);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer roomPhotoId) {
		try {
			getSession().beginTransaction();
			Room_photo roomPhoto = getSession().get(Room_photo.class, roomPhotoId);
			if (roomPhoto != null) {
				getSession().delete(roomPhoto);
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
	public Room_photo findByPK(Integer roomPhotoId) {
		try {
			getSession().beginTransaction();
			Room_photo roomPhoto = getSession().get(Room_photo.class, roomPhotoId);
			getSession().getTransaction().commit();
			return roomPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room_photo> getAll() {
		try {
			getSession().beginTransaction();
			List<Room_photo> list = getSession().createQuery("from Room_photo", Room_photo.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

}
