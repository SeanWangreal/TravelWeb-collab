package com.tha103.gogoyu.trip_photo.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;


public class Trip_photoHibernateDAO implements Trip_photoDAO_interface {
	private SessionFactory factory;

	public Trip_photoHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(Trip_photo tripPhoto) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(tripPhoto);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Trip_photo tripPhoto) {
		try {
			getSession().beginTransaction();
			getSession().update(tripPhoto);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer tripPhotoId) {
		try {
			getSession().beginTransaction();
			Trip_photo tripPhoto = getSession().get(Trip_photo.class, tripPhotoId);
			if (tripPhoto != null) {
				getSession().delete(tripPhoto);
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
	public Trip_photo findByPK(Integer tripPhotoId) {
		try {
			getSession().beginTransaction();
			Trip_photo tripPhoto = getSession().get(Trip_photo.class, tripPhotoId);
			getSession().getTransaction().commit();
			return tripPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Trip_photo> getAll() {
		try {
			getSession().beginTransaction();
			List<Trip_photo> list = getSession().createQuery("from Trip_photo", Trip_photo.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	public static void main(String[] args) {
		Trip_photoHibernateDAO dao = new Trip_photoHibernateDAO(HibernateUtil.getSessionFactory());
		
	}
}
