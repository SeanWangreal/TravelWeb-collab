//package com.tha103.gogoyu.trip_ord.controller;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.tha103.gogoyu.trip_ord.model.Trip_ord;
//import com.tha103.gogoyu.trip_ord.model.Trip_ordService;
//
//public class Trip_ordServlet extends HttpServlet{
//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		
//		
//		if ("getOne_For_Display".equals(action)) { // 嚙諉佗蕭select_page.jsp嚙踝蕭嚙請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲**********************/
//				String str = req.getParameter("empno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("嚙請選蕭J嚙踝蕭嚙線嚙編嚙踝蕭");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
//				}
//				
//				Integer empno = null;
//				try {
//					empno = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.add("嚙踝蕭嚙線嚙編嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
//				}
//				
//				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�*****************************************/
//				Trip_ordService empSvc = new Trip_ordService();
//				Trip_ord empVO = empSvc.getOneEmp(empno);
//				if (empVO == null) {
//					errorMsgs.add("嚙範嚙盤嚙踝蕭嚙�");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
//				}
//				
//				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 嚙踝蕭おw嚙踝蕭嚙碼嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙� listOneEmp.jsp
//				successView.forward(req, res);
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 嚙諉佗蕭listAllEmp.jsp嚙踝蕭嚙請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭****************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
//				Trip_ordService empSvc = new Trip_ordServicef();
//				Trip_ord empVO = empSvc.getOneEmp(empno);
//								
//				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)************/
//				req.setAttribute("empVO", empVO);         // 嚙踝蕭おw嚙踝蕭嚙碼嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
//				String url = "/emp/update_emp_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭嚙穀嚙踝蕭嚙� update_emp_input.jsp
//				successView.forward(req, res);
//		}
//		
//		
//		if ("update".equals(action)) { // 嚙諉佗蕭update_emp_input.jsp嚙踝蕭嚙請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲**********************/
//				Integer tripOrdId = Integer.valueOf(req.getParameter("tripOrdId").trim());
//								
//				//String ename = req.getParameter("ename");
//				//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				//				if (ename == null || ename.trim().length() == 0) {
//				//					errorMsgs.add("嚙踝蕭嚙線嚙練嚙磕: 嚙請勿空伐蕭");
//				//				} else if(!ename.trim().matches(enameReg)) { //嚙瘡嚙磊嚙練嚙賠伐蕭嚙篁(嚙磕)嚙踝蕭雃嚙�(regular-expression)
//				//					errorMsgs.add("嚙踝蕭嚙線嚙練嚙磕: 嚙線嚙踝蕭O嚙踝蕭嚙畿嚙稷嚙踝蕭r嚙踝蕭嚙畿嚙複字嚙瞎_ , 嚙畿嚙踝蕭嚙論伐蕭嚙豎在2嚙踝蕭10嚙踝蕭嚙踝蕭");
//				//	            }
//				
//				Integer tripId = Integer.valueOf(req.getParameter("tripId").trim());
//				
//				Integer planId = Integer.valueOf(req.getParameter("planId").trim());
//				
//				Integer cusId = Integer.valueOf(req.getParameter("cusId").trim());
//							
//				Integer amount = null;
//				try {
//					amount = Integer.valueOf(req.getParameter("amount").trim());
//				} catch (NumberFormatException e) {
//					amount = 0;
//					errorMsgs.add("嚙複量嚙請塚蕭あr.");
//				}
//				
//				BigDecimal totalPrice = null;
//				try {
//					totalPrice = new BigDecimal(String.valueOf(req.getParameter("totalPrice").trim()));
//				} catch (NumberFormatException e) {
//					totalPrice = new BigDecimal(0.0);
//					errorMsgs.add("嚙羯嚙踝蕭嚙請塚蕭あr.");
//				}
//				
//				BigDecimal commission = null;
//				try {
//					commission = new BigDecimal(String.valueOf(req.getParameter("commission").trim()));
//				} catch (NumberFormatException e) {
//					commission = new BigDecimal(0.0);
//					errorMsgs.add("嚙衝迎蕭嚙請塚蕭あr.");
//				}
//				
//				Integer ordStatus = Integer.valueOf(req.getParameter("ordStatus").trim());
//				
//				java.sql.Timestamp ordTime = null;
//				try {
//					ordTime = java.sql.Timestamp.valueOf(req.getParameter("ordTime").trim());
//				} catch (IllegalArgumentException e) {
//					ordTime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("嚙請選蕭J嚙磊嚙緬嚙褕塚蕭!");
//				}
//
//				String remark = req.getParameter("remark").trim();
//				
//				Integer score = null;
//				try {
//					score = Integer.valueOf(req.getParameter("score").trim());
//				} catch (NumberFormatException e) {
//					score = 0;
//					errorMsgs.add("嚙踝蕭嚙複請塚蕭あr.");
//				}
//
//				String comments = req.getParameter("comments").trim();
//				
//				java.sql.Timestamp commentsTime = null;
//				try {
//					commentsTime = java.sql.Timestamp.valueOf(req.getParameter("commentsTime").trim());
//				} catch (IllegalArgumentException e) {
//					commentsTime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("嚙請選蕭J嚙磊嚙緬嚙褕塚蕭!");
//				}
//				
//				Trip_ord tripOrd = new Trip_ord();
//				
//				tripOrd.setTripOrdId(tripOrdId);
//				tripOrd.setTripId(tripId);
//				tripOrd.setPlanId(planId);
//				tripOrd.setCusId(cusId);
//				tripOrd.setAmount(amount);
//				tripOrd.setTotalPrice(totalPrice);
//				tripOrd.setCommission(commission);
//				tripOrd.setOrdStatus(ordStatus);
//				tripOrd.setOrdTime(ordTime);
//				tripOrd.setRemark(remark);
//				tripOrd.setScore(score);
//				tripOrd.setComments(comments);
//				tripOrd.setCommentsTime(commentsTime);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//req.setAttribute("empVO", tripOrd); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //嚙緹嚙踝蕭嚙踝蕭嚙稻
//				}
//				
//				/***************************2.嚙罷嚙締嚙論改蕭嚙踝蕭*****************************************/
//				Trip_ordService empSvc = new Trip_ordService();
//				tripOrd = empSvc.updateEmp(tripId, planId, cusId, amount, totalPrice,commission, ordStatus,
//						ordTime,remark,score,comments,commentsTime,tripOrdId);
//				
//				/***************************3.嚙論改完嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)*************/
//				req.setAttribute("empVO", tripOrd); // 嚙踝蕭おwupdate嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙確嚙踝蕭嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙論改成嚙穀嚙踝蕭,嚙踝蕭嚙締istOneEmp.jsp
//				successView.forward(req, res);
//		}
//
//        if ("insert".equals(action)) { // 嚙諉佗蕭addEmp.jsp嚙踝蕭嚙請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***********************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲*************************/
//			//String ename = req.getParameter("ename");
//			//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			//				if (ename == null || ename.trim().length() == 0) {
//			//					errorMsgs.add("嚙踝蕭嚙線嚙練嚙磕: 嚙請勿空伐蕭");
//			//				} else if(!ename.trim().matches(enameReg)) { //嚙瘡嚙磊嚙練嚙賠伐蕭嚙篁(嚙磕)嚙踝蕭雃嚙�(regular-expression)
//			//					errorMsgs.add("嚙踝蕭嚙線嚙練嚙磕: 嚙線嚙踝蕭O嚙踝蕭嚙畿嚙稷嚙踝蕭r嚙踝蕭嚙畿嚙複字嚙瞎_ , 嚙畿嚙踝蕭嚙論伐蕭嚙豎在2嚙踝蕭10嚙踝蕭嚙踝蕭");
//			//	            }
//			
//			Integer tripId = Integer.valueOf(req.getParameter("tripId").trim());
//			
//			Integer planId = Integer.valueOf(req.getParameter("planId").trim());
//			
//			Integer cusId = Integer.valueOf(req.getParameter("cusId").trim());
//						
//			Integer amount = null;
//			try {
//				amount = Integer.valueOf(req.getParameter("amount").trim());
//			} catch (NumberFormatException e) {
//				amount = 0;
//				errorMsgs.add("嚙複量嚙請塚蕭あr.");
//			}
//			
//			BigDecimal totalPrice = null;
//			try {
//				totalPrice = new BigDecimal(String.valueOf(req.getParameter("totalPrice").trim()));
//			} catch (NumberFormatException e) {
//				totalPrice = new BigDecimal(0.0);
//				errorMsgs.add("嚙羯嚙踝蕭嚙請塚蕭あr.");
//			}
//			
//			BigDecimal commission = null;
//			try {
//				commission = new BigDecimal(String.valueOf(req.getParameter("commission").trim()));
//			} catch (NumberFormatException e) {
//				commission = new BigDecimal(0.0);
//				errorMsgs.add("嚙衝迎蕭嚙請塚蕭あr.");
//			}
//			
//			Integer ordStatus = Integer.valueOf(req.getParameter("ordStatus").trim());
//			
//			java.sql.Timestamp ordTime = null;
//			try {
//				ordTime = java.sql.Timestamp.valueOf(req.getParameter("ordTime").trim());
//			} catch (IllegalArgumentException e) {
//				ordTime=new java.sql.Timestamp(System.currentTimeMillis());
//				errorMsgs.add("嚙請選蕭J嚙磊嚙緬嚙褕塚蕭!");
//			}
//
//			String remark = req.getParameter("remark").trim();
//			
//			Integer score = null;
//			try {
//				score = Integer.valueOf(req.getParameter("score").trim());
//			} catch (NumberFormatException e) {
//				score = 0;
//				errorMsgs.add("嚙踝蕭嚙複請塚蕭あr.");
//			}
//
//			String comments = req.getParameter("comments").trim();
//			
//			java.sql.Timestamp commentsTime = null;
//			try {
//				commentsTime = java.sql.Timestamp.valueOf(req.getParameter("commentsTime").trim());
//			} catch (IllegalArgumentException e) {
//				commentsTime=new java.sql.Timestamp(System.currentTimeMillis());
//				errorMsgs.add("嚙請選蕭J嚙磊嚙緬嚙褕塚蕭!");
//			}
//			
//			Trip_ord tripOrd = new Trip_ord();
//			
//			tripOrd.setTripId(tripId);
//			tripOrd.setPlanId(planId);
//			tripOrd.setCusId(cusId);
//			tripOrd.setAmount(amount);
//			tripOrd.setTotalPrice(totalPrice);
//			tripOrd.setCommission(commission);
//			tripOrd.setOrdStatus(ordStatus);
//			tripOrd.setOrdTime(ordTime);
//			tripOrd.setRemark(remark);
//			tripOrd.setScore(score);
//			tripOrd.setComments(comments);
//			tripOrd.setCommentsTime(commentsTime);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//req.setAttribute("empVO", tripOrd); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.嚙罷嚙締嚙編嚙磕嚙踝蕭嚙�***************************************/
//				Trip_ordService empSvc = new Trip_ordService();
//				tripOrd = empSvc.addEmp(tripId, planId, cusId, amount, totalPrice,commission, ordStatus,
//						ordTime,remark,score,comments,commentsTime);
//				
//				/***************************3.嚙編嚙磕嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙編嚙磕嚙踝蕭嚙穀嚙踝蕭嚙踝蕭嚙締istAllEmp.jsp
//				successView.forward(req, res);				
//		}
//		
//		
//		if ("delete".equals(action)) { // 嚙諉佗蕭listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭***************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.嚙罷嚙締嚙磋嚙踝蕭嚙踝蕭嚙�***************************************/
//				Trip_ordService empSvc = new Trip_ordService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.嚙磋嚙踝蕭嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙磋嚙踝蕭嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙稷嚙箴嚙碼嚙磋嚙踝蕭嚙踝蕭嚙諉瘀蕭嚙踝蕭嚙踝蕭
//				successView.forward(req, res);
//		}
//	}
//
//}
