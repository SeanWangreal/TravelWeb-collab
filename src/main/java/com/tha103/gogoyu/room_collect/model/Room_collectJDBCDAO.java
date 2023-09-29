package com.tha103.gogoyu.room_collect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import util.Util;
	
public class Room_collectJDBCDAO implements Room_collectDAO_interface{
	static {
		try {
			Class.forName(Util.DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}
	public static final String INSERT_STMT=
			"insert into room_collect (cus_id,room_id,collect_time) values(?,?,?)";
	private static final String GET_ALL_STMT =
			"select * from room_collect order by collect_time;";
	private static final String GET_ONE_STMT =
			"select *  from room_collect where cus_id = ? and room_id = ?";
	private static final String DELETE =
			"delete from room_collect where cus_id = ? and room_id = ?";
	private static final String UPDATE =
			"update room_collect set collect_time = ? where cus_id = ? and room_id = ?";
	
	public void insert(Room_collect room_collect) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER,Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, room_collect.getCusId());
			pstmt.setInt(2, room_collect.getRoomId());
			pstmt.setTimestamp(3, room_collect.getCollectTime());
			pstmt.executeUpdate();
		} catch(SQLException se){
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};
	public void update(Room_collect room_collect) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER,Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setTimestamp(1, room_collect.getCollectTime());
			pstmt.setInt(2, room_collect.getCusId());
			pstmt.setInt(3, room_collect.getRoomId());
			pstmt.executeUpdate();
		} catch(SQLException se){
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};
	public void delete(Integer cus_id,Integer room_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER,Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, cus_id);
			pstmt.setInt(2, room_id);
			pstmt.executeUpdate();
		} catch(SQLException se){
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	};
	public Room_collect findByPrimaryKey(Integer cus_id,Integer room_id) {
		Room_collect room_collect = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER,Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, cus_id);
			pstmt.setInt(2, room_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				room_collect = new Room_collect();
				room_collect.setCusId(rs.getInt("cus_id"));
				room_collect.setRoomId(rs.getInt("room_id"));
				room_collect.setCollectTime(rs.getTimestamp("3"));
			}
		} catch(SQLException se){
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return room_collect;
	};
	public List<Room_collect> getAll(){
		List<Room_collect> list = new ArrayList<Room_collect>();
		Room_collect room_collect = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER,Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				room_collect = new Room_collect();
				room_collect.setCusId(rs.getInt("cus_id"));
				room_collect.setRoomId(rs.getInt("room_id"));
				room_collect.setCollectTime(rs.getTimestamp("collect_time"));
				list.add(room_collect);
			}
		} catch(SQLException se){
			se.getStackTrace();
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	};
	public static void main(String[] args) {
//		Room_collectJDBCDAO dao =new Room_collectJDBCDAO();
//		Date date = new Date();
//		Timestamp time_s = new Timestamp(date.getTime());
//		//新增
//		Room_collect item = new Room_collect(121,201,time_s);
//		dao.insert(item);
		//修改
//		Room_collect item = new Room_collect(121,201,time_s);
//		dao.update(item);
		//刪除
//		dao.delete(121, 201);
		//查詢一個
//		Room_collect select = dao.findByPrimaryKey(121, 201);
//		System.out.println(select.getCusId());
//		System.out.println(select.getRoomId());
//		System.out.println(select.getCollectTime());
		//查詢全部
//		List<Room_collect> all = dao.getAll();
//		for(Room_collect item : all) {
//			System.out.println(item);
//		}
		System.out.println("OK");
	}
}
