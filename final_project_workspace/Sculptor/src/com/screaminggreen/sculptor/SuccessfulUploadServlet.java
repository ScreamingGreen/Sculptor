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

public class SuccessfulUploadServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        @SuppressWarnings("deprecation")
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("myFile");

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
        
		//Get the entity
		Entity e = CourseTab.createOrGetCourseTab(webId, "Files");
		
		//Set the filekeys
		String keys = (String) e.getProperty("fileKeys");
		if(keys != null && keys != "") {
			keys = keys + "," + blobKey.getKeyString();
			e.setProperty("fileKeys", keys);
		} else {
			keys = blobKey.getKeyString();
			e.setProperty("fileKeys", keys);
		}
		
		DatastoreAPI.persistEntity(e);
		
		//Go back to the createpage
		resp.sendRedirect("/app/createpage.jsp");
	}
}
