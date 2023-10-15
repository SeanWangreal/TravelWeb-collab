package com.tha103.gogoyu.hotel_info.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.gogoyu.hotel_info.model.Hotel_info;
import com.tha103.gogoyu.hotel_info.model.Hotel_infoServiceHibernate;

@WebServlet("/Hotel_infoServlet")
public class Hotel_infoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOne");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("hotelInfoId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/hotelInfo/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer hotelInfoId = null;
			try {
				hotelInfoId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/hotelInfo/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			Hotel_infoServiceHibernate hotelInfoSvc = new Hotel_infoServiceHibernate();
			Hotel_info hotelInfo = hotelInfoSvc.getOneHotel_info(hotelInfoId);
			if (hotelInfo == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath()+"/ken/hotelInfo/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			System.out.println(hotelInfo);
			req.setAttribute("Hotel_info", hotelInfo); // 資料庫取出的empVO物件,存入req
			String url = "/ken/com_mem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOneUpdate".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer hotelInfoId = Integer.valueOf(req.getParameter("hotelInfoId"));

			/*************************** 2.開始查詢資料 ****************************************/
			Hotel_infoServiceHibernate hotelInfoSvc = new Hotel_infoServiceHibernate();
			Hotel_info hotelInfo = hotelInfoSvc.getOneHotel_info(hotelInfoId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("Hotel_info", hotelInfo); // 資料庫取出的empVO物件,存入req
			String url = "/ken/com_mem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer hotelInfoId = Integer.valueOf(req.getParameter("hotelInfoId").trim());

			Byte restaurant = Byte.valueOf(req.getParameter("restaurant").trim());
			if (restaurant == null || restaurant == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte roomService = Byte.valueOf(req.getParameter("roomService").trim());
			if (roomService == null || roomService == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte alldayCounter = Byte.valueOf(req.getParameter("alldayCounter").trim());
			if (alldayCounter == null || alldayCounter == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte spa = Byte.valueOf(req.getParameter("spa").trim());
			if (spa == null || spa == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte gym = Byte.valueOf(req.getParameter("gym").trim());
			if (gym == null || gym == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte garden = Byte.valueOf(req.getParameter("garden").trim());
			if (garden == null || garden == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte terrace = Byte.valueOf(req.getParameter("terrace").trim());
			if (terrace == null || terrace == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte noSmoking = Byte.valueOf(req.getParameter("noSmoking").trim());
			if (noSmoking == null || noSmoking == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte freewifi = Byte.valueOf(req.getParameter("freewifi").trim());
			if (freewifi == null || freewifi == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte heater = Byte.valueOf(req.getParameter("heater").trim());
			if (heater == null || heater == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte beach = Byte.valueOf(req.getParameter("beach").trim());
			if (beach == null || beach == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte pool = Byte.valueOf(req.getParameter("pool").trim());
			if (pool == null || pool == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte chargingstation = Byte.valueOf(req.getParameter("chargingstation").trim());
			if (chargingstation == null || chargingstation == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte parking = Byte.valueOf(req.getParameter("parking").trim());
			if (parking == null || parking == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
		

//Integer hotelInfoId = Integer.valueOf(req.getParameter("hotelInfoId").trim());

			Hotel_info hotelInfo = new Hotel_info();
			hotelInfo.setHotelInfoId(hotelInfoId);
			hotelInfo.setRestaurant(restaurant);
			hotelInfo.setRoomService(roomService);
			hotelInfo.setAlldayCounter(alldayCounter);
			hotelInfo.setSpa(spa);
			hotelInfo.setGym(gym);
			hotelInfo.setGarden(garden);
			hotelInfo.setTerrace(terrace);
			hotelInfo.setNoSmoking(noSmoking);
			hotelInfo.setFreewifi(freewifi);
			hotelInfo.setHeater(heater);
			hotelInfo.setBeach(beach);
			hotelInfo.setPool(pool);
			hotelInfo.setChargingstation(chargingstation);
			hotelInfo.setParking(parking);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Hotel_info", hotelInfo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/hotelInfo/com_renewpass.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			Hotel_infoServiceHibernate hotelInfoSvc = new Hotel_infoServiceHibernate();
			hotelInfo = hotelInfoSvc.updateHotel_info(hotelInfoId, restaurant, roomService, alldayCounter, spa, gym, garden,
					terrace, noSmoking, freewifi, heater, beach, pool, chargingstation, parking );

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("Hotel_info", hotelInfo); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/ken/hotelInfo/com_renewpass.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("add".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer hotelInfoId = Integer.valueOf(req.getParameter("hotelInfoId").trim());

			Byte restaurant = Byte.valueOf(req.getParameter("restaurant").trim());
			if (restaurant == null || restaurant == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte roomService = Byte.valueOf(req.getParameter("roomService").trim());
			if (roomService == null || roomService == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte alldayCounter = Byte.valueOf(req.getParameter("alldayCounter").trim());
			if (alldayCounter == null || alldayCounter == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte spa = Byte.valueOf(req.getParameter("spa").trim());
			if (spa == null || spa == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte gym = Byte.valueOf(req.getParameter("gym").trim());
			if (gym == null || gym == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte garden = Byte.valueOf(req.getParameter("garden").trim());
			if (garden == null || garden == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte terrace = Byte.valueOf(req.getParameter("terrace").trim());
			if (terrace == null || terrace == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte noSmoking = Byte.valueOf(req.getParameter("noSmoking").trim());
			if (noSmoking == null || noSmoking == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte freewifi = Byte.valueOf(req.getParameter("freewifi").trim());
			if (freewifi == null || freewifi == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte heater = Byte.valueOf(req.getParameter("heater").trim());
			if (heater == null || heater == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte beach = Byte.valueOf(req.getParameter("beach").trim());
			if (beach == null || beach == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte pool = Byte.valueOf(req.getParameter("pool").trim());
			if (pool == null || pool == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte chargingstation = Byte.valueOf(req.getParameter("chargingstation").trim());
			if (chargingstation == null || chargingstation == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
			
			Byte parking = Byte.valueOf(req.getParameter("parking").trim());
			if (parking == null || parking == 0) {
				errorMsgs.add("餐廳請勿空白");
			}
		

//Integer hotelInfoId = Integer.valueOf(req.getParameter("hotelInfoId").trim());

			Hotel_info hotelInfo = new Hotel_info();
			hotelInfo.setHotelInfoId(hotelInfoId);
			hotelInfo.setRestaurant(restaurant);
			hotelInfo.setRoomService(roomService);
			hotelInfo.setAlldayCounter(alldayCounter);
			hotelInfo.setSpa(spa);
			hotelInfo.setGym(gym);
			hotelInfo.setGarden(garden);
			hotelInfo.setTerrace(terrace);
			hotelInfo.setNoSmoking(noSmoking);
			hotelInfo.setFreewifi(freewifi);
			hotelInfo.setHeater(heater);
			hotelInfo.setBeach(beach);
			hotelInfo.setPool(pool);
			hotelInfo.setChargingstation(chargingstation);
			hotelInfo.setParking(parking);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Hotel_info", hotelInfo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/hotelInfo/com_renewpass.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("hotelInfo", hotelInfo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/hotelInfo/com_mem.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Hotel_infoServiceHibernate hotelInfoSvc = new Hotel_infoServiceHibernate();
			hotelInfo = hotelInfoSvc.addHotel_info(hotelInfoId, restaurant, roomService, alldayCounter, spa, gym, garden,
					terrace, noSmoking, freewifi, heater, beach, pool, chargingstation, parking);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/ken/com_mem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer hotelInfoId = Integer.valueOf(req.getParameter("hotelInfoId"));

			/*************************** 2.開始刪除資料 ***************************************/
			Hotel_infoServiceHibernate hotelInfoSvc = new Hotel_infoServiceHibernate();
			hotelInfoSvc.deleteHotel_info(hotelInfoId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/ken/com_mem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
