package com.tha103.gogoyu.chat_msg.model;

import java.util.List;

public interface MessageDAO_interface {
	          public void insert(Message Message);
	          public void update(Message Message);
	          public void delete(Integer Msg_id);
	          public Message findByPrimaryKey(Integer Msg_id);
	          public List<Message> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	
	}


