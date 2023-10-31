package com.tha103.gogoyu.company.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.gogoyu.company.model.Company;
import com.tha103.gogoyu.company.model.CompanyService;

@WebServlet("/hollow/CompanyPhotoServlet")
public class CompanyPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CompanyService roomSrc = null;

	public void init() throws ServletException {
		roomSrc = new CompanyService();
	}

	public void destroy() {
		roomSrc = null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		Company company = null;
		String compId = req.getParameter("compId");
		System.out.println(compId);
		
		if (compId != null) {
			company = roomSrc.getOneCompany(Integer.valueOf(compId));
			if(company.getCompPhoto() !=null) {
				out.write(company.getCompPhoto());		
			}else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);//404 p.134,140
				InputStream in=getServletContext().getResourceAsStream("/NoData/none2.jpg");
				byte[] b=new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
