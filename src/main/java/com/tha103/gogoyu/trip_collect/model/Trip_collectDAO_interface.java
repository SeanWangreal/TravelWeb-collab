package com.tha103.gogoyu.trip_collect.model;

import java.util.List;

public interface Trip_collectDAO_interface {
	public int insert(Trip_collect tripCollect);

	public int update(Trip_collect tripCollect);

	public int delete(Integer tripId, Integer cusId);

	public Trip_collect findByPrimaryKey(Integer tripId, Integer cusId);

	public List<Trip_collect> getAll();
}
