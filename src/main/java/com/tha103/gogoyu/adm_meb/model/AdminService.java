package com.tha103.gogoyu.adm_meb.model;

import java.util.List;

public class AdminService {
	private AdminDAO_interface dao;

	public AdminService() {
		dao = new AdminHibernateDAO();
	}

	public Adm_meb addAdm(String adm_name, String adm_account, String adm_password) {

		Adm_meb admMeb = new Adm_meb();

		admMeb.setAdmName(adm_name);
		admMeb.setAdmAccount(adm_account);
		admMeb.setAdmPassword(adm_password);

		dao.add(admMeb);

		return admMeb;
	}

	public Adm_meb updateAdm(Integer adm_id, String adm_name, String adm_account, String adm_password) {

		Adm_meb admMeb = new Adm_meb();

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

	public Adm_meb getOneAdm(Integer adm_id) {
		return dao.findByPrimaryKey(adm_id);
	}
	
	public Adm_meb getOneByAccount(String adm_account) {
		return dao.findByAccount(adm_account);
	}

	public List<Adm_meb> getAll() {
		return dao.getAll();
	}
}
