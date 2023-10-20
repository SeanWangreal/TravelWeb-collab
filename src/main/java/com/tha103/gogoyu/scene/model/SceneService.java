package com.tha103.gogoyu.scene.model;

import java.util.List;

public interface SceneService {
	int add(Scene Scene);
	int update(Scene Scene);
	int delete(Integer tripId);
	Scene findByPK(Integer tripId);
	List<Scene> getAll();
}
