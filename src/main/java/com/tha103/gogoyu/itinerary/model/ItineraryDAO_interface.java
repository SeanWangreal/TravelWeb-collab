package com.tha103.gogoyu.itinerary.model;

import java.util.List;

public interface ItineraryDAO_interface {
    public void insert(Itinerary Itinerary);
    public void update(Itinerary Itinerary);
    public void delete(Integer itinerary_id);
    public Itinerary findByPrimaryKey(Integer itinerary_id);
    public List<Itinerary> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
