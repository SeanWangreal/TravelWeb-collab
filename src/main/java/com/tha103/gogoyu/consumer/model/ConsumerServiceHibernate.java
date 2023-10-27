package com.tha103.gogoyu.consumer.model;

import java.io.IOException;
import java.util.List;
import java.io.FileInputStream;

public class ConsumerServiceHibernate implements ConsumerService {

	private ConsumerDAO_interface dao;
	
	public ConsumerServiceHibernate() {
		dao = new ConsumerHibernateDAO();
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
		
		
		
		dao.add(cus);
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
		dao.update(cus);
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


	@Override
	public List<Consumer> getPassword(String cusPassword) {
		return dao.getCusPassword(cusPassword);
	}

	@Override
	public Consumer checkDuplicateAccount(String cusAccount) {
//		 List<Consumer> existingConsumers = dao.getCusAccount(cusAccount);
		 Consumer existingConsumers = dao.getCusAccount(cusAccount);

//	        return !existingConsumers.isEmpty();	
	        return existingConsumers;	
	        
	}
	public static byte[] writePicture(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		  byte[] buffer = fis.readAllBytes();
		  fis.close();
		  return buffer;
		 }




	
	
}
