package com.tha103.gogoyu.hotel_info.model;
import java.util.ArrayList;
import java.util.List;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.company.model.CompanyDAO_interface;
import com.tha103.gogoyu.company.model.CompanyHibernateDAO;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.room.model.RoomServiceHibernate;

import util.HibernateUtil;

public class Hotel_infoServiceHibernate {

		private  Hotel_infoDAO_interface dao;

		public Hotel_infoServiceHibernate() {
			dao = new Hotel_infoHibernateDAO(HibernateUtil.getSessionFactory());
		}

		public Hotel_info addHotel_info(Integer hotelInfoId, byte restaurant, byte roomService, byte alldayCounter, byte spa,
				byte gym, byte garden, byte terrace, byte noSmoking, byte freewifi, byte heater, byte beach, byte pool,
				byte chargingstation, byte parking) {

			Hotel_info hotelInfo = new Hotel_info();
			hotelInfo.setHotelInfoId(hotelInfoId);
			hotelInfo.setRestaurant(restaurant);
			hotelInfo.setRoomService(roomService);
			hotelInfo.setAlldayCounter(alldayCounter);
			hotelInfo.setSpa(spa);
			hotelInfo.setGym(gym);
			hotelInfo.setGarden(garden);
			hotelInfo.setTerrace(terrace);
			hotelInfo.setNoSmoking(noSmoking);
			hotelInfo.setFreewifi(freewifi);
			hotelInfo.setHeater(heater);
			hotelInfo.setBeach(beach);
			hotelInfo.setPool(pool);
			hotelInfo.setChargingstation(chargingstation);
			hotelInfo.setParking(parking);
			
			dao.add(hotelInfo);
			return hotelInfo;
		}

		public Hotel_info updateHotel_info(Integer hotelInfoId, byte restaurant, byte roomService, byte alldayCounter, byte spa,
				byte gym, byte garden, byte terrace, byte noSmoking, byte freewifi, byte heater, byte beach, byte pool,
				byte chargingstation, byte parking) {

			Hotel_info hotelInfo = new Hotel_info();
			hotelInfo.setHotelInfoId(hotelInfoId);
			hotelInfo.setRestaurant(restaurant);
			hotelInfo.setRoomService(roomService);
			hotelInfo.setAlldayCounter(alldayCounter);
			hotelInfo.setSpa(spa);
			hotelInfo.setGym(gym);
			hotelInfo.setGarden(garden);
			hotelInfo.setTerrace(terrace);
			hotelInfo.setNoSmoking(noSmoking);
			hotelInfo.setFreewifi(freewifi);
			hotelInfo.setHeater(heater);
			hotelInfo.setBeach(beach);
			hotelInfo.setPool(pool);
			hotelInfo.setChargingstation(chargingstation);
			hotelInfo.setParking(parking);
			
			dao.update(hotelInfo);
			return hotelInfo;
		}

		public void deleteHotel_info(Integer hotelInfoId) {
			dao.delete(hotelInfoId);
		}

		public Hotel_info getOneHotel_info(Integer hotelInfoId) {
			return dao.findByPK(hotelInfoId);
		}

		public List<Hotel_info> getAllHotel_info() {
			return dao.getAll();
		}
		
		public List<String> getHotelInfoList(Integer hoteInfoId){
			return dao.hotelInfoList(hoteInfoId);
		}
		
		public int updFromComp(Hotel_info hotel_info) {
			return dao.update(hotel_info);
		}
		
		public static void main(String[] args) {
			Hotel_infoServiceHibernate hi = new Hotel_infoServiceHibernate();
//			System.out.println(hi.getAllHotel_info());
			
		}
	}


