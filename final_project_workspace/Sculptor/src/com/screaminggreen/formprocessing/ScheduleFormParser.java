package com.screaminggreen.formprocessing;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.Entity;
import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.CourseTab;
import com.screaminggreen.datastore.DatastoreAPI;

public class ScheduleFormParser implements FormParser {

	public final static String TYPE_OF_COURSETAB = "Schedule";
	
	@Override
	public void parse(HttpServletRequest req) {
		String  schedule = req.getParameter("schedule");
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
		
		//Update this data in Datastore...
		
		//Get or make new entity
		Entity tabEntity = CourseTab.createOrGetCourseTab(webId, TYPE_OF_COURSETAB);
		
		tabEntity.setProperty("schedule", schedule);
		
		System.out.println(schedule);
		DatastoreAPI.persistEntity(tabEntity);
	}

}
