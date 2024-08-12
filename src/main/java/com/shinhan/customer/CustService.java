package com.shinhan.customer;

import java.sql.SQLException;

import com.shinhan.util.DBUtil;

public class CustService {

	CustDAO custDAO = new CustDAO();
	
	// 로그인
	public CustDTO login(String username, String pw) {
		return custDAO.login(username, pw);
	}
	
	// 계정 생성
	public int signin(String username, String pw) {
		return custDAO.signin(username, pw);
	}
	
	// username 조회
	public CustDTO selectByUsername(String username) {
		return custDAO.selectByUsername(username);
	}
	
	// 지갑 충전
	public int charge(String username, int credit) {
		return custDAO.charge(username, credit);
	}
	
	// 지갑 조회
	public int creditCheck(String username) {
		return custDAO.creditCheck(username);
	}
}
