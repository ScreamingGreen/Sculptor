package com.screaminggreen.formprocessing;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.Entity;
import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.CourseTab;
import com.screaminggreen.datastore.DatastoreAPI;

public class SyllabusFormParser implements FormParser {

	public final static String TYPE_OF_COURSETAB = "Syllabus";
	
	@Override
	public void parse(HttpServletRequest req) {

		//Get the parameters
		String description = req.getParameter("description");
		description = description.replaceAll("(\r\n|\n)", "\\\\n");

		String materials = req.getParameter("materials");
		materials = materials.replaceAll("(\r\n|\n)", "\\\\n");
		
		String infoAndHours = req.getParameter("infoAndHours");
		infoAndHours = infoAndHours.replaceAll("(\r\n|\n)", "\\\\n");
		
		String breakdown = req.getParameter("breakdown");
		breakdown = breakdown.replaceAll("(\r\n|\n)", "\\\\n");
		
		//Get the webId
		SessionBean sBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		if(sBean == null){
			return;
		}

		ProfessorBean pBean = (ProfessorBean) sBean.getProfBean();		
		if(pBean == null){
			return;
		}
		
		String webId = pBean.getWebId();
		
		Entity e = CourseTab.createOrGetCourseTab(webId, TYPE_OF_COURSETAB);
		
		//Set properties
		e.setProperty("description", description);
		e.setProperty("materials", materials);
		e.setProperty("infoAndHours", infoAndHours);
		e.setProperty("breakdown", breakdown);
		
		//Persist that shiet
		DatastoreAPI.persistEntity(e);
	}

}
