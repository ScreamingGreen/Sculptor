package com.screaminggreen.sculptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class SearchServlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		                
        Iterable<Entity> entities = Professor.getAllProfessors("Professor");
        
        String json = DatastoreAPI.writeJSON(entities);
        
        System.out.println(json);
        
        resp.setStatus(200);
        out.println(json);
        
        out.close();
                
	}
	
}

