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
	public int add(Company Company) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "comp_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, Company.getCompName());
			pstmt.setInt(2, Company.getHotelInfoId());
			pstmt.setInt(3, Company.getCompType());
			pstmt.setString(4, Company.getCompAddress());
			pstmt.setString(5, Company.getCompPhone());
			pstmt.setString(6, Company.getPrincipalName());
			pstmt.setString(7, Company.getPrincipalPhone());
			pstmt.setString(8, Company.getCompAccount());
			pstmt.setString(9, Company.getCompPassword());
			pstmt.setString(10, Company.getCompMail());
			pstmt.setBytes(11, Company.getCompPhoto());
			pstmt.setInt(12, Company.getCheckStatus());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int update(Company Company) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Company.getCompName());
			pstmt.setInt(2, Company.getHotelInfoId());
			pstmt.setInt(3, Company.getCompType());
			pstmt.setString(4, Company.getCompAddress());
			pstmt.setString(5, Company.getCompPhone());
			pstmt.setString(6, Company.getPrincipalName());
			pstmt.setString(7, Company.getPrincipalPhone());
			pstmt.setString(8, Company.getCompAccount());
			pstmt.setString(9, Company.getCompPassword());
			pstmt.setString(10, Company.getCompMail());
			pstmt.setBytes(11, Company.getCompPhoto());
			pstmt.setInt(12, Company.getCheckStatus());
			pstmt.setInt(13, Company.getCompId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
		return -1;
	}

	@Override
	public int delete(Integer comp_id) {
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
		return -1;
	}

	@Override
	public Company findByPK(Integer comp_id) {
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
				comVO.setCompId(rs.getInt("comp_id"));
				comVO.setHotelInfoId(rs.getInt("hotel_info_id"));
				comVO.setCompType(rs.getInt("comp_type"));
				comVO.setCompName(rs.getString("comp_name"));
				comVO.setCompAddress(rs.getString("comp_address"));
				comVO.setCompPhone(rs.getString("comp_phone"));
				comVO.setPrincipalName(rs.getString("principal_name"));
				comVO.setPrincipalPhone(rs.getString("principal_phone"));
				comVO.setCompAccount(rs.getString("comp_account"));
				comVO.setCompPassword(rs.getString("comp_password"));
				comVO.setCompMail(rs.getString("comp_mail"));
				comVO.setCompPhoto(rs.getBytes("comp_photo"));
				comVO.setCheckStatus(rs.getInt("check_status"));
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
				comVO.setCompId(rs.getInt("comp_id"));
				comVO.setCompName(rs.getString("comp_name"));
				comVO.setCompAddress(rs.getString("comp_address"));
				comVO.setCompPhone(rs.getString("comp_phone"));
				comVO.setPrincipalName(rs.getString("principal_name"));
				comVO.setPrincipalPhone(rs.getString("principal_phone"));
				comVO.setCompAccount(rs.getString("comp_account"));
				comVO.setCompPassword(rs.getString("comp_password"));
				comVO.setCompMail(rs.getString("comp_mail"));
				comVO.setCompPhoto(rs.getBytes("comp_photo"));
				comVO.setCompType(rs.getInt("comp_type"));
				comVO.setCheckStatus(rs.getInt("check_status"));
				comVO.setHotelInfoId(rs.getInt("hotel_info_id"));
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
//			Company comVO1= new Company();
//			comVO1.setCompId(123);
//			comVO1.setCompName("豬廷恩");
//			comVO1.setCompAddress("台北市");
//			comVO1.setCompPhone("0987654321");
//			comVO1.setPrincipalName("豬八戒");
//			comVO1.setPrincipalPhone("0987654321");
//			comVO1.setCompAccount("1");
//			comVO1.setCompPassword("2");
//			comVO1.setCompMail("3");
//			comVO1.setCompType(1);
//			comVO1.setCheckStatus(1);
//			comVO1.setHotelInfoId(1);
//			dao.add(comVO1);

		// 修改
//			Company comVO2= new Company();
//			comVO2.setCompId(1);
//			comVO2.setCompName("豬廷恩");
//			comVO2.setCompAddress("台北市");
//			comVO2.setCompPhone("0987654321");
//			comVO2.setPrincipalName("豬九戒");
//			comVO2.setPrincipalPhone("0987654321");
//			comVO2.setCompAccount("1");
//			comVO2.setCompPassword("2");
//			comVO2.setCompMail("3");
//			comVO2.setCompType(1);
//			comVO2.setCheckStatus(1);
//			comVO2.setHotelInfoId(2);
//			dao.update(comVO2);

		// 刪除
//			dao.delete(1);

		// 查詢
//		Company comVO4 = dao.findByPK(2);
//		System.out.print(comVO4.getCompName() + ",");
//		System.out.print(comVO4.getCompAddress() + ",");
//		System.out.print(comVO4.getCompPhone() + ",");
//		System.out.print(comVO4.getPrincipalName() + ",");
//		System.out.print(comVO4.getPrincipalPhone() + ",");
//		System.out.print(comVO4.getCompAccount() + ",");
//		System.out.println(comVO4.getCompPassword());
//		System.out.print(comVO4.getCompMail() + ",");
//		System.out.print(comVO4.getCompType() + ",");
//		System.out.print(comVO4.getCheckStatus() + ",");
//		System.out.println(comVO4.getHotelInfoId());
//		System.out.println("---------------------");

		// 查詢
//		List<Company> list = dao.getAll();
//		for (Company aComp : list) {
//			System.out.print(aComp.getCompName() + ",");
//			System.out.print(aComp.getCompAddress() + ",");
//			System.out.print(aComp.getCompPhone() + ",");
//			System.out.print(aComp.getPrincipalName() + ",");
//			System.out.print(aComp.getPrincipalPhone() + ",");
//			System.out.print(aComp.getCompAccount() + ",");
//			System.out.println(aComp.getCompPassword());
//			System.out.print(aComp.getCompMail() + ",");
//			System.out.print(aComp.getCompType() + ",");
//			System.out.print(aComp.getCheckStatus() + ",");
//			System.out.println(aComp.getHotelInfoId());
//			System.out.println("---------------------");
//		}
	}
}
