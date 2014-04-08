package com.screaminggreen.datastore;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.screaminggreen.datastore.Professor;;


/**
 * This class handles CRUD operations related to CourseTabs entity.
 * 
 *
 */

public class CourseTabs {

  /**
   * Create or update CourseTabs for a particular Professor. Professor entity has one to many
   * relation-ship with CourseTabs entity
   * 
   * @param ProfessorName
   *          : Professor name for which the CourseTabs is created.
   * @param CourseTabsName
   *          : CourseTabs name
   * @param Content
   *          : Content of the CourseTabs
   * @return
   */
  public static Entity createOrUpdateCourseTabs(String ProfessorName, String CourseTabsName, String Content) {
    
	  Entity professor = Professor.getProfessor(ProfessorName);
	  Entity CourseTabs = getSingleCourseTabs(CourseTabsName);
	  if(CourseTabs == null){
	    CourseTabs = new Entity("CourseTabs",professor.getKey());
	    CourseTabs.setProperty("title", CourseTabsName);
	    CourseTabs.setProperty("content", Content);
	    CourseTabs.setProperty("webIdAttached", CourseTabsName+professor.getKey());

	  } else{
	    if (CourseTabsName != null) {	    	
	      CourseTabs.setProperty("title", CourseTabsName);
	      CourseTabs.setProperty("content", Content);
	    }           
	  }
	  DatastoreAPI.persistEntity(CourseTabs);
	  return CourseTabs;
  }

  /**
   * get All the CourseTabss in the list
   * 
   * @param kind
   *          : CourseTabs kind
   * @return all the CourseTabss
   */
  public static Iterable<Entity> getAllCourseTabss() {
  	Iterable<Entity> entities = DatastoreAPI.listEntities("CourseTabs", null, null);
  	return entities;
  }

  /**
   * Get the CourseTabs by name
   * 
   * @param CourseTabsName
   *          : CourseTabs name
   * @return CourseTabs Entity
   */
  public static Iterable<Entity> getCourseTabs(String CourseTabsName) {
  	Iterable<Entity> entities = DatastoreAPI.listEntities("CourseTabs", "name", CourseTabsName);
  	return entities;
  }

  /**
   * Get all the CourseTabss for a Professor
   * 
   * @param kind
   *          : CourseTabs kind
   * @param ProfessorName
   *          : Professor name
   * @return: all CourseTabss of type Professor
   */
  public static Iterable<Entity> getCourseTabssForProfessor(String kind, String ProfessorName) {
    Key ancestorKey = KeyFactory.createKey("Professor", ProfessorName);
    return DatastoreAPI.listChildren("CourseTabs", ancestorKey);
  }

  /**
   * get CourseTabs with CourseTabs name
   * @param CourseTabsName: get CourseTabsName
   * @return  CourseTabs entity
   */
  public static Entity getSingleCourseTabs(String CourseTabsName) {
    
	  Query query = new Query("CourseTabs");
	  
	  Filter filter = new FilterPredicate("name", FilterOperator.EQUAL, CourseTabsName);
	  
	  query.setFilter(filter);
	  
	  List<Entity> results = DatastoreAPI.getDatastoreServiceInstance().prepare(query).asList(FetchOptions.Builder.withDefaults());
	  
	  if (!results.isEmpty()) {
	    return (Entity)results.remove(0);
	  }
	  return null;
  }
  
  public static String deleteCourseTabs(String CourseTabsKey)
  {
	  Entity entity = getSingleCourseTabs(CourseTabsKey);    
	  if(entity != null){
	    DatastoreAPI.deleteEntity(entity.getKey());
	    return("CourseTabs deleted successfully.");
	  } else
	    return("CourseTabs not found"); 
  }
}

