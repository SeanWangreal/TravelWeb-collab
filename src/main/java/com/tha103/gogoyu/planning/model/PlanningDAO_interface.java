package com.tha103.gogoyu.planning.model;

import java.util.*;

public interface PlanningDAO_interface {
// Hibernate
				int add(Planning planning);
				int update(Planning planning);
				int delete(Integer planId);
				Planning findByPK(Integer planId);
				List<Planning> getAll();
	
// JDBC
//           void insert(Planning notify);
//           void update(Planning notify);
//           void delete(Integer planId);
//           Planning findByPrimaryKey(Integer notifyId);
//           List<Planning> getAll();
}
