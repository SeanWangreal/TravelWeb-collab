package com.tha103.gogoyu.trip_photo.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tha103.gogoyu.trip_photo.model.*;
@WebServlet("/Trip_photo/trip_photo.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5* 1024 * 1024, maxRequestSize = 5*5* 1024 * 1024)
public class Trip_photoServlet extends HttpServlet {

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
				String str = req.getParameter("trip_photo_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入行程照片ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Trip_photo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer trip_photo_id = null;
				try {
					trip_photo_id = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("行程照片ID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Trip_photo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Trip_photoServiceJDBC tripPhotoSvc = new Trip_photoServiceJDBC();
				Trip_photo trip_photo = tripPhotoSvc.getOneTripPhoto(trip_photo_id);
				if (trip_photo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Trip_photo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("trip_photo", trip_photo); // 資料庫取出的empVO物件,存入req
				String url = "/Trip_photo/listOneTripPhoto.jsp";
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
				Integer trip_photo_id = Integer.valueOf(req.getParameter("trip_photo_id"));
				
				/***************************2.開始查詢資料****************************************/
				Trip_photoServiceJDBC tripPhotoSvc = new Trip_photoServiceJDBC();
				Trip_photo trip_photo = tripPhotoSvc.getOneTripPhoto(trip_photo_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("trip_photo", trip_photo);         // 資料庫取出的empVO物件,存入req
				String url = "/Trip_photo/update_Trip_photo_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer trip_photo_id = Integer.valueOf(req.getParameter("trip_photo_id").trim());
Integer trip_id = null;
				try {
					trip_id = Integer.valueOf(req.getParameter("trip_id"));
				}catch(Exception e){
					trip_id = 0;
					errorMsgs.add("行程套票ID請填入數字");
				}

				Part part = req.getPart("photo");
				String str = String.valueOf(part).trim();
				byte[] photo = null;
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("圖片請勿空白");
				}else {
					BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
					photo = bis.readAllBytes();
				}
				
				Trip_photo trip_photo = new Trip_photo();
				trip_photo.setTripPhotoId(trip_photo_id);
				trip_photo.setTripId(trip_id);
				trip_photo.setPhoto(photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("trip_photo",  trip_photo); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Trip_photo/update_Trip_photo_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Trip_photoServiceJDBC tripPhotoSvc = new Trip_photoServiceJDBC();
				trip_photo = tripPhotoSvc.updateTripPhoto(trip_photo_id, trip_id, photo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("trip_photo", trip_photo); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Trip_photo/listOneTripPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("add".equals(action)) { // 來自addEmp.jsp的請求    
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer trip_id = null;
			try {
				trip_id = Integer.valueOf(req.getParameter("trip_id").trim());
			}catch(Exception e){
				trip_id = 0;
				errorMsgs.add("行程套票ID請填入數字");
			}
			
//			"/Trip_photo/update_Trip_photo_input.jsp"
							
			Part part = req.getPart("photo");
			String str1 = String.valueOf(part).trim();
			byte[] photo = null;
			if (str1 == null || str1.trim().length() == 0) {
				errorMsgs.add("圖片請勿空白");
			}else {
				BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
				photo = bis.readAllBytes();
			}

			Trip_photo trip_photo = new Trip_photo();
			trip_photo.setTripId(trip_id);
			trip_photo.setPhoto(photo);
							

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("trip_photo",  trip_photo); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Trip_photo/addTripPhoto.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				Trip_photoServiceJDBC tripPhotoSvc = new Trip_photoServiceJDBC();
				trip_photo = tripPhotoSvc.addTripPhoto(trip_id, photo);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Trip_photo/listAllTripPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer trip_photo_id = Integer.valueOf(req.getParameter("trip_photo_id"));
				
				/***************************2.開始刪除資料***************************************/
				Trip_photoServiceJDBC tripPhotoSvc = new Trip_photoServiceJDBC();
				tripPhotoSvc.deleteTripPhoto(trip_photo_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Trip_photo/listAllTripPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
