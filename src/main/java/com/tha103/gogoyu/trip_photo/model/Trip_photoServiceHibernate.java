package com.tha103.gogoyu.trip_photo.model;

import java.util.List;



import util.HibernateUtil;

public class Trip_photoServiceHibernate implements Trip_photoService{
	private Trip_photoDAO_interface dao;
	
	public Trip_photoServiceHibernate() {
		dao = new Trip_photoHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public int addTripPhoto(Integer tripId, byte[] photo) {
		Trip_photo tripPhoto = new Trip_photo();
		tripPhoto.setTripId(tripId);
		tripPhoto.setPhoto(photo);
		return dao.add(tripPhoto);
	}

	@Override
	public int updateTripPhoto(Integer tripPhotoId, byte[] photo) {
		Trip_photo tripPhoto = new Trip_photo();
		tripPhoto.setTripPhotoId(tripPhotoId);
		tripPhoto.setPhoto(photo);
		return dao.update(tripPhoto);
	}

	@Override
	public void deleteTripPhoto(Integer tripPhotoId) {
		dao.delete(tripPhotoId);
	}

	@Override
	public byte[] getTripPhoto(Integer tripPhotoId) {
		return dao.findByPK(tripPhotoId).getPhoto();
	}

	@Override
	public List<Trip_photo> getAll() {
		return dao.getAll();
	}

}
