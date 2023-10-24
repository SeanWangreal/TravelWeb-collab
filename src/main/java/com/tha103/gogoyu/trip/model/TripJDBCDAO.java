package com.tha103.gogoyu.trip.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tha103.gogoyu.itinerary.model.Itinerary;
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
	public int add(Trip trip) {
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

	public static void main(String[] args) {
		TripJDBCDAO dao = new TripJDBCDAO();
		java.util.Date date = new java.util.Date();
		Date time_s = new Date(date.getTime());
		// 新增
//		Trip tripVO1 = new Trip();
//		tripVO1.setCompId(2);
//		tripVO1.setTripName("1");
//		tripVO1.setAmount(1);
//		tripVO1.setPrice(new BigDecimal(1));
//		tripVO1.setPeople(1);
//		tripVO1.setStartTime(time_s);
//		tripVO1.setEndTime(time_s);
//		tripVO1.setContent("1");
//		tripVO1.setState(1);
//		tripVO1.setTaipeiCity((byte) 1);
//		tripVO1.setNewtaipeiCity((byte) 1);
//		tripVO1.setTaoyuanCity((byte) 1);
//		tripVO1.setTaichungCity((byte) 1);
//		tripVO1.setTainanCity((byte) 1);
//		tripVO1.setKaohsiungCity((byte) 1);
//		tripVO1.setHsinchuCounty((byte) 1);
//		tripVO1.setMiaoliCounty((byte) 1);
//		tripVO1.setChanghuaCounty((byte) 1);
//		tripVO1.setNantouCounty((byte) 1);
//		tripVO1.setYunlinCounty((byte) 1);
//		tripVO1.setChiayiCounty((byte) 1);
//		tripVO1.setPingtungCounty((byte) 1);
//		tripVO1.setYilanCity((byte) 1);
//		tripVO1.setHualienCity((byte) 1);
//		tripVO1.setTaitungCounty((byte) 1);
//		tripVO1.setKinmenCounty((byte) 1);
//		tripVO1.setLienchiangCounty((byte) 1);
//		tripVO1.setKeelungCity((byte) 1);
//		tripVO1.setHsinchuCity((byte) 1);
//		tripVO1.setChiayiCity((byte) 1);
//		tripVO1.setPenghuCounty((byte) 1);
//		dao.insert(tripVO1);
		// 修改
//		Trip tripVO2 = new Trip();
//		tripVO2.setTripId(1);
//		tripVO2.setCompId(2);
//		tripVO2.setTripName("1");
//		tripVO2.setAmount(1);
//		tripVO2.setPrice(new BigDecimal(1));
//		tripVO2.setPeople(1);
//		tripVO2.setStartTime(java.sql.Date.valueOf("2005-01-01 10:10:49"));
//		tripVO2.setEndTime(java.sql.Date.valueOf("2005-01-01 10:10:49"));
//		tripVO2.setContent("1");
//		tripVO2.setState(1);
//		tripVO2.setTaipeiCity((byte) 1);
//		tripVO2.setNewtaipeiCity((byte) 1);
//		tripVO2.setTaoyuanCity((byte) 1);
//		tripVO2.setTaichungCity((byte) 1);
//		tripVO2.setTainanCity((byte) 1);
//		tripVO2.setKaohsiungCity((byte) 1);
//		tripVO2.setHsinchuCounty((byte) 1);
//		tripVO2.setMiaoliCounty((byte) 1);
//		tripVO2.setChanghuaCounty((byte) 1);
//		tripVO2.setNantouCounty((byte) 1);
//		tripVO2.setYunlinCounty((byte) 1);
//		tripVO2.setChiayiCounty((byte) 1);
//		tripVO2.setPingtungCounty((byte) 1);
//		tripVO2.setYilanCity((byte) 1);
//		tripVO2.setHualienCity((byte) 1);
//		tripVO2.setTaitungCounty((byte) 1);
//		tripVO2.setKinmenCounty((byte) 1);
//		tripVO2.setLienchiangCounty((byte) 1);
//		tripVO2.setKeelungCity((byte) 1);
//		tripVO2.setHsinchuCity((byte) 1);
//		tripVO2.setChiayiCity((byte) 1);
//		tripVO2.setPenghuCounty((byte) 1);
//
//		dao.update(tripVO2);

		// 刪除
//		dao.delete(1);

		// 查詢
//		Trip tripVO3 = dao.findByPrimaryKey(1);
//		System.out.print(tripVO3.getTripId() + ",");
//		System.out.print(tripVO3.getCompId() + ",");
//		System.out.print(tripVO3.getTripName() + ",");
//		System.out.print(tripVO3.getAmount() + ",");
//		System.out.print(tripVO3.getPrice() + ",");
//		System.out.print(tripVO3.getPeople() + ",");
//		System.out.println(tripVO3.getStartTime());
//		System.out.print(tripVO3.getEndTime() + ",");
//		System.out.print(tripVO3.getContent() + ",");
//		System.out.print(tripVO3.getState() + ",");
//		System.out.print(tripVO3.getTaipeiCity() + ",");
//		System.out.print(tripVO3.getTaipeiCity() + ",");
//		System.out.print(tripVO3.getTaoyuanCity() + ",");
//		System.out.println(tripVO3.getTaichungCity());
//		System.out.print(tripVO3.getTainanCity() + ",");
//		System.out.print(tripVO3.getKaohsiungCity() + ",");
//		System.out.print(tripVO3.getHsinchuCounty() + ",");
//		System.out.print(tripVO3.getMiaoliCounty() + ",");
//		System.out.print(tripVO3.getChanghuaCounty() + ",");
//		System.out.print(tripVO3.getNantouCounty() + ",");
//		System.out.println(tripVO3.getYunlinCounty());
//		System.out.println(tripVO3.getChiayiCounty());
//		System.out.print(tripVO3.getPingtungCounty() + ",");
//		System.out.print(tripVO3.getYilanCity() + ",");
//		System.out.print(tripVO3.getHualienCity() + ",");
//		System.out.print(tripVO3.getTaitungCounty() + ",");
//		System.out.print(tripVO3.getKinmenCounty() + ",");
//		System.out.print(tripVO3.getLienchiangCounty() + ",");
//		System.out.println(tripVO3.getKeelungCity()+",");
//		System.out.print(tripVO3.getHsinchuCity() + ",");
//		System.out.print(tripVO3.getChiayiCity() + ",");
//		System.out.println(tripVO3.getPenghuCounty());
//		
//		System.out.println("---------------------");

		// 查詢
//		List<Trip> list = dao.getAll();
//		for (Trip aTrip : list) {
//			System.out.print(aTrip.getTripId() + ",");
//			System.out.print(aTrip.getCompId() + ",");
//			System.out.print(aTrip.getTripName() + ",");
//			System.out.print(aTrip.getAmount() + ",");
//			System.out.print(aTrip.getPrice() + ",");
//			System.out.print(aTrip.getPeople() + ",");
//			System.out.println(aTrip.getStartTime());
//			System.out.print(aTrip.getEndTime() + ",");
//			System.out.print(aTrip.getContent() + ",");
//			System.out.print(aTrip.getState() + ",");
//			System.out.print(aTrip.getTaipeiCity() + ",");
//			System.out.print(aTrip.getTaipeiCity() + ",");
//			System.out.print(aTrip.getTaoyuanCity() + ",");
//			System.out.println(aTrip.getTaichungCity());
//			System.out.print(aTrip.getTainanCity() + ",");
//			System.out.print(aTrip.getKaohsiungCity() + ",");
//			System.out.print(aTrip.getHsinchuCounty() + ",");
//			System.out.print(aTrip.getMiaoliCounty() + ",");
//			System.out.print(aTrip.getChanghuaCounty() + ",");
//			System.out.print(aTrip.getNantouCounty() + ",");
//			System.out.println(aTrip.getYunlinCounty());
//			System.out.println(aTrip.getChiayiCounty());
//			System.out.print(aTrip.getPingtungCounty() + ",");
//			System.out.print(aTrip.getYilanCity() + ",");
//			System.out.print(aTrip.getHualienCity() + ",");
//			System.out.print(aTrip.getTaitungCounty() + ",");
//			System.out.print(aTrip.getKinmenCounty() + ",");
//			System.out.print(aTrip.getLienchiangCounty() + ",");
//			System.out.println(aTrip.getKeelungCity() + ",");
//			System.out.print(aTrip.getHsinchuCity() + ",");
//			System.out.print(aTrip.getChiayiCity() + ",");
//			System.out.println(aTrip.getPenghuCounty());
//		}
	}


	@Override
	public List<Trip> findTripByCompId(Integer compId) {
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
	public Set<Trip_photo> getAllPhotoByTripId(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Itinerary> getItineraryByTripId(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}
}
