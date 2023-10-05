package com.tha103.gogoyu.consumer.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Consumer")
public class Consumer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cus_id")
	private Integer cusId;
	@Column(name = "cus_name")
	private String cusName;
	@Column(name = "cus_account")
	private String cusAccount;
	@Column(name = "cus_password")
	private String cusPassword;
	@Column(name = "cus_mail")
	private String cusMail;
	@Column(name = "cus_phone",columnDefinition = "char")
	private String cusPhone;
	@Column(name = "cus_address")
	private String cusAddress;
	@Column(name = "cus_sex")
	private Integer cusSex;
	@Column(name = "cus_photo", columnDefinition = "longblob")
	private byte[] cusPhoto;

	public Consumer() {
		super();
	}

	public Consumer(Integer cus_id, String cus_name, String cus_account, String cus_password, String cus_mail,
			String cus_phone, String cus_address, Integer cus_sex, byte[] cus_photo) {
		super();
		this.cusId = cus_id;
		this.cusName = cus_name;
		this.cusAccount = cus_account;
		this.cusPassword = cus_password;
		this.cusMail = cus_mail;
		this.cusPhone = cus_phone;
		this.cusAddress = cus_address;
		this.cusSex = cus_sex;
		this.cusPhoto = cus_photo;
	}
	
	public Integer getCusId() {
		return cusId;
	}
	
	public void setCusId(Integer cus_id) {
		this.cusId = cus_id;
	}
	
	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cus_name) {
		this.cusName = cus_name;
	}
	
	public String getCusAccount() {
		return cusAccount;
	}
	
	public void setCusAccount(String cus_account) {
		this.cusAccount = cus_account;
	}
	
	public String getCusPassword() {
		return cusPassword;
	}
	
	public void setCusPassword(String cus_password) {
		this.cusPassword = cus_password;
	}
	
	public String getCusMail() {
		return cusMail;
	}
	
	public void setCusMail(String cus_mail) {
		this.cusMail = cus_mail;
	}
	
	public String getCusPhone() {
		return cusPhone;
	}
	
	public void setCusPhone(String cus_phone) {
		this.cusPhone = cus_phone;
	}
	
	public String getCusAddress() {
		return cusAddress;
	}
	
	public void setCusAddress(String cus_address) {
		this.cusAddress = cus_address;
	}
	
	public Integer getCusSex() {
		return cusSex;
	}
	
	public void setCusSex(Integer cus_sex) {
		this.cusSex = cus_sex;
	}
	
	public byte[] getCusPhoto() {
		return cusPhoto;
	}
	
	public void setCusPhoto(byte[] cus_photo) {
		this.cusPhoto = cus_photo;
	}

	

}
