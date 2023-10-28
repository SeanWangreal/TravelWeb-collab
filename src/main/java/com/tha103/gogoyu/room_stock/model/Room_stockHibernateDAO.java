package com.tha103.gogoyu.room_stock.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class Room_stockHibernateDAO implements Room_stockDAO_interface {
	private SessionFactory factory;
	private final int DEFAULT_DAY = 30;
	private final long ONE_DAY = 1 * 24 * 60 * 60 * 1000L;

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

	@Override
	public List<Room_stock> getStockByRoomId(Integer roomId) {
		try {
			getSession().beginTransaction();
			List<Room_stock> list = getSession()
					.createQuery("from Room_stock where room_id = :roomId order by stock_date", Room_stock.class)
					.setParameter("roomId", roomId).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public void addFirstTime(Room_stock roomStock) {
		try {
			getSession().beginTransaction();
			Date firstDate = roomStock.getStockDate();
			Room_stock roomStockNew = null;
			long time = firstDate.getTime();
			for (int i = 0; i < DEFAULT_DAY; i++) {
				if (i == 0) {
					getSession().save(roomStock);
				} else {
					time += ONE_DAY;
					roomStockNew = new Room_stock();
					roomStockNew.setRoomId(roomStock.getRoomId());
					roomStockNew.setStock(roomStock.getStock());
					roomStockNew.setStockDate(new Date(time));
					getSession().save(roomStockNew);
				}
			}
			getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
	}
	
	public Integer searchMinRoomStockByTime(Integer roomId, Date checkIn, Date checkOut){
		getSession().beginTransaction();
		Long out = checkOut.getTime();
		out -= ONE_DAY;
		Date newDate = new Date(out);
		Integer minStock = getSession().createNativeQuery("SELECT MIN(stock) FROM room_stock WHERE (stock_date BETWEEN :checkIn AND :checkOut) AND room_id = :roomId;", Integer.class)
					.setParameter("checkIn", checkIn)
					.setParameter("checkOut", newDate)
					.setParameter("roomId", roomId)
					.uniqueResult();
		return minStock;
	}

	public static void main(String[] args) {
		Room_stockHibernateDAO dao = new Room_stockHibernateDAO(HibernateUtil.getSessionFactory());
//		Room_stock r = new Room_stock();
//		java.util.Date ee = new java.util.Date();
//		Date time = new Date(ee.getTime());
//		r.setRoomId(6);
//		r.setStockDate(time);
//		r.setStock(5);
//		dao.addFirstTime(r);
//		System.out.println(dao.getAllByToday(6));
	}

	@Override
	public List<Room_stock> getAllByToday(Integer roomId) {
		try {
			getSession().beginTransaction();
			Calendar cal = Calendar.getInstance();
			java.util.Date now = new java.util.Date();
			cal.setTime(now);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DATE);
			String formatDate = year+"-"+month+"-"+day;
			List<Room_stock> list = getSession()
					.createQuery("from Room_stock where stockDate >= :today and roomId = :roomId", Room_stock.class)
					.setParameter("today", Date.valueOf(formatDate))
					.setParameter("roomId", roomId)
					.list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public void updateAll(List<Room_stock> oldRoomStock, List<Room_stock> deleteRoomStock,
			List<Room_stock> newRoomStock) {
		try {
			getSession().beginTransaction();
			if (oldRoomStock != null) {
				for (Room_stock roomStock: oldRoomStock) {
					getSession().update(roomStock);
				}
			}
			if (deleteRoomStock != null ) {
				for (Room_stock roomStock: deleteRoomStock) {
					getSession().delete(roomStock);
				}
			}
			if (newRoomStock != null) {
				for (Room_stock roomStock: newRoomStock) {
					getSession().save(roomStock);
				}
			}
			getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		
	}



}
