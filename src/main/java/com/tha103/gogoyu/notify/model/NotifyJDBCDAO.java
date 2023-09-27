package com.tha103.gogoyu.notify.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import util.Util;


public class NotifyJDBCDAO implements NotifyDAO_interface {

	private static final String INSERT_STMT = 
		"INSERT INTO notify(cus_id, comp_id, room_ord_id, trip_ord_id, contents, state, notify_time) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"UPDATE notify SET cus_id = ?, comp_id = ?, room_ord_id = ?, trip_ord_id = ?, contents = ?, "
		+ "state = ?, notify_time = ? WHERE notify_id = ? ";
	private static final String DELETE = 
		"DELETE FROM notify WHERE notify_id = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM notify WHERE notify_id = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM notify order by notify_id";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(Notify notify) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"notify_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setInt(1, notify.getCus_id());
			pstmt.setInt(2, notify.getComp_id());
			pstmt.setInt(3, notify.getRoom_ord_id());
			pstmt.setInt(4, notify.getTrip_ord_id());
			pstmt.setString(5, notify.getContents());
			pstmt.setBoolean(6, notify.getState());
			pstmt.setTimestamp(7, notify.getNotify_time());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Notify notify) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, notify.getCus_id());
			pstmt.setInt(2, notify.getComp_id());
			pstmt.setInt(3, notify.getRoom_ord_id());
			pstmt.setInt(4, notify.getTrip_ord_id());
			pstmt.setString(5, notify.getContents());
			pstmt.setBoolean(6, notify.getState());
			pstmt.setTimestamp(7, notify.getNotify_time());
			pstmt.setInt(8, notify.getNotify_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer notify_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, notify_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Notify findByPrimaryKey(Integer notify_id) {
		Notify notify = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, notify_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				notify = new Notify();
				notify.setNotify_id(rs.getInt("notify_id"));
				notify.setCus_id(rs.getInt("cus_id"));
				notify.setComp_id(rs.getInt("comp_id"));
				notify.setRoom_ord_id(rs.getInt("room_ord_id"));
				notify.setTrip_ord_id(rs.getInt("trip_ord_id"));
				notify.setContents(rs.getString("contents"));
				notify.setState(rs.getBoolean("state"));
				notify.setNotify_time(rs.getTimestamp("notify_time"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, rs);
		} 
		return notify;
	}

	@Override
	public List<Notify> getAll() {
		List<Notify> list = new ArrayList<Notify>();
		Notify notify = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				notify = new Notify();
				notify.setNotify_id(rs.getInt("notify_id"));
				notify.setCus_id(rs.getInt("cus_id"));
				notify.setComp_id(rs.getInt("comp_id"));
				notify.setRoom_ord_id(rs.getInt("room_ord_id"));
				notify.setTrip_ord_id(rs.getInt("trip_ord_id"));
				notify.setContents(rs.getString("contents"));
				notify.setState(rs.getBoolean("state"));
				notify.setNotify_time(rs.getTimestamp("notify_time"));
				list.add(notify); 
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, rs);
		} 
		return list;
	}

	public static void main(String[] args) {
		NotifyJDBCDAO dao = new NotifyJDBCDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
//		// 新增
//		Notify notify01 = new Notify();
//		notify01.setCus_id(3);
//		notify01.setComp_id(3);
//		notify01.setRoom_ord_id(3);
//		notify01.setTrip_ord_id(3);
//		notify01.setContents("測試新增3");
//		notify01.setState(false);
//		notify01.setNotify_time(time_s);
//		dao.insert(notify01);

//		// 修改
//		Notify notify02 = new Notify();
//		notify02.setNotify_id(1);
//		notify02.setCus_id(1);
//		notify02.setComp_id(1);
//		notify02.setRoom_ord_id(1);
//		notify02.setTrip_ord_id(1);
//		notify02.setContents("測試修改");
//		notify02.setState(false);
//		notify02.setNotify_time(time_s);
//		dao.update(notify02);

//		// 刪除
//		dao.delete(1);
		
//		// 查詢單筆
//		Notify notify03 = dao.findByPrimaryKey(1);
//		System.out.print(notify03.getNotify_id() + ",");
//		System.out.print(notify03.getCus_id() + ",");
//		System.out.print(notify03.getComp_id() + ",");
//		System.out.print(notify03.getRoom_ord_id() + ",");
//		System.out.print(notify03.getTrip_ord_id() + ",");
//		System.out.print(notify03.getContents() + ",");
//		System.out.print(notify03.getState() + ",");
//		System.out.println(notify03.getNotify_time());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Notify> list = dao.getAll();
//		for(Notify aNotify : list) {
//			System.out.print(aNotify.getNotify_id() + ",");
//			System.out.print(aNotify.getCus_id() + ",");
//			System.out.print(aNotify.getComp_id() + ",");
//			System.out.print(aNotify.getRoom_ord_id() + ",");
//			System.out.print(aNotify.getTrip_ord_id() + ",");
//			System.out.print(aNotify.getContents() + ",");
//			System.out.print(aNotify.getState() + ",");
//			System.out.print(aNotify.getNotify_time());
//			System.out.println();
//		}
	}
}
