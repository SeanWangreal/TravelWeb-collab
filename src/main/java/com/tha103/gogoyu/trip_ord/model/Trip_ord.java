package com.tha103.gogoyu.trip_ord.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.trip.model.Trip;

@Entity
@Table(name = "trip_ord")
public class Trip_ord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trip_ord_id")
	private Integer tripOrdId;

	@Column(name = "trip_id")
	private Integer tripId;

	@Column(name = "comp_id")
	private Integer compId;

	@Column(name = "plan_id")
	private Integer planId;

	@Column(name = "cus_id")
	private Integer cusId;

	@Column(name = "amount")
	private Integer amount;
	@Column(name = "total_price")
	private BigDecimal totalPrice;
	@Column(name = "commission")
	private BigDecimal commission;
	@Column(name = "profit")
	private BigDecimal profit;
	@Column(name = "ord_status")
	private Integer ordStatus;
	
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "ord_time")
	private Timestamp ordTime;
	@Column(name = "remark")
	private String remark;
	@Column(name = "score")
	private Integer score;
	@Column(name = "comments", columnDefinition = "longtext")
	private String comments;
	@Column(name = "comments_time")
	private Timestamp commentsTime;

	public Trip_ord() {
		super();
	}

	public Trip_ord(Integer tripOrdId, Integer tripId, Integer planId, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission, Integer ordStatus, Timestamp ordTime, String remark,
			Integer score, String comments, Timestamp commentsTime) {
		super();
		this.tripOrdId = tripOrdId;
		this.tripId = tripId;
		this.planId = planId;
		this.cusId = cusId;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.commission = commission;
		this.ordStatus = ordStatus;
		this.ordTime = ordTime;
		this.remark = remark;
		this.score = score;
		this.comments = comments;
		this.commentsTime = commentsTime;
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

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Integer getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(Integer ordStatus) {
		this.ordStatus = ordStatus;
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
