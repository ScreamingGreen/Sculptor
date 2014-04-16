package com.screaminggreen.sculptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.screaminggreen.datastore.DatastoreAPI;

@SuppressWarnings("serial")
public class DeleteTabServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//The response has the type of tab we want to delete
        String typeToBeDeleted = req.getParameter("type");
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
		
        Entity e = CourseTab.getCourseTab(webId, typeToBeDeleted);                 
        DatastoreAPI.deleteEntity(e.getKey());
        
        Entity tabOrderEntity = CourseTab.getTabOrderEntity(webId);
        JSONObject jObj = new JSONObject(tabOrderEntity.getProperty("TabOrderJSON"));
        
        try {
			System.out.println(jObj.get("tabOrder").toString());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
