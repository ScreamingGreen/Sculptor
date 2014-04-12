package com.screaminggreen.formprocessing;

import javax.servlet.http.HttpServletRequest;

import com.screaminggreen.datastore.CourseTabs;

public interface FormParser {
	
	//This is used to connect to DataStore
	final CourseTabs courseTabs = new CourseTabs();
	
	public void parse(HttpServletRequest req);
}
