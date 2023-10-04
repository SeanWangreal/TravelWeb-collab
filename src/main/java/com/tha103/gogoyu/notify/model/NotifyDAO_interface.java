package com.tha103.gogoyu.notify.model;

import java.util.*;

public interface NotifyDAO_interface {
		// Hibernate
           int add(Notify notify);
           int update(Notify notify);
           int delete(Integer notifyId);
           Notify findByPK(Integer notifyId);
           List<Notify> getAll();

           // JDBC
//           void insert(Notify notify);
//           void update(Notify notify);
//           void delete(Integer notifyId);
//           Notify findByPrimaryKey(Integer notifyId);
//           List<Notify> getAll();
}