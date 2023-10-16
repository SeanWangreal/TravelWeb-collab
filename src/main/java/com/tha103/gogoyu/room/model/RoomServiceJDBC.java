package com.tha103.gogoyu.room.model;

import java.math.BigDecimal;
import java.util.List;

public class RoomServiceJDBC implements RoomService{

	private RoomDAO_interface dao;

	public RoomServiceJDBC() {
		dao = new RoomJDBCDAO();
	}

	public Room addRoom(Integer compId, Integer roomType, String roomName, Integer beds, BigDecimal price,
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
		dao.add(room);

		return room;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getMainPhoto(Integer roomId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
