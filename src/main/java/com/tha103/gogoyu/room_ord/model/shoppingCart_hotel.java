package com.tha103.gogoyu.room_ord.model;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.planning.model.Planning;
import com.tha103.gogoyu.room_photo.model.Room_photo;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;

public class shoppingCart_hotel {

	// �Ӥ�
	Room_photo photo;

	// �����W
	Company compName;

	// ������
	Trip_ord score;

	// �|��id
	Consumer cusId;

	// �W��id
	Planning planId;

	// �q��id
	Room_ord orderId;

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

	public Consumer getCusId() {
		return cusId;
	}

	public void setCusId(Consumer cusId) {
		this.cusId = cusId;
	}

	public Planning getPlanId() {
		return planId;
	}

	public void setPlanId(Planning planId) {
		this.planId = planId;
	}

	public Room_ord getOrderId() {
		return orderId;
	}

	public void setOrderId(Room_ord orderId) {
		this.orderId = orderId;
	}

}
