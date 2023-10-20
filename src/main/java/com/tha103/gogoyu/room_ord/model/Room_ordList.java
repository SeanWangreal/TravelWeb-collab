package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Room_ordList {
	Integer RoomOrdId ;
	Integer CusId;
	String CompName;
	String roomType;
	Integer amount;
	String principalName;
	String principalPhone;
	Timestamp startTime;
	Timestamp endTime;
	BigDecimal profit;
	BigDecimal commission;
	BigDecimal totalPrice;
	public Integer getRoomOrdId() {
		return RoomOrdId;
	}
	public void setRoomOrdId(Integer roomOrdId) {
		RoomOrdId = roomOrdId;
	}
	public Integer getCusId() {
		return CusId;
	}
	public void setCusId(Integer cusId) {
		CusId = cusId;
	}
	public String getCompName() {
		return CompName;
	}
	public void setCompName(String compName) {
		CompName = compName;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
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
		return principalPhone;
	}
	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
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
	
}
