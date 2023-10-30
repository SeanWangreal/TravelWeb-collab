package com.tha103.gogoyu.company.controller;

import java.io.IOException;
import java.io.BufferedInputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.gogoyu.company.model.CompanyService;


@WebServlet("/CompPhotoPrintHServlet")
public class CompPhotoPrintHServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CompanyService compSrc = null;

	public void init() throws ServletException {
		compSrc = new CompanyService();
	}

	public void destroy() {
		compSrc = null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		byte[] photo = null;
		String compId = req.getParameter("comp_id");
		System.out.printf("Company Id %s\n", compId);
		if (compId != null) {
			photo = compSrc.getCompPhoto(Integer.valueOf(compId));
		}
		out.write(photo);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
