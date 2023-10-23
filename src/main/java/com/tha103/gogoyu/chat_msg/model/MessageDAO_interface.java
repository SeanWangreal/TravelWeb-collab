package com.tha103.gogoyu.chat_msg.model;

import java.util.List;

public interface MessageDAO_interface {
	          public void insert(Message message);
	          public void update(Message message);
	          public void delete(Integer msgId);
	          public Message findByPrimaryKey(Integer msgId);
	          public List<Message> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	
	}


