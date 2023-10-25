package com.tha103.gogoyu.adm_meb.model;

import java.util.List;

public interface AdminDAO_interface {
//	          int insert(Adm_meb admMeb);
	          public int update(Admin admMeb);
	          public int delete(Integer admId);
	          public Admin findByPrimaryKey(Integer admId);
	          public List<Admin> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
			int add(Admin admMeb);
	
	}


