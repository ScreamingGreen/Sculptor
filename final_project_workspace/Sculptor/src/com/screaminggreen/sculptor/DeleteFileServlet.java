package com.screaminggreen.sculptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.CourseTab;
import com.screaminggreen.datastore.DatastoreAPI;


public class DeleteFileServlet extends HttpServlet {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//Get webId
		SessionBean sBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		if(sBean == null){
			return;
		}

		ProfessorBean pBean = (ProfessorBean) sBean.getProfBean();		
		if(pBean == null){
			return;
		}
				
		String webId = pBean.getWebId();
		
		//Get blobkey
		String deleteKey = req.getParameter("key");
		
		Entity e = CourseTab.createOrGetCourseTab(webId, "Files");
		
		String keys = (String) e.getProperty("fileKeys");	
		String[] keyArray = keys.split(",");
		String newKeys = null;
		
		//Creates new fileKeys string from old string.
		for(int i=0; i <keyArray.length; i++)
		{
			//Excludes the key to be deleted.
			if(!(keyArray[i].equals(deleteKey)))
			{
				if(i==keyArray.length)
					newKeys += keyArray[i];
				else 
					newKeys += (keyArray[i]+",");
			}
		}
		
		//Updates fileKeys with newKeys
		e.setProperty("fileKeys", newKeys);
		
		DatastoreAPI.persistEntity(e);
		
		//Delete from blobstore
		BlobKey blobkey = new BlobKey(deleteKey);
		blobstoreService.delete(blobkey);
	}

}
