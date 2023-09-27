package com.tha103.gogoyu.adm_meb.model;

import java.io.Serializable;

public class Adm_meb implements Serializable {
	private Integer adm_id;
	private String adm_name;
	private String adm_account;
	private String adm_password;

	public Adm_meb() {
		super();
	}

	public Adm_meb(Integer adm_id, String adm_name, String adm_account, String adm_password) {
		super();
		this.adm_id = adm_id;
		this.adm_name = adm_name;
		this.adm_account = adm_account;
		this.adm_password = adm_password;
	}

	public Integer getAdm_id() {
		return adm_id;
	}

	public void setAdm_id(Integer adm_id) {
		this.adm_id = adm_id;
	}

	public String getAdm_name() {
		return adm_name;
	}

	public void setAdm_name(String adm_name) {
		this.adm_name = adm_name;
	}

	public String getAdm_account() {
		return adm_account;
	}

	public void setAdm_account(String adm_account) {
		this.adm_account = adm_account;
	}

	public String getAdm_password() {
		return adm_password;
	}

	public void setAdm_password(String adm_password) {
		this.adm_password = adm_password;
	}

}
