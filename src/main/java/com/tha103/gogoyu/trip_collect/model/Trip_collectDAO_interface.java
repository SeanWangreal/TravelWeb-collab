package com.tha103.gogoyu.trip_collect.model;

import java.util.List;


public interface Trip_collectDAO_interface {
	public void insert(Trip_collect trip_collect);
	public void update(Trip_collect trip_collect);
	public void delete(Integer trip_id, Integer cus_id);
	public Trip_collect findByPrimaryKey(Integer trip_id, Integer cus_id);
	public List<Trip_collect> getAll();
}
