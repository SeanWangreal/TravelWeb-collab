package com.tha103.gogoyu.trip_collect.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tha103.gogoyu.trip_collect.model.*;
@WebServlet("/trip_collect/trip_collect.do")
public class Trip_collectServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println(action);
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer cus_id = null;
			Integer trip_id = null;
			if(req.getParameter("cus_id")==null && req.getParameter("trip_id")==null && req.getParameter("trip_collect")==null ) {
					String[] trip_collect1 = req.getParameter("collect_time").split(",");
					System.out.println(trip_collect1);
					cus_id = Integer.parseInt(trip_collect1[0]);
					trip_id = Integer.parseInt(trip_collect1[1]);
				}else if(req.getParameter("cus_id")==null && req.getParameter("trip_id")==null){
					String[] trip_collect1 = req.getParameter("trip_collect").split(",");
					System.out.println(trip_collect1);
					cus_id = Integer.parseInt(trip_collect1[0]);
					trip_id = Integer.parseInt(trip_collect1[1]);
				}else {
					try {
						cus_id = Integer.valueOf(req.getParameter("cus_id"));
					}catch(Exception e){
						errorMsgs.add("行程套票ID請填入數字");
					}
					try {
						trip_id = Integer.valueOf(req.getParameter("trip_id"));
					}catch(Exception e){
						errorMsgs.add("行程ID請填入數字");
					}
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip_collect/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Trip_collectService tripCollectSvc = new Trip_collectService();
				Trip_collect trip_collect = tripCollectSvc.getOneTripCollect(cus_id, trip_id);
				if (trip_collect == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trip_collect/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("trip_collectVO", trip_collect); // 資料庫取出的empVO物件,存入req
				String url = "/trip_collect/listOneTripCollect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				return;
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
			String[] trip_collect1 = req.getParameter("One_For_Update1").split(",");
			System.out.println(trip_collect1);
			Integer cusId = Integer.parseInt(trip_collect1[0]);
			Integer tripId = Integer.parseInt(trip_collect1[1]);
			
				/***************************2.開始查詢資料****************************************/
				Trip_collectService tripCollectSvc = new Trip_collectService();
				Trip_collect trip_collect2 = tripCollectSvc.getOneTripCollect(cusId, tripId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("trip_collectVO", trip_collect2);         // 資料庫取出的empVO物件,存入req
				String url = "/trip_collect/update_Trip_collect_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String[] trip_collect1 = req.getParameter("One_For_Update2").split(",");
			System.out.println(trip_collect1);
			
			Integer cusId = null;
			try {
				cusId = Integer.parseInt(trip_collect1[0]);
			}catch(Exception e){
				cusId = 0;
				errorMsgs.add("行程套票ID請填入數字");
			}
			
			Integer tripId = null;
			try {
				tripId = Integer.parseInt(trip_collect1[1]);
			}catch(Exception e){
				tripId = 0;
				errorMsgs.add("行程ID請填入數字");
			}
				
				Trip_collect trip_collect = new Trip_collect();
				trip_collect.setCusId(cusId);
				trip_collect.setTripId(tripId);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("trip_collectVO",  trip_collect); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip_collect/update_Trip_collect_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Trip_collectService tripCollectSvc = new Trip_collectService();
				trip_collect = tripCollectSvc.updateTripCollect(cusId, tripId);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("trip_collectVO", trip_collect); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/trip_collect/listOneTripCollect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("add".equals(action)) { // 來自addEmp.jsp的請求    
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer cus_id = null;
			try {
				cus_id = Integer.valueOf(req.getParameter("cus_id").trim());
			}catch(Exception e){
				cus_id = 0;
				errorMsgs.add("行程套票ID請填入數字");
			}
			
			Integer trip_id = null;
			try {
				trip_id = Integer.valueOf(req.getParameter("trip_id").trim());
			}catch(Exception e){
				trip_id = 0;
				errorMsgs.add("行程ID請填入數字");
			}
				
				Trip_collect trip_collect = new Trip_collect();
				trip_collect.setCusId(cus_id);
				trip_collect.setTripId(trip_id);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("trip_collectVO",  trip_collect); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip_collect/addTripCollect.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				Trip_collectService tripCollectSvc = new Trip_collectService();
				trip_collect = tripCollectSvc.addTripCollect(cus_id, trip_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/trip_collect/listAllTripCollect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				String[] trip_collect1 = req.getParameter("Delete").split(",");
				Integer cusId = Integer.parseInt(trip_collect1[0]);
				Integer tripId = Integer.parseInt(trip_collect1[1]);
				
				/***************************2.開始刪除資料***************************************/
				Trip_collectService tripCollectSvc = new Trip_collectService();
				tripCollectSvc.deleteTripCollect(cusId, tripId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/trip_collect/listAllTripCollect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
