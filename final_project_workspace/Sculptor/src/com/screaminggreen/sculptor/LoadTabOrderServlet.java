package com.screaminggreen.sculptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.CourseTab;

public class LoadTabOrderServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6615804480882613066L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//Print out the JSON ez
				
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
		
		Entity tabOrderEntity = CourseTab.getTabOrderEntity(webId);
		if(tabOrderEntity == null) {
			//Create a new taborder entity with default parameters
			final String defaultTabOrderJSON = "{\"tabOrder\":[{\"type\":\"Home\"}]}";
			CourseTab.createOrUpdateTabOrderEntity(webId, defaultTabOrderJSON);
			
			PrintWriter out = resp.getWriter();
			resp.setStatus(200);			
			out.println(defaultTabOrderJSON);
			return;
			
		} else {
			String jsonString = (String) tabOrderEntity.getProperty("tabOrder");
			try {
				JSONObject jObj = new JSONObject(jsonString);
				
				PrintWriter out = resp.getWriter();
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
}
