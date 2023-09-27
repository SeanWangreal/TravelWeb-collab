package com.tha103.gogoyu.room_thumbup.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class Room_thumbupJDBCDAO {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	public static final String INSERT_STMT = "insert into thumbup_room (cus_id,room_ord_id,thumbup_time) values(?,?,?)";
	private static final String GET_ALL_STMT = "select * from thumbup_room order by thumbup_time;";
	private static final String GET_ONE_STMT = "select * from thumbup_room where cus_id = ? and room_ord_id = ?";
	private static final String DELETE = "delete from thumbup_room where cus_id = ? and room_ord_id = ?";
	private static final String UPDATE = "update thumbup_room set thumbup_time = ? where cus_id = ? and room_ord_id = ?";

	public void insert(Room_thumbup room_thumbup) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, room_thumbup.getCus_id());
			pstmt.setInt(2, room_thumbup.getRoom_ord_id());
			pstmt.setTimestamp(3, room_thumbup.getThumbup_time());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public void update(Room_thumbup room_thumbup) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setTimestamp(1, room_thumbup.getThumbup_time());
			pstmt.setInt(2, room_thumbup.getCus_id());
			pstmt.setInt(3, room_thumbup.getRoom_ord_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public void delete(Integer cus_id, Integer room_ord_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, cus_id);
			pstmt.setInt(2, room_ord_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public Room_thumbup findByPrimaryKey(Integer cus_id, Integer room_ord_id) {
		Room_thumbup room_thumbup = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, cus_id);
			pstmt.setInt(2, room_ord_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				room_thumbup = new Room_thumbup();
				room_thumbup.setCus_id(rs.getInt(1));
				room_thumbup.setRoom_ord_id(rs.getInt(2));
				room_thumbup.setThumbup_time(rs.getTimestamp(3));
			}
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return room_thumbup;
	};

	public List<Room_thumbup> getAll() {
		List<Room_thumbup> list = new ArrayList<Room_thumbup>();
		Room_thumbup room_thumbup = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				room_thumbup = new Room_thumbup();
				room_thumbup.setCus_id(rs.getInt("cus_id"));
				room_thumbup.setRoom_ord_id(rs.getInt("room_ord_id"));
				room_thumbup.setThumbup_time(rs.getTimestamp("thumbup_time"));
				list.add(room_thumbup);
			}
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	};

	public static void main(String[] args) {
//		Thumbup_roomJDBCDAO dao =new Thumbup_roomJDBCDAO();
//		Date date = new Date();
//		Timestamp time_s = new Timestamp(date.getTime());
//		//新增
//		Thumbup_room item = new Thumbup_room(121,201,time_s);
//		dao.insert(item);
		// 修改
//		Thumbup_room item = new Thumbup_room(121,201,time_s);
//		dao.update(item);
		// 刪除
//		dao.delete(121, 201);
		// 查詢一個
//		Thumbup_room select = dao.findByPrimaryKey(121, 201);
//		System.out.println(select.getCus_id());
//		System.out.println(select.getRoom_ord_id());
//		System.out.println(select.getThumbup_time());
		// 查詢全部
//		List<Thumbup_room> all = dao.getAll();
//		for(Thumbup_room item : all) {
//			System.out.println(item);
//		}
		System.out.println("OK");
	}
}
