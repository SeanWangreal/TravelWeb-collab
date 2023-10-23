package com.tha103.gogoyu.adm_meb.model;

import java.util.List;

public interface Adm_mebDAO_interface {
//	          int insert(Adm_meb admMeb);
	          public int update(Adm_meb admMeb);
	          public int delete(Integer admId);
	          public Adm_meb findByPrimaryKey(Integer admId);
	          public List<Adm_meb> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
			int add(Adm_meb admMeb);
	
	}


