package com.shinhan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.customer.CustDTO;
import com.shinhan.customer.CustService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/auth/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustService cService = new CustService();
		String username = request.getParameter("username");
		String pw = request.getParameter("password");
		CustDTO cust = cService.login(username, pw);
		if (cust == null) {
			request.setAttribute("message", "아이디 또는 비밀번호가 틀렸습니다");
		} else {
			// 로그인성공
			HttpSession session = request.getSession();

			session.setAttribute("loginCust", cust);

			response.sendRedirect("/webgame");
			return;
		}
		request.getRequestDispatcher("loginErr.jsp").forward(request, response);
	}
}
