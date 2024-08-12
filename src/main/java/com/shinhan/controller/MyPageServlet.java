package com.shinhan.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.customer.CustDTO;
import com.shinhan.customer.CustService;
import com.shinhan.game.GameDTO;
import com.shinhan.game.GameService;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/cust/mypage.do")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		HttpSession session = request.getSession();
		CustDTO cust = (CustDTO)session.getAttribute("loginCust");
		
		if (cust.getUsername().equals(username)) {
			CustDTO custInfo = new CustService().selectByUsername(username);
			List<GameDTO> gamelist = new GameService().selectByCust(username);
			
			request.setAttribute("userInfo", custInfo);
			request.setAttribute("games", gamelist);
			request.setAttribute("today", new Date());
			request.getRequestDispatcher("mypage.jsp").forward(request, response);
		}else {
			response.sendRedirect("/webgame/cust/mypage.do?username="+cust.getUsername());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
