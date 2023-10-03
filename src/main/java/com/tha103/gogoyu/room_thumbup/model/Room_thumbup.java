package com.tha103.gogoyu.room_thumbup.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tha103.gogoyu.room_thumbup.model.Room_thumbup.DoublePk;

@Entity
@Table(name = "room_thumbup")
@IdClass(DoublePk.class)
public class Room_thumbup implements Serializable {
	@Id
	@Column(name = "room_ord_id")
	private Integer roomOrdId;
	@Id
	@Column(name = "cus_id")
	private Integer cusId;
	@Column(name = "thumbup_time")
	private Timestamp thumbupTime;

	public Room_thumbup() {
		super();
	}

	public Room_thumbup(Integer roomOrdId, Integer cusId, Timestamp thumbupTime) {
		super();
		this.roomOrdId = roomOrdId;
		this.cusId = cusId;
		this.thumbupTime = thumbupTime;
	}

	public Integer getRoomOrdId() {
		return roomOrdId;
	}

	public void setRoomOrdId(Integer roomOrdId) {
		this.roomOrdId = roomOrdId;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Timestamp getThumbupTime() {
		return thumbupTime;
	}

	public void setThumbupTime(Timestamp thumbupTime) {
		this.thumbupTime = thumbupTime;
	}

	@Override
	public String toString() {
		return "Room_thumbup [roomOrdId=" + roomOrdId + ", cusId=" + cusId + ", thumbupTime=" + thumbupTime + "]";
	}

	static class DoublePk implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer roomOrdId;
		private Integer cusId;

		public DoublePk(Integer roomOrdId, Integer cusId) {
			super();
			this.roomOrdId = roomOrdId;
			this.cusId = cusId;
		}

		public DoublePk() {
			super();
		}

		public Integer getRoomOrdId() {
			return roomOrdId;
		}

		public void setRoomOrdId(Integer roomOrdId) {
			this.roomOrdId = roomOrdId;
		}

		public Integer getCusId() {
			return cusId;
		}

		public void setCusId(Integer cusId) {
			this.cusId = cusId;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(cusId, roomOrdId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoublePk other = (DoublePk) obj;
		return Objects.equals(cusId, other.cusId) && Objects.equals(roomOrdId, other.roomOrdId);
	}
}