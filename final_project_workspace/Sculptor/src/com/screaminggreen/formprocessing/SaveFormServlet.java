package com.screaminggreen.formprocessing;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveFormServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2095714708295995408L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//We don't know what form this is so lets figure it out.
		
		String typeOfForm = req.getParameter("type-of-form");
		
		if(typeOfForm == null || typeOfForm.isEmpty()) {
			//This is a erroneous case
			resp.sendRedirect("/app/createpage.jsp");
			return;
		}
		
		//Parse the inputs depending on the type of form.
		switch(typeOfForm) {
			case "Home" :
				new HomeFormParser().parse(req);
				break;
			case "Syllabus" :
				break;
			case "Schedule" :
				break;
			default :
		}
		
		return;
	}
}
