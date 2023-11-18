package com.tha103.gogoyu.room_ord.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tha103.gogoyu.room.model.Room;
import com.tha103.gogoyu.room_ord.model.Room_ord;
import com.tha103.gogoyu.room_ord.model.Room_ordServiceHibernate;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;

@WebServlet("/sean/RoomOrdServlet")
public class RoomOrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Room_ordServiceHibernate roomOrdSvc = null;

	@Override
	public void init() throws ServletException {
		roomOrdSvc = new Room_ordServiceHibernate();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		String compId = (String) session.getAttribute("compId");
		if (compId != null) {
			String action = req.getParameter("action");
			System.out.println(action);
			switch (action) {
			case "allReview":
				PrintWriter out = res.getWriter();
				Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
				String begin = req.getParameter("whichpage");
				Integer startOrd = (Integer.parseInt(begin) - 1) * 5;
				Map<Room_ord, List<String>> map = roomOrdSvc.getRoomOrdByCompId(Integer.parseInt(compId), startOrd,
						"review");
				List<Object> list = new ArrayList<Object>();
				for (Room_ord ord : map.keySet()) {
					List<String> info = map.get(ord);
					list.add(ord);
					list.add(info);
				}
				String str = gson.toJson(list);
				System.out.println(str);
				out.write(str);
				return;
			case "allOrd":
				out = res.getWriter();
				gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
				begin = req.getParameter("whichpage");
				startOrd = (Integer.parseInt(begin) - 1) * 5;
				map = roomOrdSvc.getRoomOrdByCompId(Integer.parseInt(compId), startOrd, "ord");
				list = new ArrayList<Object>();
				for (Room_ord ord : map.keySet()) {
					List<String> info = map.get(ord);
					list.add(ord);
					list.add(info);
				}
				str = gson.toJson(list);
				out.write(str);
				return;
			case "OneRoomOrd":
				out = res.getWriter();
				gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
				list = new ArrayList<Object>();
				String roomOrdId = req.getParameter("roomOrdId");
				Map<Room_ord, List<String>> roomOrd = roomOrdSvc.getOneRoomOrd(Integer.valueOf(roomOrdId),
						Integer.valueOf(compId));
				for (Room_ord ord : roomOrd.keySet()) {
					List<String> info = roomOrd.get(ord);
					list.add(ord);
					list.add(info);
				}
				str = gson.toJson(list);
				out.write(str);
			}

		} else {
			res.sendRedirect(req.getContextPath() + "/mhl/home.jsp");
			return;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
