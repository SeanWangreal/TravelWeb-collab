package com.tha103.gogoyu.notify.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notify")
public class Notify {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notify_id", updatable = false)
	private Integer notifyId;

	@Column(name = "cus_id", insertable = false, updatable = false)
	private Integer cusId;

	@Column(name = "comp_id", insertable = false, updatable = false)
	private Integer compId;

	@Column(name = "room_ord_id", insertable = false, updatable = false)
	private Integer roomOrdId;

	@Column(name = "trip_ord_id", insertable = false, updatable = false)
	private Integer tripOrdId;

	@Column(name = "contents")
	private String contents;

	@Column(name = "state")
	private Byte state;

	@Column(name = "notify_time")
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
