package com.tha103.gogoyu.room_photo.model;


import java.io.IOException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import util.Util;


public class Room_photoJDBCDAO implements Room_photoDAO_interface {

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO room_photo (room_id,photo,upload_time) VALUES (?, ? ,?)";
	private static final String GET_ALL_STMT = "SELECT room_photo_id ,room_id,photo,upload_time FROM room_photo";
	private static final String GET_ONE_STMT = "SELECT room_photo_id,room_id,photo,upload_time FROM room_photo where room_photo_id = ?";
	private static final String DELETE_ROOM_PHOTO = "DELETE FROM room_photo where room_photo_id = ?";	
	private static final String UPDATE = "UPDATE room_photo set room_id = ?,photo = ?,upload_time = ? where room_photo_id = ?";
	
	@Override
	public void insert(Room_photo room_photo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"room_photo_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setInt(1, room_photo.getRoom_id());
			pstmt.setBytes(2, room_photo.getPhoto());
			pstmt.setTimestamp(3, room_photo.getUpload_time());			
			pstmt.executeUpdate();

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	@Override
	public void update(Room_photo room_photo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, room_photo.getRoom_id());
			pstmt.setBytes(2, room_photo.getPhoto());
			pstmt.setTimestamp(3, room_photo.getUpload_time());			
			pstmt.setInt(4, room_photo.getRoom_photo_id());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}

	}

	@Override
	public void delete(Integer room_photo_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_ROOM_PHOTO);
			pstmt.setInt(1, room_photo_id);
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
	public Room_photo findByPrimaryKey(Integer room_photo_id) {
		Room_photo room_photo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_photo_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_photo = new Room_photo();
				room_photo.setRoom_photo_id(rs.getInt("room_photo_id"));
				room_photo.setRoom_id(rs.getInt("room_id"));
				room_photo.setPhoto(rs.getBytes("photo"));
				room_photo.setUpload_time(rs.getTimestamp("upload_time"));
				
			
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return room_photo;
		
	}

	@Override
	public List<Room_photo> getAll() {
		List<Room_photo> list = new ArrayList<Room_photo>();
		Room_photo room_photo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_photo = new Room_photo();
				room_photo.setRoom_photo_id(rs.getInt("room_photo_id"));
				room_photo.setRoom_id(rs.getInt("room_id"));
				room_photo.setPhoto(rs.getBytes("photo"));
				room_photo.setUpload_time(rs.getTimestamp("upload_time"));
				
				list.add(room_photo);
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

		Room_photoJDBCDAO dao = new Room_photoJDBCDAO();
//		Date date= new date();
		

	// 新增
//		Room_photo photo001 = new Room_photo();
		
//		photo001.setRoom_id(4);
//		try {
//			byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Pictures\\1693974131115.jpg");
//			photo001.setPhoto(pic);
//		} catch(IOException ie){
//			System.out.println(ie);
//		}		photo001.setUpload_time(Timestamp.valueOf("2023-09-11 17:55:55"));
//		dao.insert(photo001);
//
		//修改
//		Room_photo photo002 = new Room_photo();
//		photo002.setRoom_photo_id(1004);
//		photo002.setRoom_id(9);
//		photo002.setUpload_time(Timestamp.valueOf("2023-09-12 17:55:55"));
		
		//刪除
//		dao.delete(1004);
		
		//查詢
		Room_photo photo003 =dao.findByPrimaryKey(1005);
		System.out.print(photo003.getRoom_id() +",");
		System.out.print(photo003.getPhoto() +",");
		System.out.print(photo003.getUpload_time()+" ");
		
		List<Room_photo> list =dao.getAll();
		for(Room_photo sPhoto: list) {
			System.out.print(sPhoto.getRoom_id()+",");
			System.out.print(sPhoto.getPhoto()+",");
			System.out.print(sPhoto.getUpload_time()+",");
		}
	}
	}



