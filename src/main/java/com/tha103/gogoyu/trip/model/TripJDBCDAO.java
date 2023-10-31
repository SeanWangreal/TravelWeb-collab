package com.tha103.gogoyu.trip.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.scene.model.Scene;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;

import util.Util;

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
			+ "	newTaipei_city,taoyuan_city,taichung_city,tainan_city,kaohsiung_city,hsinchu_county,miaoli_county,"
			+ "	changhua_county,nantou_county,yunlin_county,chiayi_county,pingtung_county,yilan_city,"
			+ "	hualien_city,taitung_county,kinmen_county,lienchiang_county,keelung_city,hsinchu_city,"
			+ "	chiayi_city,penghu_county,main_photo) VALUES (?, ?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?, ?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT *  FROM trip order by trip_id";
	private static final String GET_ONE_STMT = "SELECT *  FROM trip where trip_id = ?";
	private static final String DELETE = "DELETE FROM trip where trip_id= ?";
	private static final String UPDATE = "UPDATE trip set  \r\n"
			+ "	trip_id = ? ,comp_id =  ? , trip_name = ? ,amount = ? ,price = ?,people = ?,start_time = ?,end_time = ? , content = ?, state = ? ,taipei_city = ?,"
			+ "	newTaipei_city = ? ,taoyuan_city = ?,taichung_city = ? ,tainan_city = ? ,kaohsiung_city = ? ,hsinchu_county = ? ,miaoli_county = ?,"
			+ "	changhua_county = ? ,nantou_county = ?,yunlin_county = ? ,chiayi_county = ? ,pingtung_county = ?,yilan_city = ? ,"
			+ "	hualien_city = ?,taitung_county = ?,kinmen_county = ?,lienchiang_county = ?,keelung_city = ?,hsinchu_city = ?,"
			+ "	chiayi_city = ? ,penghu_county = ? ,main_photo = ? where trip_id = ? ";

	@Override
	public int add(Trip trip,LinkedList<byte[]> allPhoto,List<Itinerary> itineraryList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "trip_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, trip.getCompId());
			pstmt.setString(2, trip.getTripName());
			pstmt.setInt(3, trip.getAmount());
			pstmt.setBigDecimal(4, trip.getPrice());
			pstmt.setInt(5, trip.getPeople());
			pstmt.setDate(6, trip.getStartTime());
			pstmt.setDate(7, trip.getEndTime());
			pstmt.setString(8, trip.getContent());
			pstmt.setInt(9, trip.getState());
			pstmt.setByte(10, trip.getTaipeiCity());
			pstmt.setByte(11, trip.getNewtaipeiCity());
			pstmt.setByte(12, trip.getTaoyuanCity());
			pstmt.setByte(13, trip.getTaichungCity());
			pstmt.setByte(14, trip.getTainanCity());
			pstmt.setByte(15, trip.getKaohsiungCity());
			pstmt.setByte(16, trip.getHsinchuCounty());
			pstmt.setByte(17, trip.getMiaoliCounty());
			pstmt.setByte(18, trip.getChanghuaCounty());
			pstmt.setByte(19, trip.getNantouCounty());
			pstmt.setByte(20, trip.getYunlinCounty());
			pstmt.setByte(21, trip.getChiayiCounty());
			pstmt.setByte(22, trip.getPingtungCounty());
			pstmt.setByte(23, trip.getYilanCity());
			pstmt.setByte(24, trip.getHualienCity());
			pstmt.setByte(25, trip.getTaitungCounty());
			pstmt.setByte(26, trip.getKinmenCounty());
			pstmt.setByte(27, trip.getLienchiangCounty());
			pstmt.setByte(28, trip.getKeelungCity());
			pstmt.setByte(29, trip.getHsinchuCity());
			pstmt.setByte(30, trip.getChiayiCity());
			pstmt.setByte(31, trip.getPenghuCounty());
			pstmt.setBytes(32, trip.getMainPhoto());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int update(Trip trip) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, trip.getTripId());
			pstmt.setInt(2, trip.getCompId());
			pstmt.setString(3, trip.getTripName());
			pstmt.setInt(4, trip.getAmount());
			pstmt.setBigDecimal(5, trip.getPrice());
			pstmt.setInt(6, trip.getPeople());
			pstmt.setDate(7, trip.getStartTime());
			pstmt.setDate(8, trip.getEndTime());
			pstmt.setString(9, trip.getContent());
			pstmt.setInt(10, trip.getState());
			pstmt.setByte(11, trip.getTaipeiCity());
			pstmt.setByte(12, trip.getNewtaipeiCity());
			pstmt.setByte(13, trip.getTaoyuanCity());
			pstmt.setByte(14, trip.getTaichungCity());
			pstmt.setByte(15, trip.getTainanCity());
			pstmt.setByte(16, trip.getKaohsiungCity());
			pstmt.setByte(17, trip.getHsinchuCounty());
			pstmt.setByte(18, trip.getMiaoliCounty());
			pstmt.setByte(19, trip.getChanghuaCounty());
			pstmt.setByte(20, trip.getNantouCounty());
			pstmt.setByte(21, trip.getYunlinCounty());
			pstmt.setByte(22, trip.getChiayiCounty());
			pstmt.setByte(23, trip.getPingtungCounty());
			pstmt.setByte(24, trip.getYilanCity());
			pstmt.setByte(25, trip.getHualienCity());
			pstmt.setByte(26, trip.getTaitungCounty());
			pstmt.setByte(27, trip.getKinmenCounty());
			pstmt.setByte(28, trip.getLienchiangCounty());
			pstmt.setByte(29, trip.getKeelungCity());
			pstmt.setByte(30, trip.getHsinchuCity());
			pstmt.setByte(31, trip.getChiayiCity());
			pstmt.setByte(32, trip.getPenghuCounty());
			pstmt.setBytes(33, trip.getMainPhoto());
			pstmt.setInt(34, trip.getTripId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int delete(Integer trip_id) {
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
		return -1;
	}

	@Override
	public Trip findByPK(Integer trip_id) {
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
				trVO.setTripId(rs.getInt("trip_id"));
				trVO.setCompId(rs.getInt("comp_id"));
				trVO.setTripName(rs.getString("trip_name"));
				trVO.setAmount(rs.getInt("amount"));
				trVO.setPrice(rs.getBigDecimal("price"));
				trVO.setPeople(rs.getInt("people"));
				trVO.setStartTime(rs.getDate("start_time"));
				trVO.setEndTime(rs.getDate("end_time"));
				trVO.setContent(rs.getString("content"));
				trVO.setState(rs.getInt("state"));
				trVO.setTaipeiCity(rs.getByte("Taipei_city"));
				trVO.setNewtaipeiCity(rs.getByte("NewTaipei_city"));
				trVO.setTaoyuanCity(rs.getByte("Taoyuan_city"));
				trVO.setTaichungCity(rs.getByte("Taichung_city"));
				trVO.setTainanCity(rs.getByte("Tainan_city"));
				trVO.setKaohsiungCity(rs.getByte("Kaohsiung_city"));
				trVO.setHsinchuCounty(rs.getByte("Hsinchu_county"));
				trVO.setMiaoliCounty(rs.getByte("Miaoli_county"));
				trVO.setChanghuaCounty(rs.getByte("changhua_county"));
				trVO.setNantouCounty(rs.getByte("Nantou_county"));
				trVO.setYunlinCounty(rs.getByte("Yunlin_county"));
				trVO.setChiayiCounty(rs.getByte("chiayi_county"));
				trVO.setPingtungCounty(rs.getByte("Pingtung_county"));
				trVO.setYilanCity(rs.getByte("Yilan_city"));
				trVO.setHualienCity(rs.getByte("Hualien_city"));
				trVO.setTaitungCounty(rs.getByte("Taitung_county"));
				trVO.setKinmenCounty(rs.getByte("Kinmen_county"));
				trVO.setLienchiangCounty(rs.getByte("Lienchiang_county"));
				trVO.setKeelungCity(rs.getByte("Keelung_city"));
				trVO.setHsinchuCity(rs.getByte("Hsinchu_city"));
				trVO.setChiayiCity(rs.getByte("chiayi_city"));
				trVO.setPenghuCounty(rs.getByte("Penghu_county"));
				trVO.setMainPhoto(rs.getBytes("main_photo"));
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
				trVO.setTripId(rs.getInt("trip_id"));
				trVO.setCompId(rs.getInt("comp_id"));
				trVO.setTripName(rs.getString("trip_name"));
				trVO.setAmount(rs.getInt("amount"));
				trVO.setPrice(rs.getBigDecimal("price"));
				trVO.setPeople(rs.getInt("people"));
				trVO.setStartTime(rs.getDate("start_time"));
				trVO.setEndTime(rs.getDate("end_time"));
				trVO.setContent(rs.getString("content"));
				trVO.setState(rs.getInt("state"));
				trVO.setTaipeiCity(rs.getByte("taipei_city"));
				trVO.setNewtaipeiCity(rs.getByte("newtaipei_city"));
				trVO.setTaoyuanCity(rs.getByte("taoyuan_city"));
				trVO.setTaichungCity(rs.getByte("taichung_city"));
				trVO.setTainanCity(rs.getByte("tainan_city"));
				trVO.setKaohsiungCity(rs.getByte("kaohsiung_city"));
				trVO.setHsinchuCounty(rs.getByte("hsinchu_county"));
				trVO.setMiaoliCounty(rs.getByte("miaoli_county"));
				trVO.setChanghuaCounty(rs.getByte("changhua_county"));
				trVO.setNantouCounty(rs.getByte("nantou_county"));
				trVO.setYunlinCounty(rs.getByte("yunlin_county"));
				trVO.setChiayiCounty(rs.getByte("chiayi_county"));
				trVO.setPingtungCounty(rs.getByte("pingtung_county"));
				trVO.setYilanCity(rs.getByte("yilan_city"));
				trVO.setHualienCity(rs.getByte("hualien_city"));
				trVO.setTaitungCounty(rs.getByte("taitung_county"));
				trVO.setKinmenCounty(rs.getByte("kinmen_county"));
				trVO.setLienchiangCounty(rs.getByte("lienchiang_county"));
				trVO.setKeelungCity(rs.getByte("keelung_city"));
				trVO.setHsinchuCity(rs.getByte("hsinchu_city"));
				trVO.setChiayiCity(rs.getByte("chiayi_city"));
				trVO.setPenghuCounty(rs.getByte("penghu_county"));
				trVO.setMainPhoto(rs.getBytes("main_photo"));
				list.add(trVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}


	@Override
	public List<Trip> findTripByCompId(Integer compId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Trip_photo> getAllPhotoByTripId(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Itinerary> getItineraryByTripId(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAllPhoto(Integer tripId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getMainPhoto(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAmount(Integer amount, Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Trip, String> searchTrip(String site, Date startTime, Date endTime, Integer number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trip> getHotTrip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getTripProdutDetail(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Scene> scenesMaps(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}
}