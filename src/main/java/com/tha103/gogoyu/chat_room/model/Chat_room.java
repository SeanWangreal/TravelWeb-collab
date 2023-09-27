package com.tha103.gogoyu.chat_room.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Chat_room implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer chat_room_id;
	private String content;
	private Timestamp create_time;
	
	public Chat_room() {
		super();
	}
	public Chat_room(Integer chat_room_id, String content, Timestamp create_time) {
		super();
		this.chat_room_id = chat_room_id;
		this.content = content;
		this.create_time = create_time;
	}
	public Integer getChat_room_id() {
		return chat_room_id;
	}
	public void setChat_room_id(Integer chat_room_id) {
		this.chat_room_id = chat_room_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	
}
