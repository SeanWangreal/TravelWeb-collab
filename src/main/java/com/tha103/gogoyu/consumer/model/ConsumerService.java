package com.tha103.gogoyu.consumer.model;

import java.util.List;

public interface ConsumerService {

	
	public Consumer addCus(String cusName,String cusAccount ,String cusPassword ,String cusMail ,String cusPhone ,String cusAddress , Integer cusSex, byte[] cusPhoto );
	public Consumer updateCus(Integer cusId ,String cusName,String cusAccount ,String cusPassword ,String cusMail ,String cusPhone ,String cusAddress , Integer cusSex, byte[] cusPhoto );
	public void deleteCus(Integer cusId);
	public Consumer getOneCus(Integer cusId);
	public List<Consumer> getAll();
	public Consumer checkDuplicateAccount(String cusAccount);
	List<Consumer> getPassword(String cusPassword);
	
	
}
