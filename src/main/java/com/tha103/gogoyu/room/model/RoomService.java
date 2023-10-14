package com.tha103.gogoyu.room.model;

import java.math.BigDecimal;
import java.util.List;

public interface RoomService {
	public Room addRoom(Integer compId, Integer roomType, String roomName, Integer beds, BigDecimal price,
			String intro, Integer roomStatus, byte tissue, byte shower, byte bathroom, byte dryer, byte tub,
			byte freetoiletries, byte flushseat, byte slippers, byte bathrobe, byte spatub, byte electricKettle,byte[] mainPhoto);
	public Room updateStatus(Integer roomId,Integer roomStatus);
	public Room updateRoom(Integer roomId, Integer compId, Integer roomType, String roomName, Integer beds, BigDecimal price,
			String intro, Integer roomStatus, byte tissue, byte shower, byte bathroom, byte dryer, byte tub,
			byte freetoiletries, byte flushseat, byte slippers, byte bathrobe, byte spatub, byte electricKettle,byte[] mainPhoto);
	public void deleteRoom(Integer roomId);
	public Room getOneRoom(Integer roomId);
	public List<Room> getAll();
	public List<Room> getRoomByCompId(Integer compId);
	public byte[] getMainPhoto(Integer roomId);
}
