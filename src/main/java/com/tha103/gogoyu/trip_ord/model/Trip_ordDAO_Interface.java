package com.tha103.gogoyu.trip_ord.model;

import java.math.BigDecimal;
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
    public Map<Trip_ord, List<String>> gettripIdComment(Integer tripId);
    public Map<Trip_ord,List<String>> getTripOrdByCompId(Integer compId,Integer beginCount, String ordOrReview);
    public  Map<Trip_ord,List<String>> getTripOrdByCompIdOrdId(Integer tripOrdId, Integer compId);
	public Map<Trip_ord, List<String>> getTripOrdByCusId(Integer cusId);
	public Map <Trip_ord , List <Object>> getTripOrdList(Integer tripOrdId);
	public Integer updateStatusAndRemark(String remark , Integer tripOrdId  ,BigDecimal profit , BigDecimal commission , BigDecimal totalPrice ,Timestamp ordTime);
	public Integer updateAmountAndPrice(Integer amount  ,Integer tripOrdId); 
	public Integer updateCartNum(Integer planId , Integer tripOrdId);
}

