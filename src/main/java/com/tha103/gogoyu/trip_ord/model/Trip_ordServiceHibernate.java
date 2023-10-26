package com.tha103.gogoyu.trip_ord.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import com.tha103.gogoyu.trip.model.Trip;

import util.HibernateUtil;

public class Trip_ordServiceHibernate implements Trip_ordService {
	private Trip_ordDAO_Interface dao;

	public Trip_ordServiceHibernate() {
		dao = new Trip_ordHibernateDAO(HibernateUtil.getSessionFactory());
	}

	public Integer addFromShopping(Integer compId , Integer tripId, Integer planId, Integer cusId, Integer amount, BigDecimal totalPrice,
			BigDecimal commission, BigDecimal profit, Date startTime , Date endTime ,Integer ordStatus) {

		Trip_ord TripOrd = new Trip_ord();
		TripOrd.setCompId(compId);
		TripOrd.setTripId(tripId);
		TripOrd.setPlanId(planId);
		TripOrd.setCusId(cusId);
		TripOrd.setAmount(amount);
		TripOrd.setTotalPrice(totalPrice);
		TripOrd.setCommission(commission);
		TripOrd.setProfit(profit);
		TripOrd.setStartTime(startTime);
		TripOrd.setEndTime(endTime);
		TripOrd.setOrdStatus(ordStatus);
		return dao.add(TripOrd);
	}

	public Trip_ord addTrip(Integer tripId, Integer planId, Integer cusId, Integer amount, BigDecimal totalPrice,
			BigDecimal commission, Integer ordStatus, Timestamp ordTime, String remark, Integer score, String comments,
			Timestamp commentsTime) {

		Trip_ord tripOrd = new Trip_ord();

		tripOrd.setTripId(tripId);
		tripOrd.setPlanId(planId);
		tripOrd.setCusId(cusId);
		tripOrd.setAmount(amount);
		tripOrd.setTotalPrice(totalPrice);
		tripOrd.setCommission(commission);
		tripOrd.setOrdStatus(ordStatus);
		tripOrd.setOrdTime(ordTime);
		tripOrd.setRemark(remark);
		tripOrd.setScore(score);
		tripOrd.setComments(comments);
		tripOrd.setCommentsTime(commentsTime);
		dao.add(tripOrd);

		return tripOrd;
	}

	public Trip_ord updateTrip(Integer tripId, Integer planId, Integer cusId, Integer amount, BigDecimal totalPrice,
			BigDecimal commission, Integer ordStatus, Timestamp ordTime, String remark, Integer score, String comments,
			Timestamp commentsTime, Integer tripOrdId) {

		Trip_ord tripOrd = new Trip_ord();

		tripOrd.setTripId(tripId);
		tripOrd.setPlanId(planId);
		tripOrd.setCusId(cusId);
		tripOrd.setAmount(amount);
		tripOrd.setTotalPrice(totalPrice);
		tripOrd.setCommission(commission);
		tripOrd.setOrdStatus(ordStatus);
		tripOrd.setOrdTime(ordTime);
		tripOrd.setRemark(remark);
		tripOrd.setScore(score);
		tripOrd.setComments(comments);
		tripOrd.setCommentsTime(commentsTime);
		tripOrd.setTripOrdId(tripOrdId);
		dao.update(tripOrd);

		return tripOrd;
	}
	
	public Map<Trip_ord, List<String>> getTripOrdVo(Integer cartId , Integer cusId){
		return dao.getTripOrdVo(cartId , cusId);
	}

	public void deleteTrip(Integer tripOrdId) {
		dao.delete(tripOrdId);
	}

	public Trip_ord getOneTrip(Integer tripOrdId) {
		return dao.findByPrimaryKey(tripOrdId);
	}

	public List<Trip_ord> getAll() {
		return dao.getAll();
	}
	
	public Map<Trip_ord,List<String>> getTripOrdByCompId(Integer compId, Integer begin, String ordOrReview){
		return dao.getTripOrdByCompId(compId,begin, ordOrReview);
	}

	@Override
	public Map<Trip_ord, List<String>> getOneTripOrd(Integer tripOrdId, Integer compId) {
		return dao.getTripOrdByCompIdOrdId(tripOrdId, compId);
	}
}
