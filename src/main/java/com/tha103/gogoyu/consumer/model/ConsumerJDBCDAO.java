package com.tha103.gogoyu.consumer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ConsumerJDBCDAO implements ConsumerDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO consumer (cus_name,cus_account,cus_password,cus_mail,cus_phone,cus_address,cus_sex,cus_photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT cus_id,cus_name,cus_account,cus_password,cus_mail,cus_phone,cus_address,cus_sex,cus_photo FROM consumer order by cus_id";
	private static final String GET_ONE_STMT = "SELECT cus_id,cus_name,cus_account,cus_password,cus_mail,cus_phone,cus_address,cus_sex,cus_photo FROM consumer where cus_id= ?";
	private static final String DELETE = "DELETE FROM consumer where cus_id = ?";
	private static final String UPDATE = "UPDATE consumer set cus_name=?, cus_account=?, cus_password=?, cus_mail=?, cus_phone=?, cus_address=?, cus_sex=?,cus_photo=? where cus_id = ?";

	@Override
	public int add(Consumer Consumer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, Consumer.getCusName());
			pstmt.setString(2, Consumer.getCusAccount());
			pstmt.setString(3, Consumer.getCusPassword());
			pstmt.setString(4, Consumer.getCusMail());
			pstmt.setString(5, Consumer.getCusPhone());
			pstmt.setString(6, Consumer.getCusAddress());
			pstmt.setInt(7, Consumer.getCusSex());
			pstmt.setBytes(8, Consumer.getCusPhoto());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}	

	@Override
	public int update(Consumer consumer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, consumer.getCusName());
			pstmt.setString(2, consumer.getCusAccount());
			pstmt.setString(3, consumer.getCusPassword());
			pstmt.setString(4, consumer.getCusMail());
			pstmt.setString(5, consumer.getCusPhone());
			pstmt.setString(6, consumer.getCusAddress());
			pstmt.setInt(7, consumer.getCusSex());
			pstmt.setBytes(8, consumer.getCusPhoto());
			pstmt.setInt(9, consumer.getCusId());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public int delete(Integer cusId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, cusId);
			pstmt.executeUpdate();
			return 1;
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
				Consumer.setCusId(rs.getInt("cus_id"));
				Consumer.setCusName(rs.getString("cus_name"));
				Consumer.setCusAccount(rs.getString("cus_account"));
				Consumer.setCusPassword(rs.getString("cus_password"));
				Consumer.setCusMail(rs.getString("cus_mail"));
				Consumer.setCusPhone(rs.getString("cus_phone"));
				Consumer.setCusAddress(rs.getString("cus_address"));
				Consumer.setCusSex(rs.getInt("cus_sex"));
				Consumer.setCusPhoto(rs.getBytes("cus_photo"));
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
				consumer.setCusId(rs.getInt("cus_id"));
				consumer.setCusName(rs.getString("cus_name"));
				consumer.setCusAccount(rs.getString("cus_account"));
				consumer.setCusPassword(rs.getString("cus_password"));
				consumer.setCusMail(rs.getString("cus_mail"));
				consumer.setCusPhone(rs.getString("cus_phone"));
				consumer.setCusAddress(rs.getString("cus_address"));
				consumer.setCusSex(rs.getInt("cus_sex"));
				consumer.setCusPhoto(rs.getBytes("cus_photo"));
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
			System.out.print(aCus.getCusName() + ",");
			System.out.print(aCus.getCusAccount() + ",");
			System.out.print(aCus.getCusPassword() + ",");
			System.out.print(aCus.getCusMail() + ",");
			System.out.print(aCus.getCusPhone() + ",");
			System.out.print(aCus.getCusAddress() + ",");
			System.out.print(aCus.getCusSex() + ",");
			System.out.print(aCus.getCusPhoto() + ",");
			System.out.println();
		}
	}

}
