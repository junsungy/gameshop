package com.shinhan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinhan.customer.CustDTO;
import com.shinhan.customer.CustService;

/**
 * Servlet implementation class UsernameChkServlet
 */
@WebServlet("/auth/usernameChk.go")
public class UsernameChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String msg = "1";
		CustDTO cust = new CustService().selectByUsername(username);
		
		if(cust == null) {
			msg = "0";
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(msg);
	}

}
