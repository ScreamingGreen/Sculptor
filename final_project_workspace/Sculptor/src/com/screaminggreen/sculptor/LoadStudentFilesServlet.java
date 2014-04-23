package com.screaminggreen.sculptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.screaminggreen.datastore.CourseTab;

public class LoadStudentFilesServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String webId = req.getParameter("webId");
		
		//Get entity
		Entity e = CourseTab.createOrGetCourseTab(webId, "Files");
		
		String keys = (String) e.getProperty("fileKeys");
		if(keys == null || keys.isEmpty()) {
			PrintWriter out = resp.getWriter();
			out.print("");
			resp.setStatus(200);
			return;
		}
		
		String[] keyArray = keys.split(",");
		
		JSONArray jArray = new JSONArray();
		for(String key : keyArray) {
			BlobKey bKey = new BlobKey(key);
            BlobInfo blobInfo =  new BlobInfoFactory().loadBlobInfo(bKey);
	
			//Build JSON
			JSONObject jObj = new JSONObject();
			try {
				jObj.put("name", blobInfo.getFilename());
				jObj.put("key", key);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			jArray.put(jObj);
		};
		
		System.out.println(jArray.toString());
		
		//Print
		PrintWriter out = resp.getWriter();		
		
		
		out.print(jArray);
		
		//Status is OK
		resp.setStatus(200);
		return;
	}
}
