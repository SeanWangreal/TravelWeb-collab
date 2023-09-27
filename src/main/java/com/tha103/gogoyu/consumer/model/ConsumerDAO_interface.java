package com.tha103.gogoyu.consumer.model;

import java.util.List;

public interface ConsumerDAO_interface {
	          public void insert(Consumer Consumer);
	          public void update(Consumer Consumer);
	          public void delete(Integer Cus_id);
	          public Consumer findByPrimaryKey(Integer Cus_id);
	          public List<Consumer> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	
	}


