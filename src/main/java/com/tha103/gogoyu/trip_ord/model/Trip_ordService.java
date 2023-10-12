package com.tha103.gogoyu.trip_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Trip_ordService {
	private Trip_ordDAO_Interface dao;

	public Trip_ordService() {
		dao = new Trip_ordHibernateDAO();
	}

	public Trip_ord addEmp(Integer tripId, Integer planId, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission, Integer ordStatus, Timestamp ordTime, String remark, Integer score,
			String comments, Timestamp commentsTime) {

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
		dao.insert(tripOrd);

		return tripOrd;
	}

	public Trip_ord updateEmp(Integer tripId, Integer planId, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission, Integer ordStatus, Timestamp ordTime, String remark, 
			Integer score,String comments, Timestamp commentsTime,Integer tripOrdId) {

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

	public void deleteEmp(Integer tripOrdId) {
		dao.delete(tripOrdId);
	}

	public Trip_ord getOneEmp(Integer tripOrdId) {
		return dao.findByPrimaryKey(tripOrdId);
	}

	public List<Trip_ord> getAll() {
		return dao.getAll();
	}
}
