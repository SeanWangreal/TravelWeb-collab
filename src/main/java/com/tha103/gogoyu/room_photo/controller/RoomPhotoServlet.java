package com.tha103.gogoyu.room_photo.controller;

import java.io.IOException;
import java.io.InputStream;
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

import com.tha103.gogoyu.room_photo.model.RoomPhotoService;
import com.tha103.gogoyu.room_photo.model.RoomPhotoServiceHibernate;

@WebServlet("/sean/RoomPhotoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RoomPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RoomPhotoService roomSrc = null;

	@Override
	public void init() throws ServletException {
		roomSrc = new RoomPhotoServiceHibernate();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String roomId = req.getParameter("roomId");
		String compId = req.getParameter("compId");
		HttpSession session = req.getSession();
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
		case "getAllRoom":

			forwardPath = "/sean/hotel_room_all.jsp";
			break;
		case "change":
	
				forwardPath = "/sean/hotel_room_add.jsp";

			break;
		case "updateRoom":
			break;
		case "delete":
			
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
