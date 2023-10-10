package com.tha103.gogoyu.notify.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.room_ord.model.Room_ord;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;

@Entity
@Table(name = "notify")
public class Notify {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notify_id", updatable = false)
	private Integer notifyId;
	
	@Column(name = "cus_id", insertable = false, updatable = false)
	private Integer cusId;
	@ManyToOne
	@JoinColumn(name = "cus_id", referencedColumnName = "cus_id")
	private Consumer  consumer;

	@Column(name = "comp_id", insertable = false, updatable = false)
	private Integer compId;
	@ManyToOne
	@JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
	private Company  company;

	@Column(name = "room_ord_id", insertable = false, updatable = false)
	private Integer roomOrdId;
	@ManyToOne
	@JoinColumn(name = "room_ord_id", referencedColumnName = "room_ord_id")
	private Room_ord  room_ord;

	@Column(name = "trip_ord_id", insertable = false, updatable = false)
	private Integer tripOrdId;
	@ManyToOne
	@JoinColumn(name = "trip_ord_id", referencedColumnName = "trip_ord_id")
	private Trip_ord  trip_ord;
	
	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Room_ord getRoom_ord() {
		return room_ord;
	}

	public void setRoom_ord(Room_ord room_ord) {
		this.room_ord = room_ord;
	}

	public Trip_ord getTrip_ord() {
		return trip_ord;
	}

	public void setTrip_ord(Trip_ord trip_ord) {
		this.trip_ord = trip_ord;
	}

	@Column(name = "contents")
	private String contents;

	@Column(name = "state", columnDefinition = "bit")
	private Byte state;

	@Column(name = "notify_time",insertable = false, updatable = false)
	private Timestamp notifyTime;

	public Notify() {
		super();
	}

	public Notify(Integer notifyId, Integer cusId, Integer compId, Integer roomOrdId, Integer tripOrdId,
			String contents, Byte state, Timestamp notifyTime) {
		super();
		this.notifyId = notifyId;
		this.cusId = cusId;
		this.compId = compId;
		this.roomOrdId = roomOrdId;
		this.tripOrdId = tripOrdId;
		this.contents = contents;
		this.state = state;
		this.notifyTime = notifyTime;
	}

	public Integer getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(Integer notifyId) {
		this.notifyId = notifyId;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Integer getRoomOrdId() {
		return roomOrdId;
	}

	public void setRoomOrdId(Integer roomOrdId) {
		this.roomOrdId = roomOrdId;
	}

	public Integer getTripOrdId() {
		return tripOrdId;
	}

	public void setTripOrdId(Integer tripOrdId) {
		this.tripOrdId = tripOrdId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Timestamp getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Timestamp notifyTime) {
		this.notifyTime = notifyTime;
	}

}
