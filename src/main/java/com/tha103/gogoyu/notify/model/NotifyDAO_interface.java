package com.tha103.gogoyu.notify.model;

import java.util.*;

public interface NotifyDAO_interface {
           void insert(Notify notify);
           void update(Notify notify);
           void delete(Integer notifyId);
           Notify findByPrimaryKey(Integer notifyId);
           List<Notify> getAll();
}