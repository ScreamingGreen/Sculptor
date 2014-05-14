package com.screaminggreen.formprocessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.screaminggreen.beans.ProfessorBean;
import com.screaminggreen.beans.SessionBean;
import com.screaminggreen.datastore.CourseTab;
import com.screaminggreen.datastore.DatastoreAPI;

public class ScheduleFormParser implements FormParser {

	public final static String TYPE_OF_COURSETAB = "Schedule";
	
	private String strArrayToString(String [] arr) {
		if(arr == null || arr.length == 0) { return ""; }		
		
		if(arr.length == 1) {
			return arr[0];
		}
		
		String bigString = "";
		
		for(String s : arr) {
			bigString += s + ",";
		}
		
		//No last comma
		bigString = bigString.substring(0, bigString.length() - 1);
		
		return bigString;
		
	}
	
	@Override
	public void parse(HttpServletRequest req) {
		
		String [] dates = req.getParameterValues("dateOfEvent");
		String [] events = req.getParameterValues("eventDesc");
		
		if(dates.length != events.length) {
			//Mismatch!
			return;
		}
		
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
		
		tabEntity.setProperty("dates", strArrayToString(dates));
		tabEntity.setProperty("events", strArrayToString(events));				
		
		DatastoreAPI.persistEntity(tabEntity);
	}

}
