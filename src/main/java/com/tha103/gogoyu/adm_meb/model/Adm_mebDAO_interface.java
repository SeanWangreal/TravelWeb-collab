package com.tha103.gogoyu.adm_meb.model;

import java.util.List;

public interface Adm_mebDAO_interface {
	          public void insert(Adm_meb Adm_meb);
	          public void update(Adm_meb Adm_meb);
	          public void delete(Integer Adm_id);
	          public Adm_meb findByPrimaryKey(Integer Adm_id);
	          public List<Adm_meb> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	
	}


