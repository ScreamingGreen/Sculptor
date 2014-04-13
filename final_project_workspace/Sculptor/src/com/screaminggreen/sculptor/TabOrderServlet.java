package com.screaminggreen.sculptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.*;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.*;

@SuppressWarnings("serial")
public class TabOrderServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		// 1. Reading JSON String
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String JSONString = "";
        if(br != null){
            JSONString = br.readLine();
        }
        
        JSONObject tabOrderJSON;

        try {
	        // Try to turn this String to JSON, if it fails we know something is wrong with the string
			tabOrderJSON = new JSONObject(JSONString);
	        
	        //Get the Web ID
			SessionBean sBean = (SessionBean) req.getSession().getAttribute("sessionBean");
			if(sBean == null) {
				//Return with failure status
			    resp.setStatus(500); 
			    PrintWriter out = resp.getWriter();
			    out.println("Not logged in");
			    out.close();			
			}
			
			ProfessorBean pBean = sBean.getProfBean();
			String webId = pBean.getWebId();
			
			
			//Directly store the JSONString in the Datastore...
			CourseTab.createOrGetTabOrderEntity(webId, JSONString);
			
	        resp.setStatus(200);	        
		} catch (JSONException e) {	
			//Return with failure status
		    resp.setStatus(500); 
		    PrintWriter out = resp.getWriter();
		    out.println("JSON Parse Error");
		    out.close();
		} catch (Exception ge) {
			//Maybe a datastore error?
			resp.setStatus(500);
		    PrintWriter out = resp.getWriter();
		    out.println("Something went wrong");
		    out.close();
		}
        
	}
}