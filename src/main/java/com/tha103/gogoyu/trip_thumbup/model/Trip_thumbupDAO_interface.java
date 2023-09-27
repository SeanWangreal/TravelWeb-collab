package com.tha103.gogoyu.trip_thumbup.model;

import java.util.List;


public interface Trip_thumbupDAO_interface {
	public void insert(Trip_thumbup trip_thumbup);
	public void update(Trip_thumbup trip_thumbup);
	public void delete(Integer trip_ord_id, Integer cus_id);
	public Trip_thumbup findByPrimaryKey(Integer trip_ord_id, Integer cus_id);
	public List<Trip_thumbup> getAll();
}
