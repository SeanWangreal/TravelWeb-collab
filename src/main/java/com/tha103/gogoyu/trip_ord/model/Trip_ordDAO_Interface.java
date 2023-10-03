package com.tha103.gogoyu.trip_ord.model;

import java.util.List;

public interface Trip_ordDAO_Interface {
    public void insert(Trip_ord tripOrd);
    public void update(Trip_ord tripOrd);
    public void delete(Integer tripOrdId);
    public Trip_ord findByPrimaryKey(Integer tripOrdId);
    public List<Trip_ord> getAll();
}

