package com.tha103.gogoyu.room_collect.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tha103.gogoyu.room_collect.model.Room_collect.DoublePk;

@Entity
@Table(name = "room_collect")
@IdClass(DoublePk.class)
public class Room_collect implements Serializable {
	@Id
	@Column(name = "cus_id")
	private Integer cusId;
	@Id
	@Column(name = "room_id")
	private Integer roomId;
	@Column(name = "collect_time")
	private Timestamp collectTime;

	public Room_collect() {
		super();
	}

	public Room_collect(Integer cusId, Integer roomId, Timestamp collectTime) {
		super();
		this.cusId = cusId;
		this.roomId = roomId;
		this.collectTime = collectTime;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Timestamp getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Timestamp collectTime) {
		this.collectTime = collectTime;
	}

	@Override
	public String toString() {
		return "Room_collect [cusId=" + cusId + ", roomId=" + roomId + ", collectTime=" + collectTime + "]";
	}

	static class DoublePk implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer cusId;
		private Integer roomId;

		public DoublePk() {
			super();
		}

		public Integer getCusId() {
			return cusId;
		}

		public void setCusId(Integer cusId) {
			this.cusId = cusId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(cusId, roomId);
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
			return Objects.equals(cusId, other.cusId) && Objects.equals(roomId, other.roomId);
		}

		public Integer getRoomId() {
			return roomId;
		}

		public void setRoomId(Integer roomId) {
			this.roomId = roomId;
		}

		public DoublePk(Integer cusId, Integer roomId) {
			super();
			this.cusId = cusId;
			this.roomId = roomId;
		}

	}

}
