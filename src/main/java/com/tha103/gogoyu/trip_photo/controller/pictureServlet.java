package com.tha103.gogoyu.trip_photo.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tha103.gogoyu.trip_photo.model.*;
import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;

@WebServlet("/pictureServlet")
public class pictureServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			
			Trip_photoService picreader = new Trip_photoService();
			String pic = req.getParameter("trip_photo_id");
			Integer intpic = Integer.parseInt(pic);
			byte[] picGet = picreader.getOnePicture(intpic);

			out.write(picGet);
			

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
