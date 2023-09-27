package com.tha103.gogoyu.scene.model;

import java.util.List;

public interface SceneDAO_interface {
    public void insert(Scene Scene);
    public void update(Scene Scene);
    public void delete(Integer scene_id);
    public Scene findByPrimaryKey(Integer scene_id);
    public List<Scene> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
