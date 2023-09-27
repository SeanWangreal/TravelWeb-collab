package com.tha103.gogoyu.trip_ord.model;

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
					+ "order_status,"
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
					+ "order_status=?,"
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
			pstmt.setInt(1, trip_ord.getTrip_id());
			pstmt.setInt(2, trip_ord.getPlan_id());
			pstmt.setInt(3, trip_ord.getCus_id());
			pstmt.setInt(4, trip_ord.getAmount());
			pstmt.setDouble(5, trip_ord.getTotal_price());
			pstmt.setDouble(6, trip_ord.getCommission());
			pstmt.setInt(7, trip_ord.getOrder_status());
			pstmt.setTimestamp(8, trip_ord.getOrd_time());
			pstmt.setString(9, trip_ord.getRemark());
			pstmt.setInt(10, trip_ord.getScore());
			pstmt.setString(11, trip_ord.getComments());
			pstmt.setTimestamp(12, trip_ord.getComments_time());
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
			pstmt.setInt(1, trip_ord.getTrip_id());
			pstmt.setInt(2, trip_ord.getPlan_id());
			pstmt.setInt(3, trip_ord.getCus_id());
			pstmt.setDouble(4, trip_ord.getAmount());
			pstmt.setDouble(5, trip_ord.getTotal_price());
			pstmt.setDouble(6, trip_ord.getCommission());
			pstmt.setInt(7, trip_ord.getOrder_status());
			pstmt.setTimestamp(8, trip_ord.getOrd_time());
			pstmt.setString(9, trip_ord.getRemark());
			pstmt.setInt(10, trip_ord.getScore());
			pstmt.setString(11, trip_ord.getComments());
			pstmt.setTimestamp(12, trip_ord.getComments_time());
			pstmt.setInt(13, trip_ord.getTrip_ord_id());
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
				trip_ord.setTrip_ord_id(rs.getInt("trip_ord_id"));
				trip_ord.setTrip_id(rs.getInt("trip_id"));
				trip_ord.setPlan_id(rs.getInt("plan_id"));
				trip_ord.setCus_id(rs.getInt("cus_id"));
				trip_ord.setAmount(rs.getInt("amount"));
				trip_ord.setTotal_price(rs.getDouble("total_price"));
				trip_ord.setCommission(rs.getDouble("commission"));
				trip_ord.setOrder_status(rs.getInt("order_status"));
				trip_ord.setOrd_time(rs.getTimestamp("ord_time"));
				trip_ord.setRemark(rs.getString("remark"));
				trip_ord.setScore(rs.getInt("score"));
				trip_ord.setComments(rs.getString("comments"));
				trip_ord.setComments_time(rs.getTimestamp("comments_time"));
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
				trip_ord.setTrip_ord_id(rs.getInt("trip_ord_id"));
				trip_ord.setTrip_id(rs.getInt("trip_id"));
				trip_ord.setPlan_id(rs.getInt("plan_id"));
				trip_ord.setCus_id(rs.getInt("cus_id"));
				trip_ord.setAmount(rs.getInt("amount"));
				trip_ord.setTotal_price(rs.getDouble("total_price"));
				trip_ord.setCommission(rs.getDouble("commission"));
				trip_ord.setOrder_status(rs.getInt("order_status"));
				trip_ord.setOrd_time(rs.getTimestamp("ord_time"));
				trip_ord.setRemark(rs.getString("remark"));
				trip_ord.setScore(rs.getInt("score"));
				trip_ord.setComments(rs.getString("comments"));
				trip_ord.setComments_time(rs.getTimestamp("comments_time"));
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
		
////		trip_ordVO1.setTrip_ord_id(103);
//		trip_ordVO1.setTrip_id(203);
//		trip_ordVO1.setPlan_id(303);
//		trip_ordVO1.setCus_id(403);
//		trip_ordVO1.setAmount(3);
//		trip_ordVO1.setTotal_price((double)50000);
//		trip_ordVO1.setCommission((double)10000);
//		trip_ordVO1.setOrder_status(0);
//		trip_ordVO1.setOrd_time(time_s);
//		trip_ordVO1.setRemark("Remark0");
//		trip_ordVO1.setScore(10);
//		trip_ordVO1.setComments("Comments0");
//		trip_ordVO1.setComments_time(time_s);
//		
//		dao.insert(trip_ordVO1);
		
		// update
		Trip_ord trip_ordVO2 = new Trip_ord();
		
//		trip_ordVO2.setTrip_id(203);
//		trip_ordVO2.setPlan_id(303);
//		trip_ordVO2.setCus_id(403);
//		trip_ordVO2.setAmount(3);
//		trip_ordVO2.setTotal_price((double)60000);
//		trip_ordVO2.setCommission((double)20000);
//		trip_ordVO2.setOrder_status(0);
//		trip_ordVO2.setOrd_time(time_s);
//		trip_ordVO2.setRemark("Remark0");
//		trip_ordVO2.setScore(10);
//		trip_ordVO2.setComments("Message0");
//		trip_ordVO2.setComments_time(time_s);
//		trip_ordVO2.setTrip_ord_id(4);
//		
//		dao.update(trip_ordVO2);
		
		// delete
//		dao.delete(3);
		
		//findByPrimaryKey
//		Trip_ord trip_ordVO3 = dao.findByPrimaryKey(5);
//
//		System.out.print(trip_ordVO3.getTrip_ord_id() + ", ");
//		System.out.print(trip_ordVO3.getTrip_id() + ", ");
//		System.out.print(trip_ordVO3.getPlan_id() + ", ");
//		System.out.print(trip_ordVO3.getCus_id() + ", ");
//		System.out.print(trip_ordVO3.getAmount() + ", ");
//		System.out.print(trip_ordVO3.getTotal_price() + ", ");
//		System.out.print(trip_ordVO3.getCommission()+", ");
//		System.out.print(trip_ordVO3.getOrder_status()+", ");
//		System.out.print(trip_ordVO3.getOrd_time()+", ");
//		System.out.print(trip_ordVO3.getRemark()+", ");
//		System.out.print(trip_ordVO3.getScore()+", ");
//		System.out.print(trip_ordVO3.getComments()+", ");
//		System.out.println(trip_ordVO3.getComments_time()+", ");
//		System.out.println("----------------------------------------------------------------");
		
		//getAll
		List<Trip_ord> list = dao.getAll();

		for (Trip_ord aTrip : list) {
			System.out.print(aTrip.getTrip_ord_id() + ", ");
			System.out.print(aTrip.getTrip_id() + ", ");
			System.out.print(aTrip.getPlan_id() + ", ");
			System.out.print(aTrip.getCus_id() + ", ");
			System.out.print(aTrip.getAmount() + ", ");
			System.out.print(aTrip.getTotal_price() + ", ");
			System.out.print(aTrip.getCommission()+", ");
			System.out.print(aTrip.getOrder_status()+", ");
			System.out.print(aTrip.getOrd_time()+", ");
			System.out.print(aTrip.getRemark()+", ");
			System.out.print(aTrip.getScore()+", ");
			System.out.print(aTrip.getComments()+", ");
			System.out.println(aTrip.getComments_time()+", ");
			System.out.println("----------------------------------------------------------------");
		}
	}
}

