package com.tha103.gogoyu.chat_room_mem.model;

import java.util.List;

public interface Chat_room_memDAO_Interface {
    public void insert(Chat_room_mem chat_room_mem);
    public void update(Chat_room_mem chat_room_mem);
    public void delete(Integer room_mem_id);
    public Chat_room_mem findByPrimaryKey(Integer room_mem_id);
    public List<Chat_room_mem> getAll();
}
