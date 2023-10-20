package com.tha103.gogoyu.scene.model;

import java.util.List;
import util.HibernateUtil;

public class SceneServiceHibernate implements SceneService {
	private SceneDAO_interface dao;

	public SceneServiceHibernate() {
		dao = new SceneHibernateDAO(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public int add(Scene Scene) {
		return dao.add(Scene);
	}

	@Override
	public int update(Scene Scene) {
		return dao.update(Scene);
	}

	@Override
	public int delete(Integer sceneId) {
		return dao.delete(sceneId);
	}

	@Override
	public Scene findByPK(Integer sceneId) {
		return dao.findByPK(sceneId);
	}

	@Override
	public List<Scene> getAll() {
		return dao.getAll();
	}

}
