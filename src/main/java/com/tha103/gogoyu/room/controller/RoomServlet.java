package com.tha103.gogoyu.room.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

@WebServlet("/sean/RoomServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RoomService roomSrc = null;

	@Override
	public void init() throws ServletException {
		roomSrc = new RoomServiceHibernate();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		Room room = null;
		String roomId = req.getParameter("roomId");
		String compId = req.getParameter("compId");
		HttpSession session = req.getSession();
		System.out.println((String)session.getAttribute("compId"));
		String id = req.getParameter("id");
		String forwardPath = "";
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		} else {
			action = action.strip();
			String correctAction = "";
			if (action.contains(" ")) {
				for (int i = 0; i < action.length(); i++) {
					if (action.charAt(i) != (char) ' ') {
						correctAction += action.charAt(i);
					}
				}
				action = correctAction;
			}
			System.out.println(action);
		}
		switch (action) {
		case "add":
			forwardPath = "/sean/hotel_room_add.jsp";
			break;
		case "getOne_For_Display":
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			if (roomId == null || (roomId.trim()).length() == 0) {
				errorMsgs.add("請輸入房間編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				forwardPath = "/sean/select_page.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
				dispatcher.forward(req, res);
			} else {
				room = roomSrc.getOneRoom(Integer.parseInt(roomId));
				req.setAttribute("room", room);
				forwardPath = "/sean/hotel_room.jsp";
			}
			break;
		case "getAllRoom":
			session.setAttribute("compId", compId);
			List<Room> RoomList = roomSrc.getRoomByCompId(Integer.parseInt(compId));
			req.setAttribute("roomList", RoomList);
			forwardPath = "/sean/hotel_room_all.jsp";
			break;
		case "change":
			room = roomSrc.getOneRoom(Integer.parseInt(id));
			System.out.println(req.getAttribute("backHere"));
			if (room != null) {
				req.setAttribute("room", room);
				forwardPath = "/sean/hotel_room_add.jsp";
			}
			break;
		case "addRoom":
			String roomName = req.getParameter("roomName");
			Integer roomType = Integer.parseInt(req.getParameter("roomType"));
			Integer beds = Integer.parseInt(req.getParameter("bedNum"));
			BigDecimal price = new BigDecimal(req.getParameter("price"));
			String intro = req.getParameter("intro");
			byte[] detail = getAllDetail(req, res);
			byte[] pic = null;
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				InputStream is = part.getInputStream();
				pic = is.readAllBytes();
			}
			Integer roomid = null;
			compId = (String) session.getAttribute("compId");
			if ((id != null) && (!id.trim().isBlank())) {
				roomid = Integer.parseInt(id);
				Room room1 = roomSrc.getOneRoom(roomid);
				room = roomSrc.updateRoom(roomid, Integer.parseInt(compId), roomType, roomName, beds, price, intro,
						room1.getRoomStatus(), (byte) detail[0], (byte) detail[1], (byte) detail[2], (byte) detail[3],
						(byte) detail[4], (byte) detail[5], (byte) detail[6], (byte) detail[7], (byte) detail[8],
						(byte) detail[9], (byte) detail[10], pic);
			} else {
				Integer roomStatus = 0;
				roomSrc.addRoom(Integer.parseInt(compId), roomType, roomName, beds, price, intro, roomStatus,
						(byte) detail[0], (byte) detail[1], (byte) detail[2], (byte) detail[3], (byte) detail[4],
						(byte) detail[5], (byte) detail[6], (byte) detail[7], (byte) detail[8], (byte) detail[9],
						(byte) detail[10], pic);
			}
			forwardPath = "/sean/hotel_room_all.jsp";
			
			break;
		case "updateRoom":
			compId = (String) session.getAttribute("compId");
			roomName = req.getParameter("roomName");
			roomType = Integer.parseInt(req.getParameter("roomType"));
			beds = Integer.parseInt(req.getParameter("bedNum"));
			price = new BigDecimal(req.getParameter("price"));
			intro = req.getParameter("intro");
			detail = getAllDetail(req, res);
			Collection<Part> parts2 = req.getParts();
			pic = null;
			for (Part part : parts2) {
				if (part.getContentType() != null && part.getSize() != 0) {
					InputStream is = part.getInputStream();
					pic = is.readAllBytes();
				}
			}
			roomid = Integer.parseInt(id);
			room = roomSrc.getOneRoom(roomid);
			if (pic == null) {
				room = roomSrc.updateRoom(roomid, Integer.parseInt(compId), roomType, roomName, beds, price, intro,
						room.getRoomStatus(), (byte) detail[0], (byte) detail[1], (byte) detail[2], (byte) detail[3],
						(byte) detail[4], (byte) detail[5], (byte) detail[6], (byte) detail[7], (byte) detail[8],
						(byte) detail[9], (byte) detail[10], room.getMainPhoto());
			} else {
				room = roomSrc.updateRoom(roomid, Integer.parseInt(compId), roomType, roomName, beds, price, intro,
						room.getRoomStatus(), (byte) detail[0], (byte) detail[1], (byte) detail[2], (byte) detail[3],
						(byte) detail[4], (byte) detail[5], (byte) detail[6], (byte) detail[7], (byte) detail[8],
						(byte) detail[9], (byte) detail[10], pic);
			}
			forwardPath = "/sean/hotel_room_all.jsp";
			break;
		case "delete1":
			compId = (String) session.getAttribute("compId");
			RoomList = roomSrc.getRoomByCompId(Integer.parseInt(compId));
			req.setAttribute("roomList", RoomList);
			id = req.getParameter("id");
			if ((id != null) && (!id.trim().isBlank())) {
				roomid = Integer.parseInt(id);
				roomSrc.updateStatus(roomid, -1);
				forwardPath = "/sean/hotel_room.jsp";
			}
			break;
		case "delete2":
			compId = (String) session.getAttribute("compId");
			RoomList = roomSrc.getRoomByCompId(Integer.parseInt(compId));
			req.setAttribute("roomList", RoomList);
			id = req.getParameter("id");
			if ((id != null) && (!id.trim().isBlank())) {
				roomid = Integer.parseInt(id);
				roomSrc.updateStatus(roomid, -1);
				res.sendRedirect(req.getContextPath() + "/sean/hotel_room_all.jsp");
				return;
			}
			break;
		case "launch":
			compId = (String) session.getAttribute("compId");
			if ((id != null) && (!id.trim().isBlank())) {
				roomid = Integer.parseInt(id);
				roomSrc.updateStatus(roomid, 1);
				RoomList = roomSrc.getRoomByCompId(Integer.parseInt(compId));
				req.setAttribute("roomList", RoomList);
				forwardPath = "/sean/hotel_room_all.jsp";
			}
			break;
		case "recall":
			compId = (String) session.getAttribute("compId");
			if ((id != null) && (!id.trim().isBlank())) {
				roomid = Integer.parseInt(id);
				roomSrc.updateStatus(roomid, 0);
				RoomList = roomSrc.getRoomByCompId(Integer.parseInt(compId));
				req.setAttribute("roomList", RoomList);
				forwardPath = "/sean/hotel_room_all.jsp";
			}
			break;
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public byte[] getAllDetail(HttpServletRequest req, HttpServletResponse res) {
		byte[] detail = new byte[11];
		String[] allDetail = req.getParameterValues("detail");
		for (String de : allDetail) {
			switch (de) {
			case "tissue":
				detail[0] = (byte) 1;
				break;
			case "shower":
				detail[1] = (byte) 1;
				break;
			case "bathroom":
				detail[2] = (byte) 1;
				break;
			case "dryer":
				detail[3] = (byte) 1;
				break;
			case "tub":
				detail[4] = (byte) 1;
				break;
			case "freetoiletries":
				detail[5] = (byte) 1;
				break;
			case "flushseat":
				detail[6] = (byte) 1;
				break;
			case "slippers":
				detail[7] = (byte) 1;
				break;
			case "bathrobe":
				detail[8] = (byte) 1;
				break;
			case "spatub":
				detail[9] = (byte) 1;
				break;
			case "electric_kettle":
				detail[10] = (byte) 1;
				break;
			}
		}
		return detail;
	}

}
