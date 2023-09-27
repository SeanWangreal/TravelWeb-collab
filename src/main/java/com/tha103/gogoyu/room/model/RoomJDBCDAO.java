package com.tha103.gogoyu.room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class RoomJDBCDAO implements RoomDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO room (comp_id,room_type,room_name,beds,price,intro,room_status,tissue,shower,bathroom,dryer,tub,freetoiletries,flushseat,slippers,bathrobe,spatub,electric_kettle) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT room_id,comp_id,room_type,room_name,beds,price,intro,room_status,tissue,shower,bathroom,dryer,tub,freetoiletries,flushseat,slippers,bathrobe,spatub,electric_kettle FROM room";
	private static final String GET_ONE_STMT = "SELECT room_id,comp_id,room_type,room_name,beds,price,intro,room_status,tissue,shower,bathroom,dryer,tub,freetoiletries,flushseat,slippers,bathrobe,spatub,electric_kettle FROM room where room_id = ?";
	private static final String DELETE_ROOM = "DELETE FROM room where room_id = ?";
	private static final String UPDATE = "UPDATE room set comp_id = ?,room_type = ?,room_name = ?,beds =?,price = ?,intro = ? ,room_status = ?,tissue = ?,shower = ?,bathroom = ?,dryer = ?,tub = ?,freetoiletries = ?,flushseat = ?,slippers = ?,bathrobe = ?,spatub = ?,electric_kettle = ? where room_id = ?";

	@Override
	public void insert(Room room) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "room_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, room.getComp_id());
			pstmt.setInt(2, room.getRoom_type());
			pstmt.setString(3, room.getRoom_name());
			pstmt.setInt(4, room.getBeds());
			pstmt.setDouble(5, room.getPrice());
			pstmt.setString(6, room.getIntro());
			pstmt.setInt(7, room.getRoom_status());
			pstmt.setByte(8, room.getTissue());
			pstmt.setByte(9, room.getShower());
			pstmt.setByte(10, room.getBathroom());
			pstmt.setByte(11, room.getDryer());
			pstmt.setByte(12, room.getTub());
			pstmt.setByte(13, room.getFreetoiletries());
			pstmt.setByte(14, room.getFlushseat());
			pstmt.setByte(15, room.getSlippers());
			pstmt.setByte(16, room.getBathrobe());
			pstmt.setByte(17, room.getSpatub());
			pstmt.setByte(18, room.getElectric_kettle());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void update(Room room) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, room.getComp_id());
			pstmt.setInt(2, room.getRoom_type());
			pstmt.setString(3, room.getRoom_name());
			pstmt.setInt(4, room.getBeds());
			pstmt.setDouble(5, room.getPrice());
			pstmt.setString(6, room.getIntro());
			pstmt.setInt(7, room.getRoom_status());
			pstmt.setByte(8, room.getTissue());
			pstmt.setByte(9, room.getShower());
			pstmt.setByte(10, room.getBathroom());
			pstmt.setByte(11, room.getDryer());
			pstmt.setByte(12, room.getTub());
			pstmt.setByte(13, room.getFreetoiletries());
			pstmt.setByte(14, room.getFlushseat());
			pstmt.setByte(15, room.getSlippers());
			pstmt.setByte(16, room.getBathrobe());
			pstmt.setByte(17, room.getSpatub());
			pstmt.setByte(18, room.getElectric_kettle());
			pstmt.setInt(19, room.getRoom_id());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void delete(Integer room_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_ROOM);
			pstmt.setInt(1, room_id);
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
	public Room findByPrimaryKey(Integer room_id) {
		Room room = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				room = new Room();
				room.setRoom_id(rs.getInt("room_id"));
				room.setComp_id(rs.getInt("comp_id"));
				room.setRoom_type(rs.getInt("room_type"));
				room.setRoom_name(rs.getString("room_name"));
				room.setBeds(rs.getInt("beds"));
				room.setPrice(rs.getDouble("price"));
				room.setIntro(rs.getString("intro"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setTissue(rs.getByte("tissue"));
				room.setShower(rs.getByte("shower"));
				room.setBathroom(rs.getByte("bathroom"));
				room.setDryer(rs.getByte("dryer"));
				room.setTub(rs.getByte("tub"));
				room.setFreetoiletries(rs.getByte("freetoiletries"));
				room.setFlushseat(rs.getByte("flushseat"));
				room.setSlippers(rs.getByte("slippers"));
				room.setSpatub(rs.getByte("spatub"));
				room.setElectric_kettle(rs.getByte("electric_kettle"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return room;
	}

	@Override
	public List<Room> getAll() {
		List<Room> list = new ArrayList<Room>();
		Room room = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room = new Room();
				room.setRoom_id(rs.getInt("room_id"));
				room.setComp_id(rs.getInt("comp_id"));
				room.setRoom_type(rs.getInt("room_type"));
				room.setRoom_name(rs.getString("room_name"));
				room.setBeds(rs.getInt("beds"));
				room.setPrice(rs.getDouble("price"));
				room.setIntro(rs.getString("intro"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setTissue(rs.getByte("tissue"));
				room.setShower(rs.getByte("shower"));
				room.setBathroom(rs.getByte("bathroom"));
				room.setDryer(rs.getByte("dryer"));
				room.setTub(rs.getByte("tub"));
				room.setFreetoiletries(rs.getByte("freetoiletries"));
				room.setFlushseat(rs.getByte("flushseat"));
				room.setSlippers(rs.getByte("slippers"));
				room.setSpatub(rs.getByte("spatub"));
				room.setElectric_kettle(rs.getByte("electric_kettle"));

				list.add(room);
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
		RoomJDBCDAO dao = new RoomJDBCDAO();

		// 新增
//		Room ro1= new Room(); 
//		ro1.setComp_id(1001);
//		ro1.setRoom_type(3);
//		ro1.setRoom_name("北邊");
//		ro1.setBeds(3);
//		ro1.setPrice(10000.0);
//		ro1.setIntro("沒什麼");
//		ro1.setRoom_status(1);
//		ro1.setTissue((byte)0);
//		ro1.setShower((byte)1);
//		ro1.setBathroom((byte)1);
//		ro1.setDryer((byte)1);
//		ro1.setTub((byte)1);
//		ro1.setFreetoiletries((byte)1);
//		ro1.setFlushseat((byte)1);
//		ro1.setSlippers((byte)1);
//		ro1.setBathrobe((byte)1);
//		ro1.setSpatub((byte)1);
//		ro1.setElectric_kettle((byte)1);
//		dao.insert(ro1);

		// 修改
//		Room r2 = new Room();
//		r2.setRoom_id(10001);
//		r2.setComp_id(1001);
//		r2.setRoom_type(3);
//		r2.setRoom_name("北邊");
//		r2.setBeds(3);
//		r2.setPrice(10000.0);
//		r2.setIntro("沒什麼");
//		r2.setRoom_status(1);
//		r2.setTissue((byte) 0);
//		r2.setShower((byte) 1);
//		r2.setBathroom((byte) 1);
//		r2.setDryer((byte) 1);
//		r2.setTub((byte) 1);
//		r2.setFreetoiletries((byte) 1);
//		r2.setFlushseat((byte) 1);
//		r2.setSlippers((byte) 1);
//		r2.setBathrobe((byte) 1);
//		r2.setSpatub((byte) 1);
//		r2.setElectric_kettle((byte) 1);
//		dao.update(r2);

		// 刪除
//		dao.delete(10004);

		// 查詢
		Room r4 =dao.findByPrimaryKey(10000);
		System.out.print(r4.getRoom_id() +",");
		System.out.print(r4.getComp_id()+",");
		System.out.print(r4.getRoom_type()+",");
		System.out.print(r4.getRoom_name()+",");
		System.out.print(r4.getBeds()+",");
		System.out.print(r4.getPrice()+",");
		System.out.print(r4.getIntro()+",");
		System.out.print(r4.getRoom_status()+",");
		System.out.print(r4.getTissue()+",");
		System.out.print(r4.getShower()+",");
		System.out.print(r4.getBathroom()+",");
		System.out.print(r4.getDryer()+",");
		System.out.print(r4.getTub()+",");
		System.out.print(r4.getFreetoiletries()+",");
		System.out.print(r4.getFlushseat()+",");
		System.out.print(r4.getSlippers()+",");
		System.out.print(r4.getSpatub()+",");
		System.out.print(r4.getElectric_kettle()+",");
		
		List<Room> list =dao.getAll();
		for(Room sRo :list) {
			
			System.out.print(sRo.getRoom_id() +",");
			System.out.print(sRo.getComp_id()+",");
			System.out.print(sRo.getRoom_type()+",");
			System.out.print(sRo.getRoom_name()+",");
			System.out.print(sRo.getBeds()+",");
			System.out.print(sRo.getPrice()+",");
			System.out.print(sRo.getIntro()+",");
			System.out.print(r4.getRoom_status()+",");
			System.out.print(sRo.getTissue()+",");
			System.out.print(sRo.getShower()+",");
			System.out.print(sRo.getBathroom()+",");
			System.out.print(sRo.getDryer()+",");
			System.out.print(sRo.getTub()+",");
			System.out.print(sRo.getFreetoiletries()+",");
			System.out.print(sRo.getFlushseat()+",");
			System.out.print(sRo.getSlippers()+",");
			System.out.print(sRo.getSpatub()+",");
			System.out.print(sRo.getElectric_kettle()+",");
		}
			
	}
}
