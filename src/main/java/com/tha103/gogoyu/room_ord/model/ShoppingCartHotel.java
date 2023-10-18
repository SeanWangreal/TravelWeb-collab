package com.tha103.gogoyu.room_ord.model;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.planning.model.Planning;
import com.tha103.gogoyu.room_photo.model.Room_photo;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;

public class ShoppingCartHotel {

	// 照片
	private Room_photo photo;

	// 飯店
	private String compName;

	// 評分數
	private String score;

	// 會員id
	private String cusId;

	// 購物車id
	private String planId;

	// 廠商類型
	private String compType;

	// 購物車號碼
	private String cartNum;

	// 價格
	private String price;

	public Room_photo getPhoto() {
		return photo;
	}

	public void setPhoto(Room_photo photo) {
		this.photo = photo;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getCompType() {
		return compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	public String getCartNum() {
		return cartNum;
	}

	public void setCartNum(String cartNum) {
		this.cartNum = cartNum;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
