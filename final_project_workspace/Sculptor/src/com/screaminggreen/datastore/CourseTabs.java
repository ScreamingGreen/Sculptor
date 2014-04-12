package com.screaminggreen.datastore;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.screaminggreen.datastore.Professor;


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
  public static Entity createOrGetCourseTab(String webId, String CourseTabsName, String Type) {

	  String entityName = "CourseTab." + Type;
	  
	  //Get the prof entity based on passed id
	  Entity professor = Professor.getProfessor(webId);
	  
	  System.out.println(entityName);
	  
	  //Attempt to find the course tab
	  Entity courseTab = getCourseTab(CourseTabsName, webId, entityName);	  
	  
	  //If doesn't exist, make new one
	  if(courseTab == null){
	    courseTab = new Entity(entityName, professor.getKey());
	    courseTab.setProperty("tabName", CourseTabsName);
	    courseTab.setProperty("webId", webId);
	    courseTab.setProperty("webIdAttached", CourseTabsName+professor.getKey());
		DatastoreAPI.persistEntity(courseTab);
	  }	  
	  //O/w return one... dam duplications -.- 
	  return courseTab;
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
  	Iterable<Entity> entities = DatastoreAPI.listEntities("CourseTabs", "tabName", CourseTabsName);
  	return entities;
  }
  
  /**
   * Gets a course tab based on web ID and tab
   * @param tabName
   * @param webId
 * @param entityName 
   * @return
   */
  public static Entity getCourseTab(String tabName, String webId, String entityName) {
	  Filter tabNameFilter = new FilterPredicate("tabName", FilterOperator.EQUAL, tabName);
	  Filter webIdFilter = new FilterPredicate("webId", FilterOperator.EQUAL, webId);
	  Filter andFilter = CompositeFilterOperator.and(tabNameFilter, webIdFilter);
	  
	  Query q = new Query(entityName);
	  q.setFilter(andFilter);
	  
	  List<Entity> entities = DatastoreAPI.getDatastoreServiceInstance().prepare(q).asList(FetchOptions.Builder.withLimit(1));

	  //Get the first element.. if there is one
	  
	  if(entities.size() == 0) {
		  return null;
	  }
	  
	  return entities.get(0);
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
  public static Iterable<Entity> getCourseTabsForProfessor(String kind, String ProfessorName) {
    Key ancestorKey = KeyFactory.createKey("Professor", ProfessorName);
    return DatastoreAPI.listChildren("CourseTabs", ancestorKey);
  }

  /**
   * get ALL CourseTabs with CourseTabs name
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

