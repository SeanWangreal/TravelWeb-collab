package com.tha103.gogoyu.room_ord.controller;

import java.io.*;

import java.math.BigDecimal;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.emp.model.*;
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1* 1024 * 1024, maxRequestSize = 10* 1024 * 1024)
@WebServlet("/shopping_hotelServlet")
public class shopping_hotelServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");  //�����ШD�Ѽƪ��s�X�]�w
		String Payment = req.getParameter("actionForPay"); //�����ШD�Ѽ�name=payment��queryString

		if ("pay".equals(Payment)) { // �Ӧ�shopping.jsp��"�e���I��"�ШD

//			List<String> errorMsgs = new LinkedList<String>(); //�N�Ҧ����~�T���]���@��list�A�e�ݴN�i�H�������~�T���[�H��V
//			req.setAttribute("errorMsgs", errorMsgs);

//			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
//			String str = req.getParameter("Scene");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("���K��Jԣ�z");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;// �{�����_
//			}
//
//			Integer SceneId = null;
//			try {
//				SceneId = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("���u�s���榡�����T");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;// �{�����_
//			}
//
//			/*************************** 2.�}�l�d�߸�� *****************************************/
//			SceneService SceneSvc = new SceneService();
//			Scene SceneVO = SceneSvc.getOneScene(SceneId);
//			if (SceneVO == null) {
//				errorMsgs.add("�d�L���");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;// �{�����_
//			}
//
//			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
//			req.setAttribute("successUpdateVO", SceneVO); // ��Ʈw���X��empVO����,�s�Jreq
//			String url = "/emp/listOneEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//			successView.forward(req, res);
//		}
//
//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.�����ШD�Ѽ� ****************************************/
//			Integer SceneId = Integer.valueOf(req.getParameter("sceneId"));
//
//			/*************************** 2.�}�l�d�߸�� ****************************************/
//			SceneService SceneSvc = new SceneService();
//			Scene SceneVO = SceneSvc.getOneScene(SceneId);
//
//			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
//			req.setAttribute("SceneVO", SceneVO); // ��Ʈw���X��empVO����,�s�Jreq
//			String url = "/emp/update_emp_input.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//			successView.forward(req, res);
//		}
//
//		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
//			Integer sceneId = Integer.valueOf(req.getParameter("sceneId").trim());
//
//			String sceneName = req.getParameter("sceneName");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (sceneName == null || sceneName.trim().length() == 0) {
//				errorMsgs.add("���u�m�W: �ФŪť�");
//			} else if (!sceneName.trim().matches(enameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//				errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//			}
//
//			String openTime = req.getParameter("openTime").trim();
//			if (openTime == null || openTime.trim().length() == 0) {
//				errorMsgs.add("�}��ɶ��ФŪť�");
//			}
//
//			String ticketPrice = req.getParameter("ticketPrice").trim();
//			if (ticketPrice == null || ticketPrice.trim().length() == 0) {
//				errorMsgs.add("��������ФŪť�");
//			}
//
//			String transInfo = req.getParameter("transInfo").trim();
//			if (transInfo == null || transInfo.trim().length() == 0) {
//				errorMsgs.add("��q��T�ФŪť�");
//			}
//
//			String parking = req.getParameter("parking").trim();
//			if (parking == null || parking.trim().length() == 0) {
//				errorMsgs.add("�������ФŪť�");
//			}
//
//			String address = req.getParameter("address").trim();
//			if (address == null || address.trim().length() == 0) {
//				errorMsgs.add("�a�}�ФŪť�");
//			}
//
//			BigDecimal lon = null;
//			try {
//				lon = new BigDecimal(req.getParameter("lon").trim());
//			} catch (Exception e) {
//				lon = new BigDecimal(0.0);
//				errorMsgs.add("�g�ײ��`");
//			}
//
//			BigDecimal lat = null;
//			try {
//				lat = new BigDecimal(req.getParameter("lat").trim());
//			} catch (Exception e) {
//				lat = new BigDecimal(0.0);
//				errorMsgs.add("�n�ײ��`");
//			}
//
//			String feature = req.getParameter("feature").trim();
//			if (feature == null || feature.trim().length() == 0) {
//				errorMsgs.add("�S��ФŪť�");
//			}
//
//			String picture = req.getParameter("picture").trim();
//			if (picture == null || picture.trim().length() == 0) {
//				errorMsgs.add("�Ϥ��ФŪť�");
//			}
//
////				
////				java.sql.Date hiredate = null;
////				try {
////hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
////				} catch (IllegalArgumentException e) {
////					hiredate=new java.sql.Date(System.currentTimeMillis());
////					errorMsgs.add("�п�J���!");
////				}
////
////				Double sal = null;
////				try {
////sal = Double.valueOf(req.getParameter("sal").trim());
////				} catch (NumberFormatException e) {
////					sal = 0.0;
////					errorMsgs.add("�~���ж�Ʀr.");
////				}
////
////				Double comm = null;
////				try {
////comm = Double.valueOf(req.getParameter("comm").trim());
////				} catch (NumberFormatException e) {
////					comm = 0.0;
////					errorMsgs.add("�����ж�Ʀr.");
////				}
////
////Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//			Scene SceneVO1 = new Scene();
//			SceneVO1.setSceneId(sceneId);
//			SceneVO1.setSceneName(sceneName);
//			SceneVO1.setOpenTime(openTime);
//			SceneVO1.setTicketPrice(ticketPrice);
//			SceneVO1.setTransInfo(transInfo);
//			SceneVO1.setParking(parking);
//			SceneVO1.setAddress(address);
//			SceneVO1.setLon(lon);
//			SceneVO1.setLat(lat);
//			SceneVO1.setFeature(feature);
//			SceneVO1.setPicture(picture);
////
////				// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("SceneVO", SceneVO1); // �t����J�榡���~��empVO����,�]�s�Jreq
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//				return; // �{�����_
//			}
////				
////				/***************************2.�}�l�ק���*****************************************/
//			SceneService SceneSvc = new SceneService();
//			Scene successUpdateVO = SceneSvc.updateScene(sceneId, sceneName, openTime, ticketPrice, transInfo, parking,
//					address, lon, lat, feature, picture);
////				
////				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//			req.setAttribute("successUpdateVO", successUpdateVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//			String url = "/emp/listOneEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//			successView.forward(req, res);
//		}
////
//		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
////				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//
//			String sceneName = req.getParameter("sceneName");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (sceneName == null || sceneName.trim().length() == 0) {
//				errorMsgs.add("���u�m�W: �ФŪť�");
//			} else if (!sceneName.trim().matches(enameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//				errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//			}
////				
//			String openTime = req.getParameter("openTime").trim();
//			if (openTime == null || openTime.trim().length() == 0) {
//				errorMsgs.add("�}��ɶ��ФŪť�");
//			}
//
//			String ticketPrice = req.getParameter("ticketPrice").trim();
//			if (ticketPrice == null || ticketPrice.trim().length() == 0) {
//				errorMsgs.add("��������ФŪť�");
//			}
//
//			String transInfo = req.getParameter("transInfo").trim();
//			if (transInfo == null || transInfo.trim().length() == 0) {
//				errorMsgs.add("��q��T�ФŪť�");
//			}
//
//			String parking = req.getParameter("parking").trim();
//			if (parking == null || parking.trim().length() == 0) {
//				errorMsgs.add("�������ФŪť�");
//			}
//
//			String address = req.getParameter("address").trim();
//			if (address == null || address.trim().length() == 0) {
//				errorMsgs.add("�a�}�ФŪť�");
//			}
//
//			BigDecimal lon = null;
//			try {
//				lon = new BigDecimal(req.getParameter("lon").trim());
//			} catch (Exception e) {
//				lon = new BigDecimal(0.0);
//				errorMsgs.add("�g�ײ��`");
//			}
//
//			BigDecimal lat = null;
//			try {
//				lat = new BigDecimal(req.getParameter("lat").trim());
//			} catch (Exception e) {
//				lat = new BigDecimal(0.0);
//				errorMsgs.add("�n�ײ��`");
//			}
//
//			String feature = req.getParameter("feature").trim();
//			if (feature == null || feature.trim().length() == 0) {
//				errorMsgs.add("�S��ФŪť�");
//			}
//
//			String picture = req.getParameter("picture").trim();
//			if (picture == null || picture.trim().length() == 0) {
//				errorMsgs.add("�Ϥ��ФŪť�");
//			}
//
////				
////Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
////
//			Scene SceneVO1 = new Scene();
//			SceneVO1.setSceneName(sceneName);
//			SceneVO1.setOpenTime(openTime);
//			SceneVO1.setTicketPrice(ticketPrice);
//			SceneVO1.setTransInfo(transInfo);
//			SceneVO1.setParking(parking);
//			SceneVO1.setAddress(address);
//			SceneVO1.setLon(lon);
//			SceneVO1.setLat(lat);
//			SceneVO1.setFeature(feature);
//			SceneVO1.setPicture(picture);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("SceneVO", SceneVO1); // �t����J�榡���~��empVO����,�]�s�Jreq
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//				return;
//			}
////				
////				/***************************2.�}�l�s�W���***************************************/
//				SceneService SceneSvc = new SceneService();
//				Scene successaddVO = SceneSvc.addScene(sceneName, openTime, ticketPrice, transInfo, parking,
//						address, lon, lat, feature, picture);
////				
////				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//				successView.forward(req, res);				
//		}
//		
//		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			===================================�����q��========================================
//			===================================�����q��========================================	
//			===================================�����q��========================================
			String Remove = req.getParameter("actionForRemove");
			if ("remove".equals(Remove)) { // �Ӧ�shopping.jsp��"�����q��"�ШD

				List<String> errorMsgs = new LinkedList<String>();//�N�Ҧ����~�T���]���@��list�A�e�ݴN�i�H�������~�T���[�H��V
				req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.�����ШD�Ѽ�***************************************/
				Integer SceneId = Integer.valueOf(req.getParameter("sceneId"));
//				
//				/***************************2.�}�l�R�����***************************************/
				SceneService SceneSvc = new SceneService();
				SceneSvc.deleteScene(SceneId);

//				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
			}
			
			
//			===================================�����q��========================================
//			===================================�����q��========================================	
//			===================================�����q��========================================
			
			
			
			
		}
	}
}

