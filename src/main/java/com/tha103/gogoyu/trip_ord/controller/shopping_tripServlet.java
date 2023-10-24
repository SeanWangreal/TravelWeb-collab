package com.tha103.gogoyu.trip_ord.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tha103.gogoyu.company.model.CompanyService;
import com.tha103.gogoyu.planning.model.PlanningServiceHibernate;
import com.tha103.gogoyu.room.model.RoomServiceHibernate;
import com.tha103.gogoyu.room_ord.model.Room_ord;
import com.tha103.gogoyu.room_ord.model.Room_ordList;
import com.tha103.gogoyu.room_ord.model.Room_ordServiceHibernate;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;
import com.tha103.gogoyu.trip.model.Trip;
import com.tha103.gogoyu.trip_ord.model.Trip_ordServiceHibernate;
import com.tha103.gogoyu.trip.model.TripServiceHibernate;
import java.sql.Date;

@WebServlet("/shopping_tripServlet")
public class shopping_tripServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 接收請求參數的編碼設定

		HttpSession session = req.getSession();

		session.setAttribute("trip_id", 1);
		session.setAttribute("cus_id", 1);
		session.setAttribute("room_id", 1);

		Integer cusId = (Integer) session.getAttribute("cus_id"); // 抓會員id

		// tripId最後要放回去行程新增裡的if!!
		Integer tripId = (Integer) session.getAttribute("trip_id"); // 抓行程 id 最後getparameter(name value)

		// tripAmount最後要放回去行程新增裡的if!!
		Integer tripAmount = 1;// Integer.valueOf(req.getParameter("amount")); //抓數量amount(name value)

		String action = req.getParameter("action");
		
		
//=========================購物車(飯店)新增===============================
		if ("trip_goShopping".equals(action)) { // trip區塊加入購物車時(新增)

			if (cusId != null) {// 如果session有cus_id資料代表有人登入
				Integer cartId = Integer.valueOf(req.getParameter("cart_id")); // 透過js給值抓購物車號(name value)

				PlanningServiceHibernate PSH = new PlanningServiceHibernate();// 創造出planning SERVICE
				Integer planId = PSH.getPlanId(cusId, cartId); // 得到1.他是誰 2.是哪台車
				
				// 透過取得的trip_id去找到該物件的屬性

				Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
				TripServiceHibernate TSH = new TripServiceHibernate();
				// 改成dateformat
				Date startTime = TSH.getOneTrip(tripId).getStartTime(); // 透過tripservicehibernate取得(等翔哥)
				Date endTime = TSH.getOneTrip(tripId).getEndTime(); // 透過tripservicehibernate取得(等翔哥)

				BigDecimal totalPrice = TSH.getOneTrip(tripId).getPrice();
//			//comm = price *10%
				BigDecimal commission = totalPrice.multiply(new BigDecimal(0.1));
//			//profit = price - comm
				BigDecimal profit = totalPrice.subtract(commission);

				TOSH.addFromShopping(tripId, planId, cusId, tripAmount, totalPrice, commission, profit, 0);

				String url = "/chu/shopping(hotel).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} else { // 導回登入
				session.setAttribute("location", req.getRequestURI()); // 如果沒登入先記錄現在的位置(網址)
				res.sendRedirect(req.getContextPath() + "/chu/bookingList(trip).jsp");// 然後導回登入頁面(等到有login.jsp再改路徑)

			}

		}
//=========================購物車(旅行)新增===============================
		
		
//=========================購物車(旅行)刪除===============================
	
if("removeTripOrder".equals(action)) { 
			
			Integer roomOrdId =Integer.valueOf(req.getParameter("TripOrdId"));
			Trip_ordServiceHibernate ROSH = new Trip_ordServiceHibernate();
			ROSH.deleteTrip(roomOrdId);
	
			
			String url = "/chu/shopping(hotel).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);	

		}
//=========================購物車(飯店)刪除===============================
//=========================購物車(飯店)結帳===============================
		if("TripCheckOut".equals(action)) { 
			//取得TripOrderId
			Integer TripOrderId  = Integer.valueOf(req.getParameter("TripOrdId"));
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			TripServiceHibernate TSH = new TripServiceHibernate();
			CompanyService CSH = new CompanyService();
			
			Trip_ord trip_ord =TOSH.getOneTrip(TripOrderId); //取得trip_ord物件
			Integer TripId = trip_ord.getTripId();//取得tripId
			Trip trip  = TSH.getTrip(TripId);//取得trip物件
			
			Integer compId = trip.getCompId();
		
			Trip_ordList TOL = new Trip_ordList();
			TOL.setTripOrdId(TripOrderId);
			TOL.setTripName(trip.getTripName());
			TOL.setCusId(trip_ord.getCusId());
			TOL.setCompName(CSH.getComp(compId).getCompName());
			TOL.setPeople(trip.getPeople());
			TOL.setStore(trip.getAmount());
			TOL.setAmount(trip_ord.getAmount());
			TOL.setPrincipalName(CSH.getComp(compId).getPrincipalName());
			TOL.setPrincipalPhone(CSH.getComp(compId).getPrincipalPhone());
			TOL.setStartTime(trip.getStartTime()); 
			TOL.setEndTime(trip.getEndTime());
			TOL.setProfit(trip_ord.getProfit().setScale(3, RoundingMode.HALF_UP).intValue());
			TOL.setCommission(trip_ord.getCommission().setScale(3, RoundingMode.HALF_UP).intValue());
			TOL.setTotalPrice(trip_ord.getTotalPrice().setScale(3, RoundingMode.HALF_UP).intValue());
		
			
			req.setAttribute( "TripOrd" , TOL);
			String url = "/chu/bookingList(trip).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);	
			
		}		
//=========================購物車(飯店)結帳===============================
	
		if("CancelTransaction".equals(action)) {
			String url = "/chu/shopping(hotel).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);	
		}
		
		
		
		
	}
}