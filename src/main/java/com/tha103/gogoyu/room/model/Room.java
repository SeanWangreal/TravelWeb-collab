package com.tha103.gogoyu.room.model;

public class Room implements java.io.Serializable {
	

	private Integer room_id;
	
	private Integer comp_id;
	private Integer room_type;
	private String room_name;
	private Integer beds;
	private Double price;
	private String intro;
	private Integer room_status;
	private byte tissue;
	private byte shower;
	private byte bathroom;
	private byte dryer;
	private byte tub;
	private byte freetoiletries;
	private byte flushseat;
	private byte slippers;
	private byte bathrobe;
	private byte spatub;
	private byte electric_kettle;
	
	public Room(Integer room_id, Integer comp_id, Integer room_type, String room_name, Integer beds, Double price,
			String intro, Integer room_status, byte tissue, byte shower, byte bathroom, byte dryer, byte tub,
			byte freetoiletries, byte flushseat, byte slippers, byte bathrobe, byte spatub, byte electric_kettle) {
		super();
		this.room_id = room_id;
		this.comp_id = comp_id;
		this.room_type = room_type;
		this.room_name = room_name;
		this.beds = beds;
		this.price = price;
		this.intro = intro;
		this.room_status = room_status;
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
		this.electric_kettle = electric_kettle;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}

	public Integer getComp_id() {
		return comp_id;
	}

	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	public Integer getRoom_type() {
		return room_type;
	}

	public void setRoom_type(Integer room_type) {
		this.room_type = room_type;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
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

	public Integer getRoom_status() {
		return room_status;
	}

	public void setRoom_status(Integer room_status) {
		this.room_status = room_status;
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

	public byte getElectric_kettle() {
		return electric_kettle;
	}

	public void setElectric_kettle(byte electric_kettle) {
		this.electric_kettle = electric_kettle;
	}
	

	
	
}
