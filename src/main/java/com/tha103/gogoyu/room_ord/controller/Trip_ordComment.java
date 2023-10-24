package com.tha103.gogoyu.room_ord.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tha103.gogoyu.trip_ord.model.Trip_ordServiceHibernate;
import java.sql.Timestamp;

@WebServlet("/Trip_ordComment")
public class Trip_ordComment extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // �����ШD�Ѽƪ��s�X�]�w

		String leaveAMessageButton = req.getParameter("submitButtonClicked");
		Integer tripOrd = 3; // ���C�Ӱ����(�᭱�n�έq���T���� �n��jsp�P�B)

		
		if (leaveAMessageButton.equals("true")) { // ���U�o����׫�

			Trip_ordServiceHibernate TOSH1 = new Trip_ordServiceHibernate();
//			if (TOSH1.getOneTrip(tripOrd).getComments() == null) {

				String comment = req.getParameter("comment"); 
				Integer score = Integer.valueOf(req.getParameter("score"));
				Timestamp commentsTime = Timestamp.valueOf(LocalDateTime.now());
				Trip_ordServiceHibernate TOSH2 = new Trip_ordServiceHibernate();
				Integer updateOrd = TOSH2.updateCommentAndScore(tripOrd, score, comment, commentsTime);
				
				String url = "/chu/commentArea(trip).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);	
				
			} 
//			else {
//				//�N��w�g�����׹L�F(���~����)
//			}
		
		
		
	}
}