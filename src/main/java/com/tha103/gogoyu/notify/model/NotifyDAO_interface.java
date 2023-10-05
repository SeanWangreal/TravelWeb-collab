package com.tha103.gogoyu.notify.model;

import java.util.*;

public interface NotifyDAO_interface {
           int add(Notify notify);
           int update(Notify notify);
           int delete(Integer notifyId);
           Notify findByPK(Integer notifyId);
           List<Notify> getAll();
}