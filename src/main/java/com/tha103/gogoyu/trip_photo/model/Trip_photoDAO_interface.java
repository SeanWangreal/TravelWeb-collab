package com.tha103.gogoyu.trip_photo.model;

import java.util.*;

public interface Trip_photoDAO_interface {
           void insert(Trip_photo tripPhoto);
           void update(Trip_photo tripPhoto);
           void delete(Integer tripPhotoId);
           Trip_photo findByPrimaryKey(Integer tripPhotoId);
           List<Trip_photo> getAll();
}
