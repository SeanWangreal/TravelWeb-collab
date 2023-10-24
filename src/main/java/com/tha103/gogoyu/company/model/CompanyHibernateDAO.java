package com.tha103.gogoyu.company.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;
import com.tha103.gogoyu.trip_ord.model.Trip_ordDAO_Interface;
import com.tha103.gogoyu.trip_ord.model.Trip_ordHibernateDAO;

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
	public int delete(Integer compId) {
		
		try {
			getSession().beginTransaction();
			Company comp = getSession().get(Company.class, compId);
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
	public List<Company> findByAccount(String compAccount) {
		
		try {
			getSession().beginTransaction();
			List<Company> list = getSession()
					.createQuery("from Company where comp_account = :compAccount", Company.class)
					.setParameter("compAccount", compAccount).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	

	@Override
	public Company findByPK(Integer compId) {
		
		try {
			getSession().beginTransaction();
			Company com= getSession().get(Company.class, compId);
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
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		CompanyHibernateDAO dao= new CompanyHibernateDAO(factory);
		List<Company> list=dao.getAll();
		System.out.print(list.get(0));
	}

	@Override
	public List<Company> getByCheckStatus() {
		
		try {
			getSession().beginTransaction();
			List<Company> list = getSession().createQuery("from Company where checkStatus = 0", Company.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public void updChkStatus(Integer compId, Integer checkStatus) {
		try {
			getSession().beginTransaction();
			Query query=getSession().createQuery("update Company set checkStatus=?0 where compId=?1");
			query.setParameter(0, checkStatus);
			query.setParameter(1, compId);
			query.executeUpdate();
			getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		
	}
	
	
	
//	public static void main(String[] args) {
//		CompanyDAO_interface dao=new CompanyHibernateDAO(HibernateUtil.getSessionFactory());
//		Date date = new Date();
//		Timestamp nowTime = new Timestamp(date.getTime());
//		
//		//getAll
//		List<Company> list = dao.getByCheckStatus();
//		for (Company aTrip : list) {
//			System.out.println("----------------------------------------------------------------");
//			System.out.print(aTrip.getCompId() + ", ");
//			System.out.print(aTrip.getHotelInfoId() + ", ");
//			System.out.print(aTrip.getCompType() + ", ");
//			System.out.print(aTrip.getCompName() + ", ");
//			System.out.print(aTrip.getCompAddress() + ", ");
//			System.out.print(aTrip.getCompPhone() + ", ");
//			System.out.print(aTrip.getPrincipalName()+", ");
//			System.out.print(aTrip.getPrincipalPhone()+", ");
//			System.out.print(aTrip.getCompAccount()+", ");
//			System.out.print(aTrip.getCompPassword()+", ");
//			System.out.print(aTrip.getCompMail()+", ");
//			System.out.print(aTrip.getCompPhoto()+", ");
//			System.out.println(aTrip.getCheckStatus()+", ");
//		}
//	}
}
