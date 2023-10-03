package com.tha103.gogoyu.room_thumbup.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class Room_thumbupJDBCDAO implements Room_thumbupDAO_interface{
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

	public int insert(Room_thumbup roomThumbup) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, roomThumbup.getCusId());
			pstmt.setInt(2, roomThumbup.getRoomOrdId());
			pstmt.setTimestamp(3, roomThumbup.getThumbupTime());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	};

	public int update(Room_thumbup roomThumbup) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setTimestamp(1, roomThumbup.getThumbupTime());
			pstmt.setInt(2, roomThumbup.getCusId());
			pstmt.setInt(3, roomThumbup.getRoomOrdId());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	};

	public int delete(Integer cusId, Integer roomOrdId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, cusId);
			pstmt.setInt(2, roomOrdId);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	};

	public Room_thumbup findByPrimaryKey(Integer cusId, Integer roomOrdId) {
		Room_thumbup roomThumbup = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, cusId);
			pstmt.setInt(2, roomOrdId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomThumbup = new Room_thumbup();
				roomThumbup.setCusId(rs.getInt(1));
				roomThumbup.setRoomOrdId(rs.getInt(2));
				roomThumbup.setThumbupTime(rs.getTimestamp(3));
			}
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return roomThumbup;
	};

	public List<Room_thumbup> getAll() {
		List<Room_thumbup> list = new ArrayList<Room_thumbup>();
		Room_thumbup roomThumbup = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomThumbup = new Room_thumbup();
				roomThumbup.setCusId(rs.getInt("cus_id"));
				roomThumbup.setRoomOrdId(rs.getInt("room_ord_id"));
				roomThumbup.setThumbupTime(rs.getTimestamp("thumbup_time"));
				list.add(roomThumbup);
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
//		System.out.println(select.getCusId());
//		System.out.println(select.getRoomOrdId());
//		System.out.println(select.getThumbupTime());
		// 查詢全部
//		List<Thumbup_room> all = dao.getAll();
//		for(Thumbup_room item : all) {
//			System.out.println(item);
//		}
		System.out.println("OK");
	}
}
