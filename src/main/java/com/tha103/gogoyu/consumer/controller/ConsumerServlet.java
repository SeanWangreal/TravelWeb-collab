package com.tha103.gogoyu.consumer.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.security.SecureRandom;

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
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.consumer.model.MailService;
import com.tha103.gogoyu.consumer.model.ConsumerServiceHibernate;
import com.tha103.gogoyu.planning.model.PlanningServiceHibernate;

@WebServlet("/eric/ConsumerServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ConsumerServlet extends HttpServlet {

//	String passrandom = null;

	// ****************************圖片方法***************************//
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	// ****************************驗證碼方法***************************//
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final SecureRandom random = new SecureRandom();

	public static String generateVerificationCode() {
		StringBuilder code = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			code.append(randomChar);
		}
		return code.toString();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
		String passrandom = null;

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
			if (consumer != null && !password.equals(consumer.getCusPassword())) {
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
		//**************寄驗證信**********//
		if ("mail".equals(action)) { // 來自select_page.jsp的請求
//			Map<String, Object> errorMsgs = new HashMap<String, Object>();

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);


			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String mail = req.getParameter("cusMail");
			String mailReg = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
			HttpSession session = req.getSession();

			if (mail == null || (mail.trim()).length() == 0) {
				errorMsgs.add("請輸入信箱");
			}else if (!mail.trim().matches(mailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("信箱格式錯誤 範例XXXX@XXX.XXX");
			}
			session.setAttribute("mail", mail);
			

			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/signin_info.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始寄信資料 *****************************************/

			MailService sentMail = new MailService();
			String to = mail;
			String subject = "歡迎註冊GOGOYU會員";

			String passRandom = generateVerificationCode();
			session.setAttribute("passRandom", passRandom);
			System.out.println(passRandom);

			String messageText = " 您好!\n\n[" + passRandom + "]\n\n為您的驗證碼" + "\n";

			new Thread(() -> sentMail.sendMail(to, subject, messageText)).start();

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			String url = "/eric/signin_info.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 jsp
			successView.forward(req, res);
		}
		if ("forgotpassword".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String account = req.getParameter("cusAccount");
			if (account == null || (account.trim()).length() == 0) {
				errorMsgs.add("請輸入帳號");
			}
			String mail = req.getParameter("cusMail");
			if (mail == null || (mail.trim()).length() == 0) {
				errorMsgs.add("請輸入信箱");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/forgotpassword.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始寄信資料 *****************************************/
			Consumer consumer = cusSvc.checkDuplicateAccount(account);
			if (consumer == null) {
				System.out.println("沒帳號");
				errorMsgs.add("查無帳號");
			}
			
			if (consumer != null && !mail.equals(consumer.getCusMail())) {
				System.out.println("密碼錯了");
				errorMsgs.add("此帳號不是對應到此信箱");
			}
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/forgotpassword.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			MailService sentMail = new MailService();
			String to = mail;
			String subject = "親愛的GOGOYU會員您好";
			String passRandom = consumer.getCusPassword();
			System.out.println(passRandom);

			String messageText = " 您好!\n\n[" + passRandom + "]\n\n為您的密碼" + "\n";

			new Thread(() -> sentMail.sendMail(to, subject, messageText)).start();

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			String url = "/eric/signin.jsp";
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
		// *******************更新會員資料*******************//
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer cusId = (Integer) req.getSession().getAttribute("cusId");

			String cusPassword = req.getParameter("cusPassword").trim();
			String passwordReg = "^[(a-zA-Z0-9)]{10,30}$";

			if (cusPassword == null || cusPassword.trim().length() == 0) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (!cusPassword.trim().matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("密碼: 只能是英文字母、數字 , 且長度必需在10到30之間");
			}

			String cusMail = req.getParameter("cusMail").trim();
			String mailReg = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
			if (cusMail == null || cusMail.trim().length() == 0) {
				errorMsgs.add("信箱: 請勿空白");
			} 
			else if (!cusMail.trim().matches(mailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("信箱格式錯誤 範例XXXX@XXX.XXX");
			}

			String cusPhone = req.getParameter("cusPhone").trim();
			String phoneReg = "^[(0-9)]{10}$";
			if (cusPhone == null || cusPhone.trim().length() == 0) {
				errorMsgs.add("手機: 請勿空白");
			} else if (!cusPhone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("只能是數字,10碼");
			}

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
				consumer = cusSvc.getOneCus(cusId);
				cusPhoto = consumer.getCusPhoto();// 抓原本舊圖
			}

			Consumer consumer = null;
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Consumer", consumer); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/personal_detail_update.jsp");
				failureView.forward(req, res);

				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			consumer = cusSvc.updateCus(cusId, null, null, cusPassword, cusMail, cusPhone, cusAddress, null, cusPhoto);

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
		// *********************註冊帳號***********************//
		if ("add".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String cusName = req.getParameter("cusName");
			String nameReg = "^[(\u4e00-\u9fa5)]{2,10}$";
			if (cusName == null || cusName.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if (!cusName.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("姓名: 只能是中文, 且長度必需在2到10之間");
			}

			String cusAccount = req.getParameter("cusAccount").trim();
			String accountReg = "^[(a-zA-Z0-9)]{10,30}$";

			if (cusAccount == null || cusAccount.trim().length() == 0) {
				errorMsgs.add("帳號: 請勿空白");
			} else if (!cusAccount.trim().matches(accountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("帳號: 只能是英文字母、數字 , 且長度必需在10到30之間");
			}

			Consumer Duplicate = cusSvc.checkDuplicateAccount(cusAccount);

			if (Duplicate != null) {
				errorMsgs.add("帳號已重複");
			}

			String cusPassword = req.getParameter("cusPassword").trim();
			String passwordReg = "^[(a-zA-Z0-9)]{10,30}$";

			if (cusPassword == null || cusPassword.trim().length() == 0) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (!cusPassword.trim().matches(passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("密碼: 只能是英文字母、數字 , 且長度必需在10到30之間");
			}
			
			String cusMailVerify = (String) req.getSession().getAttribute("cusMail");

			String cusMail = req.getParameter("cusMail").trim();
			String mailReg = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
			if (cusMail == null || cusMail.trim().length() == 0) {
				errorMsgs.add("信箱: 請勿空白");
			} else if (!cusMail.trim().matches(mailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("信箱格式錯誤 範例XXXX@XXX.XXX");
			} else if(!cusMail.equals(cusMailVerify)) {
				errorMsgs.add("信箱不一致,無法驗證");
			}

			String cusPhone = req.getParameter("cusPhone").trim();
			String phoneReg = "^[(0-9)]{10}$";
			if (cusPhone == null || cusPhone.trim().length() == 0) {
				errorMsgs.add("手機: 請勿空白");
			} else if (!cusPhone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("只能是數字,10碼");
			}

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
				e.printStackTrace();
				cusSex = 0;
				errorMsgs.add("請填數字.");
			}

			List<Integer> cartIds = new ArrayList<>();
			for (int i = 1; i < 6; i++) { // 增加五個cartid
				cartIds.add(i);
			}
			byte[] cusPhoto = null;

			Part part = req.getPart("cusPhoto");
			if (part == null || part.getSize() == 0) {
				try {
					ServletContext context = getServletContext();
					String img = context.getRealPath("/eric/img/001.jpg");
					FileInputStream fis = new FileInputStream(img);
					BufferedInputStream bis = new BufferedInputStream(fis);
					cusPhoto = bis.readAllBytes();
				} catch (IOException e) {
					e.printStackTrace();
					// 處理錯誤，例如記錄錯誤訊息或其他動作
				}
			} else {
				InputStream is = part.getInputStream();
				ByteArrayOutputStream byteArros = new ByteArrayOutputStream();
				byte[] buf = new byte[4 * 1024];
				int len;
				while ((len = is.read(buf)) != -1) {
					byteArros.write(buf, 0, len);
				}
				cusPhoto = byteArros.toByteArray();
				byteArros.close();

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
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Consumer", consumer); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/signin_realinfo.jsp");
				failureView.forward(req, res);
				return;
			}
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
		if ("verify".equals(action)) { // 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String cusMail = req.getParameter("cusMail").trim();
			String mailReg = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
			if (cusMail == null || cusMail.trim().length() == 0) {
				errorMsgs.add("信箱: 請勿空白");
			} else if (!cusMail.trim().matches(mailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("信箱格式錯誤 範例XXXX@XXX.XXX");
			}
			HttpSession session = req.getSession();
			session.setAttribute("cusMail", cusMail);

			String passrandom2 = (String) req.getSession().getAttribute("passRandom");
			String CAPTCHA = req.getParameter("CAPTCHA");
			if (CAPTCHA.trim().length() == 0) {
				errorMsgs.add("請填入驗證碼");
			} else if (!CAPTCHA.equals(passrandom2)) {
				errorMsgs.add("驗證碼錯誤");
				System.out.println("驗證碼:" + passrandom2);
			}

			Consumer consumer = new Consumer();
		
			consumer.setCusMail(cusMail);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Consumer", consumer); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/eric/signin_info.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/

			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			RequestDispatcher failureView = req.getRequestDispatcher("/eric/signin_realinfo.jsp");
			failureView.forward(req, res);

		}
		if ("Logout".equals(action)) { // 來自select_page.jsp的請求
			HttpSession session = req.getSession();
			// 清除資料
			if (session != null) {
				session.invalidate(); // 使会话无效
				String url = "/mhl/home.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}
			System.out.print("您已成功登出退出系統!");
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