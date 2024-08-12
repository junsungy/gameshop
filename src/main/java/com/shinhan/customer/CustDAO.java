package com.shinhan.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinhan.game.GameDTO;
import com.shinhan.util.DBUtil;

public class CustDAO {

	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	// 로그인
	public CustDTO login(String username, String pw) {
		CustDTO cust = null;
		String sql = "select * from customers where username = ? and pw = ?";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pw);
			rs = pst.executeQuery();

			while (rs.next()) {
				cust = makeCust(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return cust;
	}

	// 계정 생성
	public int signin(String username, String pw) {
		int result = 0;
		String sql = "insert into customers values(?,?,0,0)";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pw);
			result = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("계정 생성에 실패했습니다.");
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return result;
	}
	
	// username 조회
	public CustDTO selectByUsername(String username) {
		CustDTO cust = null;
		String sql = "select * from customers where username = ?";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				cust = makeCust(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return cust;
	}

	// 지갑 충전
	public int charge(String username, int credit) {
		int result = 0;
		String sql = "update customers set credit = credit + ? where username = ?";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, credit);
			pst.setString(2, username);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return result;
	}
	
	// 지갑 조회
	public int creditCheck(String username) {
		int credit = 0;
		String sql = "select credit from customers where username = ?";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				credit = rs.getInt("credit");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return credit;
	}

	private CustDTO makeCust(ResultSet rs2) throws SQLException {
		CustDTO cust = new CustDTO();

		cust.setCredit(rs2.getInt("credit"));
		cust.setIs_admin(rs2.getInt("is_admin"));
		cust.setPw(rs2.getString("pw"));
		cust.setUsername(rs2.getString("username"));

		return cust;
	}

}
