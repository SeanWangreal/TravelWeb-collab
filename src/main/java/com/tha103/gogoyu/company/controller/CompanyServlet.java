package com.tha103.gogoyu.company.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.company.model.CompanyService;
import com.tha103.gogoyu.hotel_info.model.Hotel_info;
import com.tha103.gogoyu.hotel_info.model.Hotel_infoServiceHibernate;
@WebServlet("/CompanyServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CompanyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		System.out.println(action);
		if ("signIn".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String compAccount = req.getParameter("account");
			System.out.println(compAccount);
			if (compAccount == null || (compAccount.trim()).length() == 0) {
				errorMsgs.add("請輸入會員帳號");
				System.out.println(errorMsgs);
			}
			
		    CompanyService companySvc = new CompanyService();
			List<Company> account = companySvc.getOneAccount(compAccount);
//			System.out.println(account.get(0).getCompPassword());

			Company company = account.size()==0?null:account.get(0);
//			String pass = company.getCompPassword();
//			System.out.println("我是密碼:"+pass);

//			System.out.println(company);
			String pass = company==null?"":company.getCompPassword();
			String compPassword = req.getParameter("password");
			if(company==null||!compPassword.equals(pass) ) {
				errorMsgs.add("此帳號不存在或密碼不正確,請重新輸入");
				System.out.println(errorMsgs);
			} 
		
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/com_mem_signin.jsp");
				req.setAttribute("errorMsgs", errorMsgs);
				System.out.println(errorMsgs);
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			/*************************** 2.開始查詢資料 *****************************************/

//		    CompanyService companySvc = new CompanyService();
//			List<Company> account = companySvc.getOneAccount(compAccount);

			if (!account.isEmpty()) {
				company = account.get(0);
				System.out.println(company);
				pass = company.getCompPassword();
				req.setAttribute("Company", company); // 資料庫取出的empVO物件,存入req
				System.out.println(pass);
			}
	
			if (account.isEmpty()) {
				errorMsgs.add("查無資料");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/com_mem_signin.jsp");
				failureView.forward(req, res);
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
		    session.setAttribute("compId", company.getCompId().toString());
		    String url = null;
			if (company.getCompType() == 0) {
				System.out.println("wqdwq");
				url = "/ken/com_mem.jsp";
			}else {
				url = "/ken/tripcom_mem.jsp";
			}
			Hotel_infoServiceHibernate hotelInfoSvc = new Hotel_infoServiceHibernate();
			Hotel_info hotelInfo = hotelInfoSvc.getOneHotel_info(company.getHotelInfoId());
			req.setAttribute("hotelinfo", hotelInfo);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 /ken/com_mem.jsp
			successView.forward(req, res);
			return;
		}
		
		if ("signout".equals(action)) { // 來自select_page.jsp的請求
			HttpSession session = req.getSession();
		        // 清除資料
			if (session != null) {
			    session.invalidate(); // 使会话无效
			    String url = "/ken/com_mem_signin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}	System.out.print("您已成功登出退出系統!");
		        System.out.close();
		    }
		

		if ("getOne".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOne");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("compId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer compId = null;
			try {
				compId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CompanyService companySvc = new CompanyService();
			Company company = companySvc.getOneCompany(compId);
			if (company == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath()+"ken/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			System.out.println(company);
			req.setAttribute("Company", company); // 資料庫取出的empVO物件,存入req
			String url = "/ken/com_mem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("getOneJSON".equals(action)) { // 來自select_page.jsp的請求
			Map<String, Object> errorMsgs = new HashMap<String, Object>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("compId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("noCompId","請輸入公司編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				errorMsgs.put("status","Failed");
				Gson gson =new Gson();
				String json=gson.toJson(errorMsgs);
				
				PrintWriter out = res.getWriter();
				out.println(json);
				System.out.println(json);
				out.close();
				return;// 程式中斷
			}

			Integer compId = null;
			try {
				compId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("wrongId","公司編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/hollow/backend.jsp");
//				failureView.forward(req, res);
				errorMsgs.put("status","Failed");
				Gson gson =new Gson();
				String json=gson.toJson(errorMsgs);
				
				PrintWriter out = res.getWriter();
				out.println(json);
				System.out.println(json);
				out.close();
				
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CompanyService companySvc = new CompanyService();
			Company company = companySvc.getOneCompany(compId);
			if (company == null) {
				errorMsgs.put("noData","查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath()+"ken/com_mem.jsp");
//				failureView.forward(req, res);
				errorMsgs.put("status","Failed");
				Gson gson =new Gson();
				String json=gson.toJson(errorMsgs);
				
				PrintWriter out = res.getWriter();
				out.println(json);
				System.out.println(json);
				out.close();
				
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			System.out.println(company);
//			req.setAttribute("Company", company); // 資料庫取出的empVO物件,存入req
//			String url = "/ken/com_mem.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
			Map<String, Object> cmpMap=new HashMap<String, Object>();
			cmpMap.put("compId", company.getCompId());
			cmpMap.put("compName", company.getCompName());
			cmpMap.put("compAddress", company.getCompAddress());
			cmpMap.put("compPhone", company.getCompPhone());
			cmpMap.put("principalName", company.getPrincipalName());
			cmpMap.put("principalPhone", company.getPrincipalPhone());
			cmpMap.put("compAccount", company.getCompAccount());
			cmpMap.put("compMail", company.getCompMail());
			cmpMap.put("compChkStatus", company.getCheckStatus());
			cmpMap.put("status", "Success");
			
			Gson gson =new Gson();
			String json=gson.toJson(cmpMap);
			
			PrintWriter out = res.getWriter();
			out.println(json);
//			System.out.println(json);
			out.close();
		}
		
		//密碼修改
		if ("getOneUpdate".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOneUpdate");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			
			String pass = req.getParameter("compId");
			System.out.println(pass+"===========");
			if (pass == null || (pass.trim()).length() == 0) {
				errorMsgs.add("請輸入密碼");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer compId = null;
			try {
				compId = Integer.valueOf(pass);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CompanyService companySvc = new CompanyService();
			Company company = companySvc.getOneCompany(compId);
			if (company == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath()+"/ken/com_mem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			System.out.println(company);
			req.setAttribute("Company", company); // 資料庫取出的empVO物件,存入req
			String url = "/ken/com_mem_renewpass.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
			
		//密碼修改確認
		if ("getPSForUpdate".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer compId = Integer.parseInt((String) req.getSession().getAttribute("compId"));
				
				String compPassword = req.getParameter("password").trim();
				if (compPassword == null || compPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				CompanyService companySvc = new CompanyService();
				Company company = companySvc.getOneCompany(compId);
				
				Integer hotelInfoId=company.getHotelInfoId();
				Integer compType=company.getCompType();
				String compName=company.getCompName();
				String compAddress=company.getCompAddress();
				String compPhone=company.getCompPhone();
				String principalName=company.getPrincipalName();
				String principalPhone=company.getPrincipalPhone();
				String compAccount=company.getCompAccount();
				String compMail=company.getCompMail();
				byte[] compPhoto=company.getCompPhoto();
				Integer checkStatus=company.getCheckStatus();
				
				/***************************2.開始修改資料*****************************************/
		
				company = companySvc.updateCompany(compId, hotelInfoId, compType, compName, compAddress, compPhone, principalName,
						principalPhone, compAccount, compPassword, compMail, compPhoto, checkStatus);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String url = "/ken/com_mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("updChkStat".equals(action)) { // 來自update_emp_input.jsp的請求
			Integer compId = Integer.valueOf(req.getParameter("compId").trim());
			Integer checkStatus = Integer.valueOf(req.getParameter("chkStatus").trim());


			/*************************** 2.開始修改資料 *****************************************/
			CompanyService companySvc = new CompanyService();
			companySvc.updChkStatus(compId, checkStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/hollow/backend.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if("updFromBackend".equals(action)) {
			Map<String, Object> errorMsgs=new HashMap<String, Object>();
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer compId = Integer.valueOf(req.getParameter("compId").trim());
			System.out.println(compId);
			
			String compName = req.getParameter("compName");
//			String compNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (compName == null || compName.trim().length() == 0) {
				errorMsgs.put("wrongName","公司名稱: 請勿空白");
			} 
//			else if (!compName.trim().matches(compNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongName","公司名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
			System.out.println(compName);
			
			String compAddress = req.getParameter("compAddress").trim();
//			String compAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]$";
			if (compAddress == null || compAddress.trim().length() == 0) {
				errorMsgs.put("wrongAddress","公司地址: 請勿空白");
			} 
//			else if (!compAddress.trim().matches(compAddressReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongAddress","公司地址: 只能是中、英文字母、數字");
//			}
			System.out.println(compAddress);
			
			String compPhone = req.getParameter("compPhone").trim();
//			String compPhoneReg = "^0[(0-9)]{1,2}-[(0-9)]{8}$";
			if (compPhone == null || compPhone.trim().length() == 0) {
				errorMsgs.put("wrongPhone","公司電話: 請勿空白");
			} 
//			else if (!compPhone.trim().matches(compPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongPhone","公司電話: 格式錯誤");
//			}
			System.out.println(compPhone);
			
			String principalName = req.getParameter("principalName").trim();
//			String principalNameReg = "^[(\u4e00-\\u9fa5)(a-zA-Z0-9_)]$";
			if (principalName == null || principalName.trim().length() == 0) {
				errorMsgs.put("wrongPrincipalName","負責人名稱: 請勿空白");
			} 
//			else if (!principalName.trim().matches(principalNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongPrincipalName","負責人名稱: 只能是中、英文字母");
//			}
			System.out.println(principalName);
			
			String principalPhone = req.getParameter("principalPhone").trim();
//			String principalPhoneReg = "^0[(0-9)]{1,2}-[(0-9)]{8}$";
			if (principalPhone == null || principalPhone.trim().length() == 0) {
				errorMsgs.put("wrongPrincipalPhone","負責人電話: 請勿空白");
			} 
//			else if (!principalPhone.trim().matches(principalPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongPrincipalPhone","負責人電話: 格式錯誤");
//			}
			System.out.println(principalPhone);
			
			String compAccount = req.getParameter("compAccount");
//			String compAccountReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (compAccount == null || compAccount.trim().length() == 0) {
				errorMsgs.put("wrongAccount","公司帳號: 請勿空白");
			} 
//			else if (!compAccount.trim().matches(compAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongAccount","公司帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
//			}
			System.out.println(compAccount);
			
			String compMail = req.getParameter("compMail");
//			String compMailReg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//網路上查的Email正規表達式
			if (compMail == null || compMail.trim().length() == 0) {
				errorMsgs.put("wrongMail","公司信箱: 請勿空白");
			} 
//			else if (!compMail.trim().matches(compMailReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongMail","公司信箱: 格式錯誤");
//			}
			System.out.println(compMail);
			
			Integer compChkStatus = Integer.valueOf(req.getParameter("compChkStatus"));
			
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath()+"ken/com_mem.jsp");
//				failureView.forward(req, res);
				errorMsgs.put("compId", compId);
				errorMsgs.put("compName", compName);
				errorMsgs.put("compAddress", compAddress);
				errorMsgs.put("compPhone", compPhone);
				errorMsgs.put("principalName", principalName);
				errorMsgs.put("principalPhone", principalPhone);
				errorMsgs.put("compAccount", compAccount);
				errorMsgs.put("compMail", compMail);
				errorMsgs.put("compChkStatus", compChkStatus);
//				errorMsgs.put("compPhoto64", compPhoto64);
				errorMsgs.put("status", "Failed");
				
				Gson gson =new Gson();
				String json=gson.toJson(errorMsgs);
				
				PrintWriter out = res.getWriter();
				out.println(json);
				System.out.println(json);
				out.close();
				
				return;// 程式中斷
			}

			CompanyService companySvc = new CompanyService();
			Company company = companySvc.getOneCompany(compId);
			
			
			String compPhoto64=req.getParameter("compPhoto64");
			
			byte[] compPhotoByte=null;
			
			if(!compPhoto64.equals("")) {
				String compPhotoStr=compPhoto64.substring(23);
//			InputStream is=compPhoto64.getInputStream();
//			byte[] compPhotoByte=is.readAllBytes();
				System.out.println(compPhotoStr);
				compPhotoByte=Base64.getDecoder().decode(compPhotoStr);				
			}else {
				 compPhotoByte=company.getCompPhoto();
			}
			
			Integer hotelInfoId=company.getHotelInfoId();
			Integer compType=company.getCompType();
			String compPassword=company.getCompPassword();
			
			company = companySvc.updateCompany(compId, hotelInfoId, compType, compName, compAddress, compPhone, principalName,
					principalPhone, compAccount, compPassword, compMail, compPhotoByte, compChkStatus);			
			
			Map<String, Object> cmpMap=new HashMap<String, Object>();
			cmpMap.put("compId", company.getCompId());
			cmpMap.put("compName", company.getCompName());
			cmpMap.put("compAddress", company.getCompAddress());
			cmpMap.put("compPhone", company.getCompPhone());
			cmpMap.put("principalName", company.getPrincipalName());
			cmpMap.put("principalPhone", company.getPrincipalPhone());
			cmpMap.put("compAccount", company.getCompAccount());
			cmpMap.put("compMail", company.getCompMail());
			cmpMap.put("compChkStatus", company.getCheckStatus());
//			cmpMap.put("compPhoto64", compPhoto64);
			cmpMap.put("status", "Success");
			
			Gson gson =new Gson();
			String json=gson.toJson(cmpMap);
			
			PrintWriter out = res.getWriter();
			out.println(json);
//			System.out.println(json);
			out.close();
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer compId = Integer.valueOf(req.getParameter("compId").trim());

			Integer hotelInfoId = Integer.valueOf(req.getParameter("hotelInfoId").trim());
			if (hotelInfoId == null || hotelInfoId == 0) {
				errorMsgs.add("飯店id請勿空白");
			}

			Integer compType = Integer.valueOf(req.getParameter("compType").trim());
			if (compType == null || compType == 0) {
				errorMsgs.add("廠商別請勿空白");
			}

			String compName = req.getParameter("compName");
			String compNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (compName == null || compName.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!compName.trim().matches(compNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String compAddress = req.getParameter("compAddress").trim();
			if (compAddress == null || compAddress.trim().length() == 0) {
				errorMsgs.add("公司地址請勿空白");
			}

			String compPhone = null;
			try {
				compPhone = String.valueOf(req.getParameter("compPhone").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("公司電話請填數字.");
			}

			String principalName = req.getParameter("principalName").trim();
			if (principalName == null || principalName.trim().length() == 0) {
				errorMsgs.add("負責人姓名請勿空白");
			}

			String principalPhone = null;
			try {
				principalPhone = String.valueOf(req.getParameter("principalPhone").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("負責人電話請填數字.");
			}

			String compAccount = req.getParameter("compAccount").trim();
			if (compAccount == null || compAccount.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			String compPassword = req.getParameter("compPassword").trim();
			if (compPassword == null || compPassword.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			String compMail = req.getParameter("compMail").trim();
			if (compMail == null || compMail.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			}

			byte[] compPhoto = new byte[] {};

//				java.sql.Date hiredate = null;
//				try {
//hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

			Integer checkStatus = null;
			try {
				checkStatus = Integer.valueOf(req.getParameter("checkStatus").trim());
			} catch (NumberFormatException e) {
				checkStatus = 0;
				errorMsgs.add("審核未通過.");
			}

//Integer compId = Integer.valueOf(req.getParameter("compId").trim());

			Company company = new Company();
			company.setCompId(compId);
			company.setHotelInfoId(hotelInfoId);
			company.setCompType(compType);
			company.setCompName(compName);
			company.setCompAddress(compAddress);
			company.setCompPhone(compPhone);
			company.setPrincipalName(principalName);
			company.setPrincipalPhone(principalPhone);
			company.setCompAccount(compAccount);
			company.setCompPassword(compPassword);
			company.setCompMail(compMail);
			company.setCompPhoto(compPhoto);
			company.setCheckStatus(checkStatus);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Company", company); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/com_mem.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			CompanyService companySvc = new CompanyService();
			company = companySvc.updateCompany(compId, hotelInfoId, compType, compName, compAddress, compPhone, principalName,
					principalPhone, compAccount, compPassword, compMail, compPhoto, checkStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("Company", company); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/ken/com_mem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("add".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
			Integer compType = Integer.valueOf(req.getParameter("compType").trim());
			System.out.println(compType);
			if (compType == null) {
				errorMsgs.add("廠商別請勿空白");
			}

			String compName = req.getParameter("compName");
			String compNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (compName == null || compName.trim().length() == 0) {
				errorMsgs.add("公司姓名: 請勿空白");
			} else if (!compName.trim().matches(compNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("公司姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String compAddress = req.getParameter("compAddress").trim();
			if (compAddress == null || compAddress.trim().length() == 0) {
				errorMsgs.add("公司地址請勿空白");
			}

			String compPhone = null;
			try {
				compPhone = String.valueOf(req.getParameter("compPhone").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("公司電話請填數字.");
			}

			String principalName = req.getParameter("principalName").trim();
			if (principalName == null || principalName.trim().length() == 0) {
				errorMsgs.add("負責人姓名請勿空白");
			}

			String principalPhone = null;
			try {
				principalPhone = String.valueOf(req.getParameter("principalPhone").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("負責人電話請填數字.");
			}

			String compAccount = req.getParameter("compAccount").trim();
			if (compAccount == null || compAccount.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
				
			}
			
//			CompanyService companySvc1 = new CompanyService();
//			List<Company> Duplicate = companySvc1.getOneAccount(compAccount);
//
//			   if (Duplicate != null) {
//			    errorMsgs.add("帳號已重複");
//			   }

			String compPassword = req.getParameter("compPassword").trim();
			if (compPassword == null || compPassword.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			String compMail = req.getParameter("compMail").trim();
			if (compMail == null || compMail.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			}

			byte[] compPhoto = null;
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				if (part.getContentType() != null && part.getSize() != 0) {
					InputStream is = part.getInputStream();
					compPhoto = is.readAllBytes();
				}else if (part.getContentType() == null && part.getSize() != 0) {
					ServletContext context = getServletContext();
				     String img = context.getRealPath("/ken/img/face.jpg");
				     FileInputStream fis = new FileInputStream(img);
				     BufferedInputStream bis = new BufferedInputStream(fis);
				     compPhoto = bis.readAllBytes();
				}
				
			}
//		    -------------------------------------hotelinfo----------------------------------
			Hotel_info hotelInfo = null;
			if (compType == 0) {
				hotelInfo = new Hotel_info();
				hotelInfo.setRestaurant((byte)0);
				hotelInfo.setRoomService((byte)0);
				hotelInfo.setAlldayCounter((byte)0);
				hotelInfo.setSpa((byte)0);
				hotelInfo.setGym((byte)0);
				hotelInfo.setGarden((byte)0);
				hotelInfo.setTerrace((byte)0);
				hotelInfo.setNoSmoking((byte)0);
				hotelInfo.setFreewifi((byte)0);
				hotelInfo.setHeater((byte)0);
				hotelInfo.setBeach((byte)0);
				hotelInfo.setPool((byte)0);
				hotelInfo.setChargingstation((byte)0);
				hotelInfo.setParking((byte)0);
			}
			System.out.println(errorMsgs);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ken/com_mem_signupinfo.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			CompanyService companySvc = new CompanyService();
			Company company =  companySvc.addCompany(compType, compName, compAddress, compPhone, principalName,
			principalPhone, compAccount, compPassword, compMail, compPhoto,hotelInfo);
			
			
			String url ="/ken/com_mem_signin.jsp";
	
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer compId = Integer.valueOf(req.getParameter("compId"));

			/*************************** 2.開始刪除資料 ***************************************/
			CompanyService companySvc = new CompanyService();
			companySvc.deleteCompany(compId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/ken/com_mem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	
//		 //發送驗證 mail 
//		  MailService msv = new MailService();
//		  String to =  req.getRequestDispatcher(compmail);
//		  String subject = "廚藝實驗室:驗證碼通知";
//		  String ch_name = nickname;
//		  String passRandom = msv.genAuthCode();
//		  String messageText =  ch_name +" 您好!\n\n["+ passRandom +"]\n\n為您在廚藝實驗室(CookLab)的驗證碼，請於10分鐘內輸入" +"\n" ;
//		
//		  MailService mailService = new MailService();
//		  new Thread(()->mailService.sendMail(to, subject, messageText)).start(); 

	}
}
