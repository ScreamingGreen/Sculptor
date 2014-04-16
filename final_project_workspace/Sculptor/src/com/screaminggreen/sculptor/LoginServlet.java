package com.screaminggreen.sculptor;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Login Attempt with username:" + req.getParameter("username") + " pw: " + req.getParameter("password"));
	}
}
