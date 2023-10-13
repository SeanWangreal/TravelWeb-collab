package com.tha103.gogoyu.room.model;

import java.math.BigDecimal;
import java.util.Arrays;
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

import com.tha103.gogoyu.room_photo.model.Room_photo;
import com.tha103.gogoyu.room_stock.model.Room_stock;

@Entity
@Table(name = "room")
public class Room implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id", updatable = false)
	private Integer roomId;
	@Column(name = "comp_id", updatable = false)
	private Integer compId;
	@Column(name = "room_type")
	private Integer roomType;
	@Column(name = "room_name")
	private String roomName;
	@Column(name = "beds")
	private Integer beds;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "intro", columnDefinition = "longtext")
	private String intro;
	@Column(name = "room_status")
	private Integer roomStatus;
	@Column(name = "tissue", columnDefinition = "bit")
	private byte tissue;
	@Column(name = "shower", columnDefinition = "bit")
	private byte shower;
	@Column(name = "bathroom", columnDefinition = "bit")
	private byte bathroom;
	@Column(name = "dryer", columnDefinition = "bit")
	private byte dryer;
	@Column(name = "tub", columnDefinition = "bit")
	private byte tub;
	@Column(name = "freetoiletries", columnDefinition = "bit")
	private byte freetoiletries;
	@Column(name = "flushseat", columnDefinition = "bit")
	private byte flushseat;
	@Column(name = "slippers", columnDefinition = "bit")
	private byte slippers;
	@Column(name = "bathrobe", columnDefinition = "bit")
	private byte bathrobe;
	@Column(name = "spatub", columnDefinition = "bit")
	private byte spatub;
	@Column(name = "electric_kettle", columnDefinition = "bit")
	private byte electricKettle;
	@Column(name = "main_photo", columnDefinition = "longblob")
	private byte[] mainPhoto;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="room_id",referencedColumnName = "room_id")
	@OrderBy("upload_time asc")
	private Set<Room_photo> roomPhoto;

	public Set<Room_photo> getRoomPhoto() {
		return roomPhoto;
	}

	public void setRoomPhoto(Set<Room_photo> roomPhoto) {
		this.roomPhoto = roomPhoto;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="room_id",referencedColumnName = "room_id")
	@OrderBy("stock_date asc")
	private Set<Room_stock> roomStock;

	public Set<Room_stock> getRoomStock() {
		return roomStock;
	}

	public void setRoomStock(Set<Room_stock> roomStock) {
		this.roomStock = roomStock;
	}

	public Room() {
		super();
	}


	public byte[] getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(byte[] mainPhoto) {
		this.mainPhoto = mainPhoto;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getBeds() {
		return beds;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(Integer roomStatus) {
		this.roomStatus = roomStatus;
	}

	public byte getTissue() {
		return tissue;
	}

	public void setTissue(byte tissue) {
		this.tissue = tissue;
	}

	public byte getShower() {
		return shower;
	}

	public void setShower(byte shower) {
		this.shower = shower;
	}

	public byte getBathroom() {
		return bathroom;
	}

	public void setBathroom(byte bathroom) {
		this.bathroom = bathroom;
	}

	public byte getDryer() {
		return dryer;
	}

	public void setDryer(byte dryer) {
		this.dryer = dryer;
	}

	public byte getTub() {
		return tub;
	}

	public void setTub(byte tub) {
		this.tub = tub;
	}

	public byte getFreetoiletries() {
		return freetoiletries;
	}

	public void setFreetoiletries(byte freetoiletries) {
		this.freetoiletries = freetoiletries;
	}

	public byte getFlushseat() {
		return flushseat;
	}

	public void setFlushseat(byte flushseat) {
		this.flushseat = flushseat;
	}

	public byte getSlippers() {
		return slippers;
	}

	public void setSlippers(byte slippers) {
		this.slippers = slippers;
	}

	public byte getBathrobe() {
		return bathrobe;
	}

	public void setBathrobe(byte bathrobe) {
		this.bathrobe = bathrobe;
	}

	public byte getSpatub() {
		return spatub;
	}

	public void setSpatub(byte spatub) {
		this.spatub = spatub;
	}

	public byte getElectricKettle() {
		return electricKettle;
	}

	public void setElectricKettle(byte electricKettle) {
		this.electricKettle = electricKettle;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", compId=" + compId + ", roomType=" + roomType + ", roomName=" + roomName
				+ ", beds=" + beds + ", price=" + price + ", intro=" + intro + ", roomStatus=" + roomStatus
				+ ", tissue=" + tissue + ", shower=" + shower + ", bathroom=" + bathroom + ", dryer=" + dryer + ", tub="
				+ tub + ", freetoiletries=" + freetoiletries + ", flushseat=" + flushseat + ", slippers=" + slippers
				+ ", bathrobe=" + bathrobe + ", spatub=" + spatub + ", electricKettle=" + electricKettle
				;
	}
	
}
