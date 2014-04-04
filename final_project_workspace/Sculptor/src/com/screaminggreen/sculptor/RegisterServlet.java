package com.screaminggreen.sculptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		//Get email and password
		String email = req.getParameter("registerEmail");
		String password = req.getParameter("registerPass");
		
		
		
		//Put this information in Datastore			
	    Key userKey = KeyFactory.createKey("Users", email);
	    Entity user = new Entity("User", userKey);
	    user.setProperty("email", email);
	    user.setProperty("password", password);

	    datastore.put(user);	
		//Try to insert data, see if email exists already
		
		//If so, just print error	    
		PrintWriter out = resp.getWriter();
		//out.println("You have an account already");
		
		//Else, print success
		out.println("Success!");
	}
}
