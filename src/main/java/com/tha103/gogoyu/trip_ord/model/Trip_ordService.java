package com.tha103.gogoyu.trip_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import com.tha103.gogoyu.trip.model.Trip;

public interface Trip_ordService {
	
	public Trip_ord addTrip(Integer tripId, Integer planId, Integer cusId, Integer amount, BigDecimal totalPrice,
			BigDecimal commission, Integer ordStatus, Timestamp ordTime, String remark, Integer score, String comments,
			Timestamp commentsTime);
	
	public Trip_ord updateTrip(Integer tripId, Integer planId, Integer cusId, Integer amount, BigDecimal totalPrice,
			BigDecimal commission, Integer ordStatus, Timestamp ordTime, String remark, Integer score, String comments,
			Timestamp commentsTime, Integer tripOrdId);
	public void deleteTrip(Integer tripOrdId);
	public Trip_ord getOneTrip(Integer tripOrdId);
	public List<Trip_ord> getAll();
	public  Map<Trip_ord,List<String>> getOneTripOrd(Integer tripOrdId, Integer compId);
}
