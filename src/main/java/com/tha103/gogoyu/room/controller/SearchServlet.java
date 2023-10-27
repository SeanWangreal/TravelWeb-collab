package com.tha103.gogoyu.room.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
	@Override
	public void init() throws ServletException {
		roomSvc = new RoomServiceHibernate();
		roomPhotoSvc = new RoomPhotoServiceHibernate();
		roomStockSvc = new RoomStockServiceHibernate();
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
			Map<Room, String> searchRoomResult = roomSvc.searchRoom(comp_address, checkIn, checkOut, number);
			req.setAttribute("searchRoomResult", searchRoomResult); 
			forwardPath = "/mhl/search_results.jsp";
			break;
		case "getProductDetailRoom":
			Integer room_Id = Integer.valueOf(req.getParameter("room_id"));
			List<Object> list = roomSvc.getRoomProdutDetail(room_Id);
			req.setAttribute("productDetailRoom", list);
			forwardPath = "/mhl/products_detail_room.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


}
