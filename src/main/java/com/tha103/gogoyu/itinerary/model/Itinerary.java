package com.tha103.gogoyu.itinerary.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itinerary_id", updatable = false) //pk無跟人關聯
	private Integer itineraryId;
	
	@Column(name = "trip_id")
	private Integer tripId;
	
	@Column(name = "scene_id")
	private Integer sceneId;
	
	@Column(name = "scene_name")
	private String sceneName;

	@Column(name = "begin_time")
	private Timestamp beginTime;

	
	public Itinerary(Integer itineraryId, Integer tripId, Integer sceneId, String sceneName, Timestamp beginTime) {
		super();
		this.itineraryId = itineraryId;
		this.tripId = tripId;
		this.sceneId = sceneId;
		this.sceneName = sceneName;
		this.beginTime = beginTime;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
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
