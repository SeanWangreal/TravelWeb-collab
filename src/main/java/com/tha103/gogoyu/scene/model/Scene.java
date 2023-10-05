package com.tha103.gogoyu.scene.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tha103.gogoyu.itinerary.model.Itinerary;

@Entity
@Table(name = "Scene")
public class Scene implements java.io.Serializable {
	@Id
	@Column(name = "scene_id", updatable = false)
	private Integer sceneId;
//	@OneToMany(mappedBy = "Scene")
//	@OrderBy("scene_id")
//	private Set<Itinerary> itinerarys;

	
	
	@Column(name = "scene_name")
	private String sceneName;

	@Column(name = "open_time", columnDefinition = "longtext")
	private String openTime;

	@Column(name = "ticket_price", columnDefinition = "longtext")
	private String ticketPrice;

	@Column(name = "trans_info", columnDefinition = "longtext")
	private String transInfo;

	@Column(name = "parking", columnDefinition = "longtext")
	private String parking;

	@Column(name = "address", columnDefinition = "longtext")
	private String address;

	@Column(name = "lon")
	private BigDecimal lon;

	@Column(name = "lat")
	private BigDecimal lat;

	@Column(name = "feature", columnDefinition = "longtext")
	private String feature;

	@Column(name = "picture")
	private String picture;

	public Scene() {
		super();
	}

	public Scene(Integer scene_id, String scene_name, String open_time, String ticket_price, String trans_info,
			String parking, String address, BigDecimal lon, BigDecimal lat, String feature, String picture) {
		super();
		this.sceneId = scene_id;
		this.sceneName = scene_name;
		this.openTime = open_time;
		this.ticketPrice = ticket_price;
		this.transInfo = trans_info;
		this.parking = parking;
		this.address = address;
		this.lon = lon;
		this.lat = lat;
		this.feature = feature;
		this.picture = picture;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getTransInfo() {
		return transInfo;
	}

	public void setTransInfo(String transInfo) {
		this.transInfo = transInfo;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
