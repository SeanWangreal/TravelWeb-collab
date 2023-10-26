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

@WebServlet("/sean/RoomServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RoomServlet extends HttpServlet {
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
		Room room = null;
		String roomId = req.getParameter("roomId");
		HttpSession session = req.getSession();
		String compId = (String) session.getAttribute("compId");
		if (compId == null) {
			res.sendRedirect(req.getContextPath() + "/sean/ken/com_mem_signin.jsp");
			return;
		}
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
				room = roomSvc.getOneRoom(Integer.parseInt(roomId));
				req.setAttribute("room", room);
				forwardPath = "/sean/hotel_room.jsp";
			}
			break;
		case "getAllRoom":
			session.setAttribute("compId", compId);
			List<Room> roomList = roomSvc.getRoomByCompId(Integer.parseInt(compId));
			LinkedHashMap<Room, Set<Room_photo>> mapPhoto = new LinkedHashMap<Room, Set<Room_photo>>();
			Set<Room_photo> roomPhoto = null;
			for (Room li : roomList) {
				roomPhoto = roomSvc.getAllPhoto(li.getRoomId());
				mapPhoto.put(li, roomPhoto);
			}
			req.setAttribute("mapPhoto", mapPhoto);
			forwardPath = "/sean/hotel_room_all.jsp";
			break;
		case "change":
			room = roomSvc.getOneRoom(Integer.parseInt(roomId));
			roomPhoto = roomSvc.getAllPhoto(Integer.parseInt(roomId));
			if (room != null) {
				req.setAttribute("room", room);
				req.setAttribute("roomPhoto", roomPhoto);
				forwardPath = "/sean/hotel_room_add.jsp";
			}
			break;
		case "addRoom":
			String roomName = req.getParameter("roomName");
			String defualtNum = req.getParameter("default-num");
			Integer roomType = Integer.parseInt(req.getParameter("roomType"));
			Integer beds = Integer.parseInt(req.getParameter("bedNum"));
			BigDecimal price = new BigDecimal(req.getParameter("price"));
			String intro = req.getParameter("intro");
			byte[] detail = getAllDetail(req, res);
			byte[] pic = null;
			List<byte[]> allPhoto = new ArrayList<byte[]>();
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				if (part.getContentType() != null && part.getSize() != 0) {
					InputStream is = part.getInputStream();
					pic = is.readAllBytes();
					allPhoto.add(pic);
				}
			}
			Integer roomid = null;
			compId = (String) session.getAttribute("compId");
			if ((roomId != null) && (!roomId.trim().isBlank())) {
				roomid = Integer.parseInt(roomId);
				room = roomSvc.getOneRoom(roomid);
				room.setCompId(Integer.parseInt(compId));
				room.setRoomType(roomType);
				room.setRoomName(roomName);
				room.setBeds(beds);
				room.setPrice(price);
				room.setIntro(intro);
				room.setTissue((byte) detail[0]);
				room.setShower( (byte) detail[1]);
				room.setBathroom( (byte) detail[2]);
				room.setDryer( (byte) detail[3]);
				room.setTub( (byte) detail[4]);
				room.setFreetoiletries( (byte) detail[5]);
				room.setFlushseat( (byte) detail[6]);
				room.setSlippers( (byte) detail[7]);
				room.setBathrobe( (byte) detail[8]);
				room.setSpatub((byte) detail[9]);
				room.setElectricKettle((byte) detail[10]);
				room.setMainPhoto(allPhoto.get(0));
				roomSvc.updateRoom(room);
				for (int i = 1; i < allPhoto.size();i++) {
					roomPhotoSvc.addRoomPhoto(roomid,  allPhoto.get(i));
				}
			} else {
				Integer roomStatus = 0;
				Integer newRoomId = roomSvc.addRoom(Integer.parseInt(compId), roomType, roomName, beds, price, intro, roomStatus,
						(byte) detail[0], (byte) detail[1], (byte) detail[2], (byte) detail[3], (byte) detail[4],
						(byte) detail[5], (byte) detail[6], (byte) detail[7], (byte) detail[8], (byte) detail[9],
						(byte) detail[10], allPhoto.get(0));
				for (int i = 1; i < allPhoto.size();i++) {
					roomPhotoSvc.addRoomPhoto(newRoomId,  allPhoto.get(i));
				}
				java.util.Date timeU = new java.util.Date();
				Date timeS = new Date(timeU.getTime());
				roomStockSvc.addFirstTime(newRoomId, timeS, Integer.parseInt(defualtNum));
			}
			res.sendRedirect(req.getContextPath() + "/sean/hotel_room_all.jsp");
			return;
		case "updateRoom":
			compId = (String) session.getAttribute("compId");
			roomName = req.getParameter("roomName");
			roomType = Integer.parseInt(req.getParameter("roomType"));
			beds = Integer.parseInt(req.getParameter("bedNum"));
			price = new BigDecimal(req.getParameter("price"));
			intro = req.getParameter("intro");
			detail = getAllDetail(req, res);
			List<byte[]> allPhotoUpdate = new ArrayList<byte[]>();
			Collection<Part> parts2 = req.getParts();
			pic = null;
			for (Part part : parts2) {
				if (part.getContentType() != null && part.getSize() != 0) {
					InputStream is = part.getInputStream();
					pic = is.readAllBytes();
					allPhotoUpdate.add(pic);
				} else if (part.getContentType() != null && part.getSize() == 0) {
					allPhotoUpdate.add(null);
				}
			}
			roomid = Integer.parseInt(roomId);
			room = roomSvc.getOneRoom(roomid);
			if (allPhotoUpdate.get(0) == null) {
				room.setCompId(Integer.parseInt(compId));
				room.setRoomType(roomType);
				room.setRoomName(roomName);
				room.setBeds(beds);
				room.setPrice(price);
				room.setIntro(intro);
				room.setTissue((byte) detail[0]);
				room.setShower( (byte) detail[1]);
				room.setBathroom( (byte) detail[2]);
				room.setDryer( (byte) detail[3]);
				room.setTub( (byte) detail[4]);
				room.setFreetoiletries( (byte) detail[5]);
				room.setFlushseat( (byte) detail[6]);
				room.setSlippers( (byte) detail[7]);
				room.setBathrobe( (byte) detail[8]);
				room.setSpatub((byte) detail[9]);
				room.setElectricKettle((byte) detail[10]);
				roomSvc.updateRoom(room);
			} else {
				room.setCompId(Integer.parseInt(compId));
				room.setRoomType(roomType);
				room.setRoomName(roomName);
				room.setBeds(beds);
				room.setPrice(price);
				room.setIntro(intro);
				room.setTissue((byte) detail[0]);
				room.setShower( (byte) detail[1]);
				room.setBathroom( (byte) detail[2]);
				room.setDryer( (byte) detail[3]);
				room.setTub( (byte) detail[4]);
				room.setFreetoiletries( (byte) detail[5]);
				room.setFlushseat( (byte) detail[6]);
				room.setSlippers( (byte) detail[7]);
				room.setBathrobe( (byte) detail[8]);
				room.setSpatub((byte) detail[9]);
				room.setElectricKettle((byte) detail[10]);
				room.setMainPhoto(allPhotoUpdate.get(0));
				roomSvc.updateRoom(room);
			}
			if (allPhotoUpdate.get(1) != null) {
				roomSvc.deleteAllPhoto(roomid);
				for (int i = 1; i < allPhotoUpdate.size();i++) {
					roomPhotoSvc.addRoomPhoto(roomid, allPhotoUpdate.get(i));
				}				
			}
			forwardPath = "/sean/hotel_room_all.jsp";
			break;
		case "delete2":
			compId = (String) session.getAttribute("compId");
			roomList = roomSvc.getRoomByCompId(Integer.parseInt(compId));
			req.setAttribute("roomList", roomList);
			roomId = req.getParameter("roomId");
			if ((roomId != null) && (!roomId.trim().isBlank())) {
				roomid = Integer.parseInt(roomId);
				roomSvc.updateStatus(roomid, -1);
				res.sendRedirect(req.getContextPath() + "/sean/hotel_room_all.jsp");
				return;
			}
			break;
		case "launch":
			compId = (String) session.getAttribute("compId");
			if ((roomId != null) && (!roomId.trim().isBlank())) {
				roomid = Integer.parseInt(roomId);
				roomSvc.updateStatus(roomid, 1);
				roomList = roomSvc.getRoomByCompId(Integer.parseInt(compId));
				req.setAttribute("roomList", roomList);
				forwardPath = "/sean/hotel_room_all.jsp";
			}
			break;
		case "recall":
			compId = (String) session.getAttribute("compId");
			if ((roomId != null) && (!roomId.trim().isBlank())) {
				roomid = Integer.parseInt(roomId);
				roomSvc.updateStatus(roomid, 0);
				roomList = roomSvc.getRoomByCompId(Integer.parseInt(compId));
				req.setAttribute("roomList", roomList);
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
