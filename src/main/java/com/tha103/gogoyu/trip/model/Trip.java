package com.tha103.gogoyu.trip.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trip")
public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "trip_id", updatable = false)
	private Integer tripId;

	@Column(name = "comp_id")
	private Integer compId;

	@Column(name = "trip_name")
	private String tripName;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "people")
	private Integer people;

	@Column(name = "start_time")
	private Timestamp startTime;

	@Column(name = "end_time")
	private Timestamp endTime;

	@Column(name = "content", columnDefinition = "longtext")
	private String content; // CLOB

	@Column(name = "state")
	private Integer state;

	@Column(name = "taipei_city")
	private byte taipeiCity;

	@Column(name = "newtaipei_city")
	private byte newtaipeiCity;

	@Column(name = "taoyuan_city")
	private byte taoyuanCity;

	@Column(name = "taichung_city")
	private byte taichungCity;

	@Column(name = " tainan_city")
	private byte tainanCity;

	@Column(name = "kaohsiung_city")
	private byte kaohsiungCity;

	@Column(name = "hsinchu_county")
	private byte hsinchuCounty;

	@Column(name = "miaoli_county")
	private byte miaoliCounty;

	@Column(name = "changhua_county")
	private byte changhuaCounty;

	@Column(name = "nantou_county")
	private byte nantouCounty;

	@Column(name = "yunlin_county")
	private byte yunlinCounty;

	@Column(name = "chiayi_county")
	private byte chiayiCounty;

	@Column(name = "pingtung_county")
	private byte pingtungCounty;

	@Column(name = "yilan_city")
	private byte yilanCity;

	@Column(name = "hualien_city")
	private byte hualienCity;

	@Column(name = "taitung_county")
	private byte taitungCounty;

	@Column(name = "kinmen_county")
	private byte kinmenCounty;

	@Column(name = "lienchiang_county")
	private byte lienchiangCounty;

	@Column(name = "keelung_city")
	private byte keelungCity;

	@Column(name = "hsinchu_city")
	private byte hsinchuCity;

	@Column(name = "chiayi_city")
	private byte chiayiCity;

	@Column(name = "penghu_county")
	private byte penghuCounty;

	public Trip(Integer trip_id, Integer comp_id, String trip_name, Integer amount, BigDecimal price, Integer people,
			Timestamp start_time, Timestamp end_time, String content, Integer state, byte taipei_city,
			byte newtaipei_city, byte taoyuan_city, byte taichung_city, byte tainan_city, byte kaohsiung_city,
			byte hsinchu_county, byte miaoli_county, byte changhua_county, byte nantou_county, byte yunlin_county,
			byte chiayi_county, byte pingtung_county, byte yilan_city, byte hualien_city, byte taitung_county,
			byte kinmen_county, byte lienchiang_county, byte keelung_city, byte hsinchu_city, byte chiayi_city,
			byte penghu_county) {
		super();
		this.tripId = trip_id;
		this.compId = comp_id;
		this.tripName = trip_name;
		this.amount = amount;
		this.price = price;
		this.people = people;
		this.startTime = start_time;
		this.endTime = end_time;
		this.content = content;
		this.state = state;
		this.taipeiCity = taipei_city;
		this.newtaipeiCity = newtaipei_city;
		this.taoyuanCity = taoyuan_city;
		this.taichungCity = taichung_city;
		this.tainanCity = tainan_city;
		this.kaohsiungCity = kaohsiung_city;
		this.hsinchuCounty = hsinchu_county;
		this.miaoliCounty = miaoli_county;
		this.changhuaCounty = changhua_county;
		this.nantouCounty = nantou_county;
		this.yunlinCounty = yunlin_county;
		this.chiayiCounty = chiayi_county;
		this.pingtungCounty = pingtung_county;
		this.yilanCity = yilan_city;
		this.hualienCity = hualien_city;
		this.taitungCounty = taitung_county;
		this.kinmenCounty = kinmen_county;
		this.lienchiangCounty = lienchiang_county;
		this.keelungCity = keelung_city;
		this.hsinchuCity = hsinchu_city;
		this.chiayiCity = chiayi_city;
		this.penghuCounty = penghu_county;
	}

	public Trip() {
		super();
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public byte getTaipeiCity() {
		return taipeiCity;
	}

	public void setTaipeiCity(byte taipeiCity) {
		this.taipeiCity = taipeiCity;
	}

	public byte getNewtaipeiCity() {
		return newtaipeiCity;
	}

	public void setNewtaipeiCity(byte newtaipeiCity) {
		this.newtaipeiCity = newtaipeiCity;
	}

	public byte getTaoyuanCity() {
		return taoyuanCity;
	}

	public void setTaoyuanCity(byte taoyuanCity) {
		this.taoyuanCity = taoyuanCity;
	}

	public byte getTaichungCity() {
		return taichungCity;
	}

	public void setTaichungCity(byte taichungCity) {
		this.taichungCity = taichungCity;
	}

	public byte getTainanCity() {
		return tainanCity;
	}

	public void setTainanCity(byte tainanCity) {
		this.tainanCity = tainanCity;
	}

	public byte getKaohsiungCity() {
		return kaohsiungCity;
	}

	public void setKaohsiungCity(byte kaohsiungCity) {
		this.kaohsiungCity = kaohsiungCity;
	}

	public byte getHsinchuCounty() {
		return hsinchuCounty;
	}

	public void setHsinchuCounty(byte hsinchuCounty) {
		this.hsinchuCounty = hsinchuCounty;
	}

	public byte getMiaoliCounty() {
		return miaoliCounty;
	}

	public void setMiaoliCounty(byte miaoliCounty) {
		this.miaoliCounty = miaoliCounty;
	}

	public byte getChanghuaCounty() {
		return changhuaCounty;
	}

	public void setChanghuaCounty(byte changhuaCounty) {
		this.changhuaCounty = changhuaCounty;
	}

	public byte getNantouCounty() {
		return nantouCounty;
	}

	public void setNantouCounty(byte nantouCounty) {
		this.nantouCounty = nantouCounty;
	}

	public byte getYunlinCounty() {
		return yunlinCounty;
	}

	public void setYunlinCounty(byte yunlinCounty) {
		this.yunlinCounty = yunlinCounty;
	}

	public byte getChiayiCounty() {
		return chiayiCounty;
	}

	public void setChiayiCounty(byte chiayiCounty) {
		this.chiayiCounty = chiayiCounty;
	}

	public byte getPingtungCounty() {
		return pingtungCounty;
	}

	public void setPingtungCounty(byte pingtungCounty) {
		this.pingtungCounty = pingtungCounty;
	}

	public byte getYilanCity() {
		return yilanCity;
	}

	public void setYilanCity(byte yilanCity) {
		this.yilanCity = yilanCity;
	}

	public byte getHualienCity() {
		return hualienCity;
	}

	public void setHualienCity(byte hualienCity) {
		this.hualienCity = hualienCity;
	}

	public byte getTaitungCounty() {
		return taitungCounty;
	}

	public void setTaitungCounty(byte taitungCounty) {
		this.taitungCounty = taitungCounty;
	}

	public byte getKinmenCounty() {
		return kinmenCounty;
	}

	public void setKinmenCounty(byte kinmenCounty) {
		this.kinmenCounty = kinmenCounty;
	}

	public byte getLienchiangCounty() {
		return lienchiangCounty;
	}

	public void setLienchiangCounty(byte lienchiangCounty) {
		this.lienchiangCounty = lienchiangCounty;
	}

	public byte getKeelungCity() {
		return keelungCity;
	}

	public void setKeelungCity(byte keelungCity) {
		this.keelungCity = keelungCity;
	}

	public byte getHsinchuCity() {
		return hsinchuCity;
	}

	public void setHsinchuCity(byte hsinchuCity) {
		this.hsinchuCity = hsinchuCity;
	}

	public byte getChiayiCity() {
		return chiayiCity;
	}

	public void setChiayiCity(byte chiayiCity) {
		this.chiayiCity = chiayiCity;
	}

	public byte getPenghuCounty() {
		return penghuCounty;
	}

	public void setPenghuCounty(byte penghuCounty) {
		this.penghuCounty = penghuCounty;
	}

}
