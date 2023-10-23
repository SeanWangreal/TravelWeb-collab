package com.tha103.gogoyu.chat_msg.model;

import java.util.*;
import java.util.Date;
import java.sql.*;
import util.Util;

public class MessageJDBCDAO implements MessageDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO Message (chat_room_id,msg_log,mem_id,msg_time) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT msg_id,chat_room_id,mem_id,msg_log,msg_time FROM Message order by msg_id";
	private static final String GET_ONE_STMT = "SELECT msg_id,chat_room_id,mem_id,msg_log,msg_time FROM Message where msg_id= ?";
	private static final String DELETE = "DELETE FROM Message where msg_id = ?";
	private static final String UPDATE = "UPDATE Message set chat_room_id=?, mem_id=?, msg_log=?, msg_time=? where msg_id = ?";
	
	@Override
	public void insert(Message Message) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, Message.getChatRoomId());
			pstmt.setInt(2, Message.getMemId());
			pstmt.setString(3, Message.getMsgLog());
			pstmt.setTimestamp(4, Message.getMsgTime());
			pstmt.executeUpdate();
			System.out.println("success");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Message Message) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, Message.getChatRoomId());
			pstmt.setInt(2, Message.getMemId());
			pstmt.setString(3, Message.getMsgLog());
			pstmt.setTimestamp(4, Message.getMsgTime());
			pstmt.setInt(5, Message.getMsgId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer Msg_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, Msg_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Message findByPrimaryKey(Integer Msg_id) {
		Message Message = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, Msg_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Message = new Message();
				Message.setMsgId(rs.getInt("Msg_id"));
				Message.setChatRoomId(rs.getInt("Chat_room_id"));
				Message.setMemId(rs.getInt("mem_id"));
				Message.setMsgLog(rs.getString("Msg_log"));
				Message.setMsgTime(rs.getTimestamp("Msg_time"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return Message;
	}

	@Override
	public List<Message> getAll() {
		List<Message> list = new ArrayList<Message>();
		Message Message = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Message = new Message();
				Message.setMsgId(rs.getInt("Msg_id"));
				Message.setChatRoomId(rs.getInt("Chat_room_id"));
				Message.setMemId(rs.getInt("mem_id"));
				Message.setMsgLog(rs.getString("Msg_log"));
				Message.setMsgTime(rs.getTimestamp("Msg_time"));
				list.add(Message);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		MessageJDBCDAO dao = new MessageJDBCDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
		// 新增
//		Message Message = new Message();
//		Message.setChat_room_id(1);
//		Message.setMsg_log("1");
//		Message.setMsg_time(time_s);
//		Message.setMem_id(1);
//		dao.insert(Message);

		// 修改
//		Message Message = new Message();
//		Message.setChat_room_id(3);
//		Message.setMsg_log("3");
//		Message.setMsg_time(time_s);
//		Message.setMem_id(1);
//		Message.setMsg_id(3);
//		dao.update(Message);
//
//		// 刪除
//		dao.delete(5);

		// 查詢
//		Message Message = dao.findByPrimaryKey(15);
//		System.out.print(Message.getMsg_id() + ",");
//		System.out.print(Message.getChat_room_id() + ",");
//		System.out.print(Message.getMem_id() + ",");
//		System.out.print(Message.getMsg_log() + ",");
//		System.out.print(Message.getMsg_time() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<Message> list = dao.getAll();
		for (Message aMsg : list) {
			System.out.print(aMsg.getMsgId() + ",");
			System.out.print(aMsg.getChatRoomId() + ",");
			System.out.print(aMsg.getMemId() + ",");
			System.out.print(aMsg.getMsgLog() + ",");
			System.out.print(aMsg.getMsgTime() + ",");
			System.out.println();
		}
	}

}
