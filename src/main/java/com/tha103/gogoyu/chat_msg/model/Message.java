package com.tha103.gogoyu.chat_msg.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "message")
public class Message implements Serializable {

	private Integer msgId;
	private Integer chatRoomId;
	private Integer memId;
	private String msgLog;
	private Timestamp msgTime;

	public Message() {
		super();
	}

	public Message(Integer msg_id, Integer chat_room_id, String msg_log, Timestamp msg_time, Integer mem_id) {
		super();
		this.msgId = msg_id;
		this.chatRoomId = chat_room_id;
		this.msgLog = msg_log;
		this.msgTime = msg_time;
		this.memId = mem_id;
	}

	public Integer getMsgId() {
		return msgId;
	}
	
	public void setMsgId(Integer msg_id) {
		this.msgId = msg_id;
	}
	
	public Integer getChatRoomId() {
		return chatRoomId;
	}
	
	public void setChatRoomId(Integer chat_room_id) {
		this.chatRoomId = chat_room_id;
	}
	
	public String getMsgLog() {
		return msgLog;
	}
	
	public void setMsgLog(String msg_log) {
		this.msgLog = msg_log;
	}
	
	public Timestamp getMsgTime() {
		return msgTime;
	}
	
	public void setMsgTime(Timestamp msg_time) {
		this.msgTime = msg_time;
	}
	
	public Integer getMemId() {
		return memId;
	}
	
	public void setMemId(Integer mem_id) {
		this.memId = mem_id;
	}

}
