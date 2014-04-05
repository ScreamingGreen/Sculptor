package com.screaminggreen.sculptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		//Get email and password
		String email = req.getParameter("registerEmail"); // Email as our username
		String password = req.getParameter("registerPass");
		
		Filter emailAddressFilter = new FilterPredicate("email",FilterOperator.EQUAL,email);
		
		Query previousExistingUser = new Query("User").setFilter(emailAddressFilter);
		
		PreparedQuery pq = datastore.prepare(previousExistingUser);		
		
		
		//If so, just print error	    
		PrintWriter out = resp.getWriter();
		
		if(pq.countEntities(FetchOptions.Builder.withDefaults())<=0){
		//Put this information in Datastore			
	    Key userKey = KeyFactory.createKey("Users", email);
	    Entity user = new Entity("User", userKey);
	    user.setProperty("email", email);
	    user.setProperty("password", password);

	    datastore.put(user);	
				
		//Else, print success
		out.println("Success!");
        resp.sendRedirect("/loginpage.jsp");

		}
		else{
			//Error 
			out.println("You have an account already");
	        resp.sendRedirect("/registerpage.jsp?error=true");
		}
	}
}
