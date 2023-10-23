package com.tha103.gogoyu.room_stock.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room_stock")
public class Room_stock implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_stock_id", updatable = false)
	private Integer roomStockId;
	
	@Column(name = "room_id", updatable = false)
	private Integer roomId;
	
	@Column(name ="stock_date")
	private Date stockDate;
	
	@Column(name = "stock")
	private Integer stock;
	
	public Room_stock() {
		super();
	}

	public Room_stock(Integer roomStockId, Integer roomId, Date stockDate, Integer stock) {
		super();
		this.roomStockId = roomStockId;
		this.roomId = roomId;
		this.stockDate = stockDate;
		this.stock = stock;
	}

	public Integer getRoomStockId() {
		return roomStockId;
	}

	public void setRoomStockId(Integer roomStockId) {
		this.roomStockId = roomStockId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
	
	
}
