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
	public int add(Itinerary Itinerary) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "itinerary_id" };
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Itinerary.getTripId());
			pstmt.setInt(2, Itinerary.getSceneId());
			pstmt.setTimestamp(3, Itinerary.getBeginTime());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int update(Itinerary Itinerary) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, Itinerary.getItineraryId());
			pstmt.setInt(2, Itinerary.getTripId());
			pstmt.setInt(3, Itinerary.getSceneId());
			pstmt.setTimestamp(4, Itinerary.getBeginTime());
			pstmt.setInt(5, Itinerary.getItineraryId());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int delete(Integer itinerary_id) {
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
		return -1;
	}

	@Override
	public Itinerary findByPK(Integer itinerary_id) {
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
				Itinerary.setItineraryId(rs.getInt("itinerary_id"));
				Itinerary.setTripId(rs.getInt("trip_id"));
				Itinerary.setSceneId(rs.getInt("scene_id"));
				Itinerary.setBeginTime(rs.getTimestamp("begin_time"));
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
				itinVO.setItineraryId(rs.getInt("itinerary_id"));
				itinVO.setTripId(rs.getInt("trip_id"));
				itinVO.setSceneId(rs.getInt("scene_id"));
				itinVO.setBeginTime(rs.getTimestamp("begin_time"));
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
//		itinerary1.setTripId(5);
//		itinerary1.setSceneId(1);
//		itinerary1.setBeginTime(java.sql.Timestamp.valueOf("2005-01-01 10:10:49"));
//		dao.add(itinerary1);

		// 修改
//	    Itinerary Itinerary2= new Itinerary();
//	    Itinerary2.setItineraryId(2);
//		Itinerary2.setTripId(1);
//		Itinerary2.setSceneId(2);
//		Itinerary2.setBeginTime(java.sql.Timestamp.valueOf("2005-01-01 10:10:49"));
//		dao.update(Itinerary2);

		// 刪除
//		dao.delete(2);

//		 查詢
		Itinerary Itinerary3 = dao.findByPK(3);
		System.out.print(Itinerary3.getItineraryId()+ ",");
		System.out.print(Itinerary3.getTripId()+ ",");
		System.out.print(Itinerary3.getSceneId()+ ",");
		System.out.print(Itinerary3.getBeginTime()  );

		// 查詢
		List<Itinerary> list = dao.getAll();
		for (Itinerary aItinerary : list) {
			System.out.print(aItinerary.getItineraryId() + ",");
			System.out.print(aItinerary.getTripId() + ",");
			System.out.print(aItinerary.getSceneId() + ",");
			System.out.print(aItinerary.getBeginTime());
		}
	}

	@Override
	public List<Itinerary> getAllByTripId(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllByTripId(Integer tripId) {
		// TODO Auto-generated method stub
		
	}
}
