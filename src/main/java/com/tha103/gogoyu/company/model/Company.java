package com.tha103.gogoyu.company.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tha103.gogoyu.hotel_info.model.Hotel_info;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.trip.model.Trip;

@Entity
@Table(name = "company")
public class Company implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "comp_id", updatable = false)
	private Integer compId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
	private Set<Room> room;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
	private Set<Trip> trip;

	public Set<Room> getRoom() {
		return room;
	}

	public void setRoom(Set<Room> room) {
		this.room = room;
	}

	public Set<Trip> getTrip() {
		return trip;
	}

	public void setTrip(Set<Trip> trip) {
		this.trip = trip;
	}
	
	@Column(name = "hotel_info_id", insertable = false, updatable = false) // fk
	private Integer hotelInfoId;
	
	@OneToOne
	@JoinColumn(name = "hotel_info_id",referencedColumnName = "hotel_info_id")
	private Hotel_info hotelInfo;
	
	public Hotel_info getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(Hotel_info hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

	@Column(name = "comp_type")
	private Integer compType;

	@Column(name = "comp_name")
	private String compName;

	@Column(name = "comp_address")
	private String compAddress;

	@Column(name = " comp_phone", columnDefinition = "character")
	private String compPhone;

	@Column(name = "principal_name")
	private String principalName;

	@Column(name = "principal_phone")
	private String principalPhone;

	@Column(name = "comp_account")
	private String compAccount;

	@Column(name = "comp_password")
	private String compPassword;

	@Column(name = "comp_mail")
	private String compMail;

	@Column(name = "comp_photo", columnDefinition = "longblob")
	private byte[] compPhoto;

	@Column(name = "check_status")
	private Integer checkStatus;

	public Company(Integer comp_id, Integer hotel_info_id, Integer comp_type, String comp_name, String comp_address,
			String comp_phone, String principal_name, String principal_phone, String comp_account, String comp_password,
			String comp_mail, byte[] comp_photo, Integer check_status) {
		super();
		this.compId = comp_id;
		this.hotelInfoId = hotel_info_id;
		this.compType = comp_type;
		this.compName = comp_name;
		this.compAddress = comp_address;
		this.compPhone = comp_phone;
		this.principalName = principal_name;
		this.principalPhone = principal_phone;
		this.compAccount = comp_account;
		this.compPassword = comp_password;
		this.compMail = comp_mail;
		this.compPhoto = comp_photo;
		this.checkStatus = check_status;
	}

	public Company() {
		super();
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Integer getHotelInfoId() {
		return hotelInfoId;
	}

	public void setHotelInfoId(Integer hotelInfoId) {
		this.hotelInfoId = hotelInfoId;
	}

	public Integer getCompType() {
		return compType;
	}

	public void setCompType(Integer compType) {
		this.compType = compType;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompAddress() {
		return compAddress;
	}

	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}

	public String getCompPhone() {
		return compPhone;
	}

	public void setCompPhone(String compPhone) {
		this.compPhone = compPhone;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public String getCompAccount() {
		return compAccount;
	}

	public void setCompAccount(String compAccount) {
		this.compAccount = compAccount;
	}

	public String getCompPassword() {
		return compPassword;
	}

	public void setCompPassword(String compPassword) {
		this.compPassword = compPassword;
	}

	public String getCompMail() {
		return compMail;
	}

	public void setCompMail(String compMail) {
		this.compMail = compMail;
	}

	public byte[] getCompPhoto() {
		return compPhoto;
	}

	public void setCompPhoto(byte[] compPhoto) {
		this.compPhoto = compPhoto;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

}
