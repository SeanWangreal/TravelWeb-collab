package com.tha103.gogoyu.trip_thumbup.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tha103.gogoyu.trip_thumbup.model.Trip_thumbup.DoublePk;

@Entity
@Table(name = "trip_thumbup")
@IdClass(DoublePk.class)
public class Trip_thumbup implements Serializable {
	@Id
	@Column(name = "trip_ord_id")
	private Integer tripOrdId;

	@Id
	@Column(name = "cus_id")
	private Integer cusId;
	@Column(name = "thumbup_time",insertable = false, updatable = false)
	private Timestamp thumbupTime;

	public Trip_thumbup() {
		super();
	}

	public Trip_thumbup(Integer tripOrdId, Integer cusId, Timestamp thumbupTime) {
		super();
		this.tripOrdId = tripOrdId;
		this.cusId = cusId;
		this.thumbupTime = thumbupTime;
	}

	public Integer getTripOrdId() {
		return tripOrdId;
	}

	public void setTripOrdId(Integer tripOrdId) {
		this.tripOrdId = tripOrdId;
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
		return "Trip_thumbup [tripOrdId=" + tripOrdId + ", cusId=" + cusId + ", thumbupTime=" + thumbupTime + "]";
	}

	static class DoublePk implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer tripOrdId;
		private Integer cusId;

		public DoublePk() {
			super();
		}

		public DoublePk(Integer tripOrdId, Integer cusId) {
			super();
			this.tripOrdId = tripOrdId;
			this.cusId = cusId;
		}

		public Integer getTripOrdId() {
			return tripOrdId;
		}

		public void setTripOrdId(Integer tripOrdId) {
			this.tripOrdId = tripOrdId;
		}

		public Integer getCusId() {
			return cusId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(cusId, tripOrdId);
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
			return Objects.equals(cusId, other.cusId) && Objects.equals(tripOrdId, other.tripOrdId);
		}

		public void setCusId(Integer cusId) {
			this.cusId = cusId;
		}
	}
}
