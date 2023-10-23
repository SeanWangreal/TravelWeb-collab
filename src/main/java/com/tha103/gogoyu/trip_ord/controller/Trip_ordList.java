package com.tha103.gogoyu.trip_ord.controller;

import java.math.BigDecimal;
import java.sql.Date;

public class Trip_ordList {
	private Integer TripOrdId;
	private Integer cusId;
	private String tripName;
	private Integer people;
	private Integer amount;
	private String compName;
	private String principalName;
	private String PrincipalPhone;
	private Date startTime;
	private Date endTime;
	private Integer profit;
	private Integer commission;
	private Integer totalPrice;

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public Integer getCommission() {
		return commission;
	}

	public Integer getTripOrdId() {
		return TripOrdId;
	}

	public void setTripOrdId(Integer tripOrdId) {
		TripOrdId = tripOrdId;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPrincipalPhone() {
		return PrincipalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		PrincipalPhone = principalPhone;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getProfit() {
		return profit;
	}

	public void setProfit(Integer profit) {
		this.profit = profit;
	}

	public Integer getCommisson() {
		return commission;
	}

	public void setCommission(Integer commisson) {
		this.commission = commisson;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
