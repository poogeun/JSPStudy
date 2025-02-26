package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		// 쿠키 받기
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		if(op.equals("=")) {
			// application 에서 값 꺼내쓰기
			//int x = (Integer)application.getAttribute("value");
			//int x = (Integer)session.getAttribute("value");
			
			// 쿠키 읽기
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break; // 찾으면 끝
				}
			}
			
			int y = v;
			//String operator = (String)application.getAttribute("op");
			//String operator = (String)session.getAttribute("op");
			
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break; // 찾으면 끝
				}
			}	
			
			int result = 0;
			
			if(operator.equals("+"))
				result = x+y;
			else 
				result = x-y;
			
			PrintWriter out = response.getWriter();
			out.printf("result is %d\n", result);
		}
		else {
			// application 에 값 담기
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);
			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			// 쿠키 저장
			Cookie valueCookie = new Cookie("value", String.valueOf(v)); // 값은 문자열만
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(24*60*60);
			opCookie.setPath("/calc2");
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");
		}
	}
}
