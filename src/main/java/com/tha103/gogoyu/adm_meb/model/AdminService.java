package com.tha103.gogoyu.adm_meb.model;

import java.util.List;

public class AdminService {
	private AdminDAO_interface dao;

	public AdminService() {
		dao = new AdminHibernateDAO();
	}

	public Admin addAdm(String adm_name, String adm_account, String adm_password) {

		Admin admMeb = new Admin();

		admMeb.setAdmName(adm_name);
		admMeb.setAdmAccount(adm_account);
		admMeb.setAdmPassword(adm_password);

		dao.add(admMeb);

		return admMeb;
	}

	public Admin updateAdm(Integer adm_id, String adm_name, String adm_account, String adm_password) {

		Admin admMeb = new Admin();

		admMeb.setAdmId(adm_id);
		admMeb.setAdmName(adm_name);
		admMeb.setAdmAccount(adm_account);
		admMeb.setAdmPassword(adm_password);

		dao.update(admMeb);

		return admMeb;
	}

	public void deleteAdm(Integer adm_id) {
		dao.delete(adm_id);
	}

	public Admin getOneAdm(Integer adm_id) {
		return dao.findByPrimaryKey(adm_id);
	}

	public List<Admin> getAll() {
		return dao.getAll();
	}
}
