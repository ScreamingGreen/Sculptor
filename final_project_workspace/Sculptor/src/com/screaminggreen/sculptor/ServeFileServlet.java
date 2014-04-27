package com.screaminggreen.sculptor;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;


public class ServeFileServlet extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
            BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
            BlobInfo blobInfo =  new BlobInfoFactory().loadBlobInfo(blobKey);
	        
            // set response header
	        res.setContentType(blobInfo.getContentType());
	        res.setHeader("Content-Disposition", "filename=" + blobInfo.getFilename());
            
	        blobstoreService.serve(blobKey, res);
        }
}
