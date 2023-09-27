package com.tha103.gogoyu.chat_room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class Chat_roomJDBCDAO implements Chat_roomDAO_Interface{
	static {
		try {
			Class.forName(Util.DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO chat_room (content,create_time) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT chat_room_id, content,create_time FROM trip_ord order by chat_room_id";
	private static final String GET_ONE_STMT = 
		"SELECT chat_room_id, content,create_time FROM chat_room where chat_room_id = ?";
	private static final String DELETE = 
		"DELETE FROM chat_room where chat_room_id = ?";
	private static final String UPDATE = 
		"UPDATE chat_room set chat_room_id, content,create_time where chat_room_id = ?";

	@Override
	public void insert(Chat_room chat_room) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"chat_room_id"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, chat_room.getContent());
			pstmt.setTimestamp(2, chat_room.getCreate_time());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Chat_room chat_room) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, chat_room.getChat_room_id());
			pstmt.setString(2, chat_room.getContent());
			pstmt.setTimestamp(3, chat_room.getCreate_time());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer chat_room_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, chat_room_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Chat_room findByPrimaryKey(Integer chat_room_id) {
		Chat_room chat_room = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, chat_room_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chat_room = new Chat_room();
				chat_room.setChat_room_id(rs.getInt("chat_room_id"));
				chat_room.setContent(rs.getString("content"));
				chat_room.setCreate_time(rs.getTimestamp("create_time"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return chat_room;
	}

	@Override
	public List<Chat_room> getAll() {
		List<Chat_room> list = new ArrayList<Chat_room>();
		Chat_room chat_room = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chat_room = new Chat_room();
				chat_room.setChat_room_id(rs.getInt("chat_room_id"));
				chat_room.setContent(rs.getString("content"));
				chat_room.setCreate_time(rs.getTimestamp("create_time"));
				list.add(chat_room);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}
}
