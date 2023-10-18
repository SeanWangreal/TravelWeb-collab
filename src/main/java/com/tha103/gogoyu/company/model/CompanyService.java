package com.tha103.gogoyu.company.model;
import java.util.List;

import util.HibernateUtil;

public class CompanyService {

		private CompanyDAO_interface dao;

		public CompanyService() {
			dao = new CompanyHibernateDAO(HibernateUtil.getSessionFactory());
		}

		public Company addCompany(Integer compId, Integer hotelInfoId, Integer compType, String compName, String compAddress, String compPhone, String principalName,
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
			dao.add(company);
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
			dao.add(company);
			return company;
		}
		public void deleteCompany(Integer compId) {
			dao.delete(compId);
		}

		public Company getOneCompany(Integer compId) {
			return dao.findByPK(compId);
		}

		public List<Company> getAllCompany() {
			return dao.getAll();
		}
	}


