package com.tha103.gogoyu.trip_photo.model;


import java.util.List;

public class Trip_photoServiceJDBC {

	private Trip_photoDAO_interface dao;

	public Trip_photoServiceJDBC() {
		dao = new Trip_photoJDBCDAO();
	}
	

	public Trip_photo addTripPhoto(Integer trip_id, byte[] photo) {
		Trip_photo trip_photo = new Trip_photo();
		trip_photo.setTripId(trip_id);
		trip_photo.setPhoto(photo);
		dao.add(trip_photo);
		return trip_photo;
	}

	public Trip_photo updateTripPhoto(Integer trip_photo_id, Integer trip_id, byte[] photo) {
		Trip_photo trip_photo = new Trip_photo();
		trip_photo.setTripPhotoId(trip_photo_id);
		trip_photo.setTripId(trip_id);
		trip_photo.setPhoto(photo);
		dao.update(trip_photo);
		return trip_photo;
	}

	public void deleteTripPhoto(Integer trip_photo_id) {
		dao.delete(trip_photo_id);
	}

	public Trip_photo getOneTripPhoto(Integer trip_photo_id) {
		return dao.findByPK(trip_photo_id);
	}

	public List<Trip_photo> getAll() {
		return dao.getAll();
	}
}
