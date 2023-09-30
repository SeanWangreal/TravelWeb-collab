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
	@Column(name = "cus_cusname")
	private String cusName;
	@Column(name = "cus_sccount")
	private String cusAccount;
	@Column(name = "cus_password")
	private String cusPassword;
	@Column(name = "cus_mail")
	private String cusMail;
	@Column(name = "cus_phone")
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
	
	public Integer getCus_id() {
		return cusId;
	}
	
	public void setCus_id(Integer cus_id) {
		this.cusId = cus_id;
	}
	
	public String getCus_name() {
		return cusName;
	}

	public void setCus_name(String cus_name) {
		this.cusName = cus_name;
	}
	
	public String getCus_account() {
		return cusAccount;
	}
	
	public void setCus_account(String cus_account) {
		this.cusAccount = cus_account;
	}
	
	public String getCus_password() {
		return cusPassword;
	}
	
	public void setCus_password(String cus_password) {
		this.cusPassword = cus_password;
	}
	
	public String getCus_mail() {
		return cusMail;
	}
	
	public void setCus_mail(String cus_mail) {
		this.cusMail = cus_mail;
	}
	
	public String getCus_phone() {
		return cusPhone;
	}
	
	public void setCus_phone(String cus_phone) {
		this.cusPhone = cus_phone;
	}
	
	public String getCus_address() {
		return cusAddress;
	}
	
	public void setCus_address(String cus_address) {
		this.cusAddress = cus_address;
	}
	
	public Integer getCus_sex() {
		return cusSex;
	}
	
	public void setCus_sex(Integer cus_sex) {
		this.cusSex = cus_sex;
	}
	
	public byte[] getCus_photo() {
		return cusPhoto;
	}
	
	public void setCus_photo(byte[] cus_photo) {
		this.cusPhoto = cus_photo;
	}

}
