package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.planning.model.Planning;
import com.tha103.gogoyu.room_photo.model.Room_photo;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;

public class ShoppingCartHotel {

	// 照片
//	private Room_photo photo;

	// 飯店
	private String compName;

	// 評分數
	private BigDecimal score;

	// 會員id
	private Integer cusId;

	// 購物車id
	private Integer planId;

	// 購物車號碼
	private Integer cart;

	// 價格
	private BigDecimal price;

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getCart() {
		return cart;
	}

	public void setCart(Integer cart) {
		this.cart = cart;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
