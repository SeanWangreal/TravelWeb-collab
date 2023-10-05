package com.tha103.gogoyu.scene.model;

import java.util.List;

public interface SceneDAO_interface {
	int add(Scene Scene);

	int update(Scene Scene);

	int delete(Integer scene_id);

	Scene findByPK(Integer scene_id);

	List<Scene> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
