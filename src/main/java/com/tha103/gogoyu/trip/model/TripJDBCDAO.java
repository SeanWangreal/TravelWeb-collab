package com.tha103.gogoyu.trip.model;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TripJDBCDAO implements TripDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO trip ("
			+ "	comp_id,trip_name,amount,price,people,start_time,end_time,content,state,taipei_city,"
			+ "	newTaipei_city,taoyuan_city,taichung_city,tainan_city,taohsiung_city,tsinchu_county,miaoli_county,"
			+ "	changhua_county,nantou_county,yunlin_county,chiayi_county,pingtung_county,yilan_city,"
			+ "	hualien_city,taitung_county,kinmen_county,lienchiang_county,keelung_city,hsinchu_city,"
			+ "	chiayi_city,penghu_county) VALUES (?, ?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?, ?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT *  FROM trip order by trip_id";
	private static final String GET_ONE_STMT = "SELECT *  FROM trip where trip_id = ?";
	private static final String DELETE = "DELETE FROM trip where trip_id= ?";
	private static final String UPDATE = "UPDATE trip set  \r\n"
			+ "	trip_id = ? ,comp_id =  ? , trip_name = ? ,amount = ? ,price = ?,people = ?,start_time = ?,end_time = ? , content = ?, state = ? ,taipei_city = ?,"
			+ "	newTaipei_city = ? ,taoyuan_city = ?,taichung_city = ? ,tainan_city = ? ,kaohsiung_city = ? ,hsinchu_county = ? ,miaoli_county = ?,"
			+ "	changhua_county = ? ,nantou_county = ?,yunlin_county = ? ,chiayi_county = ? ,pingtung_county = ?,yilan_city = ? ,"
			+ "	hualien_city = ?,taitung_county = ?,kinmen_county = ?,lienchiang_county = ?,keelung_city = ?,hsinchu_city = ?,"
			+ "	chiayi_city = ? ,penghu_county = ?  where trip_id = ? ";

	@Override
	public void insert(Trip Trip) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "trip_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, Trip.getComp_id());
			pstmt.setString(2, Trip.getTrip_name());
			pstmt.setInt(3, Trip.getAmount());
			pstmt.setInt(4, Trip.getPrice());
			pstmt.setInt(5, Trip.getPeople());
			pstmt.setTimestamp(6, Trip.getStart_time());
			pstmt.setTimestamp(7, Trip.getEnd_time());
			pstmt.setString(8, Trip.getContent());
			pstmt.setInt(9, Trip.getState());
			pstmt.setByte(10, Trip.getTaipei_city());
			pstmt.setByte(11, Trip.getNewTaipei_city());
			pstmt.setByte(12, Trip.getTaoyuan_city());
			pstmt.setByte(13, Trip.getTaichung_city());
			pstmt.setByte(14, Trip.getTainan_city());
			pstmt.setByte(15, Trip.getKaohsiung_city());
			pstmt.setByte(16, Trip.getHsinchu_county());
			pstmt.setByte(17, Trip.getMiaoli_county());
			pstmt.setByte(18, Trip.getChanghua_county());
			pstmt.setByte(19, Trip.getNantou_county());
			pstmt.setByte(20, Trip.getYunlin_county());
			pstmt.setByte(21, Trip.getChiayi_county());
			pstmt.setByte(22, Trip.getPingtung_county());
			pstmt.setByte(23, Trip.getYilan_city());
			pstmt.setByte(24, Trip.getHualien_city());
			pstmt.setByte(25, Trip.getTaitung_county());
			pstmt.setByte(26, Trip.getKinmen_county());
			pstmt.setByte(27, Trip.getLienchiang_county());
			pstmt.setByte(28, Trip.getKeelung_city());
			pstmt.setByte(29, Trip.getHsinchu_city());
			pstmt.setByte(30, Trip.getChiayi_city());
			pstmt.setByte(31, Trip.getPenghu_county());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Trip Trip) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, Trip.getTrip_id());
			pstmt.setInt(2, Trip.getComp_id());
			pstmt.setString(3, Trip.getTrip_name());
			pstmt.setInt(4, Trip.getAmount());
			pstmt.setInt(5, Trip.getPrice());
			pstmt.setInt(6, Trip.getPeople());
			pstmt.setTimestamp(7, Trip.getStart_time());
			pstmt.setTimestamp(8, Trip.getEnd_time());
			pstmt.setString(9, Trip.getContent());
			pstmt.setInt(10, Trip.getState());
			pstmt.setByte(11, Trip.getTaipei_city());
			pstmt.setByte(12, Trip.getNewTaipei_city());
			pstmt.setByte(13, Trip.getTaoyuan_city());
			pstmt.setByte(14, Trip.getTaichung_city());
			pstmt.setByte(15, Trip.getTainan_city());
			pstmt.setByte(16, Trip.getKaohsiung_city());
			pstmt.setByte(17, Trip.getHsinchu_county());
			pstmt.setByte(18, Trip.getMiaoli_county());
			pstmt.setByte(19, Trip.getChanghua_county());
			pstmt.setByte(20, Trip.getNantou_county());
			pstmt.setByte(21, Trip.getYunlin_county());
			pstmt.setByte(22, Trip.getChiayi_county());
			pstmt.setByte(23, Trip.getPingtung_county());
			pstmt.setByte(24, Trip.getYilan_city());
			pstmt.setByte(25, Trip.getHualien_city());
			pstmt.setByte(26, Trip.getTaitung_county());
			pstmt.setByte(27, Trip.getKinmen_county());
			pstmt.setByte(28, Trip.getLienchiang_county());
			pstmt.setByte(29, Trip.getKeelung_city());
			pstmt.setByte(30, Trip.getHsinchu_city());
			pstmt.setByte(31, Trip.getChiayi_city());
			pstmt.setByte(32, Trip.getPenghu_county());
			pstmt.setInt(33, Trip.getTrip_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer trip_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, trip_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Trip findByPrimaryKey(Integer trip_id) {
		Trip trVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, trip_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trVO = new Trip();
				trVO.setTrip_id(rs.getInt("trip_id"));
				trVO.setComp_id(rs.getInt("comp_id"));
				trVO.setTrip_name(rs.getString("trip_name"));
				trVO.setAmount(rs.getInt("amount"));
				trVO.setPrice(rs.getInt("price"));
				trVO.setPeople(rs.getInt("people"));
				trVO.setStart_time(rs.getTimestamp("start_time"));
				trVO.setEnd_time(rs.getTimestamp("end_time"));
				trVO.setContent(rs.getString("content"));
				trVO.setState(rs.getInt("state"));
				trVO.setTaipei_city(rs.getByte("Taipei_city"));
				trVO.setNewTaipei_city(rs.getByte("NewTaipei_city"));
				trVO.setTaoyuan_city(rs.getByte("Taoyuan_city"));
				trVO.setTaichung_city(rs.getByte("Taichung_city"));
				trVO.setTainan_city(rs.getByte("Tainan_city"));
				trVO.setKaohsiung_city(rs.getByte("Kaohsiung_city"));
				trVO.setHsinchu_county(rs.getByte("Hsinchu_county"));
				trVO.setMiaoli_county(rs.getByte("Miaoli_county"));
				trVO.setChanghua_county(rs.getByte("changhua_county"));
				trVO.setNantou_county(rs.getByte("Nantou_county"));
				trVO.setYunlin_county(rs.getByte("Yunlin_county"));
				trVO.setChiayi_county(rs.getByte("chiayi_county"));
				trVO.setPingtung_county(rs.getByte("Pingtung_county"));
				trVO.setYilan_city(rs.getByte("Yilan_city"));
				trVO.setHualien_city(rs.getByte("Hualien_city"));
				trVO.setTaitung_county(rs.getByte("Taitung_county"));
				trVO.setKinmen_county(rs.getByte("Kinmen_county"));
				trVO.setLienchiang_county(rs.getByte("Lienchiang_county"));
				trVO.setKeelung_city(rs.getByte("Keelung_city"));
				trVO.setHsinchu_city(rs.getByte("Hsinchu_city"));
				trVO.setChiayi_city(rs.getByte("chiayi_city"));
				trVO.setPenghu_county(rs.getByte("Penghu_county"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return trVO;
	}

	@Override
	public List<Trip> getAll() {
		List<Trip> list = new ArrayList<Trip>();
		Trip trVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trVO = new Trip();
				trVO.setTrip_id(rs.getInt("trip_id"));
				trVO.setComp_id(rs.getInt("comp_id"));
				trVO.setTrip_name(rs.getString("trip_name"));
				trVO.setAmount(rs.getInt("amount"));
				trVO.setPrice(rs.getInt("price"));
				trVO.setPeople(rs.getInt("people"));
				trVO.setStart_time(rs.getTimestamp("start_time"));
				trVO.setEnd_time(rs.getTimestamp("end_time"));
				trVO.setContent(rs.getString("content"));
				trVO.setState(rs.getInt("state"));
				trVO.setTaipei_city(rs.getByte("taipei_city"));
				trVO.setNewTaipei_city(rs.getByte("newtaipei_city"));
				trVO.setTaoyuan_city(rs.getByte("taoyuan_city"));
				trVO.setTaichung_city(rs.getByte("taichung_city"));
				trVO.setTainan_city(rs.getByte("tainan_city"));
				trVO.setKaohsiung_city(rs.getByte("kaohsiung_city"));
				trVO.setHsinchu_county(rs.getByte("hsinchu_county"));
				trVO.setMiaoli_county(rs.getByte("miaoli_county"));
				trVO.setChanghua_county(rs.getByte("changhua_county"));
				trVO.setNantou_county(rs.getByte("nantou_county"));
				trVO.setYunlin_county(rs.getByte("yunlin_county"));
				trVO.setChiayi_county(rs.getByte("chiayi_county"));
				trVO.setPingtung_county(rs.getByte("pingtung_county"));
				trVO.setYilan_city(rs.getByte("yilan_city"));
				trVO.setHualien_city(rs.getByte("hualien_city"));
				trVO.setTaitung_county(rs.getByte("taitung_county"));
				trVO.setKinmen_county(rs.getByte("kinmen_county"));
				trVO.setLienchiang_county(rs.getByte("lienchiang_county"));
				trVO.setKeelung_city(rs.getByte("keelung_city"));
				trVO.setHsinchu_city(rs.getByte("hsinchu_city"));
				trVO.setChiayi_city(rs.getByte("chiayi_city"));
				trVO.setPenghu_county(rs.getByte("penghu_county"));
				list.add(trVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		TripJDBCDAO dao = new TripJDBCDAO();
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());
		// 新增
		Trip tripVO1 = new Trip();
		tripVO1.setComp_id(1);
		tripVO1.setTrip_name("1");
		tripVO1.setAmount(1);
		tripVO1.setPrice(1);
		tripVO1.setPeople(1);
		tripVO1.setStart_time(time_s);
		tripVO1.setEnd_time(time_s);
		tripVO1.setContent("1");
		tripVO1.setState(1);
		tripVO1.setTaipei_city((byte) 1);
		tripVO1.setNewTaipei_city((byte) 1);
		tripVO1.setTaoyuan_city((byte) 1);
		tripVO1.setTaichung_city((byte) 1);
		tripVO1.setTainan_city((byte) 1);
		tripVO1.setKaohsiung_city((byte) 1);
		tripVO1.setHsinchu_county((byte) 1);
		tripVO1.setMiaoli_county((byte) 1);
		tripVO1.setChanghua_county((byte) 1);
		tripVO1.setNantou_county((byte) 1);
		tripVO1.setYunlin_county((byte) 1);
		tripVO1.setChiayi_county((byte) 1);
		tripVO1.setPingtung_county((byte) 1);
		tripVO1.setYilan_city((byte) 1);
		tripVO1.setHualien_city((byte) 1);
		tripVO1.setTaitung_county((byte) 1);
		tripVO1.setKinmen_county((byte) 1);
		tripVO1.setLienchiang_county((byte) 1);
		tripVO1.setKeelung_city((byte) 1);
		tripVO1.setHsinchu_city((byte) 1);
		tripVO1.setChiayi_city((byte) 1);
		tripVO1.setPenghu_county((byte) 1);
		dao.insert(tripVO1);
		// 修改
//		Trip tripVO2 = new tripVO();
//		tripVO2.setTrip_id(1);
//		tripVO2.setcomp_id(2);
//		tripVO2.setTrip_name("1");
//		tripVO2.setAmount(1);
//		tripVO2.setPrice(1);
//		tripVO2.setPeople(1);
//		tripVO2.setStart_time(java.sql.Timestamp.valueOf("2005-01-01 10:10:49"));
//		tripVO2.setEnd_time(java.sql.Timestamp.valueOf("2005-01-01 10:10:49"));
//		tripVO2.setcontent("1");
//		tripVO2.setState(1);
//		tripVO2.setTaipei_city((byte) 1);
//		tripVO2.setNewTaipei_city((byte) 1);
//		tripVO2.setTaoyuan_city((byte) 1);
//		tripVO2.setTaichung_city((byte) 1);
//		tripVO2.setTainan_city((byte) 1);
//		tripVO2.setKaohsiung_city((byte) 1);
//		tripVO2.setHsinchu_county((byte) 1);
//		tripVO2.setMiaoli_county((byte) 1);
//		tripVO2.setchanghua_county((byte) 1);
//		tripVO2.setNantou_county((byte) 1);
//		tripVO2.setYunlin_county((byte) 1);
//		tripVO2.setchiayi_county((byte) 1);
//		tripVO2.setPingtung_county((byte) 1);
//		tripVO2.setYilan_city((byte) 1);
//		tripVO2.setHualien_city((byte) 1);
//		tripVO2.setTaitung_county((byte) 1);
//		tripVO2.setKinmen_county((byte) 1);
//		tripVO2.setLienchiang_county((byte) 1);
//		tripVO2.setKeelung_city((byte) 1);
//		tripVO2.setHsinchu_city((byte) 1);
//		tripVO2.setchiayi_city((byte) 1);
//		tripVO2.setPenghu_county((byte) 1);
//
//		dao.update(tripVO2);

		// 刪除
//		dao.delete(1);

		// 查詢
//		Trip tripVO3 = dao.findByPrimaryKey(1);
//		System.out.print(tripVO3.getTrip_id() + ",");
//		System.out.print(tripVO3.getComp_id() + ",");
//		System.out.print(tripVO3.getTrip_name() + ",");
//		System.out.print(tripVO3.getAmount() + ",");
//		System.out.print(tripVO3.getPrice() + ",");
//		System.out.print(tripVO3.getPeople() + ",");
//		System.out.println(tripVO3.getStart_time());
//		System.out.print(tripVO3.getEnd_time() + ",");
//		System.out.print(tripVO3.getContent() + ",");
//		System.out.print(tripVO3.getState() + ",");
//		System.out.print(tripVO3.getTaipei_city() + ",");
//		System.out.print(tripVO3.getTaipei_city() + ",");
//		System.out.print(tripVO3.getTaoyuan_city() + ",");
//		System.out.println(tripVO3.getTaichung_city());
//		System.out.print(tripVO3.getTainan_city() + ",");
//		System.out.print(tripVO3.getKaohsiung_city() + ",");
//		System.out.print(tripVO3.getHsinchu_county() + ",");
//		System.out.print(tripVO3.getMiaoli_county() + ",");
//		System.out.print(tripVO3.getChanghua_county() + ",");
//		System.out.print(tripVO3.getNantou_county() + ",");
//		System.out.println(tripVO3.getYunlin_county());
//		System.out.println(tripVO3.getChiayi_county());
//		System.out.print(tripVO3.getPingtung_county() + ",");
//		System.out.print(tripVO3.getYilan_city() + ",");
//		System.out.print(tripVO3.getHualien_city() + ",");
//		System.out.print(tripVO3.getTaitung_county() + ",");
//		System.out.print(tripVO3.getKinmen_county() + ",");
//		System.out.print(tripVO3.getLienchiang_county() + ",");
//		System.out.println(tripVO3.getKeelung_city()+",");
//		System.out.print(tripVO3.getHsinchu_city() + ",");
//		System.out.print(tripVO3.getChiayi_city() + ",");
//		System.out.println(tripVO3.getPenghu_county());
//		
//		System.out.println("---------------------");

		// 查詢
//		List<tripVO> list = dao.getAll();
//		for (tripVO aTrip : list) {
//			System.out.print(aTrip.getTrip_id() + ",");
//			System.out.print(aTrip.getComp_id() + ",");
//			System.out.print(aTrip.getTrip_name() + ",");
//			System.out.print(aTrip.getAmount() + ",");
//			System.out.print(aTrip.getPrice() + ",");
//			System.out.print(aTrip.getPeople() + ",");
//			System.out.println(aTrip.getStart_time());
//			System.out.print(aTrip.getEnd_time() + ",");
//			System.out.print(aTrip.getContent() + ",");
//			System.out.print(aTrip.getState() + ",");
//			System.out.print(aTrip.getTaipei_city() + ",");
//			System.out.print(aTrip.getTaipei_city() + ",");
//			System.out.print(aTrip.getTaoyuan_city() + ",");
//			System.out.println(aTrip.getTaichung_city());
//			System.out.print(aTrip.getTainan_city() + ",");
//			System.out.print(aTrip.getKaohsiung_city() + ",");
//			System.out.print(aTrip.getHsinchu_county() + ",");
//			System.out.print(aTrip.getMiaoli_county() + ",");
//			System.out.print(aTrip.getChanghua_county() + ",");
//			System.out.print(aTrip.getNantou_county() + ",");
//			System.out.println(aTrip.getYunlin_county());
//			System.out.println(aTrip.getChiayi_county());
//			System.out.print(aTrip.getPingtung_county() + ",");
//			System.out.print(aTrip.getYilan_city() + ",");
//			System.out.print(aTrip.getHualien_city() + ",");
//			System.out.print(aTrip.getTaitung_county() + ",");
//			System.out.print(aTrip.getKinmen_county() + ",");
//			System.out.print(aTrip.getLienchiang_county() + ",");
//			System.out.println(aTrip.getKeelung_city() + ",");
//			System.out.print(aTrip.getHsinchu_city() + ",");
//			System.out.print(aTrip.getChiayi_city() + ",");
//			System.out.println(aTrip.getPenghu_county());
//		}
	}
}
