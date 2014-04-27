package com.screaminggreen.sculptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.screaminggreen.datastore.CourseTab;

@SuppressWarnings("serial")
public class LoadStudentSidebarServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		PrintWriter out = resp.getWriter();
		String webId = req.getParameter("webId");
		
		Entity tabOrderEntity = CourseTab.getTabOrderEntity(webId);
		
		String jsonString = (String) tabOrderEntity.getProperty("tabOrder");
		try {
			JSONObject jObj = new JSONObject(jsonString);
			
			resp.setStatus(200);
			out.println(jsonString);
			return;
		} catch (JSONException e) {
			// error with json
			resp.setStatus(500);
			return;
		}      
        
	}
	
}
