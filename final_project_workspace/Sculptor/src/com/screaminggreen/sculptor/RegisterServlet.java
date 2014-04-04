package com.screaminggreen.sculptor;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		//Get email and password
		String email = req.getParameter("registerEmail");
		String password = req.getParameter("registerPassword");
		
		//Put this information in datastore
		
		//Try to insert data, see if email exists already
		
		//If so, just print error
		PrintWriter out = resp.getWriter();
		out.println("You have an account already");
		
		//Else, print success
		out.println("Success!");
	}
}
