package com.tha103.gogoyu.chat_room.model;

import java.util.List;

import com.tha103.gogoyu.trip_ord.model.Trip_ord;

public interface Chat_roomDAO_Interface {
    public void insert(Chat_room chat_room);
    public void update(Chat_room chat_room);
    public void delete(Integer chat_room_id);
    public Chat_room findByPrimaryKey(Integer chat_room_id);
    public List<Chat_room> getAll();

}
