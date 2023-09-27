package com.tha103.gogoyu.planning.model;

import java.util.*;

public interface PlanningDAO_interface {
           void insert(Planning notify);
           void update(Planning notify);
           void delete(Integer plan_id);
           Planning findByPrimaryKey(Integer notify_id);
           List<Planning> getAll();
}
