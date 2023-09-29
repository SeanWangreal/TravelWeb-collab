package com.tha103.gogoyu.planning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "planning")
public class Planning{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_id", updatable = false)
	private Integer planId;

	@Column(name = "cus_id")
	private Integer cusId;

	@Column(name = "plan_name")
	private String planName;

	public Planning() {
		super();
	}

	public Planning(Integer planId, Integer cusId, String planName) {
		super();
		this.planId = planId;
		this.cusId = cusId;
		this.planName = planName;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

}
