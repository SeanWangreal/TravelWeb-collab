package com.tha103.gogoyu.room_ord.model;

import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class Room_ordJDBCDAO implements Room_ordDAO_interface {

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO room_ord (plan_id,room_id,cus_id,amount,total_price,commission,people,check_in_time,check_out_time,ord_status,ord_time,remark,score,comments,comments_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT room_ord_id,plan_id,room_id,cus_id,amount,total_price,commission,people,check_in_time,check_out_time,ord_status,ord_time,remark,score,comments,comments_time FROM room_ord";
	private static final String GET_ONE_STMT = "SELECT room_ord_id,plan_id,room_id,cus_id,amount,total_price,commission,people,check_in_time,check_out_time,ord_status,ord_time,remark,score,comments,comments_time FROM room_ord WHERE room_ord_id = ?";

	private static final String DELETE_ROOM_ORD = "DELETE FROM room_ord where room_ord_id = ?";

	private static final String UPDATE = "UPDATE room_ord set plan_id = ? ,room_id = ? ,cus_id = ? ,amount = ? ,total_price = ? ,commission = ? ,people = ? ,check_in_time = ? ,check_out_time = ? ,ord_status = ? ,ord_time = ? ,remark = ? ,score = ? ,comments = ? ,comments_time = ? where room_ord_id = ?";

	@Override
	public int add(Room_ord roomOrd) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"room_ord_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setInt(1, roomOrd.getPlanId());
			pstmt.setInt(2, roomOrd.getRoomId());
			pstmt.setInt(3, roomOrd.getCusId());
			pstmt.setInt(4, roomOrd.getAmount());
			pstmt.setBigDecimal(5, roomOrd.getTotalPrice());
			pstmt.setBigDecimal(6, roomOrd.getCommission());
			pstmt.setInt(7, roomOrd.getPeople());
			pstmt.setTimestamp(8, roomOrd.getCheckInTime());
			pstmt.setTimestamp(9, roomOrd.getCheckOutTime());
			pstmt.setInt(10, roomOrd.getOrdStatus());
			pstmt.setTimestamp(11, roomOrd.getOrdTime());
			pstmt.setString(12, roomOrd.getRemark());
			pstmt.setInt(13, roomOrd.getScore());
			pstmt.setString(14, roomOrd.getComments());
			pstmt.setTimestamp(15, roomOrd.getCommentsTime());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int update(Room_ord roomOrd) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roomOrd.getPlanId());
			pstmt.setInt(2, roomOrd.getRoomId());
			pstmt.setInt(3, roomOrd.getCusId());
			pstmt.setInt(4, roomOrd.getAmount());
			pstmt.setBigDecimal(5, roomOrd.getTotalPrice());
			pstmt.setBigDecimal(6, roomOrd.getCommission());
			pstmt.setInt(7, roomOrd.getPeople());
			pstmt.setTimestamp(8, roomOrd.getCheckInTime());
			pstmt.setTimestamp(9, roomOrd.getCheckOutTime());
			pstmt.setInt(10, roomOrd.getOrdStatus());
			pstmt.setTimestamp(11, roomOrd.getOrdTime());
			pstmt.setString(12, roomOrd.getRemark());
			pstmt.setInt(13, roomOrd.getScore());
			pstmt.setString(14, roomOrd.getComments());
			pstmt.setTimestamp(15, roomOrd.getCommentsTime());
			pstmt.setInt(16, roomOrd.getRoomOrdId());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int delete(Integer roomOrdId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_ROOM_ORD);
			pstmt.setInt(1, roomOrdId);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public Room_ord findByPK(Integer roomOrdId) {

		Room_ord roomOrd = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomOrdId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrd = new Room_ord();
				roomOrd.setRoomOrdId(rs.getInt("room_ord_id"));
				roomOrd.setPlanId(rs.getInt("plan_id"));
				roomOrd.setRoomId(rs.getInt("room_id"));
				roomOrd.setCusId(rs.getInt("cus_id"));
				roomOrd.setAmount(rs.getInt("amount"));
				roomOrd.setTotalPrice(rs.getBigDecimal("total_price"));
				roomOrd.setCommission(rs.getBigDecimal("commission"));
				roomOrd.setPeople(rs.getInt("people"));
				roomOrd.setCheckInTime(rs.getTimestamp("check_in_time"));
				roomOrd.setCheckOutTime(rs.getTimestamp("check_out_time"));
				roomOrd.setOrdStatus(rs.getInt("ord_status"));
				roomOrd.setRemark(rs.getString("remark"));
				roomOrd.setScore(rs.getInt("score"));
				roomOrd.setComments(rs.getString("comments"));
				roomOrd.setCommentsTime(rs.getTimestamp("comments_time"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return roomOrd;

	}

	@Override
	public List<Room_ord> getAll() {
		List<Room_ord> list = new ArrayList<Room_ord>();
		Room_ord roomOrd = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrd = new Room_ord();
				roomOrd.setRoomOrdId(rs.getInt("room_ord_id"));
				roomOrd.setPlanId(rs.getInt("plan_id"));
				roomOrd.setRoomId(rs.getInt("room_id"));
				roomOrd.setCusId(rs.getInt("cus_id"));
				roomOrd.setAmount(rs.getInt("amount"));
				roomOrd.setTotalPrice(rs.getBigDecimal("total_price"));
				roomOrd.setCommission(rs.getBigDecimal("commission"));
				roomOrd.setPeople(rs.getInt("people"));
				roomOrd.setCheckInTime(rs.getTimestamp("check_in_time"));
				roomOrd.setCheckInTime(rs.getTimestamp("check_out_time"));
				roomOrd.setOrdStatus(rs.getInt("ord_status"));
				roomOrd.setRemark(rs.getString("remark"));
				roomOrd.setScore(rs.getInt("score"));
				roomOrd.setComments(rs.getString("comments"));
				roomOrd.setCommentsTime(rs.getTimestamp("comments_time"));

				list.add(roomOrd);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}
	public static void main(String[] args) {

		Room_ordJDBCDAO dao = new Room_ordJDBCDAO();
//	// 新增
		Room_ord ord001 = new Room_ord();
		ord001.setPlanId(3);
		ord001.setRoomId(1);
		ord001.setCusId(1);
		ord001.setAmount(1);
		ord001.setTotalPrice(new BigDecimal(50000));
		ord001.setCommission(new BigDecimal(50000));
		ord001.setPeople(1);
		ord001.setCheckInTime(Timestamp.valueOf("2023-09-11 17:55:55"));
		ord001.setCheckOutTime(Timestamp.valueOf("2023-09-12 17:55:55"));
		ord001.setOrdStatus(1);
		ord001.setOrdTime(Timestamp.valueOf("2023-09-14 17:55:55"));
		ord001.setRemark("測");
		ord001.setScore(1);
		ord001.setComments("測");
		ord001.setCommentsTime(Timestamp.valueOf("2023-09-15 17:55:55"));
		dao.add(ord001);
//修改
//		Room_ord room_ord002 = new Room_ord();
//		room_ord002.setRoomOrdId(3);
//		room_ord002.setPlanId(2);
//		room_ord002.setRoomId(1);
//		room_ord002.setCusId(1);
//		room_ord002.setAmount(1);
//		room_ord002.setTotalPrice(1.0);
//		room_ord002.setCommission(5.0);
//		room_ord002.setPeople(1);
//		room_ord002.setCheckInTime(Timestamp.valueOf("2023-09-11 17:55:55"));
//		room_ord002.setCheckOutTime(Timestamp.valueOf("2023-09-12 17:55:55"));
//		room_ord002.setOrdStatus(1);
//		room_ord002.setOrdTime(Timestamp.valueOf("2023-09-14 17:55:55"));
//		room_ord002.setRemark("測");
//		room_ord002.setScore(1);
//		room_ord002.setComments("測");
//		room_ord002.setCommentsTime(Timestamp.valueOf("2023-09-15 17:55:55"));
//		dao.update(room_ord002);
		
		//刪除
//		dao.delete(2);
		
		//查詢
		Room_ord ord3 =dao.findByPK(3);
		System.out.print(ord3.getCommission() + ",");
		System.out.print(ord3.getPeople() + ",");
		System.out.println("++++++++++++++++");
		
		List<Room_ord> list = dao.getAll();
		for(Room_ord sRoom : list) {
			System.out.print(sRoom.getCommission() + ".");
			System.out.print(sRoom.getPeople());
			System.out.println();
		}
	}

}

	
