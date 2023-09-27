package com.tha103.gogoyu.notify.model;

import java.util.*;

public interface NotifyDAO_interface {
           void insert(Notify notify);
           void update(Notify notify);
           void delete(Integer notify_id);
           Notify findByPrimaryKey(Integer notify_id);
           List<Notify> getAll();
}
