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

@SuppressWarnings("serial")
public class PopulateFormServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String typeOfForm = br.readLine();
        
        //Get the Web ID
		SessionBean sBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		if(sBean == null) {
			//Return with failure status
		    resp.setStatus(500); 
		    out.println("Not logged in");
		    out.close();
		}
		
		ProfessorBean pBean = sBean.getProfBean();
		String webId = pBean.getWebId();
		        
        Entity e = CourseTab.createOrGetCourseTab(webId, typeOfForm);
        
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(e);
        
        String json = DatastoreAPI.writeJSON(entities);        
        
        resp.setStatus(200);
        out.println(json);               
        out.close();
        
	}
	
}
