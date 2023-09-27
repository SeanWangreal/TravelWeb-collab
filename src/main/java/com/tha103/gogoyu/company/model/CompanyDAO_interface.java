package com.tha103.gogoyu.company.model;

import java.util.List;

public interface CompanyDAO_interface {
              public void insert(Company Company);
	          public void update(Company Company);
	          public void delete(Integer comp_id);
	          public Company findByPrimaryKey(Integer comp_id);
	          public List<Company> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	}


