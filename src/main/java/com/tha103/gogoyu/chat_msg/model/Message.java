package com.tha103.gogoyu.chat_msg.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Message implements Serializable {
	private Integer msg_id;
	private Integer chat_room_id;
	private Integer mem_id;
	private String msg_log;
	private Timestamp msg_time;

	public Message() {
		super();
	}

	public Message(Integer msg_id, Integer chat_room_id, String msg_log, Timestamp msg_time, Integer mem_id) {
		super();
		this.msg_id = msg_id;
		this.chat_room_id = chat_room_id;
		this.msg_log = msg_log;
		this.msg_time = msg_time;
		this.mem_id = mem_id;
	}

	public Integer getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}

	public Integer getChat_room_id() {
		return chat_room_id;
	}

	public void setChat_room_id(Integer chat_room_id) {
		this.chat_room_id = chat_room_id;
	}

	public String getMsg_log() {
		return msg_log;
	}

	public void setMsg_log(String msg_log) {
		this.msg_log = msg_log;
	}

	public Timestamp getMsg_time() {
		return msg_time;
	}

	public void setMsg_time(Timestamp msg_time) {
		this.msg_time = msg_time;
	}

	public Integer getMem_id() {
		return mem_id;
	}

	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}

}
