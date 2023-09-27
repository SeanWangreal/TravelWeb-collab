package com.tha103.gogoyu.room_ord.model;

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
	public void insert(Room_ord room_ord) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"room_ord_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setInt(1, room_ord.getPlan_id());
			pstmt.setInt(2, room_ord.getRoom_id());
			pstmt.setInt(3, room_ord.getCus_id());
			pstmt.setInt(4, room_ord.getAmount());
			pstmt.setDouble(5, room_ord.getTotal_price());
			pstmt.setDouble(6, room_ord.getCommission());
			pstmt.setInt(7, room_ord.getPeople());
			pstmt.setTimestamp(8, room_ord.getCheck_in_time());
			pstmt.setTimestamp(9, room_ord.getCheck_out_time());
			pstmt.setInt(10, room_ord.getOrd_status());
			pstmt.setTimestamp(11, room_ord.getOrd_time());
			pstmt.setString(12, room_ord.getRemark());
			pstmt.setInt(13, room_ord.getScore());
			pstmt.setString(14, room_ord.getComments());
			pstmt.setTimestamp(15, room_ord.getComments_time());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void update(Room_ord room_ord) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, room_ord.getPlan_id());
			pstmt.setInt(2, room_ord.getRoom_id());
			pstmt.setInt(3, room_ord.getCus_id());
			pstmt.setInt(4, room_ord.getAmount());
			pstmt.setDouble(5, room_ord.getTotal_price());
			pstmt.setDouble(6, room_ord.getCommission());
			pstmt.setInt(7, room_ord.getPeople());
			pstmt.setTimestamp(8, room_ord.getCheck_in_time());
			pstmt.setTimestamp(9, room_ord.getCheck_out_time());
			pstmt.setInt(10, room_ord.getOrd_status());
			pstmt.setTimestamp(11, room_ord.getOrd_time());
			pstmt.setString(12, room_ord.getRemark());
			pstmt.setInt(13, room_ord.getScore());
			pstmt.setString(14, room_ord.getComments());
			pstmt.setTimestamp(15, room_ord.getComments_time());
			pstmt.setInt(16, room_ord.getRoom_ord_id());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void delete(Integer room_ord_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_ROOM_ORD);
			pstmt.setInt(1, room_ord_id);
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

	}

	@Override
	public Room_ord findByPrimaryKey(Integer room_ord_id) {

		Room_ord room_ord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_ord_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_ord = new Room_ord();
				room_ord.setRoom_ord_id(rs.getInt("room_ord_id"));
				room_ord.setPlan_id(rs.getInt("plan_id"));
				room_ord.setRoom_id(rs.getInt("room_id"));
				room_ord.setCus_id(rs.getInt("cus_id"));
				room_ord.setAmount(rs.getInt("amount"));
				room_ord.setTotal_price(rs.getDouble("total_price"));
				room_ord.setCommission(rs.getDouble("commission"));
				room_ord.setPeople(rs.getInt("people"));
				room_ord.setCheck_in_time(rs.getTimestamp("check_in_time"));
				room_ord.setCheck_out_time(rs.getTimestamp("check_out_time"));
				room_ord.setOrd_status(rs.getInt("ord_status"));
				room_ord.setRemark(rs.getString("remark"));
				room_ord.setScore(rs.getInt("score"));
				room_ord.setComments(rs.getString("comments"));
				room_ord.setComments_time(rs.getTimestamp("comments_time"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return room_ord;

	}

	@Override
	public List<Room_ord> getAll() {
		List<Room_ord> list = new ArrayList<Room_ord>();
		Room_ord room_ord = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_ord = new Room_ord();
				room_ord.setRoom_ord_id(rs.getInt("room_ord_id"));
				room_ord.setPlan_id(rs.getInt("plan_id"));
				room_ord.setRoom_id(rs.getInt("room_id"));
				room_ord.setCus_id(rs.getInt("cus_id"));
				room_ord.setAmount(rs.getInt("amount"));
				room_ord.setTotal_price(rs.getDouble("total_price"));
				room_ord.setCommission(rs.getDouble("commission"));
				room_ord.setPeople(rs.getInt("people"));
				room_ord.setCheck_in_time(rs.getTimestamp("check_in_time"));
				room_ord.setCheck_out_time(rs.getTimestamp("check_out_time"));
				room_ord.setOrd_status(rs.getInt("ord_status"));
				room_ord.setRemark(rs.getString("remark"));
				room_ord.setScore(rs.getInt("score"));
				room_ord.setComments(rs.getString("comments"));
				room_ord.setComments_time(rs.getTimestamp("comments_time"));

				list.add(room_ord);
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
//		Date date= new date();
		

//	// 新增
//		Room_ord room_ord001 = new Room_ord();
//		
//		room_ord001.setPlan_id(3);
//		room_ord001.setRoom_id(1);
//		room_ord001.setCus_id(1);
//		room_ord001.setAmount(1);
//		room_ord001.setTotal_price(1);
//		room_ord001.setCommission(1);
//		room_ord001.setPeople(1);
//		room_ord001.setCheck_in_time(Timestamp.valueOf("2023-09-11 17:55:55"));
//		room_ord001.setCheck_out_time(Timestamp.valueOf("2023-09-12 17:55:55"));
//		room_ord001.setOrd_status(1);
//		room_ord001.setOrd_time(Timestamp.valueOf("2023-09-14 17:55:55"));
//		room_ord001.setRemark("測");
//		room_ord001.setScore(1);
//		room_ord001.setComments("測");
//		room_ord001.setcomments_time(Timestamp.valueOf("2023-09-15 17:55:55"));
//		dao.insert(room_ord001);
//修改
//		Room_ord room_ord002 = new Room_ord();
//		room_ord002.setRoom_ord_id(3);
//		room_ord002.setPlan_id(2);
//		room_ord002.setRoom_id(1);
//		room_ord002.setCus_id(1);
//		room_ord002.setAmount(1);
//		room_ord002.setTotal_price(1);
//		room_ord002.setCommission(5);
//		room_ord002.setPeople(1);
//		room_ord002.setCheck_in_time(Timestamp.valueOf("2023-09-11 17:55:55"));
//		room_ord002.setCheck_out_time(Timestamp.valueOf("2023-09-12 17:55:55"));
//		room_ord002.setOrd_status(1);
//		room_ord002.setOrd_time(Timestamp.valueOf("2023-09-14 17:55:55"));
//		room_ord002.setRemark("測");
//		room_ord002.setScore(1);
//		room_ord002.setComments("測");
//		room_ord002.setComments_time(Timestamp.valueOf("2023-09-15 17:55:55"));
//		dao.update(room_ord002);
		
		//刪除
//		dao.delete(2);
		
		//查詢
		Room_ord room_ord3 =dao.findByPrimaryKey(3);
		System.out.print(room_ord3.getCommission() + ",");
		System.out.print(room_ord3.getPeople() + ",");
		System.out.println("++++++++++++++++");
		
		List<Room_ord> list = dao.getAll();
		for(Room_ord sRoom : list) {
			System.out.print(sRoom.getCommission() + ".");
			System.out.print(sRoom.getPeople());
			System.out.println();
		}
	}

}

	
