package com.tha103.gogoyu.trip_ord.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trip_ord")
public class Trip_ord implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="trip_ord_id")
	private Integer tripOrdId;
	@Column(name="trip_id")
	private Integer tripId;
	@Column(name="plan_id")
	private Integer planId;
	@Column(name="cus_id")
	private Integer cusId;
	@Column(name="amount")
	private Integer amount;
	@Column(name="total_price")
    private Double totalPrice;
	@Column(name="commission")
    private Double commission;
	@Column(name="order_status")
    private Integer orderStatus;
	@Column(name="ord_time")
    private Timestamp ordTime;
	@Column(name="remark")
    private String remark;
	@Column(name="score")
    private Integer score;
	@Column(name="comments")
    private String comments;
	@Column(name="comments_time")
    private Timestamp commentsTime;
    
	public Trip_ord() {
		super();
	}

	public Trip_ord(Integer tripOrdId, Integer tripId, Integer planId, Integer cusId, Integer amount,
			double totalPrice, Double commission, Integer orderStatus, Timestamp ordTime, String remark, Integer score,
			String comments, Timestamp commentsTime) {
		super();
		this.tripOrdId = tripOrdId;
		this.tripId = tripId;
		this.planId = planId;
		this.cusId = cusId;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.commission = commission;
		this.orderStatus = orderStatus;
		this.ordTime = ordTime;
		this.remark = remark;
		this.score = score;
		this.comments = comments;
		this.commentsTime = commentsTime;
	}

	public Integer getTripOrdId() {
		return tripOrdId;
	}

	public void setTripOrdId(Integer tripOrdId) {
		this.tripOrdId = tripOrdId;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getOrdTime() {
		return ordTime;
	}

	public void setOrdTime(Timestamp ordTime) {
		this.ordTime = ordTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getCommentsTime() {
		return commentsTime;
	}

	public void setCommentsTime(Timestamp commentsTime) {
		this.commentsTime = commentsTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

