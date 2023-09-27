package com.tha103.gogoyu.trip.model;

import java.util.List;

public interface TripDAO_interface {
    public void insert(Trip Trip);
    public void update(Trip Trip);
    public void delete(Integer tripVO);
    public Trip findByPrimaryKey(Integer tripVO);
    public List<Trip> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
