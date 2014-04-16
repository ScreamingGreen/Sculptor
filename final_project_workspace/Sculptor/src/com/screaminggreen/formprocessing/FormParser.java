package com.screaminggreen.formprocessing;

import javax.servlet.http.HttpServletRequest;

import com.screaminggreen.datastore.CourseTab;

public interface FormParser {
	
	//This is used to connect to DataStore
	final CourseTab courseTabs = new CourseTab();
	
	public void parse(HttpServletRequest req);
}
