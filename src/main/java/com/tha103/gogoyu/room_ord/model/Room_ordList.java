package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Room_ordList {
	String roomName;
	Integer RoomOrdId ;
	Integer CusId;
	String CompName;
	String roomType;
	Integer amount;
	String principalName;
	String principalPhone;
	Date startTime;
	Date endTime;
	Integer profit;
	Integer commission;
	Integer totalPrice;
	
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
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
	public Integer getCommission() {
		return commission;
	}
	public void setCommission(Integer commission) {
		this.commission = commission;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
