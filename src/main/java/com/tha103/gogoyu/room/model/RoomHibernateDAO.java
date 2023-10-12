package com.tha103.gogoyu.room.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.gogoyu.room_photo.model.Room_photo;

import util.HibernateUtil;

public class RoomHibernateDAO implements RoomDAO_interface {

	@Override
	public int add(Room room) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(room);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Room room) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(room);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer roomId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room room = session.get(Room.class, roomId);
			if (room != null) {
				session.delete(room);
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
	public Room findByPK(Integer roomId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Room room = session.get(Room.class, roomId);
			for (Room_photo p : room.getRoomPhoto()) {
				System.out.println(p.getUploadTime());
			}
			session.getTransaction().commit();
			return room;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Room> list = session.createQuery("from Room", Room.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room> findRoomByCompId(Integer compId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Room> list = session.createQuery("from Room where comp_id = :comp_id order by room_id", Room.class).
					setParameter("comp_id", compId).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	public static void main(String[] args) {
		RoomHibernateDAO dao = new RoomHibernateDAO();
		System.out.println(dao.findRoomByCompId(1));
		dao.getAll();
	}
}
