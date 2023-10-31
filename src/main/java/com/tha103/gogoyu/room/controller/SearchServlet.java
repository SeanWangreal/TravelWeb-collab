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
import com.tha103.gogoyu.scene.model.Scene;
import com.tha103.gogoyu.trip.model.Trip;
import com.tha103.gogoyu.trip.model.TripServiceHibernate;

@WebServlet("/sean/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RoomService roomSvc = null;
	RoomPhotoService roomPhotoSvc = null;
	RoomStockService roomStockSvc = null;
	Hotel_infoServiceHibernate hotelInfoSvc = null;
	CompanyService companySvc = null;
	TripServiceHibernate tripSvc = null;
	@Override
	public void init() throws ServletException {
		roomSvc = new RoomServiceHibernate();
		roomPhotoSvc = new RoomPhotoServiceHibernate();
		roomStockSvc = new RoomStockServiceHibernate();
		hotelInfoSvc = new Hotel_infoServiceHibernate();
		companySvc = new CompanyService();
		tripSvc = new TripServiceHibernate();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String forwardPath = "";
		String action = req.getParameter("action");
		switch (action) {
		case "hotel":
			String comp_address = null;
			comp_address = req.getParameter("site");
			Date checkIn = null;
			try {
				checkIn = java.sql.Date.valueOf(req.getParameter("checkIn"));
			} catch(Exception e){
				checkIn = null;
			}
			
			Date checkOut = null;
			try {
				checkOut = java.sql.Date.valueOf(req.getParameter("checkOut"));
			} catch(Exception e){
				checkOut = null;
			}
			
			Integer number =null;
			String str = req.getParameter("number");
			if(str == null || (str.trim()).length() == 0) {
				number = null;
			} else {
				number = Integer.valueOf(req.getParameter("number").trim());
			}

//			System.out.println(checkIn.toString()+checkOut.toString()+number);
			Map<Room, List<String>> searchRoomResult = roomSvc.searchRoom(comp_address, checkIn, checkOut, number);
			
			req.setAttribute("searchRoomComp_address", comp_address);
			req.setAttribute("searchRoomCheckIn", checkIn);
			req.setAttribute("searchRoomCheckOut", checkOut);
			req.setAttribute("roomPeople", number);
			req.setAttribute("searchRoomResult", searchRoomResult);
			forwardPath = "/mhl/search_results.jsp";
			break;
		case "getProductDetailRoom":
			String comp_address1 = req.getParameter("searchComp_address");
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
			Room room = roomSvc.getOneRoom(room_Id);
			Integer companyId = room.getCompId();
			Company company = companySvc.getOneCompany(companyId);
			Integer hotel_info_id = company.getHotelInfoId();
			List<String> hotelInfoList = hotelInfoSvc.getHotelInfoList(hotel_info_id);
			list.add(hotelInfoList);
			
			req.setAttribute("searchRoomComp_address", comp_address1);
			req.setAttribute("searchRoomCheckIn", checkIn1);
			req.setAttribute("searchRoomCheckOut", checkOut1);
			req.setAttribute("roomPeople", number1);
			req.setAttribute("productDetailRoom", list);
			forwardPath = "/mhl/products_detail_room.jsp";
			break;
		case "searchRoomStock":
			Date checkIn2 = null;
			checkIn2 = java.sql.Date.valueOf(req.getParameter("stockCheckIn"));
			
			Date checkOut2 = null;
			checkOut2 = java.sql.Date.valueOf(req.getParameter("stockCheckOut"));
			
			Integer detailPageRoomId = Integer.valueOf(req.getParameter("detailPageRoomId"));
			
			Integer minStock = roomStockSvc.searchMinRoomStockByTime(detailPageRoomId, checkIn2, checkOut2);
			
			Map<String, Integer> stock = new HashMap<String, Integer>();
			stock.put("minStock", minStock);
			Gson gson =new Gson();
			String json=gson.toJson(stock);
			PrintWriter out = res.getWriter();
			out.println(json);
//			System.out.println(json);
			out.close();
			break;
		case "trip":
			String site = req.getParameter("site");
			Date checkIn3= null;
			try {
				checkIn3 = java.sql.Date.valueOf(req.getParameter("checkIn"));
			} catch(Exception e){
				checkIn3 = null;
			}
			Date checkOut3 = null;
			try {
				checkOut3 = java.sql.Date.valueOf(req.getParameter("checkOut"));
			} catch(Exception e){
				checkOut3 = null;
			}
			
			Integer number2 =null;
			String str1 = req.getParameter("number");
			if(str1 == null || (str1.trim()).length() == 0) {
				number2 = null;
			} else {
				number2 = Integer.valueOf(req.getParameter("number").trim());
			}
			
//			System.out.println(checkIn3.toString()+checkOut3.toString()+number2);
			List<Trip> searchTripResult = tripSvc.searchTrip(site, checkIn3, checkOut3, number2);
			req.setAttribute("searchTripSite", site);
			req.setAttribute("searchTripCheckIn", checkIn3);
			req.setAttribute("searchTripCheckOut", checkOut3);
			req.setAttribute("tripPeople", number2);
			req.setAttribute("searchTripResult", searchTripResult);
			
			forwardPath = "/mhl/search_results.jsp";
			break;
			
		case "getProductDetailTrip":
			Integer trip_Id = Integer.valueOf(req.getParameter("tripId"));
			String site1 = req.getParameter("site");
			Date checkIn4 = null;
			try {
				checkIn4 = java.sql.Date.valueOf(req.getParameter("searchCheckIn"));
			} catch(Exception e){
				checkIn4 = null;
			}
			Date checkOut4 = null;
			try {
				checkOut4 = java.sql.Date.valueOf(req.getParameter("searchCheckOut"));
			} catch(Exception e){
				checkOut4 = null;
			}
			Integer number3 = null;
			if(req.getParameter("number") == null) {
				number3 = null;
			} else {
				number3 = Integer.valueOf(req.getParameter("number").trim());
			}
			
			List<Object> list1 = tripSvc.getTripProdutDetail(trip_Id);
			
			req.setAttribute("searchTripSite", site1);
			req.setAttribute("searchTripCheckIn", checkIn4);
			req.setAttribute("searchTripCheckOut", checkOut4);
			req.setAttribute("tripPeople", number3);
			req.setAttribute("productDetailTrip", list1);
			forwardPath = "/mhl/products_detail_trip.jsp";
			break;
			
		case "scenesMap":
		Integer tripId =null;
			try {
				tripId = Integer.valueOf(req.getParameter("tripId"));
			} catch(Exception e) {
				tripId = null;
			}
			
			List<Scene> scenesMap = tripSvc.scenesMaps(tripId);
			req.setAttribute("scenesMap", scenesMap);
			forwardPath = "/mhl/scenesMap.jsp";
			break;
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


}
