package com.tha103.gogoyu.company.model;

import java.util.*;

import util.Util;

import java.sql.*;

public class CompanyJDBCDAO implements CompanyDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO company (comp_name,hotel_info_id,comp_type,"
			+ "comp_address,comp_phone,principal_name,principal_phone,"
			+ "comp_account,comp_password,comp_mail,comp_photo,check_status) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT comp_id,comp_name,hotel_info_id,comp_type,comp_address,"
			+ "comp_phone,principal_name,principal_phone,comp_account,comp_password,"
			+ "comp_mail,comp_photo,check_status FROM company order by comp_id";
	private static final String GET_ONE_STMT = "SELECT comp_id,comp_name,comp_address,comp_phone,principal_name,principal_phone,"
			+ "comp_account,comp_password,comp_mail,comp_photo,comp_type,check_status,"
			+ "hotel_info_id  FROM company where comp_id = ?";
	private static final String DELETE = "DELETE FROM company where comp_id = ?";
	private static final String UPDATE = "UPDATE company set comp_name = ?,hotel_info_id =?,comp_type = ?,comp_address = ? "
			+ ",comp_phone = ? ,principal_name = ? ,principal_phone = ? ,comp_account = ?,"
			+ "comp_password = ? ,comp_mail = ? ,comp_photo = ?,check_status = ?  " + "where comp_id = ?";

	@Override
	public void insert(Company Company) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "comp_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, Company.getComp_name());
			pstmt.setInt(2, Company.getHotel_info_id());
			pstmt.setInt(3, Company.getComp_type());
			pstmt.setString(4, Company.getComp_address());
			pstmt.setString(5, Company.getComp_phone());
			pstmt.setString(6, Company.getPrincipal_name());
			pstmt.setString(7, Company.getPrincipal_phone());
			pstmt.setString(8, Company.getComp_account());
			pstmt.setString(9, Company.getComp_password());
			pstmt.setString(10, Company.getComp_mail());
			pstmt.setBytes(11, Company.getComp_photo());
			pstmt.setInt(12, Company.getCheck_status());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Company Company) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Company.getComp_name());
			pstmt.setInt(2, Company.getHotel_info_id());
			pstmt.setInt(3, Company.getComp_type());
			pstmt.setString(4, Company.getComp_address());
			pstmt.setString(5, Company.getComp_phone());
			pstmt.setString(6, Company.getPrincipal_name());
			pstmt.setString(7, Company.getPrincipal_phone());
			pstmt.setString(8, Company.getComp_account());
			pstmt.setString(9, Company.getComp_password());
			pstmt.setString(10, Company.getComp_mail());
			pstmt.setBytes(11, Company.getComp_photo());
			pstmt.setInt(12, Company.getCheck_status());
			pstmt.setInt(13, Company.getComp_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer comp_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, comp_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Company findByPrimaryKey(Integer comp_id) {
		Company comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, comp_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comVO = new Company();
				comVO.setComp_id(rs.getInt("comp_id"));
				comVO.setHotel_info_id(rs.getInt("hotel_info_id"));
				comVO.setComp_type(rs.getInt("comp_type"));
				comVO.setComp_name(rs.getString("comp_name"));
				comVO.setComp_address(rs.getString("comp_address"));
				comVO.setComp_phone(rs.getString("comp_phone"));
				comVO.setPrincipal_name(rs.getString("principal_name"));
				comVO.setPrincipal_phone(rs.getString("principal_phone"));
				comVO.setComp_account(rs.getString("comp_account"));
				comVO.setComp_password(rs.getString("comp_password"));
				comVO.setComp_mail(rs.getString("comp_mail"));
				comVO.setComp_photo(rs.getBytes("comp_photo"));
				comVO.setCheck_status(rs.getInt("check_status"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return comVO;
	}

	@Override
	public List<Company> getAll() {
		List<Company> list = new ArrayList<Company>();
		Company comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comVO = new Company();
				comVO.setComp_id(rs.getInt("comp_id"));
				comVO.setComp_name(rs.getString("comp_name"));
				comVO.setComp_address(rs.getString("comp_address"));
				comVO.setComp_phone(rs.getString("comp_phone"));
				comVO.setPrincipal_name(rs.getString("principal_name"));
				comVO.setPrincipal_phone(rs.getString("principal_phone"));
				comVO.setComp_account(rs.getString("comp_account"));
				comVO.setComp_password(rs.getString("comp_password"));
				comVO.setComp_mail(rs.getString("comp_mail"));
				comVO.setComp_photo(rs.getBytes("comp_photo"));
				comVO.setComp_type(rs.getInt("comp_type"));
				comVO.setCheck_status(rs.getInt("check_status"));
				comVO.setHotel_info_id(rs.getInt("hotel_info_id"));
				list.add(comVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		CompanyJDBCDAO dao = new CompanyJDBCDAO();
		// 新增
//			companyVO comVO1= new companyVO();
//			comVO1.setComp_id(123);
//			comVO1.setComp_name("豬廷恩");
//			comVO1.setComp_address("台北市");
//			comVO1.setComp_phone("0987654321");
//			comVO1.setPrincipal_name("豬八戒");
//			comVO1.setPrincipal_phone("0987654321");
//			comVO1.setComp_account("1");
//			comVO1.setComp_password("2");
//			comVO1.setComp_mail("3");
//			comVO1.setComp_type(1);
//			comVO1.setCheck_status(1);
//			comVO1.setHotel_info_id(1);
//			dao.insert(comVO1);

		// 修改
//			companyVO comVO2= new companyVO();
//			comVO2.setComp_id(2);
//			comVO2.setComp_name("豬廷恩");
//			comVO2.setComp_address("台北市");
//			comVO2.setComp_phone("0987654321");
//			comVO2.setPrincipal_name("豬九戒");
//			comVO2.setPrincipal_phone("0987654321");
//			comVO2.setComp_account("1");
//			comVO2.setComp_password("2");
//			comVO2.setComp_mail("3");
//			comVO2.setComp_type(1);
//			comVO2.setCheck_status(1);
//			comVO2.setHotel_info_id(2);
//			dao.update(comVO2);

		// 刪除
//			dao.delete(1);

		// 查詢
		Company comVO4 = dao.findByPrimaryKey(2);
		System.out.print(comVO4.getComp_name() + ",");
		System.out.print(comVO4.getComp_address() + ",");
		System.out.print(comVO4.getComp_phone() + ",");
		System.out.print(comVO4.getPrincipal_name() + ",");
		System.out.print(comVO4.getPrincipal_phone() + ",");
		System.out.print(comVO4.getComp_account() + ",");
		System.out.println(comVO4.getComp_password());
		System.out.print(comVO4.getComp_mail() + ",");
		System.out.print(comVO4.getComp_type() + ",");
		System.out.print(comVO4.getCheck_status() + ",");
		System.out.println(comVO4.getHotel_info_id());
		System.out.println("---------------------");

		// 查詢
		List<Company> list = dao.getAll();
		for (Company aComp : list) {
			System.out.print(aComp.getComp_name() + ",");
			System.out.print(aComp.getComp_address() + ",");
			System.out.print(aComp.getComp_phone() + ",");
			System.out.print(aComp.getPrincipal_name() + ",");
			System.out.print(aComp.getPrincipal_phone() + ",");
			System.out.print(aComp.getComp_account() + ",");
			System.out.println(aComp.getComp_password());
			System.out.print(aComp.getComp_mail() + ",");
			System.out.print(aComp.getComp_type() + ",");
			System.out.print(aComp.getCheck_status() + ",");
			System.out.println(aComp.getHotel_info_id());
			System.out.println("---------------------");
		}
	}
}
