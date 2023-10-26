package com.tha103.gogoyu.room.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.room_ord.model.Room_ord;
import com.tha103.gogoyu.room_photo.model.Room_photo;

import util.HibernateUtil;

public class RoomHibernateDAO implements RoomDAO_interface {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public RoomHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

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
					.createQuery("from Room where comp_id = :comp_id order by room_status desc,room_id desc", Room.class)
					.setParameter("comp_id", compId).list();
//			CriteriaBuilder builder = getSession().getCriteriaBuilder();
//			CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
//			Root<Room> roomRoot = criteria.from(Room.class);
//			Join<Room, Room_photo> roomPhotoRoot = roomRoot.join("roomPhoto", JoinType.LEFT);
//			criteria.select(builder.array(roomRoot, roomPhotoRoot));
//			criteria.where(builder.equal(roomRoot.get("compId"), compId));
//			List<Object[]> rooms = getSession().createQuery(criteria).getResultList();
//			Map<Room, List<Room_photo>> map = new LinkedHashMap();
//			List<Room_photo> list = new ArrayList();
//			for (Object[] room : rooms) {
//				System.out.println(room[0]);
//				Room_photo tp = (Room_photo) room[1];
//				System.out.println(tp);
//				list.add(tp);
//				map.put((Room) room[0], list);
//			}
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
			byte[] mainPhoto = getSession()
					.createQuery("select mainPhoto from Room where room_id = :room_id", byte[].class)
					.setParameter("room_id", roomId).uniqueResult();
			getSession().getTransaction().commit();
			return mainPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public int deleteAllPhoto(Integer roomId) {
		try {
			getSession().beginTransaction();
			Room room = getSession().get(Room.class, roomId);
			for (Room_photo p : room.getRoomPhoto()) {
				getSession().delete(p);
			}
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	public Set<Room_photo> getAllPhoto(Integer roomId) {
		try {
			getSession().beginTransaction();
			Room room = getSession().get(Room.class, roomId);
//			CriteriaBuilder builder = getSession().getCriteriaBuilder();
//			CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
//			Root<Room> roomRoot = criteria.from(Room.class);
//			Join<Room, Room_photo> roomPhotoRoot = roomRoot.join("roomPhoto", JoinType.LEFT);
//			criteria.select(builder.array(roomRoot, roomPhotoRoot));
//			criteria.where(builder.equal(roomRoot.get("roomId"), roomId));
//			List<Object[]> rooms = getSession().createQuery(criteria).getResultList();
//			Map<Room, List<Room_photo>> map = new LinkedHashMap();
//			List<Room_photo> list = new ArrayList();
//			for (Object[] room : rooms) {
//				Room_photo tp = (Room_photo) room[1];
//				list.add(tp);
//			}
//			map.put((Room) rooms.get(0)[0], list);
			Set<Room_photo> set = room.getRoomPhoto();
			for (Room_photo rp: set) {
				getSession().get(Room_photo.class, rp.getRoomPhotoId());
			}
			getSession().getTransaction().commit();
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	public List<Room> getHotRoom() {
		try {
			getSession().beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery<Room> query1 = getSession().createNativeQuery("SELECT * FROM room WHERE room_id IN (SELECT room_id FROM (SELECT room_id, count(room_id) FROM room_ord GROUP BY room_id ORDER BY 2 DESC LIMIT 3) as xxx);", Room.class);
			List<Room> list = query1.list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
//	public Map<Room, String> gotRoomProdutDetail(Integer roomId) {
//	try {
//		getSession().beginTransaction();
//		Map<Room, String> map = new LinkedHashMap<Room, String>();
////		@SuppressWarnings("unchecked")
//		NativeQuery<Room> query1 = getSession().createNativeQuery(
//				"SELECT * FROM room WHERE room_id IN "
//				+ "(SELECT r.room_id FROM room r"
//				+ " JOIN room_stock rs ON r.room_id = rs.room_id JOIN company c ON r.comp_id = c.comp_id WHERE (c.comp_address LIKE :comp_address) AND (rs.stock_date BETWEEN :checkIn AND :checkOut) AND (r.room_type <= :number) AND (rs.stock > 0) GROUP BY room_id)", Room.class)
//		.setParameter("comp_address", "'%"+comp_address+"%'")
//		.setParameter("checkIn", checkIn)
//		.setParameter("checkOut", checkOut)
//		.setParameter("number", number);
//		List<Room> list = query1.list();
//		for(Room room : list) {
//			Company company = getSession().get(Company.class, room.getCompId());
//			String compName = company.getCompName();
//			map.put(room, compName);
//		}
//		getSession().getTransaction().commit();
//		return map;
//	} catch (Exception e) {
//		e.printStackTrace();
//		getSession().getTransaction().rollback();
//	}
//	return null;
//}
	
	public List<Room> searchRoom(String comp_address,Date checkIn,Date checkOut,Integer number) {
		try {
			getSession().beginTransaction();
//			@SuppressWarnings("unchecked")
			System.out.println( "'%"+comp_address+"%'");
			System.out.println(checkIn);
			System.out.println(checkOut);
			System.out.println(number);
			NativeQuery<Room> query1 = getSession().createNativeQuery(
					"SELECT * FROM room WHERE room_id IN "
					+ "(SELECT r.room_id FROM room r"
					+ " JOIN room_stock rs ON r.room_id = rs.room_id JOIN company c ON r.comp_id = c.comp_id WHERE (c.comp_address LIKE :comp_address) AND (rs.stock_date BETWEEN :checkIn AND :checkOut) AND (r.room_type <= :number) AND (rs.stock > 0) GROUP BY room_id)", Room.class)
			.setParameter("comp_address", "'%"+comp_address+"%'")
			.setParameter("checkIn", checkIn)
			.setParameter("checkOut", checkOut)
			.setParameter("number", number);
			List<Room> list = query1.list();
			System.out.println(list);
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
//	public Map<Room, String> searchRoom(String comp_address,Date checkIn,Date checkOut,Integer number) {
//		try {
//			getSession().beginTransaction();
//			Map<Room, String> map = new LinkedHashMap<Room, String>();
////			@SuppressWarnings("unchecked")
//			NativeQuery<Room> query1 = getSession().createNativeQuery(
//					"SELECT * FROM room WHERE room_id IN "
//					+ "(SELECT r.room_id FROM room r"
//					+ " JOIN room_stock rs ON r.room_id = rs.room_id JOIN company c ON r.comp_id = c.comp_id WHERE (c.comp_address LIKE :comp_address) AND (rs.stock_date BETWEEN :checkIn AND :checkOut) AND (r.room_type <= :number) AND (rs.stock > 0) GROUP BY room_id)", Room.class)
//			.setParameter("comp_address", "'%"+comp_address+"%'")
//			.setParameter("checkIn", checkIn)
//			.setParameter("checkOut", checkOut)
//			.setParameter("number", number);
//			List<Room> list = query1.list();
//			for(Room room : list) {
//				Company company = getSession().get(Company.class, room.getCompId());
//				String compName = company.getCompName();
//				map.put(room, compName);
//			}
//			getSession().getTransaction().commit();
//			return map;
//		} catch (Exception e) {
//			e.printStackTrace();
//			getSession().getTransaction().rollback();
//		}
//		return null;
//	}
	
	
	
	public static void main(String[] args) {
		RoomHibernateDAO dao = new RoomHibernateDAO(HibernateUtil.getSessionFactory());
		System.out.println(dao.getAllPhoto(6));
	}


}
