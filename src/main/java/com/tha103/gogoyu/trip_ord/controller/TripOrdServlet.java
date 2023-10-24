package com.tha103.gogoyu.trip_ord.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tha103.gogoyu.trip.model.Trip;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;
import com.tha103.gogoyu.trip_ord.model.Trip_ordServiceHibernate;

@WebServlet("/sean/TripOrdServlet")
public class TripOrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Trip_ordServiceHibernate tripOrdSvc = null;

	@Override
	public void init() throws ServletException {
		tripOrdSvc = new Trip_ordServiceHibernate();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		Trip room = null;
		HttpSession session = req.getSession();
		String compId = (String) session.getAttribute("compId");
		if (compId == null ){
			res.sendRedirect(req.getContextPath() + "/sean/select_page.jsp");
			return;
		}
		String action = req.getParameter("action");
		switch (action) {
		case "allReview":
			PrintWriter out  = res.getWriter();
			Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
			Map<Trip_ord, List<String>>  map = tripOrdSvc.getTripOrdByCompId(Integer.parseInt(compId));
			List list = new ArrayList();
			for (Trip_ord ord :map.keySet()) {
				System.out.println(ord);
				List info = map.get(ord);
				list.add(ord);
				list.add(info);
			}
			String str = gson.toJson(list);
			System.out.println(str);
			out.write(str);
			return;
		}
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
