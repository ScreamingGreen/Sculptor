package com.screaminggreen.sculptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.screaminggreen.datastore.CourseTab;
import com.screaminggreen.datastore.DatastoreAPI;

public class LoadStudentMainServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//Sends JSON string with the body of student page 
		PrintWriter out = resp.getWriter();
		String webId = req.getParameter("webId");
		String type = req.getParameter("type");
		
		Entity tabEntity = CourseTab.getCourseTab(webId,type);
		
		List<Entity> entities = new ArrayList<Entity>();
        entities.add(tabEntity);
        
        String json = DatastoreAPI.writeJSON(entities);        
        
        resp.setStatus(200);
        out.print(json);               
        out.close();
	}

}
