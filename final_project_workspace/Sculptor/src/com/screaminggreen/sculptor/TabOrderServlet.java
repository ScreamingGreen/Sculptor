package com.screaminggreen.sculptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.*;

import com.screaminggreen.datastore.*;

@SuppressWarnings("serial")
public class TabOrderServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		// 1. Reading JSON String
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String JSONString = "a";
        if(br != null){
            JSONString = br.readLine();
        }
     }
}