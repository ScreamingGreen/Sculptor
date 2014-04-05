package com.screaminggreen.sculptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		String user = req.getParameter("username");
		String pass = req.getParameter("password");

	    Filter userFilter = new FilterPredicate("email", FilterOperator.EQUAL, user);
	    Filter passFilter = new FilterPredicate("password", FilterOperator.EQUAL, pass);
	    Filter accountValidationFilter = CompositeFilterOperator.and(userFilter, passFilter);
	 

	    Query query = new Query("User").setFilter(accountValidationFilter);
	    List<Entity> users = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1));
	    
	    if(users.size() == 1) {
	    	out.println("User/pass found!");
	    	
	    	//Send them to their homepage
	    } else {
	    	out.println("No user/pass found...");	    	
	    	resp.sendRedirect("/loginpage.jsp?error=true");	    	
	    }
	}
}
