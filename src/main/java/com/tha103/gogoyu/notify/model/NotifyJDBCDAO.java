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
	public int add(Notify notify) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"notify_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setInt(1, notify.getCusId());
			pstmt.setInt(2, notify.getCompId());
			pstmt.setInt(3, notify.getRoomOrdId());
			pstmt.setInt(4, notify.getTripOrdId());
			pstmt.setString(5, notify.getContents());
			pstmt.setByte(6, notify.getState());
			pstmt.setTimestamp(7, notify.getNotifyTime());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public int update(Notify notify) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, notify.getCusId());
			pstmt.setInt(2, notify.getCompId());
			pstmt.setInt(3, notify.getRoomOrdId());
			pstmt.setInt(4, notify.getTripOrdId());
			pstmt.setString(5, notify.getContents());
			pstmt.setByte(6, notify.getState());
			pstmt.setTimestamp(7, notify.getNotifyTime());
			pstmt.setInt(8, notify.getNotifyId());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public int delete(Integer notifyId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, notifyId);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Notify findByPK(Integer notifyId) {
		Notify notify = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, notifyId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				notify = new Notify();
				notify.setNotifyId(rs.getInt("notify_id"));
				notify.setCusId(rs.getInt("cus_id"));
				notify.setCompId(rs.getInt("comp_id"));
				notify.setRoomOrdId(rs.getInt("room_ord_id"));
				notify.setTripOrdId(rs.getInt("trip_ord_id"));
				notify.setContents(rs.getString("contents"));
				notify.setState(rs.getByte("state"));
				notify.setNotifyTime(rs.getTimestamp("notify_time"));
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
				notify.setNotifyId(rs.getInt("notify_id"));
				notify.setCusId(rs.getInt("cus_id"));
				notify.setCompId(rs.getInt("comp_id"));
				notify.setRoomOrdId(rs.getInt("room_ord_id"));
				notify.setTripOrdId(rs.getInt("trip_ord_id"));
				notify.setContents(rs.getString("contents"));
				notify.setState(rs.getByte("state"));
				notify.setNotifyTime(rs.getTimestamp("notify_time"));
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
//		notify01.setCusId(3);
//		notify01.setCompId(3);
//		notify01.setRoomOrdId(3);
//		notify01.setTripOrdId(3);
//		notify01.setContents("測試新增3");
//		notify01.setState((byte)0);
//		notify01.setNotifyTime(time_s);
//		dao.insert(notify01);

//		// 修改
//		Notify notify02 = new Notify();
//		notify02.setNotifyId(1);
//		notify02.setCusId(1);
//		notify02.setCompId(1);
//		notify02.setRoomOrdId(1);
//		notify02.setTripOrdId(1);
//		notify02.setContents("測試修改");
//		notify02.setState((byte)1);
//		notify02.setNotifyTime(time_s);
//		dao.update(notify02);

//		// 刪除
//		dao.delete(1);
		
//		// 查詢單筆
//		Notify notify03 = dao.findByPrimaryKey(1);
//		System.out.print(notify03.getNotifyId() + ",");
//		System.out.print(notify03.getCusId() + ",");
//		System.out.print(notify03.getCompId() + ",");
//		System.out.print(notify03.getRoomOrdId() + ",");
//		System.out.print(notify03.getTripOrdId() + ",");
//		System.out.print(notify03.getContents() + ",");
//		System.out.print(notify03.getState() + ",");
//		System.out.println(notify03.getNotifyTime());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Notify> list = dao.getAll();
//		for(Notify aNotify : list) {
//			System.out.print(aNotify.getNotifyId() + ",");
//			System.out.print(aNotify.getCusId() + ",");
//			System.out.print(aNotify.getCompId() + ",");
//			System.out.print(aNotify.getRoomOrdId() + ",");
//			System.out.print(aNotify.getTripOrdId() + ",");
//			System.out.print(aNotify.getContents() + ",");
//			System.out.print(aNotify.getState() + ",");
//			System.out.print(aNotify.getNotifyTime());
//			System.out.println();
//		}
	}
}
