package com.tha103.gogoyu.consumer.model;

import java.util.List;

import util.HibernateUtil;

public class ConsumerServiceHibernate implements ConsumerService {

	private ConsumerDAO_interface dao;
	
	public ConsumerServiceHibernate() {
		dao = new ConsumerHibernateDAO(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public Consumer addCus(String cusName, String cusAccount, String cusPassword, String cusMail, String cusPhone,
			String cusAddress, Integer cusSex, byte[] cusPhoto) {
		Consumer cus = new Consumer();
		cus.setCusName(cusName);
		cus.setCusAccount(cusAccount);
		cus.setCusPassword(cusPassword);
		cus.setCusMail(cusAddress);
		cus.setCusPhone(cusPhone);
		cus.setCusAddress(cusAddress);
		cus.setCusSex(cusSex);
		cus.setCusPhoto(cusPhoto);
		
		return cus;
	}

	@Override
	public Consumer updateCus(Integer cusId, String cusName, String cusAccount, String cusPassword, String cusMail,
			String cusPhone, String cusAddress, Integer cusSex, byte[] cusPhoto) {
		Consumer cus = new Consumer();
		cus.setCusId(cusId);
		cus.setCusName(cusName);
		cus.setCusAccount(cusAccount);
		cus.setCusPassword(cusPassword);
		cus.setCusMail(cusAddress);
		cus.setCusPhone(cusPhone);
		cus.setCusAddress(cusAddress);
		cus.setCusSex(cusSex);
		cus.setCusPhoto(cusPhoto);
		
		return cus;
	}

	@Override
	public void deleteCus(Integer cusId) {
dao.delete(cusId);		
	}

	@Override
	public Consumer getOneCus(Integer cusId) {
		return dao.findByPK(cusId);
	}

	@Override
	public List<Consumer> getAll() {
		return dao.getAll();
	}

	public byte[] getOnePicture(Integer cusId) throws Exception {
		return dao.getPicture(cusId);
	}

}