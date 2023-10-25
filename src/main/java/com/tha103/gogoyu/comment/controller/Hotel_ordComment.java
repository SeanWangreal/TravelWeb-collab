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

		String leaveAMessageButton = req.getParameter("submitButtonClicked");
		Integer roomOrd = 11; // ���C�Ӱ����(�᭱�n�έq���T���� �n��jsp�P�B)

		
		if (leaveAMessageButton.equals("true")) { // ���U�o����׫�
			Room_ordServiceHibernate ROSH1 = new Room_ordServiceHibernate();
//			if (TOSH1.getOneTrip(tripOrd).getComments() == null) {

				String comment = req.getParameter("comment"); 
				Integer score = Integer.valueOf(req.getParameter("score"));
				Timestamp commentsTime = Timestamp.valueOf(LocalDateTime.now());
				Room_ordServiceHibernate TOSH2 = new Room_ordServiceHibernate();
				Integer updateOrd = TOSH2.updateCommentAndScore(roomOrd, score, comment, commentsTime);
				
				String url = "/chu/commentArea(hotel).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);	
				
			} 
//			else {
//				//�N��w�g�����׹L�F(���~����)
//			}
		
		
		
	}
}