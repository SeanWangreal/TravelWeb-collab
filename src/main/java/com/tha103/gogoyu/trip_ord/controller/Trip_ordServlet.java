package com.tha103.gogoyu.trip_ord.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.gogoyu.trip_ord.model.Trip_ord;
import com.tha103.gogoyu.trip_ord.model.Trip_ordService;

public class Trip_ordServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("empno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer empno = null;
				try {
					empno = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Trip_ordService empSvc = new Trip_ordService();
				Trip_ord empVO = empSvc.getOneEmp(empno);
				if (empVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer empno = Integer.valueOf(req.getParameter("empno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Trip_ordService empSvc = new Trip_ordService();
				Trip_ord empVO = empSvc.getOneEmp(empno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdId").trim());
								
				//String ename = req.getParameter("ename");
				//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				//				if (ename == null || ename.trim().length() == 0) {
				//					errorMsgs.add("���u�m�W: �ФŪť�");
				//				} else if(!ename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				//					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				//	            }
				
				Integer tripId = Integer.valueOf(req.getParameter("tripId").trim());
				
				Integer planId = Integer.valueOf(req.getParameter("planId").trim());
				
				Integer cusId = Integer.valueOf(req.getParameter("cusId").trim());
							
				Integer amount = null;
				try {
					amount = Integer.valueOf(req.getParameter("amount").trim());
				} catch (NumberFormatException e) {
					amount = 0;
					errorMsgs.add("�ƶq�ж�Ʀr.");
				}
				
				BigDecimal totalPrice = null;
				try {
					totalPrice = new BigDecimal(String.valueOf(req.getParameter("totalPrice").trim()));
				} catch (NumberFormatException e) {
					totalPrice = new BigDecimal(0.0);
					errorMsgs.add("�~���ж�Ʀr.");
				}
				
				BigDecimal commission = null;
				try {
					commission = new BigDecimal(String.valueOf(req.getParameter("commission").trim()));
				} catch (NumberFormatException e) {
					commission = new BigDecimal(0.0);
					errorMsgs.add("�Ī��ж�Ʀr.");
				}
				
				Integer ordStatus = Integer.valueOf(req.getParameter("ordStatus").trim());
				
				java.sql.Timestamp ordTime = null;
				try {
					ordTime = java.sql.Timestamp.valueOf(req.getParameter("ordTime").trim());
				} catch (IllegalArgumentException e) {
					ordTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�U�q�ɶ�!");
				}

				String remark = req.getParameter("remark").trim();
				
				Integer score = null;
				try {
					score = Integer.valueOf(req.getParameter("score").trim());
				} catch (NumberFormatException e) {
					score = 0;
					errorMsgs.add("���ƽж�Ʀr.");
				}

				String comments = req.getParameter("comments").trim();
				
				java.sql.Timestamp commentsTime = null;
				try {
					commentsTime = java.sql.Timestamp.valueOf(req.getParameter("commentsTime").trim());
				} catch (IllegalArgumentException e) {
					commentsTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�U�q�ɶ�!");
				}
				
				Trip_ord tripOrd = new Trip_ord();
				
				tripOrd.setTripOrdId(tripOrdId);
				tripOrd.setTripId(tripId);
				tripOrd.setPlanId(planId);
				tripOrd.setCusId(cusId);
				tripOrd.setAmount(amount);
				tripOrd.setTotalPrice(totalPrice);
				tripOrd.setCommission(commission);
				tripOrd.setOrdStatus(ordStatus);
				tripOrd.setOrdTime(ordTime);
				tripOrd.setRemark(remark);
				tripOrd.setScore(score);
				tripOrd.setComments(comments);
				tripOrd.setCommentsTime(commentsTime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("empVO", tripOrd); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Trip_ordService empSvc = new Trip_ordService();
				tripOrd = empSvc.updateEmp(tripId, planId, cusId, amount, totalPrice,commission, ordStatus,
						ordTime,remark,score,comments,commentsTime,tripOrdId);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("empVO", tripOrd); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
			//String ename = req.getParameter("ename");
			//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			//				if (ename == null || ename.trim().length() == 0) {
			//					errorMsgs.add("���u�m�W: �ФŪť�");
			//				} else if(!ename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
			//					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			//	            }
			
			Integer tripId = Integer.valueOf(req.getParameter("tripId").trim());
			
			Integer planId = Integer.valueOf(req.getParameter("planId").trim());
			
			Integer cusId = Integer.valueOf(req.getParameter("cusId").trim());
						
			Integer amount = null;
			try {
				amount = Integer.valueOf(req.getParameter("amount").trim());
			} catch (NumberFormatException e) {
				amount = 0;
				errorMsgs.add("�ƶq�ж�Ʀr.");
			}
			
			BigDecimal totalPrice = null;
			try {
				totalPrice = new BigDecimal(String.valueOf(req.getParameter("totalPrice").trim()));
			} catch (NumberFormatException e) {
				totalPrice = new BigDecimal(0.0);
				errorMsgs.add("�~���ж�Ʀr.");
			}
			
			BigDecimal commission = null;
			try {
				commission = new BigDecimal(String.valueOf(req.getParameter("commission").trim()));
			} catch (NumberFormatException e) {
				commission = new BigDecimal(0.0);
				errorMsgs.add("�Ī��ж�Ʀr.");
			}
			
			Integer ordStatus = Integer.valueOf(req.getParameter("ordStatus").trim());
			
			java.sql.Timestamp ordTime = null;
			try {
				ordTime = java.sql.Timestamp.valueOf(req.getParameter("ordTime").trim());
			} catch (IllegalArgumentException e) {
				ordTime=new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J�U�q�ɶ�!");
			}

			String remark = req.getParameter("remark").trim();
			
			Integer score = null;
			try {
				score = Integer.valueOf(req.getParameter("score").trim());
			} catch (NumberFormatException e) {
				score = 0;
				errorMsgs.add("���ƽж�Ʀr.");
			}

			String comments = req.getParameter("comments").trim();
			
			java.sql.Timestamp commentsTime = null;
			try {
				commentsTime = java.sql.Timestamp.valueOf(req.getParameter("commentsTime").trim());
			} catch (IllegalArgumentException e) {
				commentsTime=new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J�U�q�ɶ�!");
			}
			
			Trip_ord tripOrd = new Trip_ord();
			
			tripOrd.setTripId(tripId);
			tripOrd.setPlanId(planId);
			tripOrd.setCusId(cusId);
			tripOrd.setAmount(amount);
			tripOrd.setTotalPrice(totalPrice);
			tripOrd.setCommission(commission);
			tripOrd.setOrdStatus(ordStatus);
			tripOrd.setOrdTime(ordTime);
			tripOrd.setRemark(remark);
			tripOrd.setScore(score);
			tripOrd.setComments(comments);
			tripOrd.setCommentsTime(commentsTime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("empVO", tripOrd); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Trip_ordService empSvc = new Trip_ordService();
				tripOrd = empSvc.addEmp(tripId, planId, cusId, amount, totalPrice,commission, ordStatus,
						ordTime,remark,score,comments,commentsTime);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer empno = Integer.valueOf(req.getParameter("empno"));
				
				/***************************2.�}�l�R�����***************************************/
				Trip_ordService empSvc = new Trip_ordService();
				empSvc.deleteEmp(empno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
		}
	}

}
