package com.tha103.gogoyu.trip.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tha103.gogoyu.itinerary.model.Itinerary;
import com.tha103.gogoyu.itinerary.model.ItineraryService;
import com.tha103.gogoyu.itinerary.model.ItineraryServiceHibernate;
import com.tha103.gogoyu.trip.model.Trip;
import com.tha103.gogoyu.trip.model.TripService;
import com.tha103.gogoyu.trip.model.TripServiceHibernate;
import com.tha103.gogoyu.trip_photo.model.Trip_photo;
import com.tha103.gogoyu.trip_photo.model.Trip_photoService;
import com.tha103.gogoyu.trip_photo.model.Trip_photoServiceHibernate;

@WebServlet("/sean/TripServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 100, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 10 * 5 * 1024
		* 1024)
public class TripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TripService tripSvc = null;
	Trip_photoService tripPhotoSvc = null;
	ItineraryService itinerarySvc = null;

	@Override
	public void init() throws ServletException {
		tripSvc = new TripServiceHibernate();
		tripPhotoSvc = new Trip_photoServiceHibernate();
		itinerarySvc = new ItineraryServiceHibernate();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		Trip trip = null;
		String tripId = req.getParameter("tripId");
		HttpSession session = req.getSession();
		String compId = (String) session.getAttribute("compId");
		if (compId == null) {
			res.sendRedirect(req.getContextPath() + "/sean/ken/com_mem_signin.jsp");
			return;
		}
		String forwardPath = "";
		String action = req.getParameter("action");
		switch (action) {
		case "add":
			forwardPath = "/sean/trip_ticket_add.jsp";
			break;
		case "getAllTrip":
			session.setAttribute("compId", compId);
			List<Trip> tripList = tripSvc.getTripByCompId(Integer.parseInt(compId));
			LinkedHashMap<Trip, Set<Trip_photo>> map = new LinkedHashMap<Trip, Set<Trip_photo>>();
			LinkedHashMap<Trip, Set<Itinerary>> itineraryMap = new LinkedHashMap<Trip, Set<Itinerary>>();
			Set<Trip_photo> tripPhoto = null;
			Set<Itinerary> itinerary = null;
			for (Trip li : tripList) {
				itinerary = tripSvc.getItineraryByTripId(li.getTripId());
				tripPhoto = tripSvc.getAllPhoto(li.getTripId());
				map.put(li, tripPhoto);
				itineraryMap.put(li, itinerary);
			}
			req.setAttribute("map", map);
			req.setAttribute("itineraryMap", itineraryMap);
			forwardPath = "/sean/trip_ticket_all.jsp";
			break;
		case "change":
			trip = tripSvc.getOneTrip(Integer.parseInt(tripId));
			tripPhoto = tripSvc.getAllPhoto(Integer.parseInt(tripId));
			List<Itinerary> itineraryList = itinerarySvc.getAllByTripId(Integer.parseInt(tripId));
			if (trip != null) {
				req.setAttribute("trip", trip);
				req.setAttribute("tripPhoto", tripPhoto);
				req.setAttribute("itineraryList", itineraryList);
				forwardPath = "/sean/trip_ticket_add.jsp";
			}
			break;
		case "addTrip":
			String tripName = req.getParameter("tripName");
			Integer amount = Integer.parseInt(req.getParameter("amount"));
			BigDecimal price = new BigDecimal(req.getParameter("price"));
			Integer people = Integer.parseInt(req.getParameter("people"));
			Date startTime = Date.valueOf(req.getParameter("startTime"));
			Date endTime = Date.valueOf(req.getParameter("endTime"));
			String content = req.getParameter("content");
			String[] sceneIdUnion = req.getParameterValues("sceneId");
			String[] beginTimeUnion = req.getParameterValues("beginTime");
			String[] sceneNameUnion = req.getParameterValues("sceneName");
			byte[] detail = getAllDetail(req, res);
			byte[] pic = null;
			LinkedList<byte[]> allPhoto = new LinkedList<byte[]>();
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				if (part.getContentType() != null && part.getSize() != 0) {
					InputStream is = part.getInputStream();
					pic = is.readAllBytes();
					allPhoto.add(pic);
				}
			}
			Integer tripid = null;
			compId = (String) session.getAttribute("compId");
			Integer tripStatus = 0;
			List<Itinerary> itineraryList2 = new ArrayList<Itinerary>();
			for (int i = 0; i < sceneIdUnion.length; i++) {
				String newbeginTime = beginTimeUnion[i].replace('T', ' ');
				newbeginTime += ":00";
				Itinerary it = new Itinerary();
				it.setBeginTime(Timestamp.valueOf(newbeginTime));
				it.setSceneId(Integer.parseInt(sceneIdUnion[i]));
				it.setSceneName(sceneNameUnion[i]);
				itineraryList2.add(it);
			}
			tripSvc.addTrip(Integer.parseInt(compId), tripName, amount, price, people, startTime, endTime, content,
					tripStatus, (byte) detail[0], (byte) detail[1], (byte) detail[2], (byte) detail[3],
					(byte) detail[4], (byte) detail[5], (byte) detail[6], (byte) detail[7], (byte) detail[8],
					(byte) detail[9], (byte) detail[10], (byte) detail[11], (byte) detail[12], (byte) detail[13],
					(byte) detail[14], (byte) detail[15], (byte) detail[16], (byte) detail[17], (byte) detail[18],
					(byte) detail[19], (byte) detail[20], (byte) detail[21], allPhoto.pop(), allPhoto, itineraryList2);
			res.sendRedirect(req.getContextPath() + "/sean/trip_ticket_all.jsp");
			return;
		case "updateTrip":
			compId = (String) session.getAttribute("compId");
			tripName = req.getParameter("tripName");
			amount = Integer.parseInt(req.getParameter("amount"));
			price = new BigDecimal(req.getParameter("price"));
			people = Integer.parseInt(req.getParameter("people"));
			startTime = Date.valueOf(req.getParameter("startTime"));
			endTime = Date.valueOf(req.getParameter("endTime"));
			content = req.getParameter("content");
			detail = getAllDetail(req, res);
			sceneIdUnion = req.getParameterValues("sceneId");
			beginTimeUnion = req.getParameterValues("beginTime");
			sceneNameUnion = req.getParameterValues("sceneName");
			List<byte[]> allPhotoUpdate = new ArrayList<byte[]>();
			Collection<Part> parts2 = req.getParts();
			pic = null;
			for (Part part : parts2) {
				if (part.getContentType() != null && part.getSize() != 0) {
					InputStream is = part.getInputStream();
					pic = is.readAllBytes();
					allPhotoUpdate.add(pic);
				} else if (part.getContentType() != null && part.getSize() == 0) {
					allPhotoUpdate.add(null);
				}
			}
			tripid = Integer.parseInt(tripId);
			trip = tripSvc.getOneTrip(tripid);
			if (allPhotoUpdate.get(0) == null) {
				tripSvc.updateTrip(trip.getTripId(), Integer.parseInt(compId), tripName, amount, price, people,
						startTime, endTime, content, trip.getState(), detail[0], detail[1], detail[2], detail[3],
						detail[4], detail[5], detail[6], detail[7], detail[8], detail[9], detail[10], detail[11],
						detail[12], detail[13], detail[14], detail[15], detail[16], detail[17], detail[18], detail[19],
						detail[20], detail[21], trip.getMainPhoto());
			} else {
				tripSvc.updateTrip(trip.getTripId(), Integer.parseInt(compId), tripName, amount, price, people,
						startTime, endTime, content, trip.getState(), detail[0], detail[1], detail[2], detail[3],
						detail[4], detail[5], detail[6], detail[7], detail[8], detail[9], detail[10], detail[11],
						detail[12], detail[13], detail[14], detail[15], detail[16], detail[17], detail[18], detail[19],
						detail[20], detail[21], allPhotoUpdate.get(0));
			}
			if (allPhotoUpdate.get(1) != null) {
				tripSvc.deleteAllPhoto(tripid);
				for (int i = 1; i < allPhotoUpdate.size(); i++) {
					tripPhotoSvc.addTripPhoto(tripid, allPhotoUpdate.get(i));
				}
			}
			List<Integer> sceneIdList = new ArrayList<Integer>();
			List<String> sceneNameList = new ArrayList<String>();
			List<Timestamp> beginTimeList = new ArrayList<Timestamp>();
			for (int i = 0; i < sceneIdUnion.length; i++) {
				String newbeginTime = beginTimeUnion[i].replace('T', ' ') + ":00";
				sceneIdList.add(Integer.parseInt(sceneIdUnion[i]));
				sceneNameList.add(sceneNameUnion[i]);
				beginTimeList.add(Timestamp.valueOf(newbeginTime));
			}
			itinerarySvc.deleteAllByTripIdAndAdd(tripid, sceneIdList, sceneNameList, beginTimeList);

			res.sendRedirect(req.getContextPath() + "/sean/trip_ticket_all.jsp");
			return;
		case "delete":
			compId = (String) session.getAttribute("compId");
			tripList = tripSvc.getTripByCompId(Integer.parseInt(compId));
			req.setAttribute("tripList", tripList);
			tripId = req.getParameter("tripId");
			if ((tripId != null) && (!tripId.trim().isBlank())) {
				tripid = Integer.parseInt(tripId);
				tripSvc.updateStatus(tripid, -1);
				res.sendRedirect(req.getContextPath() + "/sean/trip_ticket_all.jsp");
				return;
			}
			break;
		case "launch":
			compId = (String) session.getAttribute("compId");
			if ((tripId != null) && (!tripId.trim().isBlank())) {
				tripid = Integer.parseInt(tripId);
				tripSvc.updateStatus(tripid, 1);
				tripList = tripSvc.getTripByCompId(Integer.parseInt(compId));
				req.setAttribute("tripList", tripList);
				forwardPath = "/sean/trip_ticket_all.jsp";
			}
			break;
		case "recall":
			compId = (String) session.getAttribute("compId");
			if ((tripId != null) && (!tripId.trim().isBlank())) {
				tripid = Integer.parseInt(tripId);
				tripSvc.updateStatus(tripid, 0);
				tripList = tripSvc.getTripByCompId(Integer.parseInt(compId));
				req.setAttribute("tripList", tripList);
				forwardPath = "/sean/trip_ticket_all.jsp";
			}
			break;
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public byte[] getAllDetail(HttpServletRequest req, HttpServletResponse res) {
		byte[] detail = new byte[22];
		String[] allDetail = req.getParameterValues("detail");
		for (String de : allDetail) {
			System.out.println(de);
			switch (de) {
			case "taipeiCity":
				detail[0] = (byte) 1;
				break;
			case "newtaipeiCity":
				detail[1] = (byte) 1;
				break;
			case "taoyuanCity":
				detail[2] = (byte) 1;
				break;
			case "taichungCity":
				detail[3] = (byte) 1;
				break;
			case "tainanCity":
				detail[4] = (byte) 1;
				break;
			case "kaohsiungCity":
				detail[5] = (byte) 1;
				break;
			case "hsinchuCounty":
				detail[6] = (byte) 1;
				break;
			case "miaoliCounty":
				detail[7] = (byte) 1;
				break;
			case "changhuaCounty":
				detail[8] = (byte) 1;
				break;
			case "nantouCounty":
				detail[9] = (byte) 1;
				break;
			case "yunlinCounty":
				detail[10] = (byte) 1;
				break;
			case "chiayiCounty":
				detail[11] = (byte) 1;
				break;
			case "pingtungCounty":
				detail[12] = (byte) 1;
				break;
			case "yilanCity":
				detail[13] = (byte) 1;
				break;
			case "hualienCity":
				detail[14] = (byte) 1;
				break;
			case "taitungCounty":
				detail[15] = (byte) 1;
				break;
			case "kinmenCounty":
				detail[16] = (byte) 1;
				break;
			case "lienchiangCounty":
				detail[17] = (byte) 1;
				break;
			case "keelungCity":
				detail[18] = (byte) 1;
				break;
			case "hsinchuCity":
				detail[19] = (byte) 1;
				break;
			case "chiayiCity":
				detail[20] = (byte) 1;
				break;
			case "penghuCounty":
				detail[21] = (byte) 1;
				break;
			}
		}
		return detail;
	}

}
