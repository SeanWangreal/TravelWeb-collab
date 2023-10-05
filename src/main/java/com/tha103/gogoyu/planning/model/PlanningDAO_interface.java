package com.tha103.gogoyu.planning.model;

import java.util.*;

public interface PlanningDAO_interface {
				int add(Planning planning);
				int update(Planning planning);
				int delete(Integer planId);
				Planning findByPK(Integer planId);
				List<Planning> getAll();
}
