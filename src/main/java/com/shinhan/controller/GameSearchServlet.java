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
 * Servlet implementation class GameSearchServlet
 */
@WebServlet("/game/search.go")
public class GameSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchItem = request.getParameter("searchItem");
		
		List<GameDTO> gamelist = new GameService().selectByName(searchItem);
		request.setAttribute("games", gamelist);
		request.setAttribute("searchItem", searchItem);
		
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
