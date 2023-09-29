package com.tha103.gogoyu.planning.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class PlanningJDBCDAO implements PlanningDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO planning(cus_id, plan_name) VALUES (?, ?)";
	private static final String UPDATE = 
		"UPDATE planning SET cus_id = ?, plan_name = ? WHERE plan_id = ?";
	private static final String DELETE = 
		"DELETE FROM planning WHERE plan_id = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM planning WHERE plan_id = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM planning";
	
	@Override
	public void insert(Planning planning) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, planning.getCusId());
			pstmt.setString(2, planning.getPlanName());
			pstmt.executeUpdate();
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Planning planning) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, planning.getCusId());
			pstmt.setString(2, planning.getPlanName());
			pstmt.setInt(3, planning.getPlanId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer planning_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, planning_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Planning findByPrimaryKey(Integer planning_id) {
		Planning planning = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, planning_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				planning = new Planning();
				planning.setPlanId(rs.getInt("plan_id"));
				planning.setCusId(rs.getInt("cus_id"));
				planning.setPlanName(rs.getString("plan_name"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, rs);
		}
		return planning;
	}

	@Override
	public List<Planning> getAll() {
		List<Planning> list = new ArrayList<Planning>();
		Planning planning = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				planning = new Planning();
				planning.setPlanId(rs.getInt("plan_id"));
				planning.setCusId(rs.getInt("cus_id"));
				planning.setPlanName(rs.getString("plan_name"));
				list.add(planning); 
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		PlanningJDBCDAO dao = new PlanningJDBCDAO();

//		// 新增
//		Planning planning01 = new Planning();
//		planning01.setCusId(2);
//		planning01.setPlanName("測試新增2");
//		dao.insert(planning01);

//		// 修改
//		Planning planning02 = new Planning();
//		planning02.setPlanId(1);
//		planning02.setCusId(1);
//		planning02.setPlanName("測試修改");	
//		dao.update(planning02);

//		// 刪除
//		dao.delete(1);

//		// 查詢單筆
//		Planning planning03 = dao.findByPrimaryKey(2);
//		System.out.print(planning03.getPlanId() + ",");
//		System.out.print(planning03.getCusId() + ",");
//		System.out.println(planning03.getPlanName());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<Planning> list = dao.getAll();
//		for(Planning aPlanning : list) {
//			System.out.print(aPlanning.getPlanId() + ",");
//			System.out.print(aPlanning.getCusId() + ",");
//			System.out.print(aPlanning.getPlanName());
//			System.out.println();
//		}
	}
}
