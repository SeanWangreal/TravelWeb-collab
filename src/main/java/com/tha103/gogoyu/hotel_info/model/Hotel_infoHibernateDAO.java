package com.tha103.gogoyu.hotel_info.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.scene.model.Scene;

import util.HibernateUtil;

public class Hotel_infoHibernateDAO implements Hotel_infoDAO_interface {
	
	private SessionFactory factory;

	public Hotel_infoHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(Hotel_info hotelInfo) {
		
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(hotelInfo);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Hotel_info hotelInfo) {
		
		try {
			getSession().beginTransaction();
			getSession().update(hotelInfo);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer hoteInfoIid) {
		
		try {
			getSession().beginTransaction();
			Hotel_info hotelInfo = getSession().get(Hotel_info.class, hoteInfoIid);
			if (hotelInfo != null) {
				getSession().delete(hotelInfo);
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
	public Hotel_info findByPK(Integer hoteInfoIid) {
		
		try {
			getSession().beginTransaction();
			Hotel_info hotelInfo = getSession().get(Hotel_info.class, hoteInfoIid);
			getSession().getTransaction().commit();
			return hotelInfo;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Hotel_info> getAll() {
		
		try {
			getSession().beginTransaction();
			List<Hotel_info> list = getSession().createQuery("from Hotel_info", Hotel_info.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

}
