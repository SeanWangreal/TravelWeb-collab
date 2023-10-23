package com.tha103.gogoyu.company.model;

import java.util.List;

public interface CompanyDAO_interface {
	int add(Company Company);

	int update(Company Company);

	int delete(Integer comp_id);

	Company findByPK(Integer comp_id);

	List<Company> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	
	List<Company> getByCheckStatus();

	void updChkStatus(Integer compId, Integer checkStatus);
}
