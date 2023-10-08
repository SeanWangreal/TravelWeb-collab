package com.tha103.gogoyu.hotel_info.model;

import java.util.List;

public interface Hotel_infoDAO_interface {
    int add(Hotel_info Hotel_info);
    int update(Hotel_info Hotel_info);
    int delete(Integer hotel_info_id);
    Hotel_info findByPK(Integer hotel_info_id);
    List<Hotel_info> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
