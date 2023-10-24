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

		session.setAttribute("trip_id", 1);
		session.setAttribute("cus_id", 1);
		session.setAttribute("room_id", 1);

		Integer cusId = (Integer) session.getAttribute("cus_id"); // ��|��id

		// tripId�̫�n��^�h��{�s�W�̪�if!!
		Integer tripId = (Integer) session.getAttribute("trip_id"); // ���{ id �̫�getparameter(name value)

		// tripAmount�̫�n��^�h��{�s�W�̪�if!!
		Integer tripAmount = 1;// Integer.valueOf(req.getParameter("amount")); //��ƶqamount(name value)

		String action = req.getParameter("action");
		
		
//=========================�ʪ���(����)�s�W===============================
		if ("trip_goShopping".equals(action)) { // trip�϶��[�J�ʪ�����(�s�W)

			if (cusId != null) {// �p�Gsession��cus_id��ƥN���H�n�J
				Integer cartId = Integer.valueOf(req.getParameter("cart_id")); // �z�Ljs���ȧ��ʪ�����(name value)

				PlanningServiceHibernate PSH = new PlanningServiceHibernate();// �гy�Xplanning SERVICE
				Integer planId = PSH.getPlanId(cusId, cartId); // �o��1.�L�O�� 2.�O���x��
				
				// �z�L���o��trip_id�h���Ӫ����ݩ�

				Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
				TripServiceHibernate TSH = new TripServiceHibernate();
				// �令dateformat
				Date startTime = TSH.getOneTrip(tripId).getStartTime(); // �z�Ltripservicehibernate���o(������)
				Date endTime = TSH.getOneTrip(tripId).getEndTime(); // �z�Ltripservicehibernate���o(������)

				BigDecimal totalPrice = TSH.getOneTrip(tripId).getPrice();
//			//comm = price *10%
				BigDecimal commission = totalPrice.multiply(new BigDecimal(0.1));
//			//profit = price - comm
				BigDecimal profit = totalPrice.subtract(commission);

				TOSH.addFromShopping(tripId, planId, cusId, tripAmount, totalPrice, commission, profit, 0);

				String url = "/chu/shopping(hotel).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

			} else { // �ɦ^�n�J
				session.setAttribute("location", req.getRequestURI()); // �p�G�S�n�J���O���{�b����m(���})
				res.sendRedirect(req.getContextPath() + "/chu/bookingList(trip).jsp");// �M��ɦ^�n�J����(���즳login.jsp�A����|)

			}

		}
//=========================�ʪ���(�Ȧ�)�s�W===============================
		
		
//=========================�ʪ���(�Ȧ�)�R��===============================
	
if("removeTripOrder".equals(action)) { 
			
			Integer roomOrdId =Integer.valueOf(req.getParameter("TripOrdId"));
			Trip_ordServiceHibernate ROSH = new Trip_ordServiceHibernate();
			ROSH.deleteTrip(roomOrdId);
	
			
			String url = "/chu/shopping(hotel).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);	

		}
//=========================�ʪ���(����)�R��===============================
//=========================�ʪ���(����)���b===============================
		if("TripCheckOut".equals(action)) { 
			//���oTripOrderId
			Integer TripOrderId  = Integer.valueOf(req.getParameter("TripOrdId"));
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			TripServiceHibernate TSH = new TripServiceHibernate();
			CompanyService CSH = new CompanyService();
			
			Trip_ord trip_ord =TOSH.getOneTrip(TripOrderId); //���otrip_ord����
			Integer TripId = trip_ord.getTripId();//���otripId
			Trip trip  = TSH.getTrip(TripId);//���otrip����
			
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
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);	
			
		}		
//=========================�ʪ���(����)���b===============================
	
		if("CancelTransaction".equals(action)) {
			String url = "/chu/shopping(hotel).jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);	
		}
		
		
		
		
	}
}