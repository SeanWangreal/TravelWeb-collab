package com.tha103.gogoyu.room_stock.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tha103.gogoyu.room_stock.model.RoomStockService;
import com.tha103.gogoyu.room_stock.model.RoomStockServiceHibernate;
import com.tha103.gogoyu.room_stock.model.Room_stock;

@WebServlet("/sean/RoomStockServlet")
public class RoomStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomStockService roomStockSvc = null;

	@Override
	public void init() throws ServletException {
		roomStockSvc = new RoomStockServiceHibernate();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.getWriter().append("Served at: ").append(req.getContextPath());
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		String roomId = req.getParameter("roomId");
		String forwardPath = "";
		HttpSession session = req.getSession();
		String compId = (String) session.getAttribute("compId");
		if (compId != null) {
			switch (action) {
			case "change":
				List<Room_stock> list = roomStockSvc.getStockByTodayByRoomId(Integer.parseInt(roomId));
				req.setAttribute("list", list);
				req.setAttribute("roomId", roomId);
				forwardPath = "/sean/hotel_room_stock.jsp";
				break;
			case "changeStock":
				String[] oldId = req.getParameterValues("oldStock");
				String[] oldStock = req.getParameterValues("stockNum");
				String[] deleteId = req.getParameterValues("deleteStock");
				String[] newStock = req.getParameterValues("newStock");
				String[] newStockDate = req.getParameterValues("newStockDate");
				Map<Integer, Integer> oldMap = null;
				List<Integer> deleteIdList = null;
				Map<Date, Integer> newStockMap = null;
				if (oldId != null) {
					oldMap = new LinkedHashMap<Integer, Integer>();
					for (int i = 0; i < oldId.length; i++) {
						oldMap.put(Integer.parseInt(oldId[i]), Integer.parseInt(oldStock[i]));
					}
				}
				if (deleteId != null) {
					deleteIdList = new ArrayList<Integer>();
					for (int i = 0; i < deleteId.length; i++) {
						deleteIdList.add(Integer.parseInt(deleteId[i]));
					}
				}
				if (newStock != null) {
					newStockMap = new LinkedHashMap<Date, Integer>();
					for (int i = 0; i < newStock.length; i++) {
						newStockMap.put(Date.valueOf(newStockDate[i]), Integer.parseInt(newStock[i]));
					}
				}
				roomStockSvc.updateAllRoomStock(Integer.parseInt(roomId), oldMap, deleteIdList, newStockMap);
				forwardPath = "/sean/hotel_room_all.jsp";
				break;
			case "showStocks":
				PrintWriter out  = res.getWriter();
				List<Room_stock> stocks = roomStockSvc.getStockByTodayByRoomId(Integer.parseInt(roomId));
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String str = gson.toJson(stocks);
				out.write(str);
				out.close();
				return;
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
			
		}
	}

}
