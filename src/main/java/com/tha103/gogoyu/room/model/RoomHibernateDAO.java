package com.tha103.gogoyu.room.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.room_photo.model.Room_photo;

import util.HibernateUtil;

public class RoomHibernateDAO implements RoomDAO_interface {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public RoomHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(Room room) {
		try {
			getSession().getTransaction().begin();
			Integer id = (Integer) getSession().save(room);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Room room) {
		try {
			getSession().getTransaction().begin();
			getSession().update(room);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer roomId) {
		try {
			getSession().beginTransaction();
			Room room = getSession().get(Room.class, roomId);
			if (room != null) {
				getSession().delete(room);
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
	public Room findByPK(Integer roomId) {
		try {
			getSession().beginTransaction();
			Room room = getSession().get(Room.class, roomId);
			for (Room_photo p : room.getRoomPhoto()) {
				System.out.println(p.getUploadTime());
			}
			getSession().getTransaction().commit();
			return room;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room> getAll() {
		try {
			getSession().beginTransaction();
			List<Room> list = getSession().createQuery("from Room", Room.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Room> findRoomByCompId(Integer compId) {

		try {
			getSession().beginTransaction();
			List<Room> list = getSession()
					.createQuery("from Room where comp_id = :comp_id order by room_id", Room.class)
					.setParameter("comp_id", compId).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public byte[] getMainPhoto(Integer roomId) {
		try {
			getSession().beginTransaction();
			byte[] mainPhoto = getSession().createQuery("select mainPhoto from Room where room_id = :room_id",byte[].class)
					.setParameter("room_id", roomId)
					.uniqueResult();
			getSession().getTransaction().commit();
			return mainPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	public static void main(String[] args) {
		RoomHibernateDAO dao = new RoomHibernateDAO(HibernateUtil.getSessionFactory());
//		System.out.println(dao.getMainPhoto(2));
//		dao.getAll();
	}

}
