package com.tha103.gogoyu.room_stock.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// ------------------------------------------------------------------------
		if (action == null) {
			action = "";
		} else {
			action = action.strip();
			String correctAction = "";
			if (action.contains(" ")) {
				for (int i = 0; i < action.length(); i++) {
					if (action.charAt(i) != (char) ' ')
						correctAction += action.charAt(i);
				}
				action = correctAction;
			}
		}
		System.out.println(action);
		// ------------------------------------------------------------------------
		String forwardPath = "";
		switch (action) {
		case "change":
			List<Room_stock> list = roomStockSvc.getStockByRoomId(Integer.parseInt(roomId));
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
			if (oldId != null) {
				for (int i = 0; i < oldId.length; i++) {
					roomStockSvc.updateRoomStock(Integer.parseInt(oldId[i]), Integer.parseInt(oldStock[i]));
				}
			}
			if (deleteId != null) {
				for (int i = 0; i < deleteId.length; i++) {
					roomStockSvc.deleteRoomStock(Integer.parseInt(deleteId[i]));
				}
			}
			if (newStock != null) {
				for (int i = 0; i < newStock.length; i++) {
					roomStockSvc.addRoomStock(Integer.parseInt(roomId), Date.valueOf(newStockDate[i]),
							Integer.parseInt(newStock[i]));
				}
			}
			forwardPath = "/sean/hotel_room_all.jsp";
			break;
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

}
