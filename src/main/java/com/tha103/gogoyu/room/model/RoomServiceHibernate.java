package com.tha103.gogoyu.room.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.tha103.gogoyu.room_photo.model.Room_photo;

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

	public Room updateRoom(Integer roomId, Integer compId, Integer roomType, String roomName, Integer beds, BigDecimal price,
			String intro, Integer roomStatus, byte tissue, byte shower, byte bathroom, byte dryer, byte tub,
			byte freetoiletries, byte flushseat, byte slippers, byte bathrobe, byte spatub, byte electricKettle,byte[] mainPhoto) {

		Room room = new Room();

		room.setRoomId(roomId);
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
		dao.update(room);

		return room;
	}

	public void deleteRoom(Integer roomId) {
		dao.delete(roomId);
	}

	public Room getOneRoom(Integer roomId) {
		return dao.findByPK(roomId);
	}

	public List<Room> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Room> getRoomByCompId(Integer compId) {
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
	public static void main(String[] args) {
		RoomServiceHibernate hi = new RoomServiceHibernate();
		System.out.println(hi.getAll());
	}



}
