package com.shinhan.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;

public class GameDAO {

	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	// 게임 모두 조회
	public List<GameDTO> selectAll() {
		List<GameDTO> gamelist = new ArrayList<GameDTO>();
		String sql = "select * from games order by 8 DESC";
		conn = DBUtil.dbConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				GameDTO game = makeGame(rs);
				gamelist.add(game);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return gamelist;
	}

	// 특정 아이디 게임 조회
	public GameDTO selectById(int gameid) {
		GameDTO game = null;
		String sql = "select * from games where game_id = ?";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, gameid);
			rs = pst.executeQuery();
			if (rs.next()) {
				game = makeGame(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return game;
	}
	
	// 검색
	public List<GameDTO> selectByName(String search) {
		List<GameDTO> gamelist = new ArrayList<GameDTO>();
		String sql = "select * from games " + "where game_name like '%'||upper(?)||'%' "
				+ "or alternative_name like '%'||upper(?)||'%' " + "or maker like '%'||upper(?)||'%' " + "or genre like '%'||upper(?)||'%' "
				+ "order by 2";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, search);
			pst.setString(2, search);
			pst.setString(3, search);
			pst.setString(4, search);
			rs = pst.executeQuery();
			while (rs.next()) {
				GameDTO game = makeGame(rs);
				gamelist.add(game);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return gamelist;
	}

	// 할인 중인 게임 조회
	public List<GameDTO> selectByDiscount() {
		List<GameDTO> gamelist = new ArrayList<GameDTO>();
		String sql = "select * from games where discount_rate > 0 order by 2";
		conn = DBUtil.dbConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				GameDTO game = makeGame(rs);
				gamelist.add(game);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}

		return gamelist;
	}

	// 게임 구매
	public int buy(String username, int gameId) {
		int result = 0;
		String sql = "INSERT INTO PURCHASE VALUES (?, ?, (SELECT ROUND(PRICE*(1-DISCOUNT_RATE)) FROM GAMES WHERE GAME_ID = ?), SYSDATE)";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setInt(2, gameId);
			pst.setInt(3, gameId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("구매할 수 없습니다.");
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}

	// 유저 게임 조회
	public List<GameDTO> selectByCust(String username) {
		List<GameDTO> gamelist = new ArrayList<GameDTO>();
		String sql = "select * from purchase join games using (game_id) where username = ? order by 4";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();

			while (rs.next()) {
				GameDTO game = makeGame(rs);
				gamelist.add(game);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return gamelist;
	}

	// 다운로드
	public String download(String username, int gameId) {
		String gameName = null;
		String sql = "select game_name from purchase join games using (game_id) "
				+ "where username = ? and game_id = ? " + "and release_date <= sysdate";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setInt(2, gameId);
			rs = pst.executeQuery();

			if (rs.next()) {
				gameName = rs.getString("game_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return gameName;
	}

	// 게임 추가
	public int insertGame(GameDTO game) {
		int result = 0;
		String sql = "INSERT INTO GAMES VALUES (GAME_SEQ.NEXTVAL, ?, ?, ?, 0, ?, ?, ?, 0, ?)";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, game.getGame_name());
			pst.setString(2, game.getAlternative_name());
			pst.setInt(3, game.getPrice());
			pst.setString(4, game.getGenre());
			pst.setString(5, game.getMaker());
			pst.setDate(6, game.getRelease_date());
			pst.setString(7, game.getThumbnail());
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("잘못된 입력값입니다. 다시 확인해주세요.");
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}
	
	// 게임 할인율 조정
	public int modifyDiscount(int gameId, double dRate) {
		int result = 0;
		
		if (dRate < 0 || dRate > 1) {
			System.out.println("할인율은 0과 1 사이의 값이어야 합니다.");
			return result;
		}
		
		String sql = "update games set discount_rate = ? where game_id = ?";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, dRate);
			pst.setInt(2, gameId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("해당 게임이 존재하지 않습니다.");
		}
		
		return result;
	}

	// 환불
	public int refund(String username, int gameId) {
		int result = 0;
		
		String sql = "delete from purchase where username = ? and game_id = ? and purchase_date > sysdate - 2";
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setInt(2, gameId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			
		}
		
		return result;
	}
	
	// 인기 순위 상위 5개 게임 조회
	public List<GameDTO> selectByRank() {
		List<GameDTO> gamelist = new ArrayList<GameDTO>();
		
		String sql = "SELECT * FROM (SELECT * FROM GAMES ORDER BY DOWNLOADS DESC) WHERE ROWNUM <= 5";
		conn = DBUtil.dbConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				GameDTO game = makeGame(rs);
				gamelist.add(game);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return gamelist;
	}
	
	private GameDTO makeGame(ResultSet rs2) throws SQLException {
		GameDTO game = new GameDTO();

		game.setAlternative_name(rs2.getString("alternative_name"));
		game.setDiscount_rate(rs2.getDouble("discount_rate"));
		game.setDownloads(rs2.getInt("downloads"));
		game.setGame_id(rs2.getInt("game_id"));
		game.setGame_name(rs2.getString("game_name"));
		game.setGenre(rs2.getString("genre"));
		game.setMaker(rs2.getString("maker"));
		game.setPrice(rs2.getInt("price"));
		game.setRelease_date(rs2.getDate("release_date"));
		game.setThumbnail(rs2.getString("thumbnail"));
		
		return game;
	}

	
}
