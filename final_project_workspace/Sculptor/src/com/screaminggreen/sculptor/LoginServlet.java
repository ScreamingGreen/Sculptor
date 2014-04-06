package com.screaminggreen.sculptor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;

import com.screamminggreen.datastore.Professor;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();		

		//Website ID
	    String webId = req.getParameter("webId");

		//Get Password
		String password = req.getParameter("password");
		
		//Check for nulls
		if(webId == null || webId.isEmpty() || 
			password == null || password.isEmpty()){
			resp.sendRedirect("/loginpage.jsp?missingfields=true");			
			return;
		}
		//Get professor from the datastore with a specific webId to identify his/her website
		else if(Professor.getProfessor(webId)==null){	
	    	resp.sendRedirect("/loginpage.jsp?error=true");
	    	return;
		}
		//if professor entity not null checking if the password matches
		else if(Professor.getProfessor(webId) != null && password.equals(Professor.getProfessor(webId).getProperty("password"))){
	    	out.println("User/pass found!");
		}
		//if nothing matches redirect the page back to login page 
		else{
	    	resp.sendRedirect("/loginpage.jsp?error=true");
	    	return;
		}
	}
}

