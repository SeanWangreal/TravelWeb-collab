package com.tha103.gogoyu.trip.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tha103.gogoyu.room.model.RoomServiceHibernate;

@WebServlet("/sean/MainPhotoTripPrintServlet")
public class MainPhotoTripPrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomServiceHibernate roomSrc = null;

	public void init() throws ServletException {
		roomSrc = new RoomServiceHibernate();
	}

	public void destroy() {
		roomSrc = null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		byte[] photo = null;
		String tripId = req.getParameter("tripId");
		if (tripId != null) {
			photo = roomSrc.getMainPhoto(Integer.valueOf(tripId));
		}
		out.write(photo);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
