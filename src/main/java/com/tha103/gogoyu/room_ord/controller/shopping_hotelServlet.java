package com.tha103.gogoyu.room_ord.controller;

import java.io.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.tha103.gogoyu.room_ord.model.ShoppingCartHotel;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.planning.model.*;
import com.tha103.gogoyu.room_ord.model.*;
import com.tha103.gogoyu.room.model.*;
import com.tha103.gogoyu.trip.model.*;
import com.tha103.gogoyu.trip_ord.model.*;
import com.tha103.gogoyu.company.model.*;
import java.text.SimpleDateFormat;

@WebServlet("/shopping_hotelServlet")
public class shopping_hotelServlet extends HttpServlet {

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
		
		//roomId最後要放回去房間新增裡的if!!
		Integer roomId = (Integer) session.getAttribute("room_id"); // 抓房間id  最後會用getparameter(name value)
	
		//roomAmount最後要放回去房間新增裡的if!!
		Integer roomAmount= 1;//Integer.valueOf(req.getParameter("amount")); //抓數量amount(name value)
		
		String Timestart= "2023-10-21"; // 示例日期字符串
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date checkInTime = Date.valueOf(Timestart);
		
		
		String Timeend= "2023-10-22"; // 示例日期字符串
		Date checkOutTime =Date.valueOf(Timeend);
		
		
		
		String action = req.getParameter("action");
		
		
		
		
		
//=========================購物車(飯店)新增===============================
		if ("room_goShopping".equals(action)) { 
			
			if (cusId != null) {// 如果session有cus_id資料代表有人登入
				Integer cartId = Integer.valueOf(req.getParameter("cart_id")); //透過js給值抓購物車號(name value)
				RoomServiceHibernate RSH = new RoomServiceHibernate();
				Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
				PlanningServiceHibernate PSH = new PlanningServiceHibernate();// 創造出SERVICE
				
				Integer plan_id = PSH.getPlanId(cusId, cartId); //得到1.他是誰   2.是哪台車 
				
				
			//跟room_ord 從room拿price
				
				BigDecimal totalPrice = RSH.getRoom(roomId).getPrice().setScale(0, RoundingMode.HALF_UP);
			System.out.println(totalPrice);
				//comm = price *10%
				BigDecimal commission =  totalPrice.multiply(new BigDecimal(0.1)).setScale(0, RoundingMode.HALF_UP);
			//profit = price - comm
				BigDecimal profit = totalPrice.subtract(commission);
			//people = 數量 (明翰提供) * room_type
				Integer RoomType = RSH.getRoom(roomId).getRoomType();
				Integer people = roomAmount * RoomType;
				
			// check in  out 時間由明翰搜尋的結果得知
				
				Integer newRoomOrder = ROSH.addFromShopping(plan_id,roomId,cusId,roomAmount,totalPrice,commission, profit , people ,checkInTime, checkOutTime,1);
				
				
				String url = "/chu/shopping(hotel).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	

			} else { // 導回登入
				session.setAttribute("location", req.getRequestURI()); // 如果沒登入先記錄現在的位置(網址)
				res.sendRedirect(req.getContextPath() + "/chu/bookingList(trip).jsp");// 然後導回登入頁面(等到有login.jsp再改路徑)
				System.out.println(cusId);
			}

		}
		
//=========================購物車(飯店)新增===============================
		
		
//=========================購物車(飯店)刪除===============================
		
		if("removeHotelOrder".equals(action)) { 
			
			Integer roomOrdId =Integer.valueOf(req.getParameter("roomOrdId"));
			Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
			ROSH.delete(roomOrdId);
	
			
			String url = "/chu/shopping(hotel).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);	

		}
//=========================購物車(飯店)刪除===============================
	
		
	
		
//=========================購物車(飯店)結帳===============================
		if("hotelcheckOut".equals(action)) { 
			Integer roomOrderId  = Integer.valueOf(req.getParameter("roomOrdId"));
			Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
			RoomServiceHibernate RSH = new RoomServiceHibernate();
			CompanyService CSH = new CompanyService();
			
			Room_ord RoomOrd =ROSH.getRoomOrd(roomOrderId);//取得該訂單pk的物件
			
			Date checkIn =RoomOrd.getCheckInTime();
			Date checkOut =RoomOrd.getCheckOutTime();
			Integer RoomId = RoomOrd.getRoomId();
			Integer compId = RSH.getOneRoom(RoomId).getCompId();
			
			String compName=CSH.getComp(compId).getCompName();//抓房名
			String principalName=CSH.getComp(compId).getPrincipalName();//抓聯絡人
			String principalPhone=CSH.getComp(compId).getPrincipalPhone();//抓聯絡人電話
			Integer profit = RoomOrd.getProfit().setScale(3, RoundingMode.HALF_UP).intValue();
			Integer commission = RoomOrd.getCommission().setScale(3, RoundingMode.HALF_UP).intValue();
			Integer totalPrice= RoomOrd.getTotalPrice().setScale(3, RoundingMode.HALF_UP).intValue();
			String roomName = RSH.getRoom(RoomId).getRoomName();
			Integer roomTypeId=RSH.getRoom(RoomId).getRoomType();
			String roomType  = null ;//抓房型
			switch (roomTypeId) {
			case 1 :
				roomType = "單人房";
			break;
			case 2 :
				roomType = "雙人房";
			break;
			case 3 :
				roomType = "三人房";
			break;
			case 4 :
				roomType = "四人房";
			break;
			}
			
			
			Room_ordList ROL = new Room_ordList();
			ROL.setRoomName(roomName);
			ROL.setRoomOrdId(RoomOrd.getRoomOrdId());
			ROL.setCusId(RoomOrd.getCusId());
			ROL.setCompName(compName);
			ROL.setRoomType(roomType);
			ROL.setAmount(RoomOrd.getAmount());
			ROL.setPrincipalName(principalName);
			ROL.setPrincipalPhone(principalPhone);
			ROL.setStartTime(checkIn); 
			ROL.setEndTime(checkOut);
			ROL.setProfit(profit);
			ROL.setCommission(commission);
			ROL.setTotalPrice(totalPrice);
			
			
			req.setAttribute( "RoomOrd" , ROL);
			String url = "/chu/bookingList(hotel).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);	
			
		}		
//=========================購物車(飯店)結帳===============================
		

	
	}
}