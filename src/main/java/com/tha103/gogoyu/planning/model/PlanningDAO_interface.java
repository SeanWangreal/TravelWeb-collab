package com.tha103.gogoyu.planning.model;

import java.util.*;

public interface PlanningDAO_interface {
           void insert(Planning notify);
           void update(Planning notify);
           void delete(Integer planId);
           Planning findByPrimaryKey(Integer notifyId);
           List<Planning> getAll();
}
