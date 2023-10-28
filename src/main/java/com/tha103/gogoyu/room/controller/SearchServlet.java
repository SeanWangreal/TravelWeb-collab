package com.tha103.gogoyu.room.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.company.model.CompanyService;
import com.tha103.gogoyu.hotel_info.model.Hotel_infoServiceHibernate;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.room.model.RoomService;
import com.tha103.gogoyu.room.model.RoomServiceHibernate;
import com.tha103.gogoyu.room_photo.model.RoomPhotoService;
import com.tha103.gogoyu.room_photo.model.RoomPhotoServiceHibernate;
import com.tha103.gogoyu.room_photo.model.Room_photo;
import com.tha103.gogoyu.room_stock.model.RoomStockService;
import com.tha103.gogoyu.room_stock.model.RoomStockServiceHibernate;
import com.tha103.gogoyu.room_stock.model.Room_stock;

@WebServlet("/sean/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RoomService roomSvc = null;
	RoomPhotoService roomPhotoSvc = null;
	RoomStockService roomStockSvc = null;
	Hotel_infoServiceHibernate hotelInfoSvc = null;
	CompanyService companySvc = null;
	@Override
	public void init() throws ServletException {
		roomSvc = new RoomServiceHibernate();
		roomPhotoSvc = new RoomPhotoServiceHibernate();
		roomStockSvc = new RoomStockServiceHibernate();
		hotelInfoSvc = new Hotel_infoServiceHibernate();
		companySvc = new CompanyService();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String forwardPath = "";
		String action = req.getParameter("action");
		switch (action) {
		case "roomSearch":
			String comp_address = null;
			comp_address = req.getParameter("comp_address");
			Date checkIn = null;
			checkIn = java.sql.Date.valueOf(req.getParameter("checkIn"));
			Date checkOut = null;
			checkOut = java.sql.Date.valueOf(req.getParameter("checkOut"));
			Integer number = null;
			try {
				number = Integer.valueOf(req.getParameter("number").trim());
			}catch(NumberFormatException e){
				number = 0;
			}
			System.out.println(checkIn.toString()+checkOut.toString()+number);
			Map<Room, String> searchRoomResult = roomSvc.searchRoom(comp_address, checkIn, checkOut, number);
			req.setAttribute("searchCheckIn", checkIn);
			req.setAttribute("searchCheckOut", checkOut);
			req.setAttribute("people", number);
			req.setAttribute("searchRoomResult", searchRoomResult);
			forwardPath = "/mhl/search_results.jsp";
			break;
		case "getProductDetailRoom":
			Integer room_Id = Integer.valueOf(req.getParameter("room_id"));
			Date checkIn1 = null;
			try {
				checkIn1 = java.sql.Date.valueOf(req.getParameter("searchCheckIn"));
			} catch(Exception e){
				checkIn1 = null;
			}
			Date checkOut1 = null;
			try {
				checkOut1 = java.sql.Date.valueOf(req.getParameter("searchCheckOut"));
			} catch(Exception e){
				checkOut1 = null;
			}
			Integer number1 = null;
			if(req.getParameter("number") == null) {
				number1 = null;
			} else {
				number1 = Integer.valueOf(req.getParameter("number").trim());
			}
			
			List<Object> list = roomSvc.getRoomProdutDetail(room_Id);
			System.out.println(list);
			Room room = roomSvc.getOneRoom(room_Id);
			Integer companyId = room.getCompId();
			Company company = companySvc.getOneCompany(companyId);
			Integer hotel_info_id = company.getHotelInfoId();
			List<String> hotelInfoList = hotelInfoSvc.getHotelInfoList(hotel_info_id);
			list.add(hotelInfoList);
			req.setAttribute("searchCheckIn", checkIn1);
			req.setAttribute("searchCheckOut", checkOut1);
			req.setAttribute("people", number1);
			req.setAttribute("productDetailRoom", list);
			forwardPath = "/mhl/products_detail_room.jsp";
			break;
		case "searchRoomStock":
			Date checkIn2 = null;
			checkIn2 = java.sql.Date.valueOf(req.getParameter("stockCheckIn"));
			System.out.println("-------------------------------------------------------------------");
			System.out.println(checkIn2);
			
			Date checkOut2 = null;
			checkOut2 = java.sql.Date.valueOf(req.getParameter("stockCheckOut"));
			System.out.println("-------------------------------------------------------------------");
			System.out.println(checkOut2);
			
			Integer detailPageRoomId = Integer.valueOf(req.getParameter("detailPageRoomId"));
			System.out.println("-------------------------------------------------------------------");
			System.out.println(detailPageRoomId);
			
			Integer minStock = roomStockSvc.searchMinRoomStockByTime(detailPageRoomId, checkIn2, checkOut2);
			Map<String, Integer> stock = new HashMap<String, Integer>();
			stock.put("minStock", minStock);
			Gson gson =new Gson();
			String json=gson.toJson(stock);
			PrintWriter out = res.getWriter();
			out.println(json);
//			System.out.println(json);
			out.close();
			return;
		case "tripSearch":
//			String site = null;
//			site = req.getParameter("site");
//			Date checkIn3= null;
//			checkIn3 = java.sql.Date.valueOf(req.getParameter("checkIn"));
//			Date checkOut3 = null;
//			checkOut3 = java.sql.Date.valueOf(req.getParameter("checkOut"));
//			Integer number2 = null;
//			try {
//				number2 = Integer.valueOf(req.getParameter("number").trim());
//			}catch(NumberFormatException e){
//				number2 = 0;
//			}
//			System.out.println(checkIn.toString()+checkOut.toString()+number);
//			Map<Room, String> searchRoomResult = roomSvc.searchRoom(comp_address, checkIn, checkOut, number);
//			req.setAttribute("searchCheckIn", checkIn);
//			req.setAttribute("searchCheckOut", checkOut);
//			req.setAttribute("people", number);
//			req.setAttribute("searchRoomResult", searchRoomResult);
//			forwardPath = "/mhl/search_results.jsp";
//			return;
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


}
