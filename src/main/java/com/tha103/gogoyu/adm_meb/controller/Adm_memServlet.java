package com.tha103.gogoyu.adm_meb.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.gogoyu.adm_meb.model.*;

@WebServlet("/hollow/AdmServlet")
public class Adm_memServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("admId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hollow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer admId = null;
				try {
					admId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hollow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Adm_mebService admSvc = new Adm_mebService();
				Adm_meb admVO = admSvc.getOneAdm(admId);
				if (admVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hollow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫取出的empVO物件,存入req
				String url = "/hollow/listOneAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer empno = Integer.valueOf(req.getParameter("admId"));
				
				/***************************2.開始查詢資料****************************************/
				Adm_mebService empSvc = new Adm_mebService();
				Adm_meb empVO = empSvc.getOneAdm(empno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("admVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/hollow/update_adm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer admId = Integer.valueOf(req.getParameter("admId").trim());
								
				String admName = req.getParameter("admName");
						String admNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
						if (admName == null || admName.trim().length() == 0) {
							errorMsgs.add("管理員姓名: 請勿空白");
						} else if(!admName.trim().matches(admNameReg)) { //以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			            }
						
				String admAcc = req.getParameter("admAcc");
						String admAccReg = "^[(a-zA-Z0-9_)]{2,10}$";
						if (admAcc == null || admAcc.trim().length() == 0) {
							errorMsgs.add("管理員帳號: 請勿空白");
						} else if(!admAcc.trim().matches(admAccReg)) { //以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("管理員帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
						}
						
				String admPass = req.getParameter("admPass");
						String admPassReg = "^[(a-zA-Z0-9_)]{2,10}$";
						if (admPass == null || admPass.trim().length() == 0) {
							errorMsgs.add("管理員密碼: 請勿空白");
						} else if(!admPass.trim().matches(admPassReg)) { //以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("管理員密碼: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
						}
				
				Adm_meb admVO = new Adm_meb();
				
				admVO.setAdmId(admId);
				admVO.setAdmName(admName);
				admVO.setAdmAccount(admAcc);
				admVO.setAdmPassword(admPass);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hollow/update_adm_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Adm_mebService empSvc = new Adm_mebService();
				admVO = empSvc.updateAdm(admId, admName, admAcc, admPass);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/hollow/listOneAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String admName = req.getParameter("admName");
					String admNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (admName == null || admName.trim().length() == 0) {
						errorMsgs.add("管理員姓名: 請勿空白");
					} else if(!admName.trim().matches(admNameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
			String admAcc = req.getParameter("admAcc");
					String admAccReg = "^[(a-zA-Z0-9_)]{2,10}$";
					if (admAcc == null || admAcc.trim().length() == 0) {
						errorMsgs.add("管理員帳號: 請勿空白");
					} else if(!admAcc.trim().matches(admAccReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("管理員帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
					}
					
			String admPass = req.getParameter("admPass");
					String admPassReg = "^[(a-zA-Z0-9_)]{2,10}$";
					if (admPass == null || admPass.trim().length() == 0) {
						errorMsgs.add("管理員密碼: 請勿空白");
					} else if(!admPass.trim().matches(admPassReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("管理員密碼: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
					}
			
				Adm_meb admVO = new Adm_meb();

				admVO.setAdmName(admName);
				admVO.setAdmAccount(admAcc);
				admVO.setAdmPassword(admPass);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hollow/addAdm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Adm_mebService empSvc = new Adm_mebService();
				admVO = empSvc.addAdm(admName, admAcc, admPass);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/hollow/listAllAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer admId = Integer.valueOf(req.getParameter("admId"));
				
				/***************************2.開始刪除資料***************************************/
				Adm_mebService empSvc = new Adm_mebService();
				empSvc.deleteAdm(admId);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/hollow/listAllAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
		}
	}
}
