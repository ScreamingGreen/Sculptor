package com.screaminggreen.sculptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.CourseTab;
import com.screaminggreen.datastore.DatastoreAPI;
import com.screaminggreen.datastore.Professor;

@SuppressWarnings("serial")
public class EnteredSearchCourseServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String course = req.getParameter("searchCourse");
		
		//Try to see if the webId exist
		Entity e = Professor.getProfessor(course);
		if(e == null) {
			System.out.println("Null");
			PrintWriter pw = resp.getWriter();
			pw.print("Such webId does not exist");
			return;
		}
		
    	resp.sendRedirect("/studentpage.jsp?webId="+ course);
        
	}
	
}

