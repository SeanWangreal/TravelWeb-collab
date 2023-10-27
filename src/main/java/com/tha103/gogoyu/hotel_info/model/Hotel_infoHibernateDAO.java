package com.tha103.gogoyu.hotel_info.model;

import java.util.ArrayList;
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
	public Hotel_info findByPK(Integer hoteInfoId) {
		
		try {
			getSession().beginTransaction();
			Hotel_info hotelInfo = getSession().get(Hotel_info.class, hoteInfoId);
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
	
	public List<String> hotelInfoList(Integer hoteInfoId){
		try {
			Hotel_info hotel_info = findByPK(hoteInfoId);
			List<String> hotelInfoList = new ArrayList();
			if(hotel_info.getRestaurant() == (byte)1) {
				hotelInfoList.add("餐廳");
			}
			if(hotel_info.getRoomService() == (byte)1) {
				hotelInfoList.add("客房服務");
			}
			if(hotel_info.getAlldayCounter() == (byte)1) {
				hotelInfoList.add("24小時接待櫃檯");
			}
			if(hotel_info.getSpa() == (byte)1) {
				hotelInfoList.add("SPA");
			}
			if(hotel_info.getGym() == (byte)1) {
				hotelInfoList.add("健身中心");
			}
			if(hotel_info.getGarden() == (byte)1) {
				hotelInfoList.add("花園");
			}
			if(hotel_info.getTerrace() == (byte)1) {
				hotelInfoList.add("露臺");
			}
			if(hotel_info.getNoSmoking() == (byte)1) {
				hotelInfoList.add("禁菸客房");
			}
			if(hotel_info.getFreewifi() == (byte)1) {
				hotelInfoList.add("免費無線網路");
			}
			if(hotel_info.getHeater() == (byte)1) {
				hotelInfoList.add("暖氣");
			}
			if(hotel_info.getBeach() == (byte)1) {
				hotelInfoList.add("海灘");
			}
			if(hotel_info.getPool() == (byte)1) {
				hotelInfoList.add("泳池");
			}
			if(hotel_info.getChargingstation() == (byte)1) {
				hotelInfoList.add("電動車充電站");
			}
			if(hotel_info.getParking() == (byte)1) {
				hotelInfoList.add("停車場");
			}
			return hotelInfoList;
		} catch(Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	public static void main (String[] args) {
		
	}
}
