package com.tha103.gogoyu.consumer.model;

import java.util.List;

public interface ConsumerService {

	
	public Consumer addCus(String cusName,String cusAccount ,String cusPassword ,String cusMail ,String cusPhone ,String cusAddress , Integer cusSex, byte[] cusPhoto );
	public Consumer updateCus(Integer cusId ,String cusName,String cusAccount ,String cusPassword ,String cusMail ,String cusPhone ,String cusAddress , Integer cusSex, byte[] cusPhoto );
	public void deleteCus(Integer cusId);
	public Consumer getOneCus(Integer cusId);
	public List<Consumer> getAll();
	public List<Consumer> getCusAccount(String cusAccount);
	public List<Consumer> getCusPassword(String cusPassword);
	public boolean checkDuplicateAccount(String cusAccount);
	public void updFromBackend(Integer cusId, String cusName,String cusAccount ,String cusMail ,String cusPhone ,String cusAddress , Integer cusSex);
	
}
