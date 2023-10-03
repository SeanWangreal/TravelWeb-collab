package com.tha103.gogoyu.trip_thumbup.model;

import java.util.List;


public interface Trip_thumbupDAO_interface {
	public int insert(Trip_thumbup tripThumbup);
	public int update(Trip_thumbup tripThumbup);
	public int delete(Integer tripOrdId, Integer cusId);
	public Trip_thumbup findByPrimaryKey(Integer ttripOrdId, Integer cusId);
	public List<Trip_thumbup> getAll();
}
