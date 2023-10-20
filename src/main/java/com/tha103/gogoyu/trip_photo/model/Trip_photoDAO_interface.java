package com.tha103.gogoyu.trip_photo.model;

import java.util.*;

public interface Trip_photoDAO_interface {
           int add(Trip_photo tripPhoto);
           int update(Trip_photo tripPhoto);
           int delete(Integer tripPhotoId);
           Trip_photo findByPK(Integer tripPhotoId);
           List<Trip_photo> getAll();
    
}
