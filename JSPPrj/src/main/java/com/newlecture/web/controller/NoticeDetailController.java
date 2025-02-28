package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE ID=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery(); // 이때 실행

			rs.next(); // rs에 결과가 적재됨

			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			String regdate = rs.getString("REGDATE");
			String hit = rs.getString("HIT");
			String files = rs.getString("FILES");
			String content = rs.getString("CONTENT");
			
			// request 에 담기
			request.setAttribute("title", title);
			request.setAttribute("writerId", writerId);
			request.setAttribute("regdate", regdate);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// redirect
		
		// forward 작업하던 내용 이어받아서 뷰로 내보내기
		request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
	}
}
