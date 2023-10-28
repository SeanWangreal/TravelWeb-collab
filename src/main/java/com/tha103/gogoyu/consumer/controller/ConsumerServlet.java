package com.tha103.gogoyu.consumer.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.consumer.model.ConsumerServiceHibernate;
import com.tha103.gogoyu.planning.model.PlanningServiceHibernate;

@WebServlet("/eric/ConsumerServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ConsumerServlet extends HttpServlet {
	
	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

	        // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	        // ●1) 登入你的Gmail的: 
	        // ●2) 點選【管理你的 Google 帳戶】
	        // ●3) 點選左側的【安全性】
	       
	        // ●4) 完成【兩步驟驗證】的所有要求如下:
	        //     ●4-1) (請自行依照步驟要求操作之.....)
	       
	        // ●5) 完成【應用程式密碼】的所有要求如下:
	        //     ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
	        //     ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
	        //     ●5-3) 最後按【產生】密碼
			final String myGmail = "cyj92016@gmail.com";
			final String myGmail_password = "adsl123123";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	public static InputStream getPictureStream(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}
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

		if ("getOne_For_Login".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String account = req.getParameter("cusAccount");
			if (account == null || (account.trim()).length() == 0) {
				errorMsgs.add("請輸入帳號");
			}
			String password = req.getParameter("cusPassword");
			if (password != null && password == null || (password.trim()).length() == 0) {
				errorMsgs.add("請輸入密碼");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/signin.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			Consumer consumer = cusSvc.checkDuplicateAccount(account);
			if (consumer == null) {
				System.out.println("沒帳號");
				errorMsgs.add("查無帳號");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/signin.jsp");
				failureView.forward(req, res);
			}
			if (consumer != null&&!password.equals(consumer.getCusPassword())) {
				System.out.println("密碼錯了");
				errorMsgs.add("密碼錯誤");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/signin.jsp");
				failureView.forward(req, res);
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("consumer", consumer); // 資料庫取出的empVO物件,存入req
			req.getSession().setAttribute("cusId", consumer.getCusId());
			String url = "/eric/personal_detail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 jsp
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
				errorMsgs.put("noCustId", "請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				errorMsgs.put("status", "Failed");
				Gson gson = new Gson();
				String json = gson.toJson(errorMsgs);

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
				errorMsgs.put("wrongId", "會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/hollow/backend.jsp");
//				failureView.forward(req, res);
				errorMsgs.put("status", "Failed");
				Gson gson = new Gson();
				String json = gson.toJson(errorMsgs);

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
				errorMsgs.put("noData", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath()+"ken/com_mem.jsp");
//				failureView.forward(req, res);
				errorMsgs.put("status", "Failed");
				Gson gson = new Gson();
				String json = gson.toJson(errorMsgs);

				PrintWriter out = res.getWriter();
				out.println(json);
				System.out.println(json);
				out.close();

				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			Map<String, Object> cusMap = new HashMap<String, Object>();
			cusMap.put("cusId", consumer.getCusId());
			cusMap.put("cusName", consumer.getCusName());
			cusMap.put("cusAccount", consumer.getCusAccount());
			cusMap.put("cusMail", consumer.getCusMail());
			cusMap.put("cusPhone", consumer.getCusPhone());
			cusMap.put("cusAddress", consumer.getCusAddress());
			cusMap.put("cusGender", consumer.getCusSex());
			cusMap.put("status", "Success");

			Gson gson = new Gson();
			String json = gson.toJson(cusMap);

			PrintWriter out = res.getWriter();
			out.println(json);
			System.out.println(json);
			out.close();
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			System.out.println("testssss");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
//			Integer cusId = Integer.valueOf(req.getParameter("cusId"));
			Integer cusId = (Integer) req.getSession().getAttribute("cusId");
			System.out.println(cusId);

			/*************************** 2.開始查詢資料 ****************************************/
			Consumer consumer = cusSvc.getOneCus(cusId);

			System.out.println("consumer = " + consumer);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("consumer", consumer); // 資料庫取出的empVO物件,存入req
			String url = "/eric/personal_detail_update.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer cusId = Integer.valueOf(req.getParameter("cusId").trim());
			Integer cusId = (Integer) req.getSession().getAttribute("cusId");



			String accountReg = "^[(a-zA-Z0-9_)]{10,30}$";

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


			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("consumer", consumer); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/eric/personal_detail.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}

			Consumer consumer = null;
			/*************************** 2.開始修改資料 *****************************************/
			consumer = cusSvc.updateCus(cusId,null,null, cusPassword, cusMail, cusPhone, cusAddress, 
					null,cusPhoto);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("consumer", consumer); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/eric/personal_detail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("updFromBackend".equals(action)) {
			Map<String, Object> errorMsgs = new HashMap<String, Object>();
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer cusId = Integer.valueOf(req.getParameter("custId").trim());
			System.out.println(cusId);

			String cusName = req.getParameter("custName");
//			String compNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (cusName == null || cusName.trim().length() == 0) {
				errorMsgs.put("wrongName", "會員名稱: 請勿空白");
			}
			System.out.println(cusName);
//			else if (!compName.trim().matches(compNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongName","公司名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}

			Integer cusGender = Integer.valueOf(req.getParameter("custGender"));
			System.out.println(cusGender);

			String cusAccount = req.getParameter("custAccount");
//			String compAccountReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (cusAccount == null || cusAccount.trim().length() == 0) {
				errorMsgs.put("wrongAccount", "會員帳號: 請勿空白");
			}
			System.out.println(cusAccount);
//			else if (!compAccount.trim().matches(compAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongAccount","公司帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
//			}

			String cusMail = req.getParameter("custMail");
//			String compMailReg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//網路上查的Email正規表達式
			if (cusMail == null || cusMail.trim().length() == 0) {
				errorMsgs.put("wrongMail", "會員信箱: 請勿空白");
			}
			System.out.println(cusMail);
//			else if (!compMail.trim().matches(compMailReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongMail","公司信箱: 格式錯誤");
//			}

			String cusPhone = req.getParameter("custPhone").trim();
//			String compPhoneReg = "^0[(0-9)]{1,2}-[(0-9)]{8}$";
			if (cusPhone == null || cusPhone.trim().length() == 0) {
				errorMsgs.put("wrongPhone", "會員電話: 請勿空白");
			}
			System.out.println(cusPhone);
//			else if (!compPhone.trim().matches(compPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.put("wrongPhone","公司電話: 格式錯誤");
//			}

			String cusAddress = req.getParameter("custAddress").trim();
//			String compAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]$";
			if (cusAddress == null || cusAddress.trim().length() == 0) {
				errorMsgs.put("wrongAddress", "會員地址: 請勿空白");
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

				Gson gson = new Gson();
				String json = gson.toJson(errorMsgs);

				PrintWriter out = res.getWriter();
				out.println(json);
				System.out.println(json);
				out.close();

				return;// 程式中斷
			}

			ConsumerServiceHibernate consumerSvc = new ConsumerServiceHibernate();
			consumerSvc.updFromBackend(cusId, cusName, cusAccount, cusMail, cusPhone, cusAddress, cusGender);
			Consumer consumer = consumerSvc.getOneCus(cusId);

			Map<String, Object> cusMap = new HashMap<String, Object>();
			cusMap.put("custId", consumer.getCusId());
			cusMap.put("custName", consumer.getCusName());
			cusMap.put("custAccount", consumer.getCusAccount());
			cusMap.put("custMail", consumer.getCusMail());
			cusMap.put("custPhone", consumer.getCusPhone());
			cusMap.put("custAddress", consumer.getCusAddress());
			cusMap.put("custGender", consumer.getCusSex());
			cusMap.put("status", "Success");

			Gson gson = new Gson();
			String json = gson.toJson(cusMap);

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

			Consumer Duplicate = cusSvc.checkDuplicateAccount(cusAccount);

			if (Duplicate != null) {
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
				e.printStackTrace();
				cusSex = 0;
				errorMsgs.add("請填數字.");
			}
			
		    List<Integer> cartIds = new ArrayList<>();
		    for (int i = 1; i < 6; i++) {			//增加五個cartid
		        cartIds.add(i);
		    }
		    byte[] cusPhoto = null;

			Part part = req.getPart("cusPhoto");
			if (part == null || part.getSize()== 0) {
				try {
					FileInputStream fis = new FileInputStream(req.getContextPath()+"/eric/img/001.jpg");
					BufferedInputStream bis = new BufferedInputStream(fis);
					cusPhoto = bis.readAllBytes();
					System.out.println("有嗎");
				} catch (IOException e) {
					e.printStackTrace();
					// 處理錯誤，例如記錄錯誤訊息或其他動作
				}
			}
			Consumer consumer = new Consumer();
			consumer.setCusName(cusName);
			consumer.setCusAccount(cusAccount);
			consumer.setCusPassword(cusPassword);
			consumer.setCusMail(cusMail);
			consumer.setCusPhone(cusPhone);
			consumer.setCusAddress(cusAddress);
			consumer.setCusSex(cusSex);
			consumer.setCusPhoto(cusPhoto);
			/*************************** 2.開始新增資料 ***************************************/

			consumer = cusSvc.addCus(cusName, cusAccount, cusPassword, cusMail, cusPhone, cusAddress, cusSex, cusPhoto);
		    PlanningServiceHibernate planningSvc = new PlanningServiceHibernate();
		    for (Integer cartId : cartIds) {
		        planningSvc.add(consumer.getCusId(), cartId); // 把cartid存進去
		    }
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			req.getSession().setAttribute("cusId", consumer.getCusId());
			res.sendRedirect(req.getContextPath() + "/mhl/home.jsp");
			return;
		}
		if ("Logout".equals(action)) { // 來自select_page.jsp的請求
			HttpSession session = req.getSession();
		        // 清除資料
			if (session != null) {
			    session.invalidate(); // 使会话无效
			    String url = "/mhl/home.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}	System.out.print("您已成功登出退出系統!");
		        System.out.close();
		    }

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ***************************************/
//			Integer cusId = Integer.valueOf(req.getParameter("cusId"));
//
//			/*************************** 2.開始刪除資料 ***************************************/
//			cusSvc.deleteCus(cusId);
//
//			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//			String url = "/eric/listAllCus.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//			successView.forward(req, res);
//		}
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String str = req.getParameter("cusId");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入會員編號");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/eric/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			Integer cusId = null;
//			try {
//				cusId = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("員工編號格式不正確");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/eric/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 2.開始查詢資料 *****************************************/
////			ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
//			Consumer consumer = cusSvc.getOneCus(cusId);
//			if (consumer == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/eric/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("consumer", consumer); // 資料庫取出的empVO物件,存入req
//			String url = "/eric/listOneCus.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//		}
		
	
		
	}
}