package com.tha103.gogoyu.trip_ord.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.tha103.gogoyu.trip.model.Trip;

public interface Trip_ordDAO_Interface {
    public Integer add(Trip_ord tripOrd);
    public Integer update(Trip_ord tripOrd);
    public  Integer updateCommentAndScore(Integer tripOrd, Integer score, String comment , Timestamp commentsTime);
    public Integer delete(Integer tripOrdId);
    public Trip_ord findByPrimaryKey(Integer tripOrdId);
    public List<Trip_ord> getAll();
    public Map<Trip_ord, List<String>> getTripOrdVo(Integer cartId, Integer cusId);
    public Map<Trip_ord,List<String>> getTripOrdByCompId(Integer compId);
    public Map<Trip_ord, List<String>> gettripIdComment(Integer tripId);
}

