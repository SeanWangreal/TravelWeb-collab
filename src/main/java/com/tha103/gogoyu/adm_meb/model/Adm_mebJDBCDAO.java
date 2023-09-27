package com.tha103.gogoyu.adm_meb.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import util.Util;
import java.sql.*;

public class Adm_mebJDBCDAO implements Adm_mebDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO Adm_meb (adm_name,adm_account,adm_password) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT adm_id,adm_name,adm_account,adm_password FROM Adm_meb order by adm_id";
	private static final String GET_ONE_STMT = "SELECT adm_id,adm_name,adm_account,adm_password FROM Adm_meb where adm_id= ?";
	private static final String DELETE = "DELETE FROM Adm_meb where adm_id = ?";
	private static final String UPDATE = "UPDATE Adm_meb set adm_name=?, adm_account=?, adm_password=? where adm_id = ?";
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Adm_meb Adm_meb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "adm_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, Adm_meb.getAdm_name());
			pstmt.setString(2, Adm_meb.getAdm_account());
			pstmt.setString(3, Adm_meb.getAdm_password());
			pstmt.executeUpdate();
			System.out.println("success");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Adm_meb Adm_meb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Adm_meb.getAdm_name());
			pstmt.setString(2, Adm_meb.getAdm_account());
			pstmt.setString(3, Adm_meb.getAdm_password());
			pstmt.setInt(4, Adm_meb.getAdm_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer Adm_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, Adm_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Adm_meb findByPrimaryKey(Integer Adm_id) {
		Adm_meb Adm_meb = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, Adm_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Adm_meb = new Adm_meb();
				Adm_meb.setAdm_id(rs.getInt("Adm_id"));
				Adm_meb.setAdm_name(rs.getString("Adm_name"));
				Adm_meb.setAdm_account(rs.getString("Adm_account"));
				Adm_meb.setAdm_password(rs.getString("Adm_password"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return Adm_meb;
	}

	@Override
	public List<Adm_meb> getAll() {
		List<Adm_meb> list = new ArrayList<Adm_meb>();
		Adm_meb Adm_meb = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Adm_meb = new Adm_meb();
				Adm_meb.setAdm_id(rs.getInt("Adm_id"));
				Adm_meb.setAdm_name(rs.getString("Adm_name"));
				Adm_meb.setAdm_account(rs.getString("Adm_account"));
				Adm_meb.setAdm_password(rs.getString("Adm_password"));
				list.add(Adm_meb);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		Adm_mebJDBCDAO dao = new Adm_mebJDBCDAO();

		// 新增
//		Adm_meb Adm_meb = new Adm_meb();
//		Adm_meb.setAdm_name("1");
//		Adm_meb.setAdm_account("1");
//		Adm_meb.setAdm_password("1");
//		dao.insert(Adm_meb);

		// 修改
//		Adm_meb Adm_meb = new Adm_meb();
//		Adm_meb.setAdm_name("1");
//		Adm_meb.setAdm_account("7");
//		Adm_meb.setAdm_password("1");
//		Adm_meb.setAdm_id(12);
//		dao.update(Adm_meb);
//
		// 刪除
//		dao.delete(14);

		// 查詢
//		Adm_meb Adm_meb = dao.findByPrimaryKey(12);
//		System.out.print(Adm_meb.getAdm_id() + ",");
//		System.out.print(Adm_meb.getAdm_name() + ",");
//		System.out.print(Adm_meb.getAdm_account() + ",");
//		System.out.print(Adm_meb.getAdm_password() + ",");
//		System.out.println("---------------------");

		// 查詢

		List<Adm_meb> list = dao.getAll();
		for (Adm_meb aAdm : list) {
			System.out.print(aAdm.getAdm_name() + ",");
			System.out.print(aAdm.getAdm_account() + ",");
			System.out.print(aAdm.getAdm_password() + ",");
			System.out.println();
		}
	}

}