package com.tha103.gogoyu.consumer.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.consumer.model.ConsumerService;
import com.tha103.gogoyu.consumer.model.ConsumerServiceHibernate;


@WebServlet("/eric/ConsumerServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ConsumerServlet extends HttpServlet {
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("cusId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer cusId = null;
			try {
				cusId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
//			ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
			Consumer consumer = cusSvc.getOneCus(cusId);
			if (consumer == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("consumer", consumer); // 資料庫取出的empVO物件,存入req
			String url = "/eric/listOneCus.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("getOneJSON".equals(action)) { // 來自select_page.jsp的請求
			Map<String, Object> errorMsgs = new HashMap<String, Object>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			res.setCharacterEncoding("UTF-8");
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("cusId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("noCustId","請輸入會員編號");
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

			Integer cusId = null;
			try {
				cusId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("wrongId","會員編號格式不正確");
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
			ConsumerServiceHibernate consumerSvc = new ConsumerServiceHibernate();
			Consumer consumer = consumerSvc.getOneCus(cusId);
			if (consumer == null) {
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
			Map<String, Object> cusMap=new HashMap<String, Object>();
			cusMap.put("cusId", consumer.getCusId());
			cusMap.put("cusName", consumer.getCusName());
			cusMap.put("cusAccount", consumer.getCusAccount());
			cusMap.put("cusMail", consumer.getCusMail());
			cusMap.put("cusPhone", consumer.getCusPhone());
			cusMap.put("cusAddress", consumer.getCusAddress());
			cusMap.put("cusGender", consumer.getCusSex());
			cusMap.put("status", "Success");
			
			Gson gson =new Gson();
			String json=gson.toJson(cusMap);
			
			PrintWriter out = res.getWriter();
			out.println(json);
			System.out.println(json);
			out.close();
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer cusId = Integer.valueOf(req.getParameter("cusId"));

			/*************************** 2.開始查詢資料 ****************************************/
//			ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
			Consumer consumer = cusSvc.getOneCus(cusId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("consumer", consumer); // 資料庫取出的empVO物件,存入req
			String url = "/eric/update_cus_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer cusId = Integer.valueOf(req.getParameter("cusId").trim());

			String cusName = req.getParameter("cusName");
			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (cusName == null || cusName.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if (!cusName.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String cusAccount = req.getParameter("cusAccount").trim();
			String accountReg = "^[(a-zA-Z0-9_)]{10,30}$";

			if (cusAccount == null || cusAccount.trim().length() == 0) {
				errorMsgs.add("帳號: 請勿空白");
			} else if (!cusAccount.trim().matches(accountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("帳號: 只能是英文字母、數字和_ , 且長度必需在10到30之間");
			}

			String cusPassword = req.getParameter("cusPassword").trim();
			String passwordReg = "^[(a-zA-Z0-9_)]{10,30}$";

			if (cusPassword == null || cusPassword.trim().length() == 0) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (!cusPassword.trim().matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("密碼: 只能是英文字母、數字和_ , 且長度必需在10到30之間");
			}

			String cusMail = req.getParameter("cusMail").trim();
//			String mailReg = "^[(a-zA-Z0-9_)]{10,30}$";

//			if (cus_mail == null || cus_mail.trim().length() == 0) {
//				errorMsgs.add("信箱: 請勿空白");
//			} else if (!cus_mail.trim().matches(mailReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("格式錯誤");
//			}

			String cusPhone = req.getParameter("cusPhone").trim();
//			String phoneReg = "^[(a-zA-Z0-9_)]{10,30}$";
//
//			if (cus_phone == null || cus_phone.trim().length() == 0) {
//				errorMsgs.add("手機: 請勿空白");
//			} else if (!cus_phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("手機格式錯誤");
//			}

			String cusAddress = req.getParameter("cusAddress").trim();
			String addressReg = "^[\\u4e00-\\u9fa5\\w\\s()-,]+$";

			if (cusAddress == null || cusAddress.trim().length() == 0) {
				errorMsgs.add("地址: 請勿空白");
			} else if (!cusAddress.trim().matches(addressReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("地址格式錯誤");
			}

			Integer cusSex = null;
			try {
				cusSex = Integer.valueOf(req.getParameter("cusSex").trim());
			} catch (NumberFormatException e) {
				cusSex = 0;
				errorMsgs.add("獎金請填數字.");
			}

			Part newPicture = req.getPart("cusPhoto");
			   byte[] cusPhoto = null;
			   // 沒有選圖片也不會null而是空物件 與insert 處理方式不同(未選圖就抓原本的圖)
			   if (newPicture != null && newPicture.getSize() > 0) {
			    System.out.println("profilePhoto1," + newPicture);
			    InputStream is = newPicture.getInputStream();
			    ByteArrayOutputStream byteArros = new ByteArrayOutputStream();
			    byte[] buf = new byte[4 * 1024];
			    int len;
			    while ((len = is.read(buf)) != -1) {
			     byteArros.write(buf, 0, len);
			    }
			    cusPhoto = byteArros.toByteArray();
			    byteArros.close();
			   } else {
			    Consumer consumer = new Consumer();
//			    ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
			    consumer = cusSvc.getOneCus(cusId);
			    cusPhoto = consumer.getCusPhoto();// 抓原本舊圖
			   }
			   
//			Part part = req.getPart("cusPhoto");
//			String str = String.valueOf(part).trim();
//			byte[] cusPhoto = null;
//			if (str == null || str.trim().length() == 0) {
//				errorMsgs.add("圖片請勿空白");
//			}else {
//				BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
//				cusPhoto = bis.readAllBytes();
//				System.out.println(cusPhoto +"吃大便");
//			}

			Consumer consumer = new Consumer();
			consumer.setCusId(cusId);
			consumer.setCusName(cusName);
			consumer.setCusAccount(cusAccount);
			consumer.setCusPassword(cusPassword);
			consumer.setCusMail(cusMail);
			consumer.setCusPhone(cusPhone);
			consumer.setCusAddress(cusAddress);
			consumer.setCusSex(cusSex);
			consumer.setCusPhoto(cusPhoto);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("consumer", consumer); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/update_cus_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
//			ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
			consumer = cusSvc.updateCus(cusId, cusName, cusAccount, cusPassword, cusMail, cusPhone, cusAddress,
					cusSex, cusPhoto);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("consumer", consumer); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/eric/listOneCus.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if("updFromBackend".equals(action)) {
			Map<String, Object> errorMsgs=new HashMap<String, Object>();
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer cusId = Integer.valueOf(req.getParameter("custId").trim());
			System.out.println(cusId);
			
			String cusName = req.getParameter("custName");
//			String compNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (cusName == null || cusName.trim().length() == 0) {
				errorMsgs.put("wrongName","會員名稱: 請勿空白");
			} 
			System.out.println(cusName);
//			else if (!compName.trim().matches(compNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongName","公司名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
			
			Integer cusGender = Integer.valueOf(req.getParameter("custGender"));
//			System.out.println(cusGender);
			
			String cusAccount = req.getParameter("custAccount");
//			String compAccountReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (cusAccount == null || cusAccount.trim().length() == 0) {
				errorMsgs.put("wrongAccount","會員帳號: 請勿空白");
			} 
			System.out.println(cusAccount);
//			else if (!compAccount.trim().matches(compAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongAccount","公司帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
//			}
			
			String cusMail = req.getParameter("custMail");
//			String compMailReg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//網路上查的Email正規表達式
			if (cusMail == null || cusMail.trim().length() == 0) {
				errorMsgs.put("wrongMail","會員信箱: 請勿空白");
			} 
			System.out.println(cusMail);
//			else if (!compMail.trim().matches(compMailReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongMail","公司信箱: 格式錯誤");
//			}
			
			String cusPhone = req.getParameter("custPhone").trim();
//			String compPhoneReg = "^0[(0-9)]{1,2}-[(0-9)]{8}$";
			if (cusPhone == null || cusPhone.trim().length() == 0) {
				errorMsgs.put("wrongPhone","會員電話: 請勿空白");
			} 
			System.out.println(cusPhone);
//			else if (!compPhone.trim().matches(compPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongPhone","公司電話: 格式錯誤");
//			}
			
			String cusAddress = req.getParameter("custAddress").trim();
//			String compAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]$";
			if (cusAddress == null || cusAddress.trim().length() == 0) {
				errorMsgs.put("wrongAddress","會員地址: 請勿空白");
			} 
			System.out.println(cusAddress);
//			else if (!compAddress.trim().matches(compAddressReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongAddress","公司地址: 只能是中、英文字母、數字");
//			}
			
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath()+"ken/com_mem.jsp");
//				failureView.forward(req, res);
				errorMsgs.put("custId", cusId);
				errorMsgs.put("custName", cusName);
				errorMsgs.put("custAccount", cusAccount);
				errorMsgs.put("custMail", cusMail);
				errorMsgs.put("custPhone", cusPhone);
				errorMsgs.put("custAddress", cusAddress);
				errorMsgs.put("custGender", cusGender);
				errorMsgs.put("status", "Failed");
				
				Gson gson =new Gson();
				String json=gson.toJson(errorMsgs);
				
				PrintWriter out = res.getWriter();
				out.println(json);
				System.out.println(json);
				out.close();
				
				return;// 程式中斷
			}

			ConsumerServiceHibernate consumerSvc = new ConsumerServiceHibernate();
			consumerSvc.updFromBackend(cusId, cusName, cusAccount, cusMail, cusPhone, cusAddress, cusGender);
			Consumer consumer=consumerSvc.getOneCus(cusId);
			
			Map<String, Object> cusMap=new HashMap<String, Object>();
			cusMap.put("custId", consumer.getCusId());
			cusMap.put("custName", consumer.getCusName());
			cusMap.put("custAccount", consumer.getCusAccount());
			cusMap.put("custMail", consumer.getCusMail());
			cusMap.put("custPhone", consumer.getCusPhone());
			cusMap.put("custAddress", consumer.getCusAddress());
			cusMap.put("custGender", consumer.getCusSex());
			cusMap.put("status", "Success");
			
			Gson gson =new Gson();
			String json=gson.toJson(cusMap);
			
			PrintWriter out = res.getWriter();
			out.println(json);
//			System.out.println(json);
			out.close();
		}

		if ("add".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			Integer cus_id = Integer.valueOf(req.getParameter("cusId").trim());

			String cusName = req.getParameter("cusName");
			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (cusName == null || cusName.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if (!cusName.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			

			String cusAccount = req.getParameter("cusAccount").trim();
			String accountReg = "^[(a-zA-Z0-9_)]{10,30}$";

			if (cusAccount == null || cusAccount.trim().length() == 0) {
				errorMsgs.add("帳號: 請勿空白");
			} else if (!cusAccount.trim().matches(accountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("帳號: 只能是英文字母、數字和_ , 且長度必需在10到30之間");
			}

			boolean Duplicate = cusSvc.checkDuplicateAccount(cusAccount);

		    if (Duplicate) {
		       errorMsgs.add("帳號已重複");
		    } 
		    

			String cusPassword = req.getParameter("cusPassword").trim();
			String passwordReg = "^[(a-zA-Z0-9_)]{10,30}$";

			if (cusPassword == null || cusPassword.trim().length() == 0) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (!cusPassword.trim().matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("密碼: 只能是英文字母、數字和_ , 且長度必需在10到30之間");
			}

			String cusMail = req.getParameter("cusMail").trim();
//			String mailReg = "^[(a-zA-Z0-9_)]{10,30}$";
//			if (cus_mail == null || cus_mail.trim().length() == 0) {
//				errorMsgs.add("信箱: 請勿空白");
//			} else if (!cus_mail.trim().matches(mailReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("格式錯誤");
//			}

			String cusPhone = req.getParameter("cusPhone").trim();
//			String phoneReg = "^[(a-zA-Z0-9_)]{10,30}$";
//			if (cus_phone == null || cus_phone.trim().length() == 0) {
//				errorMsgs.add("手機: 請勿空白");
//			} else if (!cus_phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("手機格式錯誤");
//			}

			String cusAddress = req.getParameter("cusAddress").trim();
//			String addressReg = "^[\\u4e00-\\u9fa5\\w\\s()-,]+$";
//			if (cus_address == null || cus_address.trim().length() == 0) {
//				errorMsgs.add("地址: 請勿空白");
//			} else if (!cus_address.trim().matches(addressReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("地址格式錯誤");
//			}

			Integer cusSex = null;
			try {
				cusSex = Integer.valueOf(req.getParameter("cusSex").trim());
			} catch (NumberFormatException e) {
				cusSex = 0;
				errorMsgs.add("請填數字.");
			}

			Part part = req.getPart("cusPhoto");
			String str = String.valueOf(part).trim();
			byte[] cusPhoto = null;
			if (str == null || str.trim().length() == 0) {
				errorMsgs.add("哈");
			} else {
				BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
				cusPhoto = bis.readAllBytes();
			}
			
			  
			
//			Part part = req.getPart("cusPhoto");
//			String str = String.valueOf(part).trim();
//			byte[] cusPhoto = null;
//
//			if (str == null || str.trim().length() == 0) {
//			    // 如果未收到有效的檔案上傳，則自動上傳 1.jpg
//			    try {
//			        InputStream inputStream = getServletContext().getResourceAsStream("/eric/images/good.jpg");
//			        BufferedInputStream bis = new BufferedInputStream(inputStream);
//			        cusPhoto = bis.readAllBytes();
//			        System.out.println(part,"圖呢");
//			    } catch (IOException e) {
//			        e.printStackTrace();
//			        // 處理錯誤，例如記錄錯誤訊息或其他動作
//			    }
//			} else {
//			    BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
//			    cusPhoto = bis.readAllBytes();
//			}

			Consumer consumer = new Consumer();
			consumer.setCusName(cusName);
			consumer.setCusAccount(cusAccount);
			consumer.setCusPassword(cusPassword);
			consumer.setCusMail(cusMail);
			consumer.setCusPhone(cusPhone);
			consumer.setCusAddress(cusAddress);
			consumer.setCusSex(cusSex);
			consumer.setCusPhoto(cusPhoto);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("consumer", consumer); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/signup_info.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
//			ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
			consumer = cusSvc.addCus(cusName, cusAccount, cusPassword, cusMail, cusPhone, cusAddress,
					cusSex, cusPhoto);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/eric/listAllCus.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer cusId = Integer.valueOf(req.getParameter("cusId"));

			/*************************** 2.開始刪除資料 ***************************************/
//			ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
			cusSvc.deleteCus(cusId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/eric/listAllCus.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
