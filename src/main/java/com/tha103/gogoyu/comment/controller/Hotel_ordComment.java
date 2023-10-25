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

		req.setCharacterEncoding("UTF-8"); // 接收請求參數的編碼設定
		HttpSession session = req.getSession();
		String leaveAMessageButton = req.getParameter("submitButtonClicked");
		
		Integer roomOrdId = Integer.valueOf(req.getParameter("roomOrdId"));  //由前端jsp 147取得
		Integer roomId = Integer.valueOf(req.getParameter("roomId"));//由前端jsp 148取得
		
		
		if (leaveAMessageButton.equals("true")) { // 按下發表評論後
			Room_ordServiceHibernate ROSH1 = new Room_ordServiceHibernate();
			Room_ord roomOrdObj = ROSH1.getBycusID(roomOrdId);
			if (roomOrdObj.getComments() == null) {

				String comment = req.getParameter("comment");
				Integer score = Integer.valueOf(req.getParameter("score"));
				Timestamp commentsTime = Timestamp.valueOf(LocalDateTime.now());

				Room_ordServiceHibernate ROSH2 = new Room_ordServiceHibernate();
				Integer updateOrd = ROSH2.updateCommentAndScore(roomOrdId, score, comment, commentsTime);

				
				res.sendRedirect(req.getContextPath() + "/chu/commentArea(hotel).jsp");//跳到

			} else {
				req.setAttribute("errorMessage", "親愛的會員您好，您已經評論過囉~再次輸入將會覆蓋之前的回覆內容!");
				String url = "/chu/AfterBookingComment(hotel).jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}

		}
	}
}