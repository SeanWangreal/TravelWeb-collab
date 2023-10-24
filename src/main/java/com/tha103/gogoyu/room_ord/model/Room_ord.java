package com.tha103.gogoyu.room_ord.model;

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
import com.tha103.gogoyu.room.model.Room;

@Entity
@Table(name = "room_ord")
public class Room_ord implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_ord_id")
	private Integer roomOrdId;

	@Column(name = "plan_id")
	private Integer planId;

	@Column(name = "room_id")
	private Integer roomId;
	
	@Column(name = "comp_id")
	private Integer compId;
	
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
	
	@Column(name = "people")
	private Integer people;
	
	@Column(name = "check_in_time", insertable = false, updatable = false)
	private Date checkInTime;
	
	@Column(name = "check_out_time", insertable = false, updatable = false)
	private Date checkOutTime;
	
	@Column(name = "ord_status")
	private Integer ordStatus;
	
	@Column(name = "ord_time", insertable = false, updatable = false)
	private Timestamp ordTime;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "comments", columnDefinition = "longtext")
	private String comments;
	
	@Column(name = "comments_time", insertable = false, updatable = false)
	private Timestamp commentsTime;

	public Room_ord() {
		super();
	}

	public Room_ord(Integer roomOrdId, Integer planId, Integer roomId, Integer cusId, Integer amount,
			BigDecimal totalPrice, BigDecimal commission, Integer people, Date checkInTime, Date checkOutTime,
			Integer ordStatus, Timestamp ordTime, String remark, Integer score, String comments,
			Timestamp commentsTime) {
		super();
		this.roomOrdId = roomOrdId;
		this.planId = planId;
		this.roomId = roomId;
		this.cusId = cusId;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.commission = commission;
		this.people = people;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.ordStatus = ordStatus;
		this.ordTime = ordTime;
		this.remark = remark;
		this.score = score;
		this.comments = comments;
		this.commentsTime = commentsTime;
	}

	public Integer getRoomOrdId() {
		return roomOrdId;
	}

	public void setRoomOrdId(Integer roomOrdId) {
		this.roomOrdId = roomOrdId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
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

}
