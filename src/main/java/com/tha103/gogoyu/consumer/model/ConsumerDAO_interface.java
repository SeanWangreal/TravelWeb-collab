package com.tha103.gogoyu.consumer.model;

import java.util.List;

public interface ConsumerDAO_interface {
	public int add(Consumer consumer);

	public int update(Consumer consumer);

	public int delete(Integer cusId);

	public Consumer findByPK(Integer cusId);

	public List<Consumer> getAll();

	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	public byte[] getPicture(Integer cusId) throws Exception;
	public List<Consumer> getCusAccount(String cusAccount);
	public List<Consumer> getCusPassword(String cusPassword);

	public void updFromBackend(Integer cusId, String cusName, String cusAccount, String cusMail, String cusPhone,
			String cusAddress, Integer cusSex);


}
