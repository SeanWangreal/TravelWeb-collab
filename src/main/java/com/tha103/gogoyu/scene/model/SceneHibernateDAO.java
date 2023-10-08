package com.tha103.gogoyu.scene.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.gogoyu.itinerary.model.Itinerary;

import util.HibernateUtil;

public class SceneHibernateDAO implements  SceneDAO_interface {
	@Override
	public int add(Scene Scene) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(Scene);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Scene Scene) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(Scene);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer scene_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Scene emp = session.get(Scene.class, scene_id);
			if (emp != null) {
				session.delete(emp);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Scene findByPK(Integer scene_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Scene scene = session.get(Scene.class, scene_id);
			session.getTransaction().commit();
			return scene;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Scene> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Scene> list = session.createQuery("from scene", Scene.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
