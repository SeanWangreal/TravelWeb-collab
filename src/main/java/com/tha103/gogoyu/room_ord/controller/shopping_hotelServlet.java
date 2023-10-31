package com.tha103.gogoyu.room_ord.controller;

import java.io.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.planning.model.*;
import com.tha103.gogoyu.room_ord.model.*;
import com.tha103.gogoyu.room.model.*;
import com.tha103.gogoyu.trip.model.*;
import com.tha103.gogoyu.trip_ord.model.*;
import com.tha103.gogoyu.company.model.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tha103.gogoyu.room_stock.model.RoomStockServiceHibernate;

@WebServlet("/shopping_hotelServlet")
public class shopping_hotelServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // 接收請求參數的編碼設定

		HttpSession session = req.getSession();

		String Timestart = "2023-10-17"; // 示例日期字符串
		String Timeend = "2023-10-20"; // 示例日期字符串
		Date checkInTime = Date.valueOf(Timestart);
		Date checkOutTime = Date.valueOf(Timeend);

		String action = req.getParameter("action");

//=========================購物車(飯店)新增===============================
		if ("room_goShopping".equals(action)) {
			Integer cusId = (Integer) session.getAttribute("cusId");
//			 Date checkInTime = Date.valueOf(req.getParameter("checkInTime"));
//			 Date checkOutTime = Date.valueOf(req.getParameter("checkOutTime"));
			Integer roomId = Integer.valueOf(req.getParameter("roomId"));
			Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
			RoomStockServiceHibernate RSSH = new RoomStockServiceHibernate();
			
			
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate startDate = LocalDate.parse(Timestart, formatter);
	        LocalDate endDate = LocalDate.parse(Timeend, formatter);
	        long days = endDate.toEpochDay() - startDate.toEpochDay() ;  
	        
	        
	        
	        
	        
			if (cusId != null) {// 如果session有cus_id資料代表有人登入
				Integer product = ROSH.queryProduct(roomId, cusId, checkInTime, checkOutTime);
				Integer roomStock = RSSH.searchMinRoomStockByTime(roomId, checkInTime, checkOutTime);
				
				if (product == 1 && roomStock != null && roomStock != 0) {
					Integer cartId = Integer.valueOf(req.getParameter("cart_id"));
					RoomServiceHibernate RSH = new RoomServiceHibernate();
					PlanningServiceHibernate PSH = new PlanningServiceHibernate();// 創造出SERVICE
					Integer plan_id = PSH.getPlanId(cartId, cusId); // 得到1.他是誰 2.是哪台車
					Integer compId = RSH.getRoom(roomId).getCompId();
					// 跟room_ord 從room拿price

					BigDecimal totalPrice = RSH.getRoom(roomId).getPrice().multiply(new BigDecimal(days)).setScale(0, RoundingMode.HALF_UP);

					// comm = price *10%
					BigDecimal commission = totalPrice.multiply(new BigDecimal(0.1)).setScale(0, RoundingMode.HALF_UP);
					// profit = price - comm
					BigDecimal profit = totalPrice.subtract(commission);
					// people = 數量 (明翰提供) * room_type
					Integer RoomType = RSH.getRoom(roomId).getRoomType();
					Integer roomAmount = 1; // 固定給他1(預設，後續可以到結帳前面去更改數量)
					Integer people = roomAmount * RoomType;

					// check in out 時間由明翰搜尋的結果得知

					Integer newRoomOrder = ROSH.addFromShopping(compId, plan_id, roomId, cusId, roomAmount, totalPrice,
							commission, profit, people, checkInTime, checkOutTime, 0);

					String url = "/chu/shopping(hotel).jsp";
					res.sendRedirect(req.getContextPath() + url);
					return;
				} else {
					List<String> errorMessages = new ArrayList<String>();
					if (product != 1) {
						errorMessages.add("<i style = 'font-size :25px'>購物車已有此商品!</i>");
					}
					if (roomStock == null || roomStock == 0) {
						errorMessages.add("<i style = 'font-size :25px'>此商品已無庫存或者日期輸入錯誤!</i>");
					}

					req.setAttribute("errorMessages", errorMessages);
					returnForPage("/chu/shopping(hotel).jsp", res, req);
				}
			} else { // 導回登入
				session.setAttribute("location", req.getRequestURI()); // 如果沒登入先記錄現在的位置(網址)
				res.sendRedirect(req.getContextPath() + "/eric/signin.jsp");// 然後導回登入頁面(等到有login.jsp再改路徑)
				return;
			}

		}

//=========================購物車(飯店)新增===============================

//=========================購物車(飯店)刪除===============================

		if ("removeHotelOrder".equals(action)) {

			Integer roomOrdId = Integer.valueOf(req.getParameter("roomOrdId"));
			Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
			PlanningServiceHibernate PSH = new PlanningServiceHibernate();
			Integer carts = PSH.findCartByPlanning(ROSH.getRoomOrd(roomOrdId).getPlanId());

			ROSH.delete(roomOrdId);

			req.setAttribute("carts", carts);
			returnForPage("/chu/shopping(hotel).jsp", res, req);
			return;

		}
