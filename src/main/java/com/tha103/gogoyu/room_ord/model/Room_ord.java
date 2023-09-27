package com.tha103.gogoyu.room_ord.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Room_ord implements Serializable {
	private Integer room_ord_id;
	private Integer plan_id;
	private Integer room_id;
	private Integer cus_id;
	private Integer amount;
	private Double total_price;
	private Double commission;
	private Integer people;
	private Timestamp check_in_time;
	private Timestamp check_out_time;
	private Integer ord_status;
	private Timestamp ord_time;
	private String remark;
	private Integer score;
	private String comments;
	private Timestamp comments_time;
	
	public Room_ord() {
		super();
	}
	
	
	
	
	public Room_ord(Integer room_ord_id, Integer plan_id, Integer room_id, Integer cus_id, Integer amount,
			Double total_price, Double commission, Integer people, Timestamp check_in_time, Timestamp check_out_time,
			Integer ord_status, Timestamp ord_time, String remark, Integer score, String comments, Timestamp comments_time) {
		super();
		this.room_ord_id = room_ord_id;
		this.plan_id = plan_id;
		this.room_id = room_id;
		this.cus_id = cus_id;
		this.amount = amount;
		this.total_price = total_price;
		this.commission = commission;
		this.people = people;
		this.check_in_time = check_in_time;
		this.check_out_time = check_out_time;
		this.ord_status = ord_status;
		this.ord_time = ord_time;
		this.remark = remark;
		this.score = score;
		this.comments = comments;
		this.comments_time = comments_time;
	}
	
	
	public Integer getRoom_ord_id() {
		return room_ord_id;
	}
	public void setRoom_ord_id(Integer room_ord_id) {
		this.room_ord_id = room_ord_id;
	}
	public Integer getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
	}
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public Integer getCus_id() {
		return cus_id;
	}
	public void setCus_id(Integer cus_id) {
		this.cus_id = cus_id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public Integer getPeople() {
		return people;
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
	public Timestamp getCheck_in_time() {
		return check_in_time;
	}
	public void setCheck_in_time(Timestamp check_in_time) {
		this.check_in_time = check_in_time;
	}
	public Timestamp getCheck_out_time() {
		return check_out_time;
	}
	public void setCheck_out_time(Timestamp check_out_time) {
		this.check_out_time = check_out_time;
	}
	public Integer getOrd_status() {
		return ord_status;
	}
	public void setOrd_status(Integer ord_status) {
		this.ord_status = ord_status;
	}
	public Timestamp getOrd_time() {
		return ord_time;
	}
	public void setOrd_time(Timestamp ord_time) {
		this.ord_time = ord_time;
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
	public Timestamp getComments_time() {
		return comments_time;
	}
	public void setComments_time(Timestamp comments_time) {
		this.comments_time = comments_time;
	}
	
	
	
	
}
