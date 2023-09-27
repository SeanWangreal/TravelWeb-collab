package com.tha103.gogoyu.room_stock.model;

import java.sql.Date;

public class Room_stock implements java.io.Serializable {
	private Integer room_stock_id;
	private Integer room_id;
	private Date stock_date;
	private Integer stock;
	
	
	public Room_stock() {
		super();
	}


	public Room_stock(Integer room_stock_id, Integer room_id, Date stock_date, Integer stock) {
		super();
		this.room_stock_id = room_stock_id;
		this.room_id = room_id;
		this.stock_date = stock_date;
		this.stock = stock;
	}


	public Integer getRoom_stock_id() {
		return room_stock_id;
	}


	public void setRoom_stock_id(Integer room_stock_id) {
		this.room_stock_id = room_stock_id;
	}


	public Integer getRoom_id() {
		return room_id;
	}


	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}


	public Date getStock_date() {
		return stock_date;
	}


	public void setStock_date(Date stock_date) {
		this.stock_date = stock_date;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
