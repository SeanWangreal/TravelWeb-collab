package com.tha103.gogoyu.consumer.model;

import java.io.Serializable;

public class Consumer implements Serializable {
	private Integer cus_id;
	private String cus_name;
	private String cus_account;
	private String cus_password;
	private String cus_mail;
	private String cus_phone;
	private String cus_address;
	private Integer cus_sex;
	private byte[] cus_photo;

	public Consumer() {
		super();
	}

	public Consumer(Integer cus_id, String cus_name, String cus_account, String cus_password, String cus_mail,
			String cus_phone, String cus_address, Integer cus_sex, byte[] cus_photo) {
		super();
		this.cus_id = cus_id;
		this.cus_name = cus_name;
		this.cus_account = cus_account;
		this.cus_password = cus_password;
		this.cus_mail = cus_mail;
		this.cus_phone = cus_phone;
		this.cus_address = cus_address;
		this.cus_sex = cus_sex;
		this.cus_photo = cus_photo;
	}

	public Integer getCus_id() {
		return cus_id;
	}

	public void setCus_id(Integer cus_id) {
		this.cus_id = cus_id;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getCus_account() {
		return cus_account;
	}

	public void setCus_account(String cus_account) {
		this.cus_account = cus_account;
	}

	public String getCus_password() {
		return cus_password;
	}

	public void setCus_password(String cus_password) {
		this.cus_password = cus_password;
	}

	public String getCus_mail() {
		return cus_mail;
	}

	public void setCus_mail(String cus_mail) {
		this.cus_mail = cus_mail;
	}

	public String getCus_phone() {
		return cus_phone;
	}

	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}

	public String getCus_address() {
		return cus_address;
	}

	public void setCus_address(String cus_address) {
		this.cus_address = cus_address;
	}

	public Integer getCus_sex() {
		return cus_sex;
	}

	public void setCus_sex(Integer cus_sex) {
		this.cus_sex = cus_sex;
	}

	public byte[] getCus_photo() {
		return cus_photo;
	}

	public void setCus_photo(byte[] cus_photo) {
		this.cus_photo = cus_photo;
	}

}
