package com.screaminggreen.formprocessing;

import javax.servlet.http.HttpServletRequest;

import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.CourseTabs;
import com.screaminggreen.datastore.DatastoreAPI;
import com.google.appengine.api.datastore.Entity;


public class HomeFormParser implements FormParser{

	@Override
	public void parse(HttpServletRequest req) {
		
		String tabName = req.getParameter("tabName");
		String courseCode = req.getParameter("courseCode");
		String courseName = req.getParameter("courseName");
		String teacherName = req.getParameter("teacherName");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
				
		//Booleans for dates
		boolean mon = req.getParameter("mon") == null ? false : true;
		boolean tue = req.getParameter("tue") == null ? false : true;
		boolean wed = req.getParameter("wed") == null ? false : true;
		boolean thu = req.getParameter("thu") == null ? false : true;
		boolean fri = req.getParameter("fri") == null ? false : true;
		boolean sat = req.getParameter("sat") == null ? false : true;
		boolean sun = req.getParameter("sun") == null ? false : true;
				
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
		Entity tabEntity = CourseTabs.createOrGetCourseTab(webId, tabName, "Home");
		
		tabEntity.setProperty("type", "Home");
		
		tabEntity.setProperty("tabName", tabName);
		tabEntity.setProperty("courseCode", courseCode);
		tabEntity.setProperty("courseName", courseName);
		tabEntity.setProperty("teacherName", teacherName);
		tabEntity.setProperty("startTime", startTime);
		tabEntity.setProperty("endTime", endTime);
		
		tabEntity.setProperty("mon", mon);
		tabEntity.setProperty("tue", tue);
		tabEntity.setProperty("wed", wed);
		tabEntity.setProperty("thu", thu);
		tabEntity.setProperty("fri", fri);
		tabEntity.setProperty("sat", sat);
		tabEntity.setProperty("sun", sun);
		
		//Update it
		DatastoreAPI.persistEntity(tabEntity);
	}

}
