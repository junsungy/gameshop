package com.shinhan.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shinhan.customer.CustDTO;
import com.shinhan.game.GameDTO;
import com.shinhan.game.GameService;
import com.shinhan.util.DateUtil;

/**
 * Servlet implementation class GameInsertServlet
 */
@WebServlet("/game/insertGame.do")
public class GameInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustDTO cust = (CustDTO)session.getAttribute("loginCust");
		if (cust.getIs_admin() != 1) {
			response.sendRedirect("/webgame");
		}else {
			request.getRequestDispatcher("insertGame.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String encoding = "utf-8";
		request.setCharacterEncoding(encoding);
		
		String path = getServletContext().getRealPath(".");
		File currentDirPath = new File(path+"/static/images");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);
		
		GameDTO game = new GameDTO();
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);

				if (fileItem.isFormField()) {
					// 입력 텍스트 필드
					if(fileItem.getFieldName().equals("gameName")) game.setGame_name(fileItem.getString(encoding));
					if(fileItem.getFieldName().equals("altName")) game.setAlternative_name(fileItem.getString(encoding));
					if(fileItem.getFieldName().equals("price")) game.setPrice(Integer.parseInt(fileItem.getString(encoding)));
					if(fileItem.getFieldName().equals("genre")) game.setGenre(fileItem.getString(encoding));
					if(fileItem.getFieldName().equals("maker")) game.setMaker(fileItem.getString(encoding));
					if(fileItem.getFieldName().equals("rdate")) game.setRelease_date(DateUtil.getSQLDate(fileItem.getString(encoding)));
					
				} else {
					// 입력 이미지 필드
					if(fileItem.getFieldName().equals("thumbnail")) {
						System.out.println(fileItem.getName());
						game.setThumbnail(fileItem.getName());
					}

					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);
					} // end if
				} // end if
			} // end for
			
			int result = new GameService().insertGame(game);
			request.setAttribute("result", result);
			request.getRequestDispatcher("insertResult.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 }
