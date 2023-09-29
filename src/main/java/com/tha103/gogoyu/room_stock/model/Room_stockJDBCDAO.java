package com.tha103.gogoyu.room_stock.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class Room_stockJDBCDAO implements Room_stockDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO room_stock (room_id,stock_date,stock) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT room_stock_id,room_id,stock_date,stock FROM room_stock";
	private static final String GET_ONE_STMT = "SELECT room_stock_id,room_id,stock_date,stock FROM room_stock where room_stock_id = ?";
	private static final String DELETE_ROOM_STOCK = "DELETE FROM room_stock where room_stock_id = ?";	
	
	private static final String UPDATE = "UPDATE room_stock set room_id = ?,stock_date = ? ,stock = ? where room_stock_id = ?";
	@Override
	public void insert(Room_stock roomStock) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = {"room_stock_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setInt(1, roomStock.getRoomId());
			pstmt.setDate(2,roomStock.getStockDate());
			pstmt.setInt(3,roomStock.getStock());		
			pstmt.executeUpdate();

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		
	}
	@Override
	public void update(Room_stock roomStock) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roomStock.getRoomId());
			pstmt.setDate(2,roomStock.getStockDate());
			pstmt.setInt(3,roomStock.getStock());				
			pstmt.setInt(4,roomStock.getRoomStockId());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		
	}
	@Override
	public void delete(Integer roomStockId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_ROOM_STOCK);
			pstmt.setInt(1, roomStockId);
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
	public Room_stock findByPrimaryKey(Integer roomStockId) {
		Room_stock roomStock = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomStockId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomStock = new Room_stock();
				roomStock.setRoomStockId(rs.getInt("room_stock_id"));
				roomStock.setRoomId(rs.getInt("room_id"));
				roomStock.setStockDate(rs.getDate("stock_date"));
				roomStock.setStock(rs.getInt("stock"));
				
			
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return roomStock;		
	}
	@Override
	public List<Room_stock> getAll() {
		List<Room_stock> list = new ArrayList<Room_stock>();
		Room_stock roomStock = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomStock = new Room_stock();
				roomStock.setRoomStockId(rs.getInt("room_stock_id"));
				roomStock.setRoomId(rs.getInt("room_id"));
				roomStock.setStockDate(rs.getDate("stock_date"));
				roomStock.setStock(rs.getInt("stock"));
				
				list.add(roomStock);
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
	Room_stockJDBCDAO dao = new Room_stockJDBCDAO(); 
	// 新增
//	Room_stock stock01 = new Room_stock();
//	stock01.setRoomId(7);
//	stock01.setStockDate(Date.valueOf("2022-02-02"));
//	stock01.setStock(6);
//	dao.insert(stock01);
	
	//修改
//	Room_stock stock02 = new Room_stock();
//	stock02.setRoomStockId(1000);
//	stock02.setRoomId(1);
//	stock02.setStockDate(Date.valueOf("1999-10-10"));
//	stock02.setStock(1);
//	dao.update(stock02);
	//刪除
//	dao.delete(1004);
	//查詢
	Room_stock st03 = dao.findByPrimaryKey(1000);
	System.out.print(st03.getRoomId() +",");
	System.out.print(st03.getStock() +",");
	System.out.print(st03.getStockDate() +".");
	
	List<Room_stock> list = dao.getAll();
	for(Room_stock stock:list) {
		System.out.print(stock.getRoomStockId()+",");
		System.out.print(stock.getRoomId()+",");
		System.out.println(stock.getStockDate()+",");
		System.out.println(stock.getStock()+">");
	}
	
	
	}
	
}
