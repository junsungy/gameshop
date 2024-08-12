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


@WebServlet("/game/popular.go")
public class PopularGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GameDTO> gamelist = new GameService().selectByRank();
		request.setAttribute("games", gamelist);
		request.getRequestDispatcher("popular.jsp").forward(request, response);
	}
}
