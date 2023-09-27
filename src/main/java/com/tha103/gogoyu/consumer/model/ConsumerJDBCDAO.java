package com.tha103.gogoyu.consumer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import util.Util;

import java.sql.*;

public class ConsumerJDBCDAO implements ConsumerDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO consumer (cus_name,cus_account,cus_password,cus_mail,cus_phone,cus_address,cus_sex,cus_photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT cus_id,cus_name,cus_account,cus_password,cus_mail,cus_phone,cus_address,cus_sex,cus_photo FROM consumer order by cus_id";
	private static final String GET_ONE_STMT = "SELECT cus_id,cus_name,cus_account,cus_password,cus_mail,cus_phone,cus_address,cus_sex,cus_photo FROM consumer where cus_id= ?";
	private static final String DELETE = "DELETE FROM consumer where cus_id = ?";
	private static final String UPDATE = "UPDATE consumer set cus_name=?, cus_account=?, cus_password=?, cus_mail=?, cus_phone=?, cus_address=?, cus_sex=?,cus_photo=? where cus_id = ?";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Consumer Consumer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, Consumer.getCus_name());
			pstmt.setString(2, Consumer.getCus_account());
			pstmt.setString(3, Consumer.getCus_password());
			pstmt.setString(4, Consumer.getCus_mail());
			pstmt.setString(5, Consumer.getCus_phone());
			pstmt.setString(6, Consumer.getCus_address());
			pstmt.setInt(7, Consumer.getCus_sex());
			pstmt.setBytes(8, Consumer.getCus_photo());
			pstmt.executeUpdate();
			System.out.println("success");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Consumer Consumer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Consumer.getCus_name());
			pstmt.setString(2, Consumer.getCus_account());
			pstmt.setString(3, Consumer.getCus_password());
			pstmt.setString(4, Consumer.getCus_mail());
			pstmt.setString(5, Consumer.getCus_phone());
			pstmt.setString(6, Consumer.getCus_address());
			pstmt.setInt(7, Consumer.getCus_sex());
			pstmt.setBytes(8, Consumer.getCus_photo());
			pstmt.setInt(9, Consumer.getCus_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer Cus_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, Cus_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Consumer findByPrimaryKey(Integer Cus_id) {
		Consumer Consumer = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, Cus_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Consumer = new Consumer();
				Consumer.setCus_id(rs.getInt("cus_id"));
				Consumer.setCus_name(rs.getString("cus_name"));
				Consumer.setCus_account(rs.getString("cus_account"));
				Consumer.setCus_password(rs.getString("cus_password"));
				Consumer.setCus_mail(rs.getString("cus_mail"));
				Consumer.setCus_phone(rs.getString("cus_phone"));
				Consumer.setCus_address(rs.getString("cus_address"));
				Consumer.setCus_sex(rs.getInt("cus_sex"));
				Consumer.setCus_photo(rs.getBytes("cus_photo"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return Consumer;
	}

	@Override
	public List<Consumer> getAll() {
		List<Consumer> list = new ArrayList<Consumer>();
		Consumer consumer = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				consumer = new Consumer();
				consumer.setCus_id(rs.getInt("cus_id"));
				consumer.setCus_name(rs.getString("cus_name"));
				consumer.setCus_account(rs.getString("cus_account"));
				consumer.setCus_password(rs.getString("cus_password"));
				consumer.setCus_mail(rs.getString("cus_mail"));
				consumer.setCus_phone(rs.getString("cus_phone"));
				consumer.setCus_address(rs.getString("cus_address"));
				consumer.setCus_sex(rs.getInt("cus_sex"));
				consumer.setCus_photo(rs.getBytes("cus_photo"));
				list.add(consumer); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		ConsumerJDBCDAO dao = new ConsumerJDBCDAO();

		// 新增
//		Consumer Consumer = new Consumer();
//		Consumer.setCus_name("1");
//		Consumer.setCus_account("1");
//		Consumer.setCus_password("1");
//		Consumer.setCus_mail("1");
//		Consumer.setCus_phone("1");
//		Consumer.setCus_address("1");
//		Consumer.setCus_sex(1);
//		Consumer.setCus_photo(null);
//		dao.insert(Consumer);

		// 修改
//		Consumer Consumer = new Consumer();
//		Consumer.setCus_name("2");
//		Consumer.setCus_account("2");
//		Consumer.setCus_password("2");
//		Consumer.setCus_mail("1");
//		Consumer.setCus_phone("1");
//		Consumer.setCus_address("1");
//		Consumer.setCus_sex(1);
//		Consumer.setCus_photo(null);
//		Consumer.setCus_id(1);
//		dao.update(Consumer);

		// 刪除
//		dao.delete(1);

		// 查詢
//		Consumer Consumer = dao.findByPrimaryKey(2);
//		System.out.print(Consumer.getCus_id() + ",");
//		System.out.print(Consumer.getCus_name() + ",");
//		System.out.print(Consumer.getCus_account() + ",");
//		System.out.print(Consumer.getCus_password() + ",");
//		System.out.print(Consumer.getCus_mail() + ",");
//		System.out.print(Consumer.getCus_phone() + ",");
//		System.out.print(Consumer.getCus_address() + ",");
//		System.out.print(Consumer.getCus_sex() + ",");
//		System.out.println(Consumer.getCus_photo() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<Consumer> list = dao.getAll();
		for (Consumer aCus : list) {
			System.out.print(aCus.getCus_name() + ",");
			System.out.print(aCus.getCus_account() + ",");
			System.out.print(aCus.getCus_password() + ",");
			System.out.print(aCus.getCus_mail() + ",");
			System.out.print(aCus.getCus_phone() + ",");
			System.out.print(aCus.getCus_address() + ",");
			System.out.print(aCus.getCus_sex() + ",");
			System.out.print(aCus.getCus_photo() + ",");
			System.out.println();
		}
	}

}
