package com.tha103.gogoyu.room.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class RoomJDBCDAO implements RoomDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO room (comp_id,room_type,room_name,beds,price,intro,room_status,tissue,shower,bathroom,dryer,tub,freetoiletries,flushseat,slippers,bathrobe,spatub,electric_kettle,main_photo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT room_id,comp_id,room_type,room_name,beds,price,intro,room_status,tissue,shower,bathroom,dryer,tub,freetoiletries,flushseat,slippers,bathrobe,spatub,electric_kettle,main_photo FROM room";
	private static final String GET_ONE_STMT = "SELECT room_id,comp_id,room_type,room_name,beds,price,intro,room_status,tissue,shower,bathroom,dryer,tub,freetoiletries,flushseat,slippers,bathrobe,spatub,electric_kettle,main_photo FROM room where room_id = ?";
	private static final String DELETE_ROOM = "DELETE FROM room where room_id = ?";
	private static final String UPDATE = "UPDATE room set comp_id = ?,room_type = ?,room_name = ?,beds =?,price = ?,intro = ? ,room_status = ?,tissue = ?,shower = ?,bathroom = ?,dryer = ?,tub = ?,freetoiletries = ?,flushseat = ?,slippers = ?,bathrobe = ?,spatub = ?,electric_kettle = ?,main_photo=? where room_id = ?";

	@Override
	public int add(Room room) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "room_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, room.getCompId());
			pstmt.setInt(2, room.getRoomType());
			pstmt.setString(3, room.getRoomName());
			pstmt.setInt(4, room.getBeds());
			pstmt.setBigDecimal(5, room.getPrice());
			pstmt.setString(6, room.getIntro());
			pstmt.setInt(7, room.getRoomStatus());
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
			pstmt.setByte(18, room.getElectricKettle());
			pstmt.setBytes(19, room.getMainPhoto());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				 id = rs.getInt(1);
			}
			return id;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public int update(Room room) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, room.getCompId());
			pstmt.setInt(2, room.getRoomType());
			pstmt.setString(3, room.getRoomName());
			pstmt.setInt(4, room.getBeds());
			pstmt.setBigDecimal(5, room.getPrice());
			pstmt.setString(6, room.getIntro());
			pstmt.setInt(7, room.getRoomStatus());
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
			pstmt.setByte(18, room.getElectricKettle());
			pstmt.setBytes(19, room.getMainPhoto());
			pstmt.setInt(20, room.getRoomId());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int delete(Integer room_id) {
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
		return -1;
	}

	@Override
	public Room findByPK(Integer roomId) {
		Room room = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				room = new Room();
				room.setRoomId(rs.getInt("room_id"));
				room.setCompId(rs.getInt("comp_id"));
				room.setRoomType(rs.getInt("room_type"));
				room.setRoomName(rs.getString("room_name"));
				room.setBeds(rs.getInt("beds"));
				room.setPrice(rs.getBigDecimal("price"));
				room.setIntro(rs.getString("intro"));
				room.setRoomStatus(rs.getInt("room_status"));
				room.setTissue(rs.getByte("tissue"));
				room.setShower(rs.getByte("shower"));
				room.setBathroom(rs.getByte("bathroom"));
				room.setDryer(rs.getByte("dryer"));
				room.setTub(rs.getByte("tub"));
				room.setFreetoiletries(rs.getByte("freetoiletries"));
				room.setFlushseat(rs.getByte("flushseat"));
				room.setSlippers(rs.getByte("slippers"));
				room.setSpatub(rs.getByte("spatub"));
				room.setElectricKettle(rs.getByte("electric_kettle"));
				room.setMainPhoto(rs.getBytes("main_photo"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				room.setRoomId(rs.getInt("room_id"));
				room.setCompId(rs.getInt("comp_id"));
				room.setRoomType(rs.getInt("room_type"));
				room.setRoomName(rs.getString("room_name"));
				room.setBeds(rs.getInt("beds"));
				room.setPrice(rs.getBigDecimal("price"));
				room.setIntro(rs.getString("intro"));
				room.setRoomStatus(rs.getInt("room_status"));
				room.setTissue(rs.getByte("tissue"));
				room.setShower(rs.getByte("shower"));
				room.setBathroom(rs.getByte("bathroom"));
				room.setDryer(rs.getByte("dryer"));
				room.setTub(rs.getByte("tub"));
				room.setFreetoiletries(rs.getByte("freetoiletries"));
				room.setFlushseat(rs.getByte("flushseat"));
				room.setSlippers(rs.getByte("slippers"));
				room.setSpatub(rs.getByte("spatub"));
				room.setElectricKettle(rs.getByte("electric_kettle"));
				room.setMainPhoto(rs.getBytes("main_photo"));
				list.add(room);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		RoomJDBCDAO dao = new RoomJDBCDAO();

		// 新增
		Room ro1= new Room(); 
//		ro1.setCompId(1001);
//		ro1.setRoomType(3);
//		ro1.setRoomName("北邊");
//		ro1.setBeds(3);
//		ro1.setPrice(new BigDecimal(5000));
//		ro1.setIntro("沒什麼");
//		ro1.setRoomStatus(1);
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
//		ro1.setElectricKettle((byte)1);
//		dao.insert(ro1);

		// 修改
//		Room r2 = new Room();
//		r2.setRoomId(10001);
//		r2.setCompId(1001);
//		r2.setRoomType(3);
//		r2.setRoomName("北邊");
//		r2.setBeds(3);
//		r2.setPrice(new BigDecimal(5000));
//		r2.setIntro("沒什麼");
//		r2.setRoomStatus(1);
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
//		r2.setElectricKettle((byte) 1);
//		dao.update(r2);

		// 刪除
//		dao.delete(10004);

		// 查詢
//		Room r4 =dao.findByPK(10000);
//		System.out.print(r4.getRoomId() +",");
//		System.out.print(r4.getCompId()+",");
//		System.out.print(r4.getRoomType()+",");
//		System.out.print(r4.getRoomName()+",");
//		System.out.print(r4.getBeds()+",");
//		System.out.print(r4.getPrice()+",");
//		System.out.print(r4.getIntro()+",");
//		System.out.print(r4.getRoomStatus()+",");
//		System.out.print(r4.getTissue()+",");
//		System.out.print(r4.getShower()+",");
//		System.out.print(r4.getBathroom()+",");
//		System.out.print(r4.getDryer()+",");
//		System.out.print(r4.getTub()+",");
//		System.out.print(r4.getFreetoiletries()+",");
//		System.out.print(r4.getFlushseat()+",");
//		System.out.print(r4.getSlippers()+",");
//		System.out.print(r4.getSpatub()+",");
//		System.out.print(r4.getElectricKettle()+",");
		
		List<Room> list =dao.getAll();
		for(Room sRo :list) {
			
			System.out.print(sRo.getRoomId() +",");
			System.out.print(sRo.getCompId()+",");
			System.out.print(sRo.getRoomType()+",");
			System.out.print(sRo.getRoomName()+",");
			System.out.print(sRo.getBeds()+",");
			System.out.print(sRo.getPrice()+",");
			System.out.print(sRo.getIntro()+",");
			System.out.print(sRo.getRoomStatus()+",");
			System.out.print(sRo.getTissue()+",");
			System.out.print(sRo.getShower()+",");
			System.out.print(sRo.getBathroom()+",");
			System.out.print(sRo.getDryer()+",");
			System.out.print(sRo.getTub()+",");
			System.out.print(sRo.getFreetoiletries()+",");
			System.out.print(sRo.getFlushseat()+",");
			System.out.print(sRo.getSlippers()+",");
			System.out.print(sRo.getSpatub()+",");
			System.out.print(sRo.getElectricKettle()+",");
		}
			
	}

	@Override
	public List<Room> findRoomByCompId(Integer compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getMainPhoto(Integer roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAllPhoto(Integer roomId) {
		// TODO Auto-generated method stub
		return 0;
	}
}
