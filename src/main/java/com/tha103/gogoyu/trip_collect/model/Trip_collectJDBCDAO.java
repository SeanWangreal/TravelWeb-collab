package com.tha103.gogoyu.trip_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import util.Util;

public class Trip_collectJDBCDAO implements Trip_collectDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	public static final String INSERT_STMT = "insert into trip_collect (cus_id,trip_id,collect_time) values(?,?,?)";
	private static final String GET_ALL_STMT = "select * from trip_collect order by collect_time;";
	private static final String GET_ONE_STMT = "select * from trip_collect where cus_id = ?  and trip_id = ?";
	private static final String DELETE = "delete from trip_collect where cus_id = ? and trip_id = ?";
	private static final String UPDATE = "update trip_collect set collect_time = ? where cus_id = ? and trip_id = ?";

	public void insert(Trip_collect trip_collect) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, trip_collect.getCusId());
			pstmt.setInt(2, trip_collect.getTripId());
			pstmt.setTimestamp(3, trip_collect.getCollectTime());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public void update(Trip_collect trip_collect) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setTimestamp(1, trip_collect.getCollectTime());
			pstmt.setInt(2, trip_collect.getCusId());
			pstmt.setInt(3, trip_collect.getTripId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public void delete(Integer cus_id, Integer trip_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, cus_id);
			pstmt.setInt(2, trip_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};

	public Trip_collect findByPrimaryKey(Integer cus_id, Integer trip_id) {
		Trip_collect trip_collect = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, cus_id);
			pstmt.setInt(2, trip_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_collect = new Trip_collect();
				trip_collect.setCusId(rs.getInt("cus_id"));
				trip_collect.setTripId(rs.getInt("trip_id"));
				trip_collect.setCollectTime(rs.getTimestamp("collect_time"));
			}
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return trip_collect;
	};

	public List<Trip_collect> getAll() {
		List<Trip_collect> list = new ArrayList<Trip_collect>();
		Trip_collect trip_collect = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_collect = new Trip_collect();
				trip_collect.setCusId(rs.getInt("cus_id"));
				trip_collect.setTripId(rs.getInt("trip_id"));
				trip_collect.setCollectTime(rs.getTimestamp("collect_time"));
				list.add(trip_collect);
			}
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	};

	public static void main(String[] args) {
		Trip_collectJDBCDAO dao =new Trip_collectJDBCDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
//		//新增
//		Trip_collect item = new Trip_collect(121,201,time_s);
//		dao.insert(item);
		// 修改
//		Trip_collect item = new Trip_collect(121,201,time_s);
//		dao.update(item);
		// 刪除
//		dao.delete(121, 201);
		// 查詢一個
//		Trip_collect select = dao.findByPrimaryKey(121, 201);
//		System.out.println(select.getCusId());
//		System.out.println(select.getTripId());
//		System.out.println(select.getCollectTime());
		// 查詢全部
//		List<Trip_collect> all = dao.getAll();
//		for(Trip_collect item : all) {
//			System.out.println(item.getCusId());
//			System.out.println(item.getTripId());
//			System.out.println(item.getCollectTime());
//		}
		System.out.println("OK");
	}
}
