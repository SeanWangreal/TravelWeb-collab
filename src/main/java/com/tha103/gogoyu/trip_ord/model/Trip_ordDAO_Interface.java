package com.tha103.gogoyu.trip_ord.model;

import java.util.List;
import java.util.Map;

public interface Trip_ordDAO_Interface {
    public Integer add(Trip_ord tripOrd);
    public Integer update(Trip_ord tripOrd);
    public Integer delete(Integer tripOrdId);
    public Trip_ord findByPrimaryKey(Integer tripOrdId);
    public List<Trip_ord> getAll();
    public List<Trip_ord> getTripOrdVo(Integer cartId, Integer cusId);
    public Map<Trip_ord,List<String>> getTripOrdByCompId(Integer compId);
}

