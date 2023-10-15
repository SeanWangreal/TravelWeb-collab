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
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(Company Company) {
		
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(Company);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Company Company) {
		
		try {
			getSession().beginTransaction();
			getSession().update(Company);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer comp_id) {
		
		try {
			getSession().beginTransaction();
			Company comp = getSession().get(Company.class, comp_id);
			if (comp != null) {
				getSession().delete(comp);
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
	public Company findByPK(Integer comp_id) {
		
		try {
			getSession().beginTransaction();
			Company com= getSession().get(Company.class, comp_id);
			getSession().getTransaction().commit();
			return com;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Company> getAll() {
		
		try {
			getSession().beginTransaction();
			List<Company> list = getSession().createQuery("from Company", Company.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

}
