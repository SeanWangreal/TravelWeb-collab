package com.tha103.gogoyu.trip_photo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tha103.gogoyu.trip_photo.model.*;

@WebServlet("/pictureServlet")
public class pictureServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		try {
			Trip_photoServiceJDBC picreader = new Trip_photoServiceJDBC();
			String pic = req.getParameter("trip_photo_id");
			Integer intpic = Integer.parseInt(pic);
			Trip_photo tripPhoto = picreader.getOneTripPhoto(intpic);
			out.write(tripPhoto.getPhoto());
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