//=========================購物車(飯店)刪除===============================

//=========================購物車(飯店)更改車號===============================

		if ("changeHotelCart".equals(action)) {

			Integer changeCartId = Integer.valueOf(req.getParameter("changeCartId"));
			Integer roomOrdId = Integer.valueOf(req.getParameter("roomOrdId"));
			Integer cusId = (Integer) session.getAttribute("cusId");
			PlanningServiceHibernate PSH = new PlanningServiceHibernate();// 創造出SERVICE
			Integer planId = PSH.getPlanId(changeCartId, cusId); // 得到1.他是誰 2.是哪台車
			Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
			ROSH.updateCartNum(planId, roomOrdId);

			res.sendRedirect(req.getContextPath() + "/chu/shopping(hotel).jsp");
			return;

		}

//=========================購物車(飯店)更改車號===============================			

//=========================訂單頁面取消===============================

		if ("CancelTransaction".equals(action)) {
			res.sendRedirect(req.getContextPath() + "/chu/shopping(hotel).jsp");
			return;
		}
//=========================訂單頁面取消===============================

//=========================bookingList更改數量===============================

		if ("countAmount".equals(action)) {
			Integer roomOrdId = Integer.valueOf(req.getParameter("roomOrdIdPk"));// tripOrdId

			if (req.getParameter("roomAmount") != null) {
				Integer amount = Integer.valueOf(req.getParameter("roomAmount"));
				Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
				ROSH.updateAmount(amount, roomOrdId);// 更新數量
				req.setAttribute("roomOrdId", roomOrdId);

				returnForPage("/chu/bookingList(hotel).jsp", res, req);
			} else {
				String errorMessages = "親愛的客戶您好，目前此商品暫無庫存";
				req.setAttribute("errorMessages", errorMessages);
				returnForPage("/chu/shopping(hotel).jsp", res, req);
			}
		}
//=========================bookingList更改數量===============================

//=========================訂單頁面結帳===============================

		if ("ConnectToECPAY".equals(action)) {
			Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
			RoomServiceHibernate RSH = new RoomServiceHibernate();
			RoomStockServiceHibernate RSSH = new RoomStockServiceHibernate();
				ConnectToECPAYDTO dto = new ConnectToECPAYDTO();
				dto.setRoomOrdId(Integer.valueOf(req.getParameter("roomOrdId")));
				dto.setProfit(new BigDecimal(req.getParameter("profit")));
				dto.setCommission(new BigDecimal(req.getParameter("commission")));
				dto.setTotalPrice(new BigDecimal(req.getParameter("totalPrice")));
				dto.setRemark(req.getParameter("remark"));
				dto.setOrdTime(Timestamp.valueOf(LocalDateTime.now()));
				Integer roomAmount = ROSH.getRoomOrd(dto.getRoomOrdId()).getAmount();
				Integer roomType = RSH.getOneRoom(ROSH.getRoomOrd(dto.getRoomOrdId()).getRoomId()).getRoomType();
				dto.setPeople(roomAmount * roomType);
				Room_ord RO = ROSH.getRoomOrd(dto.getRoomOrdId());
				Integer roomStock = RSSH.searchMinRoomStockByTime( RO.getRoomId(), RO.getCheckInTime(), RO.getCheckOutTime());
				if( roomStock != 0) {
						// 交易後更新內容
						ROSH.updateStatusAndRemark(dto.getRemark(), dto.getRoomOrdId(), dto.getProfit(), dto.getCommission(),
								dto.getTotalPrice(), dto.getOrdTime(), dto.getPeople());
			
						// 更新庫存
						
						RSSH.updateRoomStock(RO.getRoomId(), RO.getAmount(), RO.getCheckInTime(), RO.getCheckOutTime());
						res.sendRedirect(req.getContextPath() + "/chu/payForSuccess.jsp");
						return;
			}else {
				String errorMessages = "親愛的客戶您好，目前此商品暫無庫存";
				req.setAttribute("errorMessages", errorMessages);
				returnForPage("/chu/shopping(hotel).jsp", res, req);
				return;
			}
		}

//=========================訂單頁面結帳===============================

//=========================購物車(飯店)結帳===============================
		if ("hotelcheckOut".equals(action)) {
			Integer roomOrderId = Integer.valueOf(req.getParameter("roomOrdId"));

			req.setAttribute("roomOrdId", roomOrderId);
			returnForPage("/chu/bookingList(hotel).jsp", res, req);

		}
//=========================購物車(飯店)結帳===============================

	}

	public static void returnForPage(String newUrl, HttpServletResponse res, HttpServletRequest req)
			throws ServletException, IOException {
		String url = newUrl;
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		return;
	}

}
