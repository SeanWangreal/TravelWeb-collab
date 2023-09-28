package com.tha103.gogoyu.trip_ord.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Util;

public class Trip_ordJDBCDAO implements Trip_ordDAO_Interface {

	static {
		try {
			Class.forName(Util.DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO trip_ord ("
					+ "trip_id,"
					+ "plan_id,"
					+ "cus_id,"
					+ "amount,"
					+ "total_price,"
					+ "commission,"
					+ "ord_status,"
					+ "ord_time,"
					+ "remark,"
					+ "score,"
					+ "comments,"
					+ "comments_time)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT *  FROM trip_ord ORDER BY trip_ord_id";
		private static final String GET_ONE_STMT = 
			"SELECT *  FROM trip_ord WHERE trip_ord_id = ?";
		private static final String DELETE = 
			"DELETE FROM trip_ord WHERE trip_ord_id = ?";
		private static final String UPDATE = 
			"UPDATE trip_ord SET "
					+ "trip_id=?,"
					+ "plan_id=?,"
					+ "cus_id=?,"
					+ "amount=?,"
					+ "total_price=?,"
					+ "commission=?,"
					+ "ord_status=?,"
					+ "ord_time=?,"
					+ "remark=?,"
					+ "score=?,"
					+ "comments=?,"
					+ "comments_time=?"
					+ " WHERE trip_ord_id = ?";

	@Override
	public void insert(Trip_ord trip_ord) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] trip_ord_id = {"trip_ord_id"};
			pstmt = con.prepareStatement(INSERT_STMT,trip_ord_id);
			pstmt.setInt(1, trip_ord.getTripId());
			pstmt.setInt(2, trip_ord.getPlanId());
			pstmt.setInt(3, trip_ord.getCusId());
			pstmt.setInt(4, trip_ord.getAmount());
			pstmt.setBigDecimal(5, trip_ord.getTotalPrice());
			pstmt.setBigDecimal(6, trip_ord.getCommission());
			pstmt.setInt(7, trip_ord.getOrdStatus());
			pstmt.setTimestamp(8, trip_ord.getOrdTime());
			pstmt.setString(9, trip_ord.getRemark());
			pstmt.setInt(10, trip_ord.getScore());
			pstmt.setString(11, trip_ord.getComments());
			pstmt.setTimestamp(12, trip_ord.getCommentsTime());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Trip_ord trip_ord) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, trip_ord.getTripId());
			pstmt.setInt(2, trip_ord.getPlanId());
			pstmt.setInt(3, trip_ord.getCusId());
			pstmt.setInt(4, trip_ord.getAmount());
			pstmt.setBigDecimal(5, trip_ord.getTotalPrice());
			pstmt.setBigDecimal(6, trip_ord.getCommission());
			pstmt.setInt(7, trip_ord.getOrdStatus());
			pstmt.setTimestamp(8, trip_ord.getOrdTime());
			pstmt.setString(9, trip_ord.getRemark());
			pstmt.setInt(10, trip_ord.getScore());
			pstmt.setString(11, trip_ord.getComments());
			pstmt.setTimestamp(12, trip_ord.getCommentsTime());
			pstmt.setInt(13, trip_ord.getTripOrdId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer trip_ord_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, trip_ord_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Trip_ord findByPrimaryKey(Integer trip_ord_id) {
		Trip_ord trip_ord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, trip_ord_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_ord = new Trip_ord();
				trip_ord.setTripOrdId(rs.getInt("trip_ord_id"));
				trip_ord.setTripId(rs.getInt("trip_id"));
				trip_ord.setPlanId(rs.getInt("plan_id"));
				trip_ord.setCusId(rs.getInt("cus_id"));
				trip_ord.setAmount(rs.getInt("amount"));
				trip_ord.setTotalPrice(rs.getBigDecimal("total_price"));
				trip_ord.setCommission(rs.getBigDecimal("commission"));
				trip_ord.setOrdStatus(rs.getInt("ord_status"));
				trip_ord.setOrdTime(rs.getTimestamp("ord_time"));
				trip_ord.setRemark(rs.getString("remark"));
				trip_ord.setScore(rs.getInt("score"));
				trip_ord.setComments(rs.getString("comments"));
				trip_ord.setCommentsTime(rs.getTimestamp("comments_time"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return trip_ord;
	}

	@Override
	public List<Trip_ord> getAll() {
		List<Trip_ord> list = new ArrayList<Trip_ord>();
		Trip_ord trip_ord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_ord = new Trip_ord();
				trip_ord.setTripOrdId(rs.getInt("trip_ord_id"));
				trip_ord.setTripId(rs.getInt("trip_id"));
				trip_ord.setPlanId(rs.getInt("plan_id"));
				trip_ord.setCusId(rs.getInt("cus_id"));
				trip_ord.setAmount(rs.getInt("amount"));
				trip_ord.setTotalPrice(rs.getBigDecimal("total_price"));
				trip_ord.setCommission(rs.getBigDecimal("commission"));
				trip_ord.setOrdStatus(rs.getInt("ord_status"));
				trip_ord.setOrdTime(rs.getTimestamp("ord_time"));
				trip_ord.setRemark(rs.getString("remark"));
				trip_ord.setScore(rs.getInt("score"));
				trip_ord.setComments(rs.getString("comments"));
				trip_ord.setCommentsTime(rs.getTimestamp("comments_time"));
				list.add(trip_ord);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}
	
	public static void main(String args[]) {
		Trip_ordJDBCDAO dao=new Trip_ordJDBCDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
		
		//insert
		Trip_ord trip_ordVO1 = new Trip_ord();
		
//		trip_ordVO1.setTripId(306);
//		trip_ordVO1.setPlanId(206);
//		trip_ordVO1.setCusId(106);
//		trip_ordVO1.setAmount(3);
//		trip_ordVO1.setTotalPrice(new BigDecimal(50000));
//		trip_ordVO1.setCommission(new BigDecimal(5000));
//		trip_ordVO1.setOrdStatus(0);
//		trip_ordVO1.setOrdTime(time_s);
//		trip_ordVO1.setRemark("remark_insert");
//		trip_ordVO1.setScore(10);
//		trip_ordVO1.setComments("comments_insert");
//		trip_ordVO1.setCommentsTime(time_s);
//		
//		dao.insert(trip_ordVO1);
		
		// update
		Trip_ord trip_ordVO2 = new Trip_ord();
		
//		trip_ordVO2.setTripId(306);
//		trip_ordVO2.setPlanId(206);
//		trip_ordVO2.setCusId(106);
//		trip_ordVO2.setAmount(5);
//		trip_ordVO2.setTotalPrice(new BigDecimal(60000));
//		trip_ordVO2.setCommission(new BigDecimal(6000));
//		trip_ordVO2.setOrdStatus(0);
//		trip_ordVO2.setOrdTime(time_s);
//		trip_ordVO2.setRemark("remark_update");
//		trip_ordVO2.setScore(10);
//		trip_ordVO2.setComments("comments_update");
//		trip_ordVO2.setCommentsTime(time_s);
//		trip_ordVO2.setTripOrdId(6);
//		
//		dao.update(trip_ordVO2);
		
		// delete
//		dao.delete(6);
		
		//findByPrimaryKey
		Trip_ord trip_ordVO3 = dao.findByPrimaryKey(5);
//
		System.out.print(trip_ordVO3.getTripOrdId() + ", ");
		System.out.print(trip_ordVO3.getTripId() + ", ");
		System.out.print(trip_ordVO3.getPlanId() + ", ");
		System.out.print(trip_ordVO3.getCusId() + ", ");
		System.out.print(trip_ordVO3.getAmount() + ", ");
		System.out.print(trip_ordVO3.getTotalPrice() + ", ");
		System.out.print(trip_ordVO3.getCommission()+", ");
		System.out.print(trip_ordVO3.getOrdStatus()+", ");
		System.out.print(trip_ordVO3.getOrdTime()+", ");
		System.out.print(trip_ordVO3.getRemark()+", ");
		System.out.print(trip_ordVO3.getScore()+", ");
		System.out.print(trip_ordVO3.getComments()+", ");
		System.out.println(trip_ordVO3.getCommentsTime()+", ");
		System.out.println("----------------------------------------------------------------");
		
		//getAll
		List<Trip_ord> list = dao.getAll();

		for (Trip_ord aTrip : list) {
			System.out.print(aTrip.getTripOrdId() + ", ");
			System.out.print(aTrip.getTripId() + ", ");
			System.out.print(aTrip.getPlanId() + ", ");
			System.out.print(aTrip.getCusId() + ", ");
			System.out.print(aTrip.getAmount() + ", ");
			System.out.print(aTrip.getTotalPrice() + ", ");
			System.out.print(aTrip.getCommission()+", ");
			System.out.print(aTrip.getOrdStatus()+", ");
			System.out.print(aTrip.getOrdTime()+", ");
			System.out.print(aTrip.getRemark()+", ");
			System.out.print(aTrip.getScore()+", ");
			System.out.print(aTrip.getComments()+", ");
			System.out.println(aTrip.getCommentsTime()+", ");
			System.out.println("---------------------------------------------------------------------------------------------------------------");
		}
	}
}

