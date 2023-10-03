package com.tha103.gogoyu.room.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id", updatable = false)
	private Integer roomId;
	@Column(name = "comp_id")
	private Integer compId;
	@Column(name = "room_type")
	private Integer roomType;
	@Column(name = "room_name")
	private String roomName;
	@Column(name = "beds")
	private Integer beds;
	@Column(name = "price")
	private Double price;
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
	
	public Room() {
		super();
	}

	public Room(Integer roomId, Integer compId, Integer roomType, String roomName, Integer beds, Double price,
			String intro, Integer roomStatus, byte tissue, byte shower, byte bathroom, byte dryer, byte tub,
			byte freetoiletries, byte flushseat, byte slippers, byte bathrobe, byte spatub, byte electricKettle) {
		super();
		this.roomId = roomId;
		this.compId = compId;
		this.roomType = roomType;
		this.roomName = roomName;
		this.beds = beds;
		this.price = price;
		this.intro = intro;
		this.roomStatus = roomStatus;
		this.tissue = tissue;
		this.shower = shower;
		this.bathroom = bathroom;
		this.dryer = dryer;
		this.tub = tub;
		this.freetoiletries = freetoiletries;
		this.flushseat = flushseat;
		this.slippers = slippers;
		this.bathrobe = bathrobe;
		this.spatub = spatub;
		this.electricKettle = electricKettle;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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
	
	
	
	
}
