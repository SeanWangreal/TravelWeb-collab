package com.tha103.gogoyu.room_photo.model;

import java.util.List;

import util.HibernateUtil;

public class RoomPhotoServiceHibernate implements RoomPhotoService {
	private Room_photoDAO_interface dao;
	
	public RoomPhotoServiceHibernate() {
		dao = new Room_photoHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public int addRoomPhoto(Integer roomId, byte[] photo) {
		Room_photo roomPhoto = new Room_photo();
		roomPhoto.setRoomId(roomId);
		roomPhoto.setPhoto(photo);
		return dao.add(roomPhoto);
	}

	@Override
	public int updateRoomPhoto(Integer roomPhotoId, byte[] photo) {
		Room_photo roomPhoto = new Room_photo();
		roomPhoto.setRoomPhotoId(roomPhotoId);
		roomPhoto.setPhoto(photo);
		return dao.update(roomPhoto);
	}

	@Override
	public void deleteRoomPhoto(Integer roomPhotoId) {
		dao.delete(roomPhotoId);
	}

	@Override
	public byte[] getRoomPhoto(Integer roomPhotoId) {
		return dao.findByPK(roomPhotoId).getPhoto();
	}

	@Override
	public List<Room_photo> getAll() {
		return dao.getAll();
	}

}
