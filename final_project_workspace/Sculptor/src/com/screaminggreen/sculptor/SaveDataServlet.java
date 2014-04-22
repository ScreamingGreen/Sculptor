package com.screaminggreen.sculptor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.*;

import com.screaminggreen.beans.*;

@SuppressWarnings("serial")
public class SaveDataServlet extends HttpServlet {
	
	 private static final Logger logger = Logger.getLogger(SaveDataServlet.class.getCanonicalName());

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
        HttpServletRequest request = (HttpServletRequest) req;        

		SessionBean sBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        if(sBean == null) {
        	HttpServletResponse response = (HttpServletResponse) resp;
        	response.sendRedirect("/loginpage.jsp?nouser=true&error=true");
        	return;
        }
        else{
        	
        	String sample = req.getParameter("tab");
        	
        	String[] tabs = req.getParameterValues("tab");
        	
    	  	logger.log(Level.INFO, "Data:"+ sample);
    	  	
    	  	for(int i = 0 ;i< tabs.length;i++){
        	  	logger.log(Level.INFO, "tabs:"+ i +">" +tabs[i]);

    	  	}
        	        	
        }
        
		
		
	}

}
