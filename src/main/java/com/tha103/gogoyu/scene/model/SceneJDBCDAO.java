package com.tha103.gogoyu.scene.model;

import java.sql.Connection;
import java.io.*;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import util.Util;

public class SceneJDBCDAO implements SceneDAO_interface {
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO scene (scene_name,open_time,ticket_price,trans_info,parking,address ,lon,lat,feature , picture ) VALUES (?, ?, ? , ? , ?, ? , ? , ? ,?,? )";
	private static final String GET_ALL_STMT = "SELECT scene_name, scene_id, open_time, ticket_price,trans_info,parking,address ,lon,lat,feature , picture  FROM scene order by scene_id";
	private static final String GET_ONE_STMT = "SELECT  scene_id, scene_name, open_time,ticket_price,trans_info,parking, address ,lon,lat,feature , picture  FROM scene where scene_id = ?";
	private static final String DELETE = "DELETE FROM scene where scene_id = ?";
	private static final String UPDATE = "UPDATE scene set scene_name = ?,  open_time = ?,ticket_price=?,trans_info=?,parking=?, address = ? ,lon=?,lat=?, feature = ? , picture = ?   where scene_id = ? ";

	@Override
	public void insert(Scene Scene) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols = { "scene_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, Scene.getSceneName());
			pstmt.setString(2, Scene.getOpenTime());
			pstmt.setString(3, Scene.getTicketPrice());
			pstmt.setString(4, Scene.getTransInfo());
			pstmt.setString(5, Scene.getParking());
			pstmt.setString(6, Scene.getAddress());
			pstmt.setBigDecimal(7, Scene.getLon());
			pstmt.setBigDecimal(8, Scene.getLat());
			pstmt.setString(9, Scene.getFeature());
			pstmt.setString(10, Scene.getPicture());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(Scene Scene) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Scene.getSceneName());
			pstmt.setString(2, Scene.getOpenTime());
			pstmt.setString(3, Scene.getTicketPrice());
			pstmt.setString(4, Scene.getTransInfo());
			pstmt.setString(5, Scene.getParking());
			pstmt.setString(6, Scene.getAddress());
			pstmt.setBigDecimal(7, Scene.getLon());
			pstmt.setBigDecimal(8, Scene.getLat());
			pstmt.setString(9, Scene.getFeature());
			pstmt.setString(10, Scene.getPicture());
			pstmt.setInt(11, Scene.getSceneId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer scene_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, scene_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, null);
		}
	}

	@Override
	public Scene findByPrimaryKey(Integer scene_id) {
		Scene scVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, scene_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				scVO = new Scene();
				scVO.setSceneName(rs.getString("scene_name"));
				scVO.setSceneId(rs.getInt("scene_id"));
				scVO.setOpenTime(rs.getString("open_time"));
				scVO.setTicketPrice(rs.getString("ticket_price"));
				scVO.setTransInfo(rs.getString("trans_info"));
				scVO.setParking(rs.getString("parking"));
				scVO.setAddress(rs.getString("address"));
				scVO.setLon(rs.getBigDecimal("lon"));
				scVO.setLat(rs.getBigDecimal("lat"));
				scVO.setPicture(rs.getString("picture"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return scVO;
	}

	@Override
	public List<Scene> getAll() {
		List<Scene> list = new ArrayList<Scene>();
		Scene scVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				scVO = new Scene();
				scVO.setSceneName(rs.getString("scene_name"));
				scVO.setSceneId(rs.getInt("scene_id"));
				scVO.setOpenTime(rs.getString("open_time"));
				scVO.setTicketPrice(rs.getString("ticket_price"));
				scVO.setTransInfo(rs.getString("trans_info"));
				scVO.setParking(rs.getString("parking"));
				scVO.setAddress(rs.getString("address"));
				scVO.setLon(rs.getBigDecimal("lon"));
				scVO.setLat(rs.getBigDecimal("lat"));
				scVO.setFeature(rs.getString("feature"));
				scVO.setPicture(rs.getString("picture"));
				list.add(scVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			Util.closeResources(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) throws IOException {
		SceneJDBCDAO dao = new SceneJDBCDAO();
//		File scene_data = new File("./src/main/java/com/tha103/gogoyu/scene/model/景點.csv");
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(scene_data));
//			String line;
//			int count = 0;
//			while ((line = br.readLine()) != null) {
//				if (count == 0) {
//					count++;
//				} else {
//					String[] data_row = line.split(",");
//					Scene sc = new Scene();
//					sc.setSceneName(data_row[1]);
//					sc.setOpenTime(data_row[9]);
//					sc.setTransInfo(data_row[8]);
//					sc.setAddress(data_row[6]);
//					sc.setLon(new BigDecimal(data_row[17]));
//					sc.setLat(new BigDecimal(data_row[18]));
//					sc.setFeature(data_row[3]);
//					sc.setPicture(data_row[10]);
//					if (data_row.length == 33) {
//						sc.setParking(data_row[25]);
//						sc.setTicketPrice(data_row[28]);
//					} 
//					dao.insert(sc);
//					count++;
//				}
//			}
//			br.close();
//			System.out.println("Success rows :"+--count);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		Date date = new Date();
//		String time_s = new String(date.getTime());
//		// 新增
//		Scene scene1 = new Scene();
//
//		scene1.setOpenTime(java.lang.String.valueOf("2005-01-01 10:10:49"));
//		scene1.setAddress("1");
//		scene1.setTicketPrice("1");
//		scene1.setTransInfo("1");
//		scene1.setParking("1");
//		scene1.setFeature("1");
//		scene1.setLon(new BigDecimal(1.341421));
//		scene1.setLat(new BigDecimal(1.341421));
//		dao.insert(scene1);

		// 修改
//		Scene scene2=new Scene();
//		scene2.setSceneId(1);
//		scene2.setOpenTime(java.lang.String.valueOf("2225-01-01 10:10:49"));
//		scene2.setLon(new BigDecimal(1.21312321321321321321));
//		scene2.setLat(new BigDecimal(1.21312321321321321321));
//		scene2.setTicketPrice("1232");
//		scene2.setTransInfo("1232");
//		scene2.setParking("123");
//		scene2.setAddress("211111111111111111");
//		scene2.setFeature("1");
//		dao.update(scene2);

		// 刪除
//		dao.delete(1);

		// 查詢
//		Scene scene3 = dao.findByPrimaryKey(1);
//		System.out.print(scene3.getSceneId() + ",");
//		System.out.print(scene3.getOpenTime() + ",");
//		System.out.print(scene3.getAddress() + ",");
//		System.out.print(scene3.getLon() + ",");
//		System.out.print(scene3.getLat() + ",");
//		System.out.print(scene3.getTicketPrice() + ",");
//		System.out.print(scene3.getParking() + ",");
//		System.out.print(scene3.getTransInfo() + ",");
//		System.out.print(scene3.getFeature() + ",");
//		System.out.print(scene3.getPicture() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<Scene> list = dao.getAll();
//		for (Scene ascene : list) {
//			System.out.print(ascene.getSceneId() + ",");
//			System.out.print(ascene.getOpenTime() + ",");
//			System.out.print(ascene.getAddress() + ",");
//			System.out.print(ascene.getFeature() + ",");
//			System.out.print(ascene.getLon() + ",");
//			System.out.print(ascene.getLat() + ",");
//			System.out.print(ascene.getTicketPrice() + ",");
//			System.out.print(ascene.getParking() + ",");
//			System.out.print(ascene.getTransInfo() + ",");
//			System.out.print(ascene.getPicture() + ",");
//			System.out.println();
//		}
	}
}
