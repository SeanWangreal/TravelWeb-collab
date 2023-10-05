package com.tha103.gogoyu.hotel_info.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.gogoyu.scene.model.Scene;

import util.HibernateUtil;

public class Hotel_infoHibernateDAO implements Hotel_infoDAO_interface {
	@Override
	public int add(Hotel_info Hotel_info) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(Hotel_info);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Hotel_info Hotel_info) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(Hotel_info);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer Hotel_info_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Hotel_info hotel_info = session.get(Hotel_info.class, Hotel_info_id);
			if (hotel_info != null) {
				session.delete(hotel_info);
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
	public Hotel_info findByPK(Integer Hotel_info_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Hotel_info hotel_info = session.get(Hotel_info.class, Hotel_info_id);
			session.getTransaction().commit();
			return hotel_info;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Hotel_info> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Hotel_info> list = session.createQuery("from hotel_info", Hotel_info.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
