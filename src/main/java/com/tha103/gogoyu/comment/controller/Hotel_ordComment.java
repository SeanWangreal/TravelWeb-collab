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

import com.tha103.gogoyu.room_ord.model.Room_ord;
import com.tha103.gogoyu.room_ord.model.Room_ordServiceHibernate;
import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;
import com.tha103.gogoyu.room.model.RoomServiceHibernate;
import java.sql.Timestamp;

@WebServlet("/Hotel_ordCommentServlet")
public class Hotel_ordComment extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); 
	
		
		
		String action = req.getParameter("action");
		
		
		if ("goToComment".equals(action)) {
			Integer roomOrdId = Integer.valueOf(req.getParameter("roomOrdId")); 
			Integer roomId = Integer.valueOf(req.getParameter("roomId"));

			req.setAttribute("roomOrdId", roomOrdId);
			req.setAttribute("roomId", roomId);
			String url = "/chu/AfterBookingComment(hotel).jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, res);
			return;
		}

		
		if ("leaveComment".equals(action)) {
			res.sendRedirect(req.getContextPath() + "/chu/bookedList(hotel).jsp");
			return;
		}
		
		
	
		
		String leaveAMessageButton = req.getParameter("submitButtonClicked");
		
		
		
		if (leaveAMessageButton.equals("true")) { 
			Integer roomOrdId = Integer.valueOf(req.getParameter("roomOrdId"));  
			Room_ordServiceHibernate ROSH1 = new Room_ordServiceHibernate();
			Room_ord roomOrdObj = ROSH1.getBycusID(roomOrdId);
			if (roomOrdObj.getComments() == null) {

				String comment = req.getParameter("comment");
				Integer score = Integer.valueOf(req.getParameter("score"));
				Timestamp commentsTime = Timestamp.valueOf(LocalDateTime.now());

				Room_ordServiceHibernate ROSH2 = new Room_ordServiceHibernate();
				Integer updateOrd = ROSH2.updateCommentAndScore(roomOrdId, score, comment, commentsTime);

				
				req.setAttribute("errorMessage", "評論完成!");
				String url = "/chu/bookedList(hotel).jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);

			} else {
				req.setAttribute("roomOrdId", roomOrdId); 
				req.setAttribute("errorMessage", "親愛的會員您好，您已經評論過囉~");
				String url = "/chu/AfterBookingComment(hotel).jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}

		}
	}
}