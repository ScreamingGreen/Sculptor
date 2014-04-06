package com.screaminggreen.sculptor;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import com.screamminggreen.datastore.*;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		PrintWriter out = resp.getWriter();

		//Get Website ID
	    String webId = req.getParameter("registerWebId");
	    
	    //Get Professor Email
		String email = req.getParameter("registerEmail");
		
		//Get Password
		String password = req.getParameter("registerPass");
		
		//Ain't nobody want null fields, 
		//if the frontend fails to validate, we have to do it just in-case...
		
		if(webId == null || webId.isEmpty() || email == null || email.isEmpty() 
				|| password == null || password.isEmpty()) {
			resp.sendRedirect("/registeruser.jsp?error=true");
		}		
		
		if(Professor.getProfessor(webId)==null){			
			
			Professor.createOrUpdateProfessor(webId, email, password);
			 
			resp.sendRedirect("/loginpage.jsp?success=true");			 
		}
		else{
			//Error 
			out.println("You have an account already");
	        resp.sendRedirect("/registeruser.jsp?error=true");
		}
		
	}
}
