package com.tha103.gogoyu.hotel_info.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class Hotel_infoJDBCDAO implements Hotel_infoDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO hotel_info (restaurant , room_service , allday_counter , spa , gym , garden , terrace , no_smoking ,  freewifi , heater , beach , pool , chargingstation ,  parking) VALUES (?, ?,?, ?, ?, ?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT hotel_info_id , restaurant , room_service , allday_counter , spa , gym , garden , terrace , no_smoking ,  freewifi , heater , beach , pool , chargingstation ,  parking  FROM hotel_info order by hotel_info_id";
	private static final String GET_ONE_STMT = "SELECT hotel_info_id , restaurant , room_service , allday_counter , spa , gym , garden , terrace , no_smoking ,  freewifi , heater , beach , pool , chargingstation ,  parking  FROM hotel_info where hotel_info_id = ?";
	private static final String DELETE = "DELETE FROM hotel_info where hotel_info_id = ?";
	private static final String UPDATE = "UPDATE hotel_info set  hotel_info_id = ?, restaurant = ?, room_service = ? , allday_counter = ? , spa = ? , gym = ? , garden = ? , terrace = ? , no_smoking = ? ,  freewifi = ? , heater = ? , beach = ? , pool = ? , chargingstation = ? ,  parking = ?  where hotel_info_id = ? ";

	@Override
	public void insert(Hotel_info Hotel_info) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "hotel_info_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setByte(1, Hotel_info.getRestaurant());
			pstmt.setByte(2, Hotel_info.getRoomService());
			pstmt.setByte(3, Hotel_info.getAlldayCounter());
			pstmt.setByte(4, Hotel_info.getSpa());
			pstmt.setByte(5, Hotel_info.getGym());
			pstmt.setByte(6, Hotel_info.getGarden());
			pstmt.setByte(7, Hotel_info.getTerrace());
			pstmt.setByte(8, Hotel_info.getNoSmoking());
			pstmt.setByte(9, Hotel_info.getFreewifi());
			pstmt.setByte(10, Hotel_info.getHeater());
			pstmt.setByte(11, Hotel_info.getBeach());
			pstmt.setByte(12, Hotel_info.getPool());
			pstmt.setByte(13, Hotel_info.getChargingstation());
			pstmt.setByte(14, Hotel_info.getParking());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void update(Hotel_info Hotel_info) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, Hotel_info.getHotelInfoId());
			pstmt.setByte(2, Hotel_info.getRestaurant());
			pstmt.setByte(3, Hotel_info.getRoomService());
			pstmt.setByte(4, Hotel_info.getAlldayCounter());
			pstmt.setByte(5, Hotel_info.getSpa());
			pstmt.setByte(6, Hotel_info.getGym());
			pstmt.setByte(7, Hotel_info.getGarden());
			pstmt.setByte(8, Hotel_info.getTerrace());
			pstmt.setByte(9, Hotel_info.getNoSmoking());
			pstmt.setByte(10, Hotel_info.getFreewifi());
			pstmt.setByte(11, Hotel_info.getHeater());
			pstmt.setByte(12, Hotel_info.getBeach());
			pstmt.setByte(13, Hotel_info.getPool());
			pstmt.setByte(14, Hotel_info.getChargingstation());
			pstmt.setByte(15, Hotel_info.getParking());
			pstmt.setInt(16, Hotel_info.getHotelInfoId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void delete(Integer hotel_info_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, hotel_info_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Hotel_info findByPrimaryKey(Integer hotel_info_id) {
		Hotel_info hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, hotel_info_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hotelVO = new Hotel_info();
				hotelVO.setHotelInfoId(rs.getInt("hotel_info_id"));
				hotelVO.setRestaurant(rs.getByte("restaurant"));
				hotelVO.setRoomService(rs.getByte("room_service"));
				hotelVO.setAlldayCounter(rs.getByte("allday_counter"));
				hotelVO.setSpa(rs.getByte("spa"));
				hotelVO.setGym(rs.getByte("gym"));
				hotelVO.setGarden(rs.getByte("garden"));
				hotelVO.setTerrace(rs.getByte("terrace"));
				hotelVO.setNoSmoking(rs.getByte("no_smoking"));
				hotelVO.setFreewifi(rs.getByte("freewifi"));
				hotelVO.setHeater(rs.getByte("heater"));
				hotelVO.setBeach(rs.getByte("beach"));
				hotelVO.setPool(rs.getByte("pool"));
				hotelVO.setChargingstation(rs.getByte("chargingstation"));
				hotelVO.setParking(rs.getByte("parking"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return hotelVO;
	}

	@Override
	public List<Hotel_info> getAll() {
		List<Hotel_info> list = new ArrayList<Hotel_info>();
		Hotel_info hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hotelVO = new Hotel_info();
				hotelVO.setHotelInfoId(rs.getInt("hotel_info_id"));
				hotelVO.setRestaurant(rs.getByte("restaurant"));
				hotelVO.setRoomService(rs.getByte("room_service"));
				hotelVO.setAlldayCounter(rs.getByte("allday_counter"));
				hotelVO.setSpa(rs.getByte("spa"));
				hotelVO.setGym(rs.getByte("gym"));
				hotelVO.setGarden(rs.getByte("garden"));
				hotelVO.setTerrace(rs.getByte("terrace"));
				hotelVO.setNoSmoking(rs.getByte("no_smoking"));
				hotelVO.setFreewifi(rs.getByte("freewifi"));
				hotelVO.setHeater(rs.getByte("heater"));
				hotelVO.setBeach(rs.getByte("beach"));
				hotelVO.setPool(rs.getByte("pool"));
				hotelVO.setChargingstation(rs.getByte("chargingstation"));
				hotelVO.setParking(rs.getByte("parking"));
				list.add(hotelVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		Hotel_infoJDBCDAO dao = new Hotel_infoJDBCDAO();
//		// 新增
//		Hotel_info hotelVO1= new Hotel_info();
//		hotelVO1.setRestaurant((byte)1);
//		hotelVO1.setRoomService((byte)1);
//		hotelVO1.setAlldayCounter((byte)1);
//		hotelVO1.setSpa((byte)1);
//		hotelVO1.setGym((byte)1);
//		hotelVO1.setGarden((byte)1);
//		hotelVO1.setTerrace((byte)1);
//		hotelVO1.setNoSmoking((byte)1);
//		hotelVO1.setFreewifi((byte)1);
//		hotelVO1.setHeater((byte)1);
//		hotelVO1.setBeach((byte)1);
//		hotelVO1.setPool((byte)1);
//		hotelVO1.setChargingstation((byte)1);
//		hotelVO1.setParking((byte)1);
//		dao.insert(hotelVO1);
		// 修改
//		Hotel_info hotelVO2 = new Hotel_info();
//		hotelVO2.setHotelInfoId(1);
//		hotelVO2.setRestaurant((byte)1);
//		hotelVO2.setRoomService((byte)1);
//		hotelVO2.setAlldayCounter((byte)1);
//		hotelVO2.setSpa((byte)1);
//		hotelVO2.setGym((byte)1);
//		hotelVO2.setGarden((byte)1);
//		hotelVO2.setTerrace((byte)1);
//		hotelVO2.setNoSmoking((byte)1);
//		hotelVO2.setFreewifi((byte)1);
//		hotelVO2.setHeater((byte)1);
//		hotelVO2.setBeach((byte)1);
//		hotelVO2.setPool((byte)1);
//		hotelVO2.setChargingstation((byte)1);
//		hotelVO2.setParking((byte)1);
//		dao.update(hotelVO2);

		// 刪除
//		dao.delete(2);

		// 查詢
//		Hotel_info hotelVO3= dao.findByPrimaryKey(1);
//		System.out.print(hotelVO3.getHotelInfoId() + ",");
//		System.out.print(hotelVO3.getRestaurant() + ",");
//		System.out.print(hotelVO3.getRoomService() + ",");
//		System.out.print(hotelVO3.getAlldayCounter() + ",");
//		System.out.print(hotelVO3.getSpa() + ",");
//		System.out.print(hotelVO3.getGym() + ",");
//		System.out.print(hotelVO3.getGarden() + ",");
//		System.out.println(hotelVO3.getTerrace()+",");		
//		System.out.print(hotelVO3.getNoSmoking() + ",");
//		System.out.print(hotelVO3.getFreewifi() + ",");
//		System.out.print(hotelVO3.getHeater() + ",");
//		System.out.print(hotelVO3.getBeach() + ",");
//		System.out.println(hotelVO3.getPool()+",");
//		System.out.print(hotelVO3.getChargingstation() + ",");
//		System.out.println(hotelVO3.getParking()+",");
//		System.out.println("---------------------");

		// 查詢
		List<Hotel_info> list = dao.getAll();
		for (Hotel_info aHotel : list) {
			System.out.print(aHotel.getHotelInfoId() + ",");
			System.out.print(aHotel.getRestaurant() + ",");
			System.out.print(aHotel.getRoomService() + ",");
			System.out.print(aHotel.getAlldayCounter() + ",");
			System.out.print(aHotel.getSpa() + ",");
			System.out.print(aHotel.getGym() + ",");
			System.out.print(aHotel.getGarden() + ",");
			System.out.println(aHotel.getTerrace() + ",");
			System.out.print(aHotel.getNoSmoking() + ",");
			System.out.print(aHotel.getFreewifi() + ",");
			System.out.print(aHotel.getHeater() + ",");
			System.out.print(aHotel.getBeach() + ",");
			System.out.println(aHotel.getPool() + ",");
			System.out.print(aHotel.getChargingstation() + ",");
			System.out.println(aHotel.getParking() + ",");
			System.out.println("---------------------");
		}
	}
}
