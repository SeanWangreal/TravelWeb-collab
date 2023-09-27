package com.tha103.gogoyu.trip_photo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;
import java.io.FileInputStream;
import java.io.IOException;

// 此類別實作DAO interface，並將資料庫操作細節封裝起來
public class Trip_photoJDBCDAO implements Trip_photoDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO trip_photo(trip_id, photo, upload_time) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE trip_photo SET trip_id = ?, photo = ?, upload_time = ? WHERE trip_photo_id = ?";
	private static final String DELETE = "DELETE FROM trip_photo WHERE trip_photo_id = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM trip_photo WHERE trip_photo_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM trip_photo";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Trip_photo trip_photo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"trip_photo_id"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, trip_photo.getTrip_id());
			pstmt.setBytes(2, trip_photo.getPhoto());
			pstmt.setTimestamp(3, trip_photo.getUpload_time());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Trip_photo trip_photo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, trip_photo.getTrip_id());
			pstmt.setBytes(2, trip_photo.getPhoto());
			pstmt.setTimestamp(3, trip_photo.getUpload_time());
			pstmt.setInt(4, trip_photo.getTrip_photo_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer trip_photo_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, trip_photo_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Trip_photo findByPrimaryKey(Integer trip_photo_id) {
		Trip_photo trip_photo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, trip_photo_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_photo = new Trip_photo();
				trip_photo.setTrip_photo_id(rs.getInt("Trip_photo_id"));
				trip_photo.setTrip_id(rs.getInt("Trip_id"));
				trip_photo.setPhoto(rs.getBytes("photo"));
				trip_photo.setUpload_time(rs.getTimestamp("upload_time"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return trip_photo;
	}

	@Override
	public List<Trip_photo> getAll() {
		List<Trip_photo> list = new ArrayList<Trip_photo>();
		Trip_photo trip_photo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trip_photo = new Trip_photo();
				trip_photo.setTrip_photo_id(rs.getInt("Trip_photo_id"));
				trip_photo.setTrip_id(rs.getInt("Trip_id"));
				trip_photo.setPhoto(rs.getBytes("photo"));
				trip_photo.setUpload_time(rs.getTimestamp("upload_time"));
				list.add(trip_photo);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = fis.readAllBytes();
		fis.close();
		return buffer;
	}

	public static void main(String[] args) {
		Trip_photoJDBCDAO dao = new Trip_photoJDBCDAO();

//		// 新增
//		Trip_photo trip_photo01 = new Trip_photo();
//		trip_photo01.setTrip_id(2);
//		try {
//			byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Pictures\\20230322_171359.jpg");
//			trip_photo01.setPhoto(pic);
//		} catch(IOException ie){
//			System.out.println(ie);
//		}
//		trip_photo01.setUpload_time(java.sql.Timestamp.valueOf("2023-09-11 10:36:33"));
//		dao.insert(trip_photo01);

//		// 修改
//		Trip_photo trip_photo02 = new Trip_photo();
//		trip_photo02.setTrip_photo_id(1);
//		trip_photo02.setTrip_id(1);
//		try {
//			byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Pictures\\20230322_171359.jpg");
//			trip_photo02.setPhoto(pic);
//		} catch(IOException ie) {
//			System.out.println(ie);
//		}
//		trip_photo02.setUpload_time(java.sql.Timestamp.valueOf("2023-09-11 12:00:00"));
//		dao.update(trip_photo02);

//		// 刪除
//		dao.delete(1);

//		// 查詢單筆
//		Trip_photo trip_photo03 = dao.findByPrimaryKey(2);
//		System.out.print(trip_photo03.getTrip_photo_id() + ",");
//		System.out.print(trip_photo03.getTrip_id() + ",");
//		System.out.print(trip_photo03.getPhoto() + ",");
//		System.out.println(trip_photo03.getUpload_time());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Trip_photo> list = dao.getAll();
//		for(Trip_photo aPhoto : list) {
//			System.out.print(aPhoto.getTrip_photo_id() + ",");
//			System.out.print(aPhoto.getTrip_id() + ",");
//			System.out.print(aPhoto.getPhoto() + ",");
//			System.out.print(aPhoto.getUpload_time());
//			System.out.println();
//		}
	}
}
