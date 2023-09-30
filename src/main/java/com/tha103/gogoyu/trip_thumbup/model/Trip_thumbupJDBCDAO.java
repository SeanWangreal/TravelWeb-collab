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

	public void insert(Trip_thumbup tripThumbup) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, tripThumbup.getCusId());
			pstmt.setInt(2, tripThumbup.getTripOrdId());
			pstmt.setTimestamp(3, tripThumbup.getThumbupTime());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public void update(Trip_thumbup tripThumbup) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setTimestamp(1, tripThumbup.getThumbupTime());
			pstmt.setInt(2, tripThumbup.getCusId());
			pstmt.setInt(3, tripThumbup.getTripOrdId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public void delete(Integer cusId, Integer tripOrdId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, cusId);
			pstmt.setInt(2, tripOrdId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public Trip_thumbup findByPrimaryKey(Integer cusId, Integer tripOrdId) {
		Trip_thumbup trip_thumbup = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, cusId);
			pstmt.setInt(2, tripOrdId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_thumbup = new Trip_thumbup();
				trip_thumbup.setCusId(rs.getInt(1));
				trip_thumbup.setTripOrdId(rs.getInt(2));
				trip_thumbup.setThumbupTime(rs.getTimestamp(3));
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
				trip_thumbup.setCusId(rs.getInt("cus_id"));
				trip_thumbup.setTripOrdId(rs.getInt("trip_ord_id"));
				trip_thumbup.setThumbupTime(rs.getTimestamp("thumbup_time"));
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
//		System.out.println(select.getCusId());
//		System.out.println(select.getTripOrdId());
//		System.out.println(select.getThumbupTime());
		// 查詢全部
//		List<Thumbup_trip> all = dao.getAll();
//		for(Thumbup_trip item : all) {
//		System.out.println(item.getCusId());
//		System.out.println(item.getTripOrdId());
//		System.out.println(item.getThumbupTime());
//		}
		System.out.println("OK");
	}
}
