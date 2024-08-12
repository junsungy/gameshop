package com.shinhan.game;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;


public class GameService {
	GameDAO gameDAO = new GameDAO();
	
	// 게임 모두 조회
	public List<GameDTO> selectAll() {
		return gameDAO.selectAll();
	}
	
	// 특정 아이디 게임 조회
	public GameDTO selectById(int gameid) {
		return gameDAO.selectById(gameid);
	}
	
	// 검색
	public List<GameDTO> selectByName(String search) {
		return gameDAO.selectByName(search);
	}
	
	// 할인 중인 게임 조회
	public List<GameDTO> selectByDiscount() {
		return gameDAO.selectByDiscount();
	}
	
	// 구매
	public int buy(String username, int gameId) {
		return gameDAO.buy(username, gameId);
	}

	// 유저 게임 조회
	public List<GameDTO> selectByCust(String username) {
		return gameDAO.selectByCust(username);
	}
	
	// 다운로드
	public String download(String username, int gameId) {
		return gameDAO.download(username, gameId);
	}
	
	// 게임 추가
	public int insertGame(GameDTO game) {
		return gameDAO.insertGame(game);
	}
	
	// 할인율 조정
	public int modifyDiscount(int gameId, double dRate) {
		return gameDAO.modifyDiscount(gameId, dRate);
	}
	
	// 환불
	public int refund(String username, int gameId) {
		return gameDAO.refund(username, gameId);
	}
	
	// 인기 게임 5개
	public List<GameDTO> selectByRank() {
		return gameDAO.selectByRank();
	}
}
