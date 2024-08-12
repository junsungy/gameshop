package com.shinhan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.customer.CustDTO;
import com.shinhan.game.GameDTO;
import com.shinhan.game.GameService;

/**
 * Servlet implementation class UpdateDiscountServlet
 */
@WebServlet("/game/updateDiscount.do")
public class UpdateDiscountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustDTO cust = (CustDTO)session.getAttribute("loginCust");
		if (cust.getIs_admin() != 1) {
			response.sendRedirect("/webgame");
		}else {
			List<GameDTO> gamelist = new GameService().selectAll();
			request.setAttribute("games", gamelist);
			request.getRequestDispatcher("updateDiscount.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		double dRate = Double.parseDouble(request.getParameter("dRate"));
		int result = new GameService().modifyDiscount(gameId, dRate);
		if (result == 0) {
			System.out.println("수정 실패: 0과 1 사이의 값을 입력하세요");
		}
		response.sendRedirect("updateDiscount.do");
	}

}
