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
import java.util.ArrayList;
import java.util.List;

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
			Integer cartId = Integer.valueOf(req.getParameter("cart_id"));
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			TripServiceHibernate THS = new TripServiceHibernate();
			Integer tripId = Integer.valueOf(req.getParameter("tripId"));
			Integer tripCheckAmount = THS.getOneTrip(tripId).getAmount();
			if (cusId != null) {// �p�Gsession��cus_id��ƥN���H�n�J
				Integer product = TOSH.queryProduct(cusId, tripId);

				if (product == 1 & tripCheckAmount > 0) {
					PlanningServiceHibernate PSH = new PlanningServiceHibernate();// �гy�Xplanning SERVICE
					Integer planId = PSH.getPlanId(cartId, cusId); // �o��1.�L�O�� 2.�O���x��
					// �z�L���o��trip_id�h���Ӫ����ݩ�
					Integer tripAmount = 1; // �T�w���L1(�w�]�A����i�H�쵲�b�e���h���ƶq)
					TripServiceHibernate TSH = new TripServiceHibernate();
					Integer compId = TSH.getTrip(tripId).getCompId();
					// �令dateformat
					Date startTime = TSH.getOneTrip(tripId).getStartTime();
					Date endTime = TSH.getOneTrip(tripId).getEndTime();

					BigDecimal totalPrice = TSH.getOneTrip(tripId).getPrice();
					// //comm = price *10%
					BigDecimal commission = totalPrice.multiply(new BigDecimal(0.1));
					// //profit = price - comm
					BigDecimal profit = totalPrice.subtract(commission);

					TOSH.addFromShopping(compId, tripId, planId, cusId, tripAmount, totalPrice, commission, profit,
							startTime, endTime, 0);

					String url = "/chu/shopping(hotel).jsp";
					res.sendRedirect(req.getContextPath() + url);
					return;

				} else {
					List<String> errorMessages = new ArrayList<String>();
					if (product != 1) {
						errorMessages.add("<i style = 'font-size :30px'>�ʪ����w�����ӫ~!</i>");
					}
					if (tripCheckAmount == 0) {
						errorMessages.add("<i style = 'font-size :30px'>���ӫ~�w�L�w�s�Ϊ̤����J���~!</i>");						
					}
					req.setAttribute("errorMessages", errorMessages);
					returnForPage("/chu/shopping(hotel).jsp", res, req);
//					returnForPage("/sean/SearchServlet.java?action=addTripCarErrorMsg&tripId="+tripId, res, req);
				}
			} else { // �ɦ^�n�J
				session.setAttribute("location", req.getRequestURI()); // �p�G�S�n�J���O���{�b����m(���})
				res.sendRedirect(req.getContextPath() + "/eric/signin.jsp");// �M��ɦ^�n�J����(���즳login.jsp�A����|)

			}

		}
//=========================shoppingcart�s�W===============================

//=========================shoppingcart�R��===============================

		if ("removeTripOrder".equals(action)) {

			Integer tripOrdId = Integer.valueOf(req.getParameter("TripOrdId"));
			Trip_ordServiceHibernate ROSH = new Trip_ordServiceHibernate();
			ROSH.deleteTrip(tripOrdId);

			String url = "/chu/shopping(hotel).jsp";
			res.sendRedirect(req.getContextPath() + url);
			return;
		}
//=========================shoppingcart�R��===============================

//=========================bookingList���ƶq===============================

		if ("countAmount".equals(action)) {
			Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdIdPk"));// tripOrdId

			if (req.getParameter("tripAmount") != null) {
				Integer amount = Integer.valueOf(req.getParameter("tripAmount"));
				Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
				TOSH.updateAmountAndPrice(amount, tripOrdId);// ��s�ƶq
				req.setAttribute("tripOrdId", tripOrdId);
				String url = "/chu/bookingList(trip).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
			} else {
				String errorMessages = "�˷R���Ȥ�z�n�A�ثe���ӫ~�ȵL�w�s";
				req.setAttribute("errorMessages", errorMessages);
				returnForPage("/chu/shopping(hotel).jsp", res, req);
			}
		}
//=========================bookingList���ƶq===============================

//=========================�ʪ���(����)��郞��===============================

		if ("changeTripCart".equals(action)) {

			Integer changeCartId = Integer.valueOf(req.getParameter("changeCartId"));
			Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdId"));
			Integer cusId = (Integer) session.getAttribute("cusId");
			PlanningServiceHibernate PSH = new PlanningServiceHibernate();// �гy�XSERVICE
			Integer planId = PSH.getPlanId(changeCartId, cusId); // �o��1.�L�O�� 2.�O���x��
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			TOSH.updateCartNum(planId, tripOrdId);

			res.sendRedirect(req.getContextPath() + "/chu/shopping(hotel).jsp");
			return;

		}

//=========================�ʪ���(����)��郞��===============================			

//=========================bookingList�i�Jpayforsuccess===============================

		if ("ConnectToECPAY".equals(action)) {
			Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
			TripServiceHibernate TSH = new TripServiceHibernate();
			Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdId"));
			Integer tripId = TOSH.getOneTrip(tripOrdId).getTripId();
			Integer tripAmount = TOSH.getOneTrip(tripOrdId).getAmount(); // �q���ʶR�ƶq
			Integer tripStore = TSH.getTrip(tripId).getAmount(); // �w�s
			Integer totalStore = tripStore - tripAmount;

			if (totalStore >= 0) {
				TSH.updateAmount(totalStore, tripId);
				BigDecimal profit = new BigDecimal(req.getParameter("profit"));
				BigDecimal commission = new BigDecimal(req.getParameter("commission"));
				BigDecimal totalPrice = new BigDecimal(req.getParameter("totalPrice"));
				String remark = req.getParameter("remark");
				Timestamp ordTime = Timestamp.valueOf(LocalDateTime.now());
				TOSH.updateStatusAndRemark(remark, tripOrdId, profit, commission, totalPrice, ordTime);
				res.sendRedirect(req.getContextPath() + "/chu/payForSuccess.jsp");
				return;
			} else {
				String errorMessages = "�˷R���Ȥ�z�n�A�ثe���ӫ~�ȵL�w�s";
				req.setAttribute("errorMessages", errorMessages);
				returnForPage("/chu/shopping(hotel).jsp", res, req);
				return;
			}

		}
//=========================bookingList�i�Jpayforsuccess===============================

//=========================�i�JbookingList===============================
		if ("TripCheckOut".equals(action)) {
			// ���oTripOrderId

			Integer tripOrderId = Integer.valueOf(req.getParameter("TripOrdId"));

			req.setAttribute("tripOrdId", tripOrderId);
			returnForPage("/chu/bookingList(trip).jsp", res, req);

		}
//=========================�i�JbookingList===============================

		if ("CancelTransaction".equals(action)) {
			String url = "/chu/shopping(hotel).jsp";
			res.sendRedirect(req.getContextPath() + url);
			return;
		}

	}

	public static void returnForPage(String newUrl, HttpServletResponse res, HttpServletRequest req)
			throws ServletException, IOException {
		String url = newUrl;
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		return;
	}
}