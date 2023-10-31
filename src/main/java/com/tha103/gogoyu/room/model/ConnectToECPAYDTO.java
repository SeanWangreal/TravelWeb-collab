package com.tha103.gogoyu.room.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class ConnectToECPAYDTO {
	private Integer roomOrdId;
	private BigDecimal profit;
	private BigDecimal commission;
	private BigDecimal totalPrice;
	private String remark;
	private Timestamp ordTime;
	private Integer people;

	public Timestamp getOrdTime() {
		return ordTime;
	}

	public void setOrdTime(Timestamp ordTime) {
		this.ordTime = ordTime;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public Integer getRoomOrdId() {
		return roomOrdId;
	}

	public void setRoomOrdId(Integer roomOrdId) {
		this.roomOrdId = roomOrdId;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
