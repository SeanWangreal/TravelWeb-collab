package com.tha103.gogoyu.planning.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;



import util.HibernateUtil;
import com.tha103.gogoyu.planning.model.Planning;
import com.tha103.gogoyu.planning.model.PlanningDAO_interface;
import com.tha103.gogoyu.planning.model.PlanningHibernateDAO;

public class PlanningServiceHibernate {
	private PlanningDAO_interface dao;

	public PlanningServiceHibernate() {
		dao = new PlanningHibernateDAO(HibernateUtil.getSessionFactory());//servlet�z�Lservice�Q�Φh��(daointerface)�I�sd	ao
	}

	public Planning add(Integer cusId,Integer cartId) {

		Planning planning = new Planning();
			planning.setCartId(cartId);
			planning.setCusId(cusId);
		dao.add(planning);

		return planning;
	}
	
	


	public Planning update(Integer planId,Integer cusId,Integer cartId) {

		Planning planning= new Planning();
		planning.setPlanId(planId);
		planning.setCartId(cartId);
		planning.setCusId(cusId);
		
		dao.update(planning);

		return planning;
	}
	
	public void delete(Integer plan_id) {
		dao.delete(plan_id);
	}

	
//	service�I�s���oplan_id��k
	public Integer getPlanId(Integer cart_id , Integer cus_id) { 

		return dao.findByPlanId(cart_id,cus_id);
	}

	

	public List<Planning> getAll() {
		return dao.getAll();
	}



	
}
