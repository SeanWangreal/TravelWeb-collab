package com.tha103.gogoyu.trip.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;

@Entity
@Table(name = "Trip")
public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trip_id", updatable = false)
	private Integer tripId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="trip_id",referencedColumnName = "trip_id")
	@OrderBy("upload_time asc")
	private Set<Trip_photo> tripPhoto;
	
	public Set<Trip_photo> getTripPhoto() {
		return tripPhoto;
	}
	public void setTripPhoto(Set<Trip_photo> tripPhoto) {
		this.tripPhoto = tripPhoto;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="trip_id",referencedColumnName = "trip_id")
	@OrderBy("begin_time asc")
	private Set<Itinerary> itinerary;

	public Set<Itinerary> getItinerary() {
		return itinerary;
	}
	public void setItinerary(Set<Itinerary> itinerary) {
		this.itinerary = itinerary;
	}

	@Column(name = "comp_id" ) 
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
	private Date startTime;

	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "content", columnDefinition = "longtext")
	private String content;

	@Column(name = "state")
	private Integer state;

	@Column(name = "taipei_city",columnDefinition = "bit")
	private byte taipeiCity;

	@Column(name = "newtaipei_city",columnDefinition = "bit")
	private byte newtaipeiCity;

	@Column(name = "taoyuan_city",columnDefinition = "bit")
	private byte taoyuanCity;

	@Column(name = "taichung_city",columnDefinition = "bit")
	private byte taichungCity;

	@Column(name = " tainan_city",columnDefinition = "bit")
	private byte tainanCity;

	@Column(name = "kaohsiung_city",columnDefinition = "bit")
	private byte kaohsiungCity;

	@Column(name = "hsinchu_county",columnDefinition = "bit")
	private byte hsinchuCounty;

	@Column(name = "miaoli_county",columnDefinition = "bit")
	private byte miaoliCounty;

	@Column(name = "changhua_county",columnDefinition = "bit")
	private byte changhuaCounty;

	@Column(name = "nantou_county",columnDefinition = "bit")
	private byte nantouCounty;

	@Column(name = "yunlin_county",columnDefinition = "bit")
	private byte yunlinCounty;

	@Column(name = "chiayi_county",columnDefinition = "bit")
	private byte chiayiCounty;

	@Column(name = "pingtung_county",columnDefinition = "bit")
	private byte pingtungCounty;

	@Column(name = "yilan_city",columnDefinition = "bit")
	private byte yilanCity;

	@Column(name = "hualien_city",columnDefinition = "bit")
	private byte hualienCity;

	@Column(name = "taitung_county",columnDefinition = "bit")
	private byte taitungCounty;

	@Column(name = "kinmen_county",columnDefinition = "bit")
	private byte kinmenCounty;

	@Column(name = "lienchiang_county",columnDefinition = "bit")
	private byte lienchiangCounty;

	@Column(name = "keelung_city",columnDefinition = "bit")
	private byte keelungCity;

	@Column(name = "hsinchu_city",columnDefinition = "bit")
	private byte hsinchuCity;

	@Column(name = "chiayi_city",columnDefinition = "bit")
	private byte chiayiCity;

	@Column(name = "penghu_county",columnDefinition = "bit")
	private byte penghuCounty;
	
	@Column(name = "main_photo", columnDefinition = "longblob")
	private byte[] mainPhoto;

	public Trip(Integer tripId, Integer compId, String tripName, Integer amount, BigDecimal price, Integer people,
			Date  startTime, Date  endTime, String content, Integer state, byte taipeiCity, byte newtaipeiCity,
			byte taoyuanCity, byte taichungCity, byte tainanCity, byte kaohsiungCity, byte hsinchuCounty,
			byte miaoliCounty, byte changhuaCounty, byte nantouCounty, byte yunlinCounty, byte chiayiCounty,
			byte pingtungCounty, byte yilanCity, byte hualienCity, byte taitungCounty, byte kinmenCounty,
			byte lienchiangCounty, byte keelungCity, byte hsinchuCity, byte chiayiCity, byte penghuCounty,
			byte[] mainPhoto) {
		super();
		this.tripId = tripId;
		this.compId = compId;
		this.tripName = tripName;
		this.amount = amount;
		this.price = price;
		this.people = people;
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.state = state;
		this.taipeiCity = taipeiCity;
		this.newtaipeiCity = newtaipeiCity;
		this.taoyuanCity = taoyuanCity;
		this.taichungCity = taichungCity;
		this.tainanCity = tainanCity;
		this.kaohsiungCity = kaohsiungCity;
		this.hsinchuCounty = hsinchuCounty;
		this.miaoliCounty = miaoliCounty;
		this.changhuaCounty = changhuaCounty;
		this.nantouCounty = nantouCounty;
		this.yunlinCounty = yunlinCounty;
		this.chiayiCounty = chiayiCounty;
		this.pingtungCounty = pingtungCounty;
		this.yilanCity = yilanCity;
		this.hualienCity = hualienCity;
		this.taitungCounty = taitungCounty;
		this.kinmenCounty = kinmenCounty;
		this.lienchiangCounty = lienchiangCounty;
		this.keelungCity = keelungCity;
		this.hsinchuCity = hsinchuCity;
		this.chiayiCity = chiayiCity;
		this.penghuCounty = penghuCounty;
		this.mainPhoto = mainPhoto;
	}
	public Trip() {
		super();
	}

	public byte[] getMainPhoto() {
		return mainPhoto;
	}
	public void setMainPhoto(byte[] mainPhoto) {
		this.mainPhoto = mainPhoto;
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

	public Date  getStartTime() {
		return startTime;
	}

	public void setStartTime(Date  startTime) {
		this.startTime = startTime;
	}

	public Date  getEndTime() {
		return endTime;
	}

	public void setEndTime(Date  endTime) {
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
