package com.shinhan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.customer.CustDTO;
import com.shinhan.game.GameService;

/**
 * Servlet implementation class RefundServlet
 */
@WebServlet("/cust/refund.do")
public class RefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustDTO cust = (CustDTO)session.getAttribute("loginCust");
		String username = cust.getUsername();
		int gameId = Integer.parseInt(request.getParameter("gameid"));
		
		int result = new GameService().refund(username, gameId);
		String msg = "환불되었습니다.";
		if (result == 0) {
			msg = "환불 대상이 아닙니다.";
		}
		request.setAttribute("message", msg);
		request.getRequestDispatcher("refundResult.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
