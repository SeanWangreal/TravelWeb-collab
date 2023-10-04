package com.tha103.gogoyu.trip_photo.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;


public class Trip_photoHibernateDAO implements Trip_photoDAO_interface {

	@Override
	public int add(Trip_photo trip_photo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(trip_photo);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Trip_photo trip_photo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(trip_photo);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer trip_photo_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip_photo trip_photo = session.get(Trip_photo.class, trip_photo_id);
			if (trip_photo != null) {
				session.delete(trip_photo);
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
	public Trip_photo findByPK(Integer trip_photo_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Trip_photo trip_photo = session.get(Trip_photo.class, trip_photo_id);
			session.getTransaction().commit();
			return trip_photo;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Trip_photo> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Trip_photo> list = session.createQuery("from Trip_photo", Trip_photo.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = fis.readAllBytes();
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {
		Trip_photoHibernateDAO dao = new Trip_photoHibernateDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
		
//		// 新增
//		Trip_photo trip_photo01 = new Trip_photo();
//		trip_photo01.setTripId(1);
//		try {
//			byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Pictures\\20230322_171359.jpg");
//			trip_photo01.setPhoto(pic);
//		} catch(IOException ie){
//			System.out.println(ie);
//		}
//		trip_photo01.setUploadTime(time_s);
//		dao.add(trip_photo01);

//		// 修改
//		Trip_photo trip_photo02 = new Trip_photo();
//		trip_photo02.setTripPhotoId(6);
//		trip_photo02.setTripId(1);
//		try {
//			byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Pictures\\20230322_171359.jpg");
//			trip_photo02.setPhoto(pic);
//		} catch(IOException ie) {
//			System.out.println(ie);
//		}
//		trip_photo02.setUploadTime(time_s);
//		dao.update(trip_photo02);

//		// 刪除
//		dao.delete(7);

//		// 查詢單筆
//		Trip_photo trip_photo03 = dao.findByPK(6);
//		System.out.print(trip_photo03.getTripPhotoId() + ",");
//		System.out.print(trip_photo03.getTripId() + ",");
//		System.out.print(trip_photo03.getPhoto() + ",");
//		System.out.println(trip_photo03.getUploadTime());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Trip_photo> list = dao.getAll();
//		for(Trip_photo aPhoto : list) {
//			System.out.print(aPhoto.getTripPhotoId() + ",");
//			System.out.print(aPhoto.getTripId() + ",");
//			System.out.print(aPhoto.getPhoto() + ",");
//			System.out.print(aPhoto.getUploadTime());
//			System.out.println();
//		}
	}
}
