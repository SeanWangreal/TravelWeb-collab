package com.tha103.gogoyu.trip_photo.model;

import java.util.*;

public interface Trip_photoDAO_interface {
           int add(Trip_photo trip_photo);
           int update(Trip_photo trip_photo);
           int delete(Integer trip_photo_id);
           Trip_photo findByPK(Integer trip_photo_id);
           List<Trip_photo> getAll();
           
           //JDBC
//           void insert(Trip_photo trip_photo);
//           void update(Trip_photo trip_photo);
//           void delete(Integer trip_photo_id);
//           Trip_photo findByPrimaryKey(Integer trip_photo_id);
//           List<Trip_photo> getAll();
}
