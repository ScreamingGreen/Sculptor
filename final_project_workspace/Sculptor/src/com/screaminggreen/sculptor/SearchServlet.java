package com.screaminggreen.sculptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class SearchServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String search = br.readLine();
		
		                
        Iterable<Entity> entities = Professor.getAllProfessors(search);
        
        String json = "["; //DatastoreAPI.writeJSON(entities);        
        
        while(entities.iterator().hasNext()){
        	
        	System.out.println(entities.iterator().next().toString());
        	
        	json += entities.iterator().next().toString()+",";
        	
        }
        
        json += "test]";
        
        System.out.println(json);
        
        resp.setStatus(200);
        out.println(json);               
        out.close();
        
	}
	
}
