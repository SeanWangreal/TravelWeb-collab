package com.tha103.gogoyu.trip_ord.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Trip_ord implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer trip_ord_id;	
	private Integer trip_id;
	private Integer plan_id;
	private Integer cus_id;
	private Integer amount;
    private Double total_price;
    private Double commission;
    private Integer order_status;
    private Timestamp ord_time;
    private String remark;
    private Integer score;
    private String comments;
    private Timestamp comments_time;
    
	public Trip_ord() {
		super();
	}

	public Trip_ord(Integer trip_ord_id, Integer trip_id, Integer plan_id, Integer cus_id, Integer amount,
			double total_price, Double commission, Integer order_status, Timestamp ord_time, String remark, Integer score,
			String comments, Timestamp comments_time) {
		super();
		this.trip_ord_id = trip_ord_id;
		this.trip_id = trip_id;
		this.plan_id = plan_id;
		this.cus_id = cus_id;
		this.amount = amount;
		this.total_price = total_price;
		this.commission = commission;
		this.order_status = order_status;
		this.ord_time = ord_time;
		this.remark = remark;
		this.score = score;
		this.comments = comments;
		this.comments_time = comments_time;
	}

	public Integer getTrip_ord_id() {
		return trip_ord_id;
	}

	public void setTrip_ord_id(Integer trip_ord_id) {
		this.trip_ord_id = trip_ord_id;
	}

	public Integer getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}

	public Integer getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
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

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
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

