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
	
	public Integer getAdm_id() {
		return admId;
	}

	public void setAdm_id(Integer adm_id) {
		this.admId = adm_id;
	}

	public String getAdm_name() {
		return admName;
	}

	public void setAdm_name(String adm_name) {
		this.admName = adm_name;
	}
	
	public String getAdm_account() {
		return admAccount;
	}

	public void setAdm_account(String adm_account) {
		this.admAccount = adm_account;
	}

	public String getAdm_password() {
		return admPassword;
	}

	public void setAdm_password(String adm_password) {
		this.admPassword = adm_password;
	}

}
