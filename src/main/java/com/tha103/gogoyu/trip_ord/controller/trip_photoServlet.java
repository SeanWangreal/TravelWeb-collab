package com.tha103.gogoyu.trip_ord.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tha103.gogoyu.trip.model.TripServiceHibernate;


@WebServlet("/trip_photoServlet")
public class trip_photoServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			try {
				
				TripServiceHibernate picreader = new TripServiceHibernate();
				Integer tripPic = Integer.parseInt(req.getParameter("tripId"));
				byte[] tripPhoto = picreader.getMainPhoto(tripPic);

				out.write(tripPhoto);
				

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}



