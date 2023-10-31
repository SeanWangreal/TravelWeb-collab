package com.tha103.gogoyu.trip_photo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.gogoyu.trip_photo.model.Trip_photoService;
import com.tha103.gogoyu.trip_photo.model.Trip_photoServiceHibernate;


@WebServlet("/sean/TripPhotoPrintServlet")
public class TripPhotoPrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Trip_photoService tripPhotoSvc = null;

	public void init() throws ServletException {
		tripPhotoSvc = new Trip_photoServiceHibernate();
	}

	public void destroy() {
		tripPhotoSvc = null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		byte[] photo = null;
		String tripPhotoId = req.getParameter("tripPhotoId");
		if (tripPhotoId != null) {
			photo = tripPhotoSvc.getTripPhoto(Integer.valueOf(tripPhotoId));
		}
		out.write(photo);
		out.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
