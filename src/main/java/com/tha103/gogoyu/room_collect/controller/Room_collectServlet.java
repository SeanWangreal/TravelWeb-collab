package com.tha103.gogoyu.room_collect.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tha103.gogoyu.room_collect.model.*;
@WebServlet("/room_collect/room_collect.do")
public class Room_collectServlet extends HttpServlet {

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
			Integer room_id = null;
			if(req.getParameter("cus_id")==null && req.getParameter("room_id")==null && req.getParameter("room_collect")==null ) {
					String[] room_collect1 = req.getParameter("collect_time").split(",");
					System.out.println(room_collect1);
					cus_id = Integer.parseInt(room_collect1[0]);
					room_id = Integer.parseInt(room_collect1[1]);
				}else if(req.getParameter("cus_id")==null && req.getParameter("room_id")==null){
					String[] room_collect1 = req.getParameter("room_collect").split(",");
					System.out.println(room_collect1);
					cus_id = Integer.parseInt(room_collect1[0]);
					room_id = Integer.parseInt(room_collect1[1]);
				}else {
					try {
						cus_id = Integer.valueOf(req.getParameter("cus_id"));
					}catch(Exception e){
						errorMsgs.add("行程套票ID請填入數字");
					}
					try {
						room_id = Integer.valueOf(req.getParameter("room_id"));
					}catch(Exception e){
						errorMsgs.add("行程ID請填入數字");
					}
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/room_collect/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Room_collectService roomCollectSvc = new Room_collectService();
				Room_collect room_collect = roomCollectSvc.getOneRoomCollect(cus_id, room_id);
				if (room_collect == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/room_collect/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("room_collectVO", room_collect); // 資料庫取出的empVO物件,存入req
				String url = "/room_collect/listOneRoomCollect.jsp";
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
			String[] room_collect1 = req.getParameter("One_For_Update1").split(",");
			System.out.println(room_collect1);
			Integer cusId = Integer.parseInt(room_collect1[0]);
			Integer roomId = Integer.parseInt(room_collect1[1]);
			
				/***************************2.開始查詢資料****************************************/
				Room_collectService roomCollectSvc = new Room_collectService();
				Room_collect room_collect2 = roomCollectSvc.getOneRoomCollect(cusId, roomId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("room_collectVO", room_collect2);         // 資料庫取出的empVO物件,存入req
				String url = "/room_collect/update_Room_collect_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String[] room_collect1 = req.getParameter("One_For_Update2").split(",");
			System.out.println(room_collect1);
			
			Integer cusId = null;
			try {
				cusId = Integer.parseInt(room_collect1[0]);
			}catch(Exception e){
				cusId = 0;
				errorMsgs.add("行程套票ID請填入數字");
			}
			
			Integer roomId = null;
			try {
				roomId = Integer.parseInt(room_collect1[1]);
			}catch(Exception e){
				roomId = 0;
				errorMsgs.add("行程ID請填入數字");
			}
				
				Room_collect room_collect = new Room_collect();
				room_collect.setCusId(cusId);
				room_collect.setRoomId(roomId);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("room_collectVO",  room_collect); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/room_collect/update_Room_collect_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Room_collectService roomCollectSvc = new Room_collectService();
				room_collect = roomCollectSvc.updateRoomCollect(cusId, roomId);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("room_collectVO", room_collect); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/room_collect/listOneRoomCollect.jsp";
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
			
			Integer room_id = null;
			try {
				room_id = Integer.valueOf(req.getParameter("room_id").trim());
			}catch(Exception e){
				room_id = 0;
				errorMsgs.add("行程ID請填入數字");
			}
				
				Room_collect room_collect = new Room_collect();
				room_collect.setCusId(cus_id);
				room_collect.setRoomId(room_id);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("room_collectVO",  room_collect); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/room_collect/addRoomCollect.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				Room_collectService roomCollectSvc = new Room_collectService();
				room_collect = roomCollectSvc.addRoomCollect(cus_id, room_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/room_collect/listAllRoomCollect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				String[] room_collect1 = req.getParameter("Delete").split(",");
				Integer cusId = Integer.parseInt(room_collect1[0]);
				Integer roomId = Integer.parseInt(room_collect1[1]);
				
				/***************************2.開始刪除資料***************************************/
				Room_collectService roomCollectSvc = new Room_collectService();
				roomCollectSvc.deleteRoomCollect(cusId, roomId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/room_collect/listAllRoomCollect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
