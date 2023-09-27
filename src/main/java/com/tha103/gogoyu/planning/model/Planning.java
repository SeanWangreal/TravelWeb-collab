package com.tha103.gogoyu.planning.model;

public class Planning implements java.io.Serializable{
	private Integer plan_id;
	private Integer cus_id;
	private String plan_name;
	
	public Planning() {
		super();
	}

	public Planning(Integer plan_id, Integer cus_id, String plan_name) {
		super();
		this.plan_id = plan_id;
		this.cus_id = cus_id;
		this.plan_name = plan_name;
	}

	public Integer getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
	}

	public Integer getCus_id() {
		return cus_id;
	}

	public void setCus_id(Integer cus_id) {
		this.cus_id = cus_id;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
}
	
	
	