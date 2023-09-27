package com.tha103.gogoyu.company.model;
public class Company implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer comp_id;
	private Integer hotel_info_id;
	private Integer comp_type;
	private String comp_name;
	private String comp_address;
	private String comp_phone;
	private String principal_name;
	private String principal_phone;
	private String comp_account;
	private String comp_password;
	private String comp_mail;
	private byte[] comp_photo;
	private Integer check_status;

	public Company(Integer comp_id, Integer hotel_info_id, Integer comp_type, String comp_name, String comp_address,
			String comp_phone, String principal_name, String principal_phone, String comp_account, String comp_password,
			String comp_mail, byte[] comp_photo, Integer check_status) {
		super();
		this.comp_id = comp_id;
		this.hotel_info_id = hotel_info_id;
		this.comp_type = comp_type;
		this.comp_name = comp_name;
		this.comp_address = comp_address;
		this.comp_phone = comp_phone;
		this.principal_name = principal_name;
		this.principal_phone = principal_phone;
		this.comp_account = comp_account;
		this.comp_password = comp_password;
		this.comp_mail = comp_mail;
		this.comp_photo = comp_photo;
		this.check_status = check_status;
	}
	
	public Company() {
		super();
	}

	public Integer getComp_id() {
		return comp_id;
	}

	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getComp_address() {
		return comp_address;
	}

	public void setComp_address(String comp_address) {
		this.comp_address = comp_address;
	}

	public String getComp_phone() {
		return comp_phone;
	}

	public void setComp_phone(String comp_phone) {
		this.comp_phone = comp_phone;
	}

	public String getPrincipal_name() {
		return principal_name;
	}

	public void setPrincipal_name(String principal_name) {
		this.principal_name = principal_name;
	}

	public String getPrincipal_phone() {
		return principal_phone;
	}

	public void setPrincipal_phone(String principal_phone) {
		this.principal_phone = principal_phone;
	}

	public String getComp_account() {
		return comp_account;
	}

	public void setComp_account(String comp_account) {
		this.comp_account = comp_account;
	}

	public String getComp_password() {
		return comp_password;
	}

	public void setComp_password(String comp_password) {
		this.comp_password = comp_password;
	}

	public String getComp_mail() {
		return comp_mail;
	}

	public void setComp_mail(String comp_mail) {
		this.comp_mail = comp_mail;
	}

	public byte[] getComp_photo() {
		return comp_photo;
	}

	public void setComp_photo(byte[] comp_photo) {
		this.comp_photo = comp_photo;
	}

	public Integer getComp_type() {
		return comp_type;
	}

	public void setComp_type(Integer comp_type) {
		this.comp_type = comp_type;
	}

	public Integer getCheck_status() {
		return check_status;
	}

	public void setCheck_status(Integer check_status) {
		this.check_status = check_status;
	}

	public Integer getHotel_info_id() {
		return hotel_info_id;
	}

	public void setHotel_info_id(Integer hotel_info_id) {
		this.hotel_info_id = hotel_info_id;
	}

}
