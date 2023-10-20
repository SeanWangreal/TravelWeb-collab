package com.tha103.gogoyu.trip_photo.model;

import java.util.List;

public interface Trip_photoService {
	public int addTripPhoto(Integer tripId,byte[] photo);
	public int updateTripPhoto(Integer tripPhotoId ,byte[] photo);
	public void deleteTripPhoto(Integer tripPhotoId);
	public byte[] getTripPhoto(Integer tripPhotoId);
	public List<Trip_photo> getAll();
	
}
