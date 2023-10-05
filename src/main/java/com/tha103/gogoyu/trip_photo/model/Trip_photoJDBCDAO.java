package com.tha103.gogoyu.trip_photo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.Util;
import java.io.FileInputStream;
import java.io.IOException;

public class Trip_photoJDBCDAO implements Trip_photoDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO trip_photo(trip_id, photo, upload_time) VALUES (?, ?, ?)";
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
	public int add(Trip_photo trip_photo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"trip_photo_id"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, trip_photo.getTripId());
			pstmt.setBytes(2, trip_photo.getPhoto());
			pstmt.setTimestamp(3, trip_photo.getUploadTime());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public int update(Trip_photo trip_photo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, trip_photo.getTripId());
			pstmt.setBytes(2, trip_photo.getPhoto());
			pstmt.setTimestamp(3, trip_photo.getUploadTime());
			pstmt.setInt(4, trip_photo.getTripPhotoId());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public int delete(Integer trip_photo_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, trip_photo_id);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Trip_photo findByPK(Integer trip_photo_id) {
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
				trip_photo.setTripPhotoId(rs.getInt("trip_photo_id"));
				trip_photo.setTripId(rs.getInt("trip_id"));
				trip_photo.setPhoto(rs.getBytes("photo"));
				trip_photo.setUploadTime(rs.getTimestamp("upload_time"));
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
				trip_photo.setTripPhotoId(rs.getInt("trip_photo_id"));
				trip_photo.setTripId(rs.getInt("trip_id"));
				trip_photo.setPhoto(rs.getBytes("photo"));
				trip_photo.setUploadTime(rs.getTimestamp("upload_time"));
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
		Date date = new Date();
		Timestamp time_s = new Timestamp(date.getTime());

//		// 新增
//		Trip_photo trip_photo01 = new Trip_photo();
//		trip_photo01.setTripId(3);
//		try {
//			byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Pictures\\20220405_231851.jpg");
//			trip_photo01.setPhoto(pic);
//		} catch(IOException ie){
//			System.out.println(ie);
//		}
//		trip_photo01.setUploadTime(time_s);
//		dao.insert(trip_photo01);

//		// 修改
//		Trip_photo trip_photo02 = new Trip_photo();
//		trip_photo02.setTripPhotoId(2);
//		trip_photo02.setTripId(1);
//		try {
//			byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Pictures\\20230322_171359.jpg");
//			trip_photo02.setPhoto(pic);
//		} catch(IOException ie) {
//			System.out.println(ie);
//		}
//		trip_photo02.setUploadTime(time_s);
//		dao.update(trip_photo02);

//		// 刪除
//		dao.delete(3);

//		// 查詢單筆
//		Trip_photo trip_photo03 = dao.findByPrimaryKey(2);
//		System.out.print(trip_photo03.getTripPhotoId() + ",");
//		System.out.print(trip_photo03.getTripId() + ",");
//		System.out.print(trip_photo03.getPhoto() + ",");
//		System.out.println(trip_photo03.getUploadTime());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Trip_photo> list = dao.getAll();
//		for(Trip_photo aPhoto : list) {
//			System.out.print(aPhoto.getTripPhotoId() + ",");
//			System.out.print(aPhoto.getTripId() + ",");
//			System.out.print(aPhoto.getPhoto() + ",");
//			System.out.print(aPhoto.getUploadTime());
//			System.out.println();
//		}
	}
}
