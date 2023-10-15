package com.tha103.gogoyu.company.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.itinerary.model.Itinerary;

import util.HibernateUtil;

public class CompanyHibernateDAO implements CompanyDAO_interface {
	
	private SessionFactory factory;

	public CompanyHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public int add(Company Company) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(Company);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Company Company) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(Company);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer comp_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Company comp = session.get(Company.class, comp_id);
			if (comp != null) {
				session.delete(comp);
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
	public Company findByPK(Integer comp_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Company com= session.get(Company.class, comp_id);
			session.getTransaction().commit();
			return com;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Company> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Company> list = session.createQuery("from company", Company.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
