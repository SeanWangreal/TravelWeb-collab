package com.tha103.gogoyu.hotel_info.model;

import java.util.List;

public interface Hotel_infoDAO_interface {
    public void insert(Hotel_info Hotel_info);
    public void update(Hotel_info Hotel_info);
    public void delete(Integer hotel_info_id);
    public Hotel_info findByPrimaryKey(Integer hotel_info_id);
    public List<Hotel_info> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
