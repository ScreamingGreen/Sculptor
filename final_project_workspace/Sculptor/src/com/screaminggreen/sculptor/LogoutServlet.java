package com.screaminggreen.sculptor;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//Clear the session map
		req.getSession().invalidate();
		
		//Return to home page now
		resp.sendRedirect("/index.html");
		return;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//Clear the session map
		req.getSession().invalidate();
		
		//Return to home page now
		resp.sendRedirect("/index.html");
		return;
	}
}
