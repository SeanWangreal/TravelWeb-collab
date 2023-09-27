package com.tha103.gogoyu.itinerary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ItineraryJDBCDAO implements ItineraryDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO itinerary (trip_id, scene_id , begin_time ) VALUES (?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT  itinerary_id,trip_id, scene_id , begin_time  FROM itinerary order by itinerary_id";
	private static final String GET_ONE_STMT = "SELECT  itinerary_id,trip_id, scene_id , begin_time  FROM itinerary where itinerary_id = ?";
	private static final String DELETE = "DELETE FROM itinerary where itinerary_id = ?";
	private static final String UPDATE = "UPDATE itinerary set  itinerary_id = ? ,trip_id = ?, scene_id = ?, begin_time =?  where itinerary_id = ? ";

	@Override
	public void insert(Itinerary Itinerary) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "itinerary_id" };
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Itinerary.getTrip_id());
			pstmt.setInt(2, Itinerary.getScene_id());
			pstmt.setTimestamp(3, Itinerary.getBegin_time());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void update(Itinerary Itinerary) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, Itinerary.getItinerary_id());
			pstmt.setInt(2, Itinerary.getTrip_id());
			pstmt.setInt(3, Itinerary.getScene_id());
			pstmt.setTimestamp(4, Itinerary.getBegin_time());
			pstmt.setInt(5, Itinerary.getItinerary_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void delete(Integer itinerary_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, itinerary_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public Itinerary findByPrimaryKey(Integer itinerary_id) {
		Itinerary Itinerary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, itinerary_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Itinerary = new Itinerary();
				Itinerary.setItinerary_id(rs.getInt("itinerary_id"));
				Itinerary.setTrip_id(rs.getInt("trip_id"));
				Itinerary.setScene_id(rs.getInt("scene_id"));
				Itinerary.setBegin_time(rs.getTimestamp("begin_time"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return Itinerary;
	}

	@Override
	public List<Itinerary> getAll() {
		List<Itinerary> list = new ArrayList<Itinerary>();
		Itinerary itinVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itinVO = new Itinerary();
				itinVO.setItinerary_id(rs.getInt("itinerary_id"));
				itinVO.setTrip_id(rs.getInt("trip_id"));
				itinVO.setScene_id(rs.getInt("scene_id"));
				itinVO.setBegin_time(rs.getTimestamp("begin_time"));
				list.add(itinVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {

		ItineraryJDBCDAO dao = new ItineraryJDBCDAO();

		// 新增
//		Itinerary itinerary1= new Itinerary();
//
//		itinerary1.setTrip_id(1);
//		itinerary1.setScene_id(1);
//		itinerary1.setBegin_time(java.sql.Timestamp.valueOf("2005-01-01 10:10:49"));
//		dao.insert(Itinerary1);

		// 修改
//	    itinerary Itinerary2= new Itinerary();
//	    itinerary2.setItinerary_id(2);
//		itinerary2.setTrip_id(1);
//		itinerary2.setScene_id(2);
//		itinerary2.setBegin_time(java.sql.Timestamp.valueOf("2005-01-01 10:10:49"));
//		dao.update(Itinerary2);

		// 刪除
//		dao.delete(2);

//		 查詢
//		itinerary Itinerary3 = dao.findByPrimaryKey(1);
//		System.out.print(itinerary3.getItinerary_id()+ ",");
//		System.out.print(itinerary3.getTrip_id()+ ",");
//		System.out.print(itinerary3.getScene_id()+ ",");
//		System.out.print(itinerary3.getBegin_time()  );

		// 查詢
//		List<Itinerary> list = dao.getAll();
//		for (Itinerary aItinerary : list) {
//			System.out.print(aItinerary.getItinerary_id() + ",");
//			System.out.print(aItinerary.getTrip_id() + ",");
//			System.out.print(aItinerary.getScene_id() + ",");
//			System.out.print(aItinerary.getBegin_time());
//		}
	}
}
