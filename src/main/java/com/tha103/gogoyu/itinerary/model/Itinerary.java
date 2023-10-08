package com.tha103.gogoyu.itinerary.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.scene.model.Scene;
import com.tha103.gogoyu.trip.model.Trip;

@Entity
@Table(name = "Itinerary")
public class Itinerary implements java.io.Serializable {
	@Id
	@Column(name = "itinerary_id", updatable = false) //pk無跟人關聯
	private Integer itineraryId;

	
	@Column(name = "trip_id")
	private Integer tripId;
//	@ManyToOne
//	@JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
//	private Trip trip;
	
	
	
	@Column(name = "scene_id")
	private Integer sceneId;
//	@ManyToOne
//	@JoinColumn(name = "scene_id", referencedColumnName = "scene_id")
//	private Scene scene;
	
	
	

	@Column(name = "begin_time")
	private Timestamp beginTime;

	public Itinerary(Integer itinerary_id, Integer trip_id, Integer scene_id, Timestamp begin_time) {
		super();
		this.itineraryId = itinerary_id;
		this.tripId = trip_id;
		this.sceneId = scene_id;
		this.beginTime = begin_time;
	}

	public Itinerary() {
		super();
	}

	public Integer getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(Integer itineraryId) {
		this.itineraryId = itineraryId;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	

}
