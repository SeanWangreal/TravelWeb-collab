package com.tha103.gogoyu.room.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.gogoyu.room.model.RoomServiceHibernate;

import util.Util;

@WebServlet("/sean/MainPhotoPrintHServlet")
public class MainPhotoPrintHServlet extends HttpServlet {
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
		String roomId = req.getParameter("room_id");
		System.out.println(roomId);
		if (roomId != null) {
			photo = roomSrc.getMainPhoto(Integer.valueOf(roomId));
		}
		out.write(photo);
		out.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
