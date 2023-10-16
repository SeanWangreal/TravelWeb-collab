package com.tha103.gogoyu.consumer.controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.tha103.gogoyu.consumer.model.*;

@WebServlet("/PictureServlet")
public class PictureServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			ConsumerServiceHibernate conreader = new ConsumerServiceHibernate();
			String pic =req.getParameter("cusId").trim(); //p60 p105
			Integer intpic =Integer.parseInt(pic);
			byte[] picGet = conreader.getOnePicture(intpic);
			
			out.write(picGet);
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
