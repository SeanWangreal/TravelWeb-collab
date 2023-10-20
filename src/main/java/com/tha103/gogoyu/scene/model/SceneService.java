package com.tha103.gogoyu.scene.model;

import java.util.List;

public interface SceneService {
	int add(Scene Scene);
	int update(Scene Scene);
	int delete(Integer sceneId);
	Scene findByPK(Integer sceneId);
	List<Scene> getAll();
}
