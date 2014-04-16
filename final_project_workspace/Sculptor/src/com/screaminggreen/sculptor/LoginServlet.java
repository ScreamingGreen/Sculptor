package com.screaminggreen.sculptor;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.http.*;

import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.Professor;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");

		//Website ID
	    String webId = req.getParameter("webId");

		//Get Password
		String password = req.getParameter("password");
		
		//Check for nulls
		if(webId == null || webId.isEmpty() || 			
			password == null || password.isEmpty()){
			resp.sendRedirect("/loginpage.jsp?missingfields=true&error=true");			
			return;
		}
		
		//Get professor from the datastore with a specific webId to identify his/her website
		else if(Professor.getProfessor(webId) == null){			
	    	resp.sendRedirect("/loginpage.jsp?error=true");
	    	return;
		}
		
		//if professor entity not null checking if the password matches
		else if(Professor.getProfessor(webId) != null && password.equals(Professor.getProfessor(webId).getProperty("password"))){
			
			//Set up sessions
			HttpSession session = req.getSession();
			ProfessorBean pBean = new ProfessorBean();
			SessionBean sBean = new SessionBean();
			
			//Set beans
			pBean.setWebId(webId);
			sBean.setProfBean(pBean);
			
			session.setAttribute("sessionBean", sBean);
			
			//Redirect
			resp.sendRedirect("/app/createpage.jsp");
		}
		
		//if nothing matches redirect the page back to login page 
		else{
	    	resp.sendRedirect("/loginpage.jsp?error=true");
	    	return;
		}
	}
}

