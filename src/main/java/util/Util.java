package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/tha103_g2_db?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "24708053";
	//for picture
	public static final String getBinary = "SELECT photo from trip_photo where trip_photo_id = ?";
	
	public static void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException se){
				se.getStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			}catch(SQLException se) {
				se.getStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.getStackTrace();
			}
		}
	}
}
