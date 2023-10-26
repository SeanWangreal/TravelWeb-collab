package com.tha103.gogoyu.comment.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tha103.gogoyu.trip_ord.model.Trip_ord;
import com.tha103.gogoyu.trip_ord.model.Trip_ordServiceHibernate;
import java.sql.Timestamp;

@WebServlet("/Trip_ordCommentServlet")
public class Trip_ordComment extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // �����ШD�Ѽƪ��s�X�]�w
		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		if ("goToComment".equals(action)) {
			Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdId")); // �ѫe��jsp 147���o
			Integer tripId = Integer.valueOf(req.getParameter("tripId"));// �ѫe��jsp 148���o

			// �N��ӭȶǾɨ�AfterBookingComment.jsp,�� 145. 146 set
			req.setAttribute("tripOrdId", tripOrdId);
			req.setAttribute("tripId", tripId);
			String url = "/chu/AfterBookingComment(trip).jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, res);
			return;
		}

		if ("leaveComment".equals(action)) {
			res.sendRedirect(req.getContextPath() + "/chu/bookedList(trip).jsp");
			return;
		}

		
		
		
		
		// ��AfterBookingComment.jsp 145.146�汵����
		String leaveAMessageButton = req.getParameter("submitButtonClicked");

		if (leaveAMessageButton.equals("true")) { // ���U�o����׫�

			    Trip_ordServiceHibernate TOSH1 = new Trip_ordServiceHibernate();
			
				Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdId"));
				Trip_ord tripObj = TOSH1.getOneTrip(tripOrdId);

				 if (tripObj.getComments() == null) {
					String comment = req.getParameter("comment");
					Integer score = Integer.valueOf(req.getParameter("score"));
					Timestamp commentsTime = Timestamp.valueOf(LocalDateTime.now());
					Trip_ordServiceHibernate TOSH2 = new Trip_ordServiceHibernate();
					Integer updateOrd = TOSH2.updateCommentAndScore(tripOrdId, score, comment, commentsTime);

					res.sendRedirect(req.getContextPath() + "/chu/bookedList(trip).jsp");// ����
				}
				 else {
				req.setAttribute("tripOrdId", tripOrdId); 
				req.setAttribute("errorMessage", "�˷R���|���z�n�A�z�w�g���׹L�o");
				String url = "/chu/AfterBookingComment(trip).jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}
		}

	}
}