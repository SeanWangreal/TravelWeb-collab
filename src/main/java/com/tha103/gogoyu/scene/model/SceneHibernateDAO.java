package com.tha103.gogoyu.scene.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.gogoyu.itinerary.model.Itinerary;

import util.HibernateUtil;

public class SceneHibernateDAO implements SceneDAO_interface {
	private SessionFactory factory;

	public SceneHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(Scene Scene) {
		try {
			getSession().beginTransaction();
			Integer id = (Integer) getSession().save(Scene);
			getSession().getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Scene Scene) {
		try {
			getSession().beginTransaction();
			getSession().update(Scene);
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer sceneId) {
		try {
			getSession().beginTransaction();
			Scene emp = getSession().get(Scene.class, sceneId);
			if (emp != null) {
				getSession().delete(emp);
			}
			getSession().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Scene findByPK(Integer sceneId) {
		try {
			getSession().beginTransaction();
			Scene scene = getSession().get(Scene.class, sceneId);
			getSession().getTransaction().commit();
			return scene;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Scene> getAll() {
		try {
			getSession().beginTransaction();
			List<Scene> list = getSession().createQuery("from Scene", Scene.class).list();
			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}

}
