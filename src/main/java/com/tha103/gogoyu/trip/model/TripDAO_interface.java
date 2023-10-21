package com.tha103.gogoyu.trip.model;

import java.util.List;

import com.tha103.gogoyu.room.model.Room;

public interface TripDAO_interface {
    int add(Trip Trip);
    int update(Trip Trip);
    int delete(Integer tripVO);
    Trip findByPK(Integer tripVO);
     List<Trip> getAll();
     public List<Trip> getHotTrip();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
