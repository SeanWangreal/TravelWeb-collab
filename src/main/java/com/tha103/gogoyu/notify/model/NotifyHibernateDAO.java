package com.tha103.gogoyu.notify.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;


public class NotifyHibernateDAO implements NotifyDAO_interface {

	@Override
	public int add(Notify notify) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(notify);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Notify notify) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(notify);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer notifyId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Notify notify = session.get(Notify.class, notifyId);
			if (notify != null) {
				session.delete(notify);
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
	public Notify findByPK(Integer notifyId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Notify notify = session.get(Notify.class, notifyId);
			session.getTransaction().commit();
			return notify;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Notify> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Notify> list = session.createQuery("from Notify", Notify.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	public static void main(String[] args) {
		NotifyHibernateDAO dao = new NotifyHibernateDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
//		// 新增
//		Notify notify01 = new Notify();
//		notify01.setCusId(1);
//		notify01.setCompId(1);
//		notify01.setRoomOrdId(1);
//		notify01.setTripOrdId(1);
//		notify01.setContents("測試新增1");
//		notify01.setState((byte)0);
//		notify01.setNotifyTime(time_s);
//		dao.add(notify01);
	

//		// 修改
//		Notify notify02 = new Notify();
//		notify02.setNotifyId(11);
//		notify02.setCusId(1);
//		notify02.setCompId(1);
//		notify02.setRoomOrdId(1);
//		notify02.setTripOrdId(1);
//		notify02.setContents("測試修改");
//		notify02.setState((byte)1);
//		notify02.setNotifyTime(time_s);
//		dao.update(notify02);

//		// 刪除
//		dao.delete(12);

//		// 查詢單筆
//		Notify notify03 = dao.findByPK(1);
//		System.out.print(notify03.getNotifyId() + ",");
//		System.out.print(notify03.getCusId() + ",");
//		System.out.print(notify03.getCompId() + ",");
//		System.out.print(notify03.getRoomOrdId() + ",");
//		System.out.print(notify03.getTripOrdId() + ",");
//		System.out.print(notify03.getContents() + ",");
//		System.out.print(notify03.getState() + ",");
//		System.out.println(notify03.getNotifyTime());
//		System.out.println("---------------------");

		// 查詢多筆
//		List<Notify> list = dao.getAll();
//		for(Notify aNotify : list) {
//			System.out.print(aNotify.getNotifyId() + ",");
//			System.out.print(aNotify.getCusId() + ",");
//			System.out.print(aNotify.getCompId() + ",");
//			System.out.print(aNotify.getRoomOrdId() + ",");
//			System.out.print(aNotify.getTripOrdId() + ",");
//			System.out.print(aNotify.getContents() + ",");
//			System.out.print(aNotify.getState() + ",");
//			System.out.print(aNotify.getNotifyTime());
//			System.out.println();
//		}
	}
}
