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
		req.setCharacterEncoding("UTF-8"); // �����ШD�Ѽƪ��s�X�]�w

		HttpSession session = req.getSession();

		String action = req.getParameter("action");
		
		
//=========================shoppingcart�s�W===============================
		if ("trip_goShopping".equals(action)) { // trip�϶��[�J�ʪ�����(�s�W)
			Integer cusId = (Integer) session.getAttribute("cusId"); // ��|��id
			if (cusId != null) {// �p�Gsession��cus_id��ƥN���H�n�J
				Integer cartId = Integer.valueOf(req.getParameter("cart_id")); 
				Integer tripId = Integer.valueOf(req.getParameter("tripId"));
				PlanningServiceHibernate PSH = new PlanningServiceHibernate();// �гy�Xplanning SERVICE
				Integer planId = PSH.getPlanId(cusId, cartId); // �o��1.�L�O�� 2.�O���x��
			
				// �z�L���o��trip_id�h���Ӫ����ݩ�
				Integer tripAmount= 1; //�T�w���L1(�w�]�A����i�H�쵲�b�e���h���ƶq)
				Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
				TripServiceHibernate TSH = new TripServiceHibernate();
			    Integer compId = TSH.getTrip(tripId).getCompId();
				// �令dateformat
				Date startTime = TSH.getOneTrip(tripId).getStartTime(); // �z�Ltripservicehibernate���o(������)
				Date endTime = TSH.getOneTrip(tripId).getEndTime(); // �z�Ltripservicehibernate���o(������)

				BigDecimal totalPrice = TSH.getOneTrip(tripId).getPrice();
//			//comm = price *10%
				BigDecimal commission = totalPrice.multiply(new BigDecimal(0.1));
//			//profit = price - comm
				BigDecimal profit = totalPrice.subtract(commission);

				TOSH.addFromShopping(compId,tripId, planId, cusId, tripAmount, totalPrice, commission, profit, startTime,endTime ,0);

				String url = "/chu/shopping(hotel).jsp";
				res.sendRedirect(req.getContextPath() + url);
				return;

			} else { // �ɦ^�n�J
				session.setAttribute("location", req.getRequestURI()); // �p�G�S�n�J���O���{�b����m(���})
				res.sendRedirect(req.getContextPath() +"/eric/signin.jsp");// �M��ɦ^�n�J����(���즳login.jsp�A����|)

			}

		}
//=========================shoppingcart�s�W===============================
		
		
//=========================shoppingcart�R��===============================
	
if("removeTripOrder".equals(action)) { 
			
			Integer roomOrdId =Integer.valueOf(req.getParameter("TripOrdId"));
			Trip_ordServiceHibernate ROSH = new Trip_ordServiceHibernate();
			ROSH.deleteTrip(roomOrdId);
	
			String url = "/chu/shopping(hotel).jsp";
			res.sendRedirect(req.getContextPath() + url);
			return;
		}
//=========================shoppingcart�R��===============================





//=========================bookingList���ƶq===============================

if("countAmount".equals(action)) {
	Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdIdPk"));//tripOrdId
		
	Integer amount =Integer.valueOf(req.getParameter("tripAmount"));
	Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
	TOSH.updateAmountAndPrice(amount ,tripOrdId);//��s�ƶq
	req.setAttribute("tripOrdId", tripOrdId);
	String url = "/chu/bookingList(trip).jsp";
	RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
	successView.forward(req, res);	
	
}
//=========================bookingList���ƶq===============================

//=========================bookingList�i�Jpayforsuccess===============================

		if("ConnectToECPAY".equals(action)) {
			
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			Integer tripOrdId =Integer.valueOf(req.getParameter("tripOrdId"));
			BigDecimal profit = new BigDecimal(req.getParameter("profit"));
			BigDecimal commission = new BigDecimal(req.getParameter("commission"));
			BigDecimal  totalPrice  = new BigDecimal(req.getParameter("totalPrice"));
			String remark = req.getParameter("remark");
			
			System.out.println(profit);
			TOSH.updateStatusAndRemark(remark , tripOrdId , profit , commission , totalPrice);
			res.sendRedirect(req.getContextPath() + "/chu/payForSuccess.jsp");
			return;
			
		}
//=========================bookingList�i�Jpayforsuccess===============================










//=========================�i�JbookingList===============================
		if("TripCheckOut".equals(action)) { 
			//���oTripOrderId
			Integer tripOrderId  = Integer.valueOf(req.getParameter("TripOrdId"));
			

			req.setAttribute("tripOrdId",tripOrderId);
			String url = "/chu/bookingList(trip).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);	
			
		}		
//=========================�i�JbookingList===============================
	
		if("CancelTransaction".equals(action)) {
			String url = "/chu/shopping(hotel).jsp";
			res.sendRedirect(req.getContextPath() + url);
			return;
		}
		
		

	}
}