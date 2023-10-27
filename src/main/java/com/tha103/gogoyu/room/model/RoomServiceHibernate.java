package com.tha103.gogoyu.room.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tha103.gogoyu.room_photo.model.Room_photo;

import com.tha103.gogoyu.room_ord.model.Room_ord;

import util.HibernateUtil;

public class RoomServiceHibernate implements RoomService{

	private RoomDAO_interface dao;

	public RoomServiceHibernate() {
		dao = new RoomHibernateDAO(HibernateUtil.getSessionFactory());
	}

	public int addRoom(Integer compId, Integer roomType, String roomName, Integer beds, BigDecimal price,
			String intro, Integer roomStatus, byte tissue, byte shower, byte bathroom, byte dryer, byte tub,
			byte freetoiletries, byte flushseat, byte slippers, byte bathrobe, byte spatub, byte electricKettle,byte[] mainPhoto) {

		Room room = new Room();
		room.setCompId(compId);
		room.setRoomType(roomType);
		room.setRoomName(roomName);
		room.setBeds(beds);
		room.setPrice(price);
		room.setIntro(intro);
		room.setRoomStatus(roomStatus);
		room.setTissue(tissue);
		room.setShower(shower);
		room.setBathroom(bathroom);
		room.setDryer(dryer);
		room.setTub(tub);
		room.setFreetoiletries(freetoiletries);
		room.setFlushseat(flushseat);
		room.setSlippers(slippers);
		room.setBathrobe(bathrobe);
		room.setSpatub(spatub);
		room.setElectricKettle(electricKettle);
		room.setMainPhoto(mainPhoto);
		return dao.add(room);
	}
	public Room updateStatus(Integer roomId,Integer roomStatus) {
		Room room = this.getOneRoom(roomId);
		room.setRoomStatus(roomStatus);
		dao.update(room);
		return room;
	}

	public int updateRoom(Room room) {
		return dao.update(room);
	}

	public void deleteRoom(Integer roomId) {
		dao.delete(roomId);
	}

	public Room getOneRoom(Integer roomId) {
		return dao.findByPK(roomId);
	}
	
		@Override
		public Room getRoom(Integer roomId) {
			return dao.findByPK(roomId);
		}

	public List<Room> getAll() {
		return dao.getAll();
	}

	@Override
	public  List<Room> getRoomByCompId(Integer compId) {
		return dao.findRoomByCompId(compId);
	}

	@Override
	public byte[] getMainPhoto(Integer roomId) {
		return dao.getMainPhoto(roomId);
 
	}
	@Override
	public int deleteAllPhoto(Integer roomId) {
		return dao.deleteAllPhoto(roomId);
	}
	
	@Override
	public Set<Room_photo> getAllPhoto(Integer roomId) {
		return dao.getAllPhoto(roomId);
	}
	
	public List<Room> getHotRoom(){
		return dao.getHotRoom();
	}
	
//	public List<Room> searchRoom(String comp_address,Date checkIn,Date checkOut,Integer number){
//		return dao.searchRoom(comp_address, checkIn, checkOut, number);
//	}
	
	public Map<Room, String> searchRoom(String comp_address,Date checkIn,Date checkOut,Integer number){
		return dao.searchRoom(comp_address, checkIn, checkOut, number);
	}
	
	public List<Object> getRoomProdutDetail(Integer roomId){
		return dao.getRoomProdutDetail(roomId);
	}
	
	public static void main(String[] args) {
		RoomServiceHibernate hi = new RoomServiceHibernate();
		System.out.println(hi.getAll());
	}



}