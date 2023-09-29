package com.tha103.gogoyu.trip_collect.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tha103.gogoyu.trip_collect.model.Trip_collect.DoublePk;

@Entity
@Table(name = "trip_collect")
@IdClass(DoublePk.class)
public class Trip_collect implements Serializable {
	@Id
	@Column(name = "cus_id")
	private Integer cusId;
	@Id
	@Column(name = "trip_id")
	private Integer tripId;
	@Id
	@Column(name = "collect_time")
	private Timestamp collectTime;

	public Trip_collect() {
		super();
	}

	public Trip_collect(Integer cusId, Integer tripId, Timestamp collectTime) {
		super();
		this.cusId = cusId;
		this.tripId = tripId;
		this.collectTime = collectTime;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Timestamp getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Timestamp collectTime) {
		this.collectTime = collectTime;
	}

	static class DoublePk implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer cusId;
		private Integer tripId;

		public DoublePk() {
			super();
		}

		public DoublePk(Integer cusId, Integer tripId) {
			super();
			this.cusId = cusId;
			this.tripId = tripId;
		}

		public Integer getCusId() {
			return cusId;
		}

		public void setCusId(Integer cusId) {
			this.cusId = cusId;
		}

		public Integer getTripId() {
			return tripId;
		}

		public void setTripId(Integer tripId) {
			this.tripId = tripId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(cusId, tripId);
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
			return Objects.equals(cusId, other.cusId) && Objects.equals(tripId, other.tripId);
		}

	}

}