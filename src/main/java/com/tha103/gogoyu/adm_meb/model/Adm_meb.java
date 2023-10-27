package com.tha103.gogoyu.adm_meb.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adm_meb")
public class Adm_meb implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adm_id")
	private Integer admId;
	@Column(name = "adm_name")
	private String admName;
	@Column(name = "adm_account")
	private String admAccount;
	@Column(name = "adm_password")
	private String admPassword;

	public Adm_meb() {
		super();
	}

	public Adm_meb(Integer adm_id, String adm_name, String adm_account, String adm_password) {
		super();
		this.admId = adm_id;
		this.admName = adm_name;
		this.admAccount = adm_account;
		this.admPassword = adm_password;
	}
	
	public Integer getAdmId() {
		return admId;
	}

	public void setAdmId(Integer adm_id) {
		this.admId = adm_id;
	}

	public String getAdmName() {
		return admName;
	}

	public void setAdmName(String adm_name) {
		this.admName = adm_name;
	}
	
	public String getAdmAccount() {
		return admAccount;
	}

	public void setAdmAccount(String adm_account) {
		this.admAccount = adm_account;
	}

	public String getAdmPassword() {
		return admPassword;
	}

	public void setAdmPassword(String adm_password) {
		this.admPassword = adm_password;
	}

	

}
