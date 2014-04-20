package com.screaminggreen.sculptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadFileServlet extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String uploadURL = blobstoreService.createUploadUrl("/successfulupload");
		//uploadURL = uploadURL.substring(uploadURL.lastIndexOf("_ah") - 1);
		//req.getRequestDispatcher(uploadURL).forward(req, resp);
		
		PrintWriter out = resp.getWriter();
		out.println(uploadURL);
		resp.setStatus(200);
		return;
	}
	
}
