package com.screaminggreen.sculptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.screaminggreen.datastore.Professor;

public class SearchWebIdServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String search = br.readLine();
        
		Entity e = Professor.getProfessor(search);
		PrintWriter pw = resp.getWriter();
		
		if(e == null) {
			pw.print("no");
			resp.setStatus(200);
		} else {
			pw.print("yes");
			resp.setStatus(200);
		}		
		
	}
}
