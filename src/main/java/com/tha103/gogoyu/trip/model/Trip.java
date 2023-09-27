package com.tha103.gogoyu.trip.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer trip_id;
	private Integer comp_id;
	private String trip_name;
	private Integer amount;
	private Integer price;
	private Integer people;
	private Timestamp start_time;
	private Timestamp end_time;
	private String content; // CLOB
	private Integer state;
	private byte taipei_city;
	private byte newtaipei_city;
	private byte taoyuan_city;
	private byte taichung_city;
	private byte tainan_city;
	private byte kaohsiung_city;
	private byte hsinchu_county;
	private byte miaoli_county;
	private byte changhua_county;
	private byte nantou_county;
	private byte yunlin_county;
	private byte chiayi_county;
	private byte pingtung_county;
	private byte yilan_city;
	private byte hualien_city;
	private byte taitung_county;
	private byte kinmen_county;
	private byte lienchiang_county;
	private byte keelung_city;
	private byte hsinchu_city;
	private byte chiayi_city;
	private byte penghu_county;

	public Trip(Integer trip_id, Integer comp_id, String trip_name, Integer amount, Integer price, Integer people,
			Timestamp start_time, Timestamp end_time, String content, Integer state, byte taipei_city,
			byte newtaipei_city, byte taoyuan_city, byte taichung_city, byte tainan_city, byte kaohsiung_city,
			byte hsinchu_county, byte miaoli_county, byte changhua_county, byte nantou_county, byte yunlin_county,
			byte chiayi_county, byte pingtung_county, byte yilan_city, byte hualien_city, byte taitung_county,
			byte kinmen_county, byte lienchiang_county, byte keelung_city, byte hsinchu_city, byte chiayi_city,
			byte penghu_county) {
		super();
		this.trip_id = trip_id;
		this.comp_id = comp_id;
		this.trip_name = trip_name;
		this.amount = amount;
		this.price = price;
		this.people = people;
		this.start_time = start_time;
		this.end_time = end_time;
		this.content = content;
		this.state = state;
		this.taipei_city = taipei_city;
		this.newtaipei_city = newtaipei_city;
		this.taoyuan_city = taoyuan_city;
		this.taichung_city = taichung_city;
		this.tainan_city = tainan_city;
		this.kaohsiung_city = kaohsiung_city;
		this.hsinchu_county = hsinchu_county;
		this.miaoli_county = miaoli_county;
		this.changhua_county = changhua_county;
		this.nantou_county = nantou_county;
		this.yunlin_county = yunlin_county;
		this.chiayi_county = chiayi_county;
		this.pingtung_county = pingtung_county;
		this.yilan_city = yilan_city;
		this.hualien_city = hualien_city;
		this.taitung_county = taitung_county;
		this.kinmen_county = kinmen_county;
		this.lienchiang_county = lienchiang_county;
		this.keelung_city = keelung_city;
		this.hsinchu_city = hsinchu_city;
		this.chiayi_city = chiayi_city;
		this.penghu_county = penghu_county;
	}

	public Trip() {
		super();
	}

	public Integer getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}

	public Integer getComp_id() {
		return comp_id;
	}

	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	public String getTrip_name() {
		return trip_name;
	}

	public void setTrip_name(String trip_name) {
		this.trip_name = trip_name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
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

	public byte getTaipei_city() {
		return taipei_city;
	}

	public void setTaipei_city(byte taipei_city) {
		this.taipei_city = taipei_city;
	}

	public byte getNewTaipei_city() {
		return newtaipei_city;
	}

	public void setNewTaipei_city(byte newtaipei_city) {
		this.newtaipei_city = newtaipei_city;
	}

	public byte getTaoyuan_city() {
		return taoyuan_city;
	}

	public void setTaoyuan_city(byte taoyuan_city) {
		this.taoyuan_city = taoyuan_city;
	}

	public byte getTaichung_city() {
		return taichung_city;
	}

	public void setTaichung_city(byte taichung_city) {
		this.taichung_city = taichung_city;
	}

	public byte getTainan_city() {
		return tainan_city;
	}

	public void setTainan_city(byte tainan_city) {
		this.tainan_city = tainan_city;
	}

	public byte getKaohsiung_city() {
		return kaohsiung_city;
	}

	public void setKaohsiung_city(byte kaohsiung_city) {
		this.kaohsiung_city = kaohsiung_city;
	}

	public byte getHsinchu_county() {
		return hsinchu_county;
	}

	public void setHsinchu_county(byte hsinchu_county) {
		this.hsinchu_county = hsinchu_county;
	}

	public byte getMiaoli_county() {
		return miaoli_county;
	}

	public void setMiaoli_county(byte miaoli_county) {
		this.miaoli_county = miaoli_county;
	}

	public byte getChanghua_county() {
		return changhua_county;
	}

	public void setChanghua_county(byte changhua_county) {
		this.changhua_county = changhua_county;
	}

	public byte getNantou_county() {
		return nantou_county;
	}

	public void setNantou_county(byte nantou_county) {
		this.nantou_county = nantou_county;
	}

	public byte getYunlin_county() {
		return yunlin_county;
	}

	public void setYunlin_county(byte yunlin_county) {
		this.yunlin_county = yunlin_county;
	}

	public byte getChiayi_county() {
		return chiayi_county;
	}

	public void setChiayi_county(byte chiayi_county) {
		this.chiayi_county = chiayi_county;
	}

	public byte getPingtung_county() {
		return pingtung_county;
	}

	public void setPingtung_county(byte pingtung_county) {
		this.pingtung_county = pingtung_county;
	}

	public byte getYilan_city() {
		return yilan_city;
	}

	public void setYilan_city(byte yilan_city) {
		this.yilan_city = yilan_city;
	}

	public byte getHualien_city() {
		return hualien_city;
	}

	public void setHualien_city(byte hualien_city) {
		this.hualien_city = hualien_city;
	}

	public byte getTaitung_county() {
		return taitung_county;
	}

	public void setTaitung_county(byte taitung_county) {
		this.taitung_county = taitung_county;
	}

	public byte getKinmen_county() {
		return kinmen_county;
	}

	public void setKinmen_county(byte kinmen_county) {
		this.kinmen_county = kinmen_county;
	}

	public byte getLienchiang_county() {
		return lienchiang_county;
	}

	public void setLienchiang_county(byte lienchiang_county) {
		this.lienchiang_county = lienchiang_county;
	}

	public byte getKeelung_city() {
		return keelung_city;
	}

	public void setKeelung_city(byte keelung_city) {
		this.keelung_city = keelung_city;
	}

	public byte getHsinchu_city() {
		return hsinchu_city;
	}

	public void setHsinchu_city(byte hsinchu_city) {
		this.hsinchu_city = hsinchu_city;
	}

	public byte getChiayi_city() {
		return chiayi_city;
	}

	public void setChiayi_city(byte chiayi_city) {
		this.chiayi_city = chiayi_city;
	}

	public byte getPenghu_county() {
		return penghu_county;
	}

	public void setPenghu_county(byte penghu_county) {
		this.penghu_county = penghu_county;
	}

}
