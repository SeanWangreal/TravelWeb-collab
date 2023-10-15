package com.tha103.gogoyu.room_photo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.gogoyu.room_photo.model.RoomPhotoService;
import com.tha103.gogoyu.room_photo.model.RoomPhotoServiceHibernate;

@WebServlet("/sean/RoomPhotoPrintHServlet")
public class RoomPhotoPrintHServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomPhotoService roomPhotoSrc = null;

	public void init() throws ServletException {
		roomPhotoSrc = new RoomPhotoServiceHibernate();
	}

	public void destroy() {
		roomPhotoSrc = null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		byte[] photo = null;
		String roomPhotoId = req.getParameter("room_photo_id");
		if (roomPhotoId != null) {
			photo = roomPhotoSrc.getRoomPhoto(Integer.valueOf(roomPhotoId));
			System.out.println(photo);
		}
		out.write(photo);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
