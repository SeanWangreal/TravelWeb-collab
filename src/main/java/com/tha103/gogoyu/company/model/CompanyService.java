package com.tha103.gogoyu.company.model;
import java.util.List;

import com.tha103.gogoyu.adm_meb.model.AdminDAO_interface;
import com.tha103.gogoyu.hotel_info.model.Hotel_info;
import com.tha103.gogoyu.hotel_info.model.Hotel_infoServiceHibernate;
import com.tha103.gogoyu.room.model.Room;

import util.HibernateUtil;

public class CompanyService {

		private CompanyDAO_interface dao;

		public CompanyService() {
			dao = new CompanyHibernateDAO(HibernateUtil.getSessionFactory());
		}

		public Company addCompany(Integer compType, String compName, String compAddress, String compPhone, String principalName,
				String principalPhone, String compAccount, String compPassword, String compMail, byte[] compPhoto,Hotel_info hotelInfo
				) {

			Company company = new Company();
			company.setCompType(compType);
			company.setCompName(compName);
			company.setCompAddress(compAddress);
			company.setCompPhone(compPhone);
			company.setPrincipalName(principalName);
			company.setPrincipalPhone(principalPhone);
			company.setCompAccount(compAccount);
			company.setCompPassword(compPassword);
			company.setCompMail(compMail);
			company.setCompPhoto(compPhoto);
			company.setCheckStatus(0);

			dao.add(company, hotelInfo);
			return company;
		}

		public Company updateCompany(Integer compId, Integer hotelInfoId, Integer compType, String compName, String compAddress, String compPhone, String principalName,
				String principalPhone, String compAccount, String compPassword, String compMail, byte[] compPhoto , Integer checkStatus) {

			Company company = new Company();
			company.setCompId(compId);
			company.setHotelInfoId(hotelInfoId);
			company.setCompType(compType);
			company.setCompName(compName);
			company.setCompAddress(compAddress);
			company.setCompPhone(compPhone);
			company.setPrincipalName(principalName);
			company.setPrincipalPhone(principalPhone);
			company.setCompAccount(compAccount);
			company.setCompPassword(compPassword);
			company.setCompMail(compMail);
			company.setCompPhoto(compPhoto);
			company.setCheckStatus(checkStatus);
			dao.update(company);
			return company;
		}
		public void deleteCompany(Integer compId) {
			dao.delete(compId);
		}
		
		public List<Company> getOneAccount(String compAccount) {
			return dao.findByAccount(compAccount);
		}
		
		public List<Company> getOnePassword(String compPassword) {
			return dao.findByPassword(compPassword);
		}

		public Company getOneCompany(Integer compId) {
			return dao.findByPK(compId);
		}
		
		
		public Company getComp(Integer compId) {
			return dao.findByPK(compId);
		}

		public List<Company> getAllCompany() {
			return dao.getAll();
		}
		
		public Company updatePassword(Integer compId, String compPassword) {
			Company company=this.getOneCompany(compId);
			company.setCompPassword(compPassword);
			dao.update(company);
			return company;
//			dao.update(compId, compPassword);
		}
		
		public static void main(String[] args) {
			CompanyService hi = new CompanyService();
			System.out.println(hi.getAllCompany());
		}
		
		public List<Company> getByCheckStatus(){
			return dao.getByCheckStatus();
		}
		
		public void updChkStatus(Integer compId, Integer checkStatus) {
			dao.updChkStatus(compId, checkStatus);
		}
	}


