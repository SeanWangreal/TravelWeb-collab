package com.tha103.gogoyu.trip_ord.model;

import java.util.List;

import com.tha103.gogoyu.trip.model.Trip;

public interface Trip_ordDAO_Interface {
    public Integer add(Trip_ord tripOrd);
    public Integer update(Trip_ord tripOrd);
    public Integer delete(Integer tripOrdId);
    public Trip_ord findByPrimaryKey(Integer tripOrdId);
    public List<Trip_ord> getAll();
}

