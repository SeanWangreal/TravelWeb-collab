package com.tha103.gogoyu.trip_thumbup.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import util.Util;

public class Trip_thumbupJDBCDAO {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	public static final String INSERT_STMT = "insert into thumbup_trip (cus_id,trip_ord_id,thumbup_time) values(?,?,?)";
	private static final String GET_ALL_STMT = "select * from thumbup_trip order by thumbup_time;";
	private static final String GET_ONE_STMT = "select * from  thumbup_trip where cus_id = ? and trip_ord_id = ?";
	private static final String DELETE = "delete from thumbup_trip where cus_id = ? and trip_ord_id = ?";
	private static final String UPDATE = "update thumbup_trip set thumbup_time = ? where cus_id = ? and trip_ord_id = ?";

	public void insert(Trip_thumbup trip_thumbup) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, trip_thumbup.getCus_id());
			pstmt.setInt(2, trip_thumbup.getTrip_ord_id());
			pstmt.setTimestamp(3, trip_thumbup.getThumbup_time());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public void update(Trip_thumbup trip_thumbup) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setTimestamp(1, trip_thumbup.getThumbup_time());
			pstmt.setInt(2, trip_thumbup.getCus_id());
			pstmt.setInt(3, trip_thumbup.getTrip_ord_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public void delete(Integer cus_id, Integer trip_ord_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, cus_id);
			pstmt.setInt(2, trip_ord_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public Trip_thumbup findByPrimaryKey(Integer cus_id, Integer trip_ord_id) {
		Trip_thumbup trip_thumbup = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, cus_id);
			pstmt.setInt(2, trip_ord_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_thumbup = new Trip_thumbup();
				trip_thumbup.setCus_id(rs.getInt(1));
				trip_thumbup.setTrip_ord_id(rs.getInt(2));
				trip_thumbup.setThumbup_time(rs.getTimestamp(3));
			}
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return trip_thumbup;
	};

	public List<Trip_thumbup> getAll() {
		List<Trip_thumbup> list = new ArrayList<Trip_thumbup>();
		Trip_thumbup trip_thumbup = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_thumbup = new Trip_thumbup();
				trip_thumbup.setCus_id(rs.getInt("cus_id"));
				trip_thumbup.setTrip_ord_id(rs.getInt("trip_ord_id"));
				trip_thumbup.setThumbup_time(rs.getTimestamp("thumbup_time"));
				list.add(trip_thumbup);
			}
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	};

	public static void main(String[] args) {
//		Thumbup_tripJDBCDAO dao =new Thumbup_tripJDBCDAO();
//		Date date = new Date();
//		Timestamp time_s = new Timestamp(date.getTime());
//		//新增
//		Thumbup_trip item = new Thumbup_trip(121,201,time_s);
//		dao.insert(item);
		// 修改
//		Thumbup_trip item = new Thumbup_trip(121,201,time_s);
//		dao.update(item);
		// 刪除
//		dao.delete(121, 201);
		// 查詢一個
//		Thumbup_trip select = dao.findByPrimaryKey(121, 201);
//		System.out.println(select.getCus_id());
//		System.out.println(select.getTrip_ord_id());
//		System.out.println(select.getThumbup_time());
		// 查詢全部
//		List<Thumbup_trip> all = dao.getAll();
//		for(Thumbup_trip item : all) {
//			System.out.println(item);
//		}
		System.out.println("OK");
	}
}
