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
import com.shinhan.game.GameDTO;
import com.shinhan.game.GameService;

/**
 * Servlet implementation class GameDetailServlet
 */
@WebServlet("/game/gameDetail.go")
public class GameDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gameId = Integer.parseInt(request.getParameter("gameid"));
		int purchased = 0;
		HttpSession session = request.getSession();
		CustDTO cust = (CustDTO)session.getAttribute("loginCust");
		GameDTO game = new GameService().selectById(gameId);
		List<GameDTO> gamelist = new GameService().selectByName(game.getMaker());
		if (cust != null ) {
			List<GameDTO> usergame = new GameService().selectByCust(cust.getUsername());
			for(GameDTO g:usergame) {
				if(g.getGame_id()==game.getGame_id()) {
					purchased = 1;
					break;
				}
			}
		}
		
		request.setAttribute("game", game);
		request.setAttribute("gamelist", gamelist);
		request.setAttribute("purchased", purchased);
		request.setAttribute("today", new Date());
		
		request.getRequestDispatcher("gameDetail.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
