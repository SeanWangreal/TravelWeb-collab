package com.tha103.gogoyu.trip_ord.model;

import java.util.List;

public interface Trip_ordDAO_Interface {
    public void insert(Trip_ord trip_ord);
    public void update(Trip_ord trip_ord);
    public void delete(Integer trip_ord_id);
    public Trip_ord findByPrimaryKey(Integer trip_ord_id);
    public List<Trip_ord> getAll();
}

