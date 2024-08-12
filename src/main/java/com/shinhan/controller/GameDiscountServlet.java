package com.shinhan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinhan.game.GameDTO;
import com.shinhan.game.GameService;

/**
 * Servlet implementation class GameDiscountServlet
 */
@WebServlet("/game/gameDiscount.go")
public class GameDiscountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GameDTO> gamelist = new GameService().selectByDiscount();
		request.setAttribute("games", gamelist);
		request.getRequestDispatcher("gameDiscount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
