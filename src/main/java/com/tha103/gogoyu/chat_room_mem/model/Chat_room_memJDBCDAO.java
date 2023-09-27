package com.tha103.gogoyu.chat_room_mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class Chat_room_memJDBCDAO implements Chat_room_memDAO_Interface{
	static {
		try {
			Class.forName(Util.DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO room_mem (chat_room_id,mem_id) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT room_mem_id, chat_room_id,mem_id FROM room_mem order by room_mem_id";
		private static final String GET_ONE_STMT = 
			"SELECT room_mem_id, chat_room_id,mem_id FROM room_mem where room_mem_id = ?";
		private static final String DELETE = 
			"DELETE FROM room_mem where room_mem_id = ?";
		private static final String UPDATE = 
			"UPDATE room_mem set room_mem_id = ?, chat_room_id = ?,mem_id = ?, "
			+ "where room_mem_id = ?";

	@Override
	public void insert(Chat_room_mem chat_room_mem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"room_mem_id"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, chat_room_mem.getChat_room_id());
			pstmt.setInt(2, chat_room_mem.getMem_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);		
		}		
	}

	@Override
	public void update(Chat_room_mem chat_room_mem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, chat_room_mem.getRoom_mem_id());
			pstmt.setInt(2, chat_room_mem.getChat_room_id());
			pstmt.setInt(3, chat_room_mem.getMem_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, null);
		}		
	}

	@Override
	public void delete(Integer room_mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, room_mem_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}		
	}

	@Override
	public Chat_room_mem findByPrimaryKey(Integer room_mem_id) {
		Chat_room_mem chat_room_mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, room_mem_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chat_room_mem = new Chat_room_mem();
				chat_room_mem.setRoom_mem_id(rs.getInt("room_mem_id"));
				chat_room_mem.setChat_room_id(rs.getInt("chat_room_id"));			
				chat_room_mem.setMem_id(rs.getInt("room_mem_id"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return chat_room_mem;
	}

	@Override
	public List<Chat_room_mem> getAll() {
		List<Chat_room_mem> list = new ArrayList<Chat_room_mem>();
		Chat_room_mem chat_room_mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chat_room_mem = new Chat_room_mem();
				chat_room_mem.setRoom_mem_id(rs.getInt("room_mem_id"));
				chat_room_mem.setChat_room_id(rs.getInt("chat_room_id"));			
				chat_room_mem.setMem_id(rs.getInt("room_mem_id"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

}
