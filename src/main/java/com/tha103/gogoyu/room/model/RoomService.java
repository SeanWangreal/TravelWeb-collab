package com.tha103.gogoyu.room.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.tha103.gogoyu.room_photo.model.Room_photo;

public interface RoomService {
	public int addRoom(Integer compId, Integer roomType, String roomName, Integer beds, BigDecimal price,
			String intro, Integer roomStatus, byte tissue, byte shower, byte bathroom, byte dryer, byte tub,
			byte freetoiletries, byte flushseat, byte slippers, byte bathrobe, byte spatub, byte electricKettle,byte[] mainPhoto);
	public Room updateStatus(Integer roomId,Integer roomStatus);
	public int updateRoom(Room room);
	public void deleteRoom(Integer roomId);
	public Room getOneRoom(Integer roomId);
	public List<Room> getAll();
	public List<Room> getRoomByCompId(Integer compId);
	public byte[] getMainPhoto(Integer roomId);
	public int deleteAllPhoto(Integer roomId);
	public Set<Room_photo> getAllPhoto(Integer roomId);
	public Room getRoom(Integer roomId);
}
