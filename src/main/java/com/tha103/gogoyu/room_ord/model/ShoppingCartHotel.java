package com.tha103.gogoyu.room_ord.model;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.planning.model.Planning;
import com.tha103.gogoyu.room_photo.model.Room_photo;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;

public class ShoppingCartHotel {

	// �Ӥ�
	Room_photo photo;

	// �����W
	Company compName;

	// ������
	Trip_ord score;

	// �|��id
	String cusId;

	// �ʪ���id
	String planId;

	// �q��id
//	String orderId;

	public Room_photo getPhoto() {
		return photo;
	}

	public void setPhoto(Room_photo photo) {
		this.photo = photo;
	}

	public Company getCompName() {
		return compName;
	}

	public void setCompName(Company compName) {
		this.compName = compName;
	}

	public Trip_ord getScore() {
		return score;
	}

	public void setScore(Trip_ord score) {
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

}
