package com.tha103.gogoyu.room_ord.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tha103.gogoyu.room.model.*;

@WebServlet("/room_photoServlet")
public class room_photoServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			try {
				
				RoomServiceHibernate picreader = new RoomServiceHibernate();
				String pic = req.getParameter("roomId");
				Integer roomPic = Integer.parseInt(pic);
				byte[] roomPhoto = picreader.getMainPhoto(roomPic);

				out.write(roomPhoto);
				

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}


