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
		HttpSession session = req.getSession();
		String compId = (String) session.getAttribute("compId");
		if (compId == null) {
			res.sendRedirect(req.getContextPath() + "/mhl/home.jsp");
			return;
		} else {
			String action = req.getParameter("action");
			switch (action) {
			case "allReview":
				PrintWriter out = res.getWriter();
				Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
				String begin = req.getParameter("whichpage");
				Integer startOrd = (Integer.parseInt(begin) - 1) * 5;
				Map<Trip_ord, List<String>> map = tripOrdSvc.getTripOrdByCompId(Integer.parseInt(compId), startOrd,
						"review");
				List<Object> list = new ArrayList<Object>();
				for (Trip_ord ord : map.keySet()) {
					List<String> info = map.get(ord);
					list.add(ord);
					list.add(info);
				}
				String str = gson.toJson(list);
				out.write(str);
				return;
			case "allOrd":
				out = res.getWriter();
				gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
				begin = req.getParameter("whichpage");
				startOrd = (Integer.parseInt(begin) - 1) * 5;
				map = tripOrdSvc.getTripOrdByCompId(Integer.parseInt(compId), startOrd, "ord");
				list = new ArrayList<Object>();
				for (Trip_ord ord : map.keySet()) {
					List<String> info = map.get(ord);
					list.add(ord);
					list.add(info);
				}
				str = gson.toJson(list);
				out.write(str);
				return;
			case "OneTripOrd":
				out = res.getWriter();
				gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
				list = new ArrayList<Object>();
				String tripOrdId = req.getParameter("tripOrdId");
				Map<Trip_ord, List<String>> tripOrd = tripOrdSvc.getOneTripOrd(Integer.valueOf(tripOrdId),
						Integer.valueOf(compId));
				for (Trip_ord ord : tripOrd.keySet()) {
					List<String> info = tripOrd.get(ord);
					list.add(ord);
					list.add(info);
				}
				str = gson.toJson(list);
				out.write(str);
				return;
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
