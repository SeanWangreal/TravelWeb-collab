package com.tha103.gogoyu.company.model;

import java.util.List;

import com.tha103.gogoyu.hotel_info.model.Hotel_info;

public interface CompanyDAO_interface {
	int add(Company Company,Hotel_info hotelInfo);

	int update(Company Company);

	int delete(Integer comp_id);
	
	public List<Company> findByAccount(String compAccount);
	
	public List<Company> findByPassword(String compPassword);

	Company findByPK(Integer comp_id);

	List<Company> getAll();
	
	byte[] getCompPhoto(Integer compId);
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	
	List<Company> getByCheckStatus();

	void updChkStatus(Integer compId, Integer checkStatus);
	
	Company updFromBackend(Integer comp_id, String comp_name, String comp_address,
			String comp_phone, String principal_name, String principal_phone, 
			String comp_account, String comp_mail);
}
