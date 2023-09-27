package com.tha103.gogoyu.chat_room_mem.model;

import java.io.Serializable;

public class Chat_room_mem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer room_mem_id;
	private Integer chat_room_id;
	private Integer mem_id;
	
	public Chat_room_mem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chat_room_mem(Integer room_mem_id, Integer chat_room_id, Integer mem_id) {
		super();
		this.room_mem_id = room_mem_id;
		this.chat_room_id = chat_room_id;
		this.mem_id = mem_id;
	}

	public Integer getRoom_mem_id() {
		return room_mem_id;
	}

	public void setRoom_mem_id(Integer room_mem_id) {
		this.room_mem_id = room_mem_id;
	}

	public Integer getChat_room_id() {
		return chat_room_id;
	}

	public void setChat_room_id(Integer chat_room_id) {
		this.chat_room_id = chat_room_id;
	}

	public Integer getMem_id() {
		return mem_id;
	}

	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	
}
