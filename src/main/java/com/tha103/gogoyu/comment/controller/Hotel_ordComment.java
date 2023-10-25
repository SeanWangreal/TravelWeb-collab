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
import com.tha103.gogoyu.room.model.RoomServiceHibernate;
import java.sql.Timestamp;

@WebServlet("/Hotel_ordComment")
public class Hotel_ordComment extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // �����ШD�Ѽƪ��s�X�]�w
		HttpSession session = req.getSession();
		String leaveAMessageButton = req.getParameter("submitButtonClicked");
		
		Integer roomOrdId = Integer.valueOf(req.getParameter("roomOrdId"));  //�ѫe��jsp 147���o
		Integer roomId = Integer.valueOf(req.getParameter("roomId"));//�ѫe��jsp 148���o
		
		
		if (leaveAMessageButton.equals("true")) { // ���U�o����׫�
			Room_ordServiceHibernate ROSH1 = new Room_ordServiceHibernate();
			Room_ord roomOrdObj = ROSH1.getBycusID(roomOrdId);
			if (roomOrdObj.getComments() == null) {

				String comment = req.getParameter("comment");
				Integer score = Integer.valueOf(req.getParameter("score"));
				Timestamp commentsTime = Timestamp.valueOf(LocalDateTime.now());

				Room_ordServiceHibernate ROSH2 = new Room_ordServiceHibernate();
				Integer updateOrd = ROSH2.updateCommentAndScore(roomOrdId, score, comment, commentsTime);

				
				res.sendRedirect(req.getContextPath() + "/chu/commentArea(hotel).jsp");//����

			} else {
				req.setAttribute("errorMessage", "�˷R���|���z�n�A�z�w�g���׹L�o~�A����J�N�|�л\���e���^�Ф��e!");
				String url = "/chu/AfterBookingComment(hotel).jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}

		}
	}
}