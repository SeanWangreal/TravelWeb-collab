package com.tha103.gogoyu.room_photo.model;

import java.util.List;

import util.HibernateUtil;

public class RoomPhotoServiceHibernate implements RoomPhotoService {
	private Room_photoDAO_interface dao;
	
	public RoomPhotoServiceHibernate() {
		dao = new Room_photoHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public Room_photo addRoomPhoto(Integer roomPhotoId, Integer roomId, byte[] photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room_photo updateRoomPhoto(Integer roomPhotoId, Integer roomId, byte[] photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRoomPhoto(Integer roomPhotoId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Room_photo getRoomPhoto(Integer roomPhotoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room_photo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
