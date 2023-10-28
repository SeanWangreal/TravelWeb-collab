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
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/shopping_tripServlet")
public class shopping_tripServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 接收請求參數的編碼設定

		HttpSession session = req.getSession();

		String action = req.getParameter("action");

//=========================shoppingcart新增===============================
		if ("trip_goShopping".equals(action)) { // trip區塊加入購物車時(新增)
			Integer cusId = (Integer) session.getAttribute("cusId"); // 抓會員id
			if (cusId != null) {// 如果session有cus_id資料代表有人登入
				Integer cartId = Integer.valueOf(req.getParameter("cart_id"));
				Integer tripId = Integer.valueOf(req.getParameter("tripId"));
				PlanningServiceHibernate PSH = new PlanningServiceHibernate();// 創造出planning SERVICE
				Integer planId = PSH.getPlanId(cartId, cusId); // 得到1.他是誰 2.是哪台車
				// 透過取得的trip_id去找到該物件的屬性
				Integer tripAmount = 1; // 固定給他1(預設，後續可以到結帳前面去更改數量)
				Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
				TripServiceHibernate TSH = new TripServiceHibernate();
				Integer compId = TSH.getTrip(tripId).getCompId();
				// 改成dateformat
				Date startTime = TSH.getOneTrip(tripId).getStartTime(); // 透過tripservicehibernate取得(等翔哥)
				Date endTime = TSH.getOneTrip(tripId).getEndTime(); // 透過tripservicehibernate取得(等翔哥)

				BigDecimal totalPrice = TSH.getOneTrip(tripId).getPrice();
//			//comm = price *10%
				BigDecimal commission = totalPrice.multiply(new BigDecimal(0.1));
//			//profit = price - comm
				BigDecimal profit = totalPrice.subtract(commission);

				TOSH.addFromShopping(compId, tripId, planId, cusId, tripAmount, totalPrice, commission, profit,
						startTime, endTime, 0);

				String url = "/chu/shopping(hotel).jsp";
				res.sendRedirect(req.getContextPath() + url);
				return;

			} else { // 導回登入
				session.setAttribute("location", req.getRequestURI()); // 如果沒登入先記錄現在的位置(網址)
				res.sendRedirect(req.getContextPath() + "/eric/signin.jsp");// 然後導回登入頁面(等到有login.jsp再改路徑)

			}

		}
//=========================shoppingcart新增===============================

//=========================shoppingcart刪除===============================

		if ("removeTripOrder".equals(action)) {

			Integer tripOrdId = Integer.valueOf(req.getParameter("TripOrdId"));
			Trip_ordServiceHibernate ROSH = new Trip_ordServiceHibernate();
			ROSH.deleteTrip(tripOrdId);

			String url = "/chu/shopping(hotel).jsp";
			res.sendRedirect(req.getContextPath() + url);
			return;
		}
//=========================shoppingcart刪除===============================

//=========================bookingList更改數量===============================

		if ("countAmount".equals(action)) {
			Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdIdPk"));// tripOrdId

			Integer amount = Integer.valueOf(req.getParameter("tripAmount"));
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			TOSH.updateAmountAndPrice(amount, tripOrdId);// 更新數量
			req.setAttribute("tripOrdId", tripOrdId);
			String url = "/chu/bookingList(trip).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
//=========================bookingList更改數量===============================

		
		
//=========================購物車(飯店)更改車號===============================
		
				if("changeTripCart".equals(action)) {
						
					
					Integer changeCartId = Integer.valueOf(req.getParameter("changeCartId"));
					Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdId"));
					Integer cusId = (Integer) session.getAttribute("cusId");
					PlanningServiceHibernate PSH = new PlanningServiceHibernate();// 創造出SERVICE
					Integer planId = PSH.getPlanId(changeCartId, cusId); //得到1.他是誰   2.是哪台車
					Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
					TOSH.updateCartNum(planId ,tripOrdId);
					
					res.sendRedirect(req.getContextPath() + "/chu/shopping(hotel).jsp");
					return;
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
//=========================購物車(飯店)更改車號===============================			
		
		
		
//=========================bookingList進入payforsuccess===============================

		if ("ConnectToECPAY".equals(action)) {
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			TripServiceHibernate TSH = new TripServiceHibernate();
			Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdId"));
			Integer tripId = TOSH.getOneTrip(tripOrdId).getTripId();
			Integer tripAmount = TOSH.getOneTrip(tripOrdId).getAmount(); // 訂單購買數量
			Integer tripStore = TSH.getTrip(tripId).getAmount(); // 庫存

			BigDecimal profit = new BigDecimal(req.getParameter("profit"));
			BigDecimal commission = new BigDecimal(req.getParameter("commission"));
			BigDecimal totalPrice = new BigDecimal(req.getParameter("totalPrice"));
			String remark = req.getParameter("remark");
			Timestamp ordTime = Timestamp.valueOf(LocalDateTime.now());
			TOSH.updateStatusAndRemark(remark, tripOrdId, profit, commission, totalPrice, ordTime);

			Integer totalStore = tripStore - tripAmount;
			TSH.updateAmount(totalStore, tripId);

			res.sendRedirect(req.getContextPath() + "/chu/payForSuccess.jsp");
			return;

		}
//=========================bookingList進入payforsuccess===============================

//=========================進入bookingList===============================
		if ("TripCheckOut".equals(action)) {
			// 取得TripOrderId

			Integer tripOrderId = Integer.valueOf(req.getParameter("TripOrdId"));
			System.err.println(tripOrderId);
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			TripServiceHibernate TSH = new TripServiceHibernate();
			Integer tripId = TOSH.getOneTrip(tripOrderId).getTripId();
			Integer tripStore = TSH.getTrip(tripId).getAmount(); // 庫存

			if (tripStore > 0) {

				req.setAttribute("tripOrdId", tripOrderId);
				String url = "/chu/bookingList(trip).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} else {

				req.setAttribute("errorMessage", "已經沒庫存了...抱..抱歉QQ");
				String url = "/chu/shopping(hotel).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}

		}
//=========================進入bookingList===============================

		if ("CancelTransaction".equals(action)) {
			String url = "/chu/shopping(hotel).jsp";
			res.sendRedirect(req.getContextPath() + url);
			return;
		}

	}
}