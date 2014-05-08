package com.screaminggreen.sculptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import com.screaminggreen.beans.*;
import com.screaminggreen.datastore.Professor;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class ChangePasswordServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpServletRequest request = (HttpServletRequest) req;        

		SessionBean sBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		if(sBean == null) {
			//Return with failure status
		    resp.setStatus(500); 
		    PrintWriter out = resp.getWriter();
		    out.println("Not logged in");
		    out.close();
		}
		
		ProfessorBean pBean = sBean.getProfBean();
		String webId = pBean.getWebId();
		String oldpassword = request.getParameter("oldPassword");
		String newpassword = request.getParameter("newPassword");
		
		Entity e = Professor.getProfessor(webId);
        String password = (String) e.getProperty("password");
        String user = (String) e.getProperty("webId");
        String email = (String) e.getProperty("email");
        
        System.out.println(password);
        System.out.println(oldpassword);
        
        if(oldpassword.equals(password))
        {
        	if(newpassword != null || newpassword != "")
        	{
        		Professor.createOrUpdateProfessor(user, email, newpassword);
        	}
        }
        
        resp.sendRedirect("/app/createpage.jsp");
	}

}
