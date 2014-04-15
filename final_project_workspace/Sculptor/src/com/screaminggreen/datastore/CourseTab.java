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
 * This class handles CRUD operations related to CourseTab entity.
 * 
 *
 */

public class CourseTab {

  /**
   * Create or update CourseTab for a particular Professor. Professor entity has one to many
   * relation-ship with CourseTab entity
   * 
   * @param ProfessorName
   *          : Professor name for which the CourseTab is created.
   * @param CourseTabName
   *          : CourseTab name
   * @param Content
   *          : Content of the CourseTab
   * @return
   */
  public static Entity createOrGetCourseTab(String webId, String Type) {

	  String entityName = "CourseTab." + Type;
	  
	  //Get the prof entity based on passed id
	  Entity professor = Professor.getProfessor(webId);
	  
	  System.out.println(entityName);
	  
	  //Attempt to find the course tab
	  Entity courseTab = getCourseTab(webId, Type);	  
	  
	  //If doesn't exist, make new one
	  if(courseTab == null){
	    courseTab = new Entity(entityName, professor.getKey());
	    courseTab.setProperty("webId", webId);
	    courseTab.setProperty("webIdAttached", Type+webId);
		DatastoreAPI.persistEntity(courseTab);
	  }	  
	  //O/w return one... dam duplications -.- 
	  return courseTab;
  }

/**
   * get All the CourseTabs in the list
   * 
   * @param kind
   *          : CourseTab kind
   * @return all the CourseTabs
   */
  public static Iterable<Entity> getAllCourseTabs() {
  	Iterable<Entity> entities = DatastoreAPI.listEntities("CourseTab", null, null);
  	return entities;
  }

  /**
   * Get the CourseTab by name
   * 
   * @param CourseTabName
   *          : CourseTab name
   * @return CourseTab Entity
   */
  public static Iterable<Entity> getCourseTab(String CourseTabName) {
  	Iterable<Entity> entities = DatastoreAPI.listEntities("CourseTab", "tabName", CourseTabName);
  	return entities;
  }
  
  /**
   * Gets a course tab based on web ID and tab
   * @param tabName
   * @param webId
 * @param entityName 
   * @return
   */
  public static Entity getCourseTab(String webId, String type) {
	  Filter webIdFilter = new FilterPredicate("webId", FilterOperator.EQUAL, webId);
	  
	  Query q = new Query("CourseTab." + type);
	  q.setFilter(webIdFilter);
	  
	  List<Entity> entities = DatastoreAPI.getDatastoreServiceInstance().prepare(q).asList(FetchOptions.Builder.withLimit(1));
	  
	  //Get the first element.. if there is one	  
	  if(entities.size() == 0) {
		  return null;
	  }
	  
	  return entities.get(0);
  }

  /**
   * Get all the CourseTabs for a Professor
   * 
   * @param kind
   *          : CourseTab kind
   * @param ProfessorName
   *          : Professor name
   * @return: all CourseTabs of type Professor
   */
  public static Iterable<Entity> getCourseTabForProfessor(String kind, String ProfessorName) {
    Key ancestorKey = KeyFactory.createKey("Professor", ProfessorName);
    return DatastoreAPI.listChildren("CourseTab", ancestorKey);
  }

  /**
   * get ALL CourseTab with CourseTab name
   * @param CourseTabName: get CourseTabName
   * @return  CourseTab entity
   */
  public static Entity getSingleCourseTab(String CourseTabName) {
    
	  Query query = new Query("CourseTab");
	  
	  Filter filter = new FilterPredicate("name", FilterOperator.EQUAL, CourseTabName);
	  
	  query.setFilter(filter);
	  
	  List<Entity> results = DatastoreAPI.getDatastoreServiceInstance().prepare(query).asList(FetchOptions.Builder.withDefaults());
	  
	  if (!results.isEmpty()) {
	    return (Entity)results.remove(0);
	  }
	  return null;
  }
  
  public static String deleteCourseTab(String CourseTabKey)
  {
	  Entity entity = getSingleCourseTab(CourseTabKey);    
	  if(entity != null){
	    DatastoreAPI.deleteEntity(entity.getKey());
	    return("CourseTab deleted successfully.");
	  } else
	    return("CourseTab not found"); 
  }
	
	public static Entity getTabOrderEntity(String webId) {

		Query q = new Query("TabOrderJSON");
		Filter webIdFilter = new FilterPredicate("webId", FilterOperator.EQUAL, webId);
		
		//Match on webId and only 1 of them
		q.setFilter(webIdFilter);
		List<Entity> list = DatastoreAPI.getDatastoreServiceInstance().prepare(q).asList(FetchOptions.Builder.withLimit(1));
		
		if(list.size() == 0){
			return null;
		}
				
		return list.get(0);
	}

	public static Entity createOrUpdateTabOrderEntity(String webId, String tabOrderJSON) {
				  
		  //Get the prof entity based on passed id
		  Entity professor = Professor.getProfessor(webId);
		  		  
		  //Attempt to find the tab order JSON
		  Entity courseTab = getTabOrderEntity(webId);	  
		  
		  //If doesn't exist, make new one
		  if(courseTab == null){
		    courseTab = new Entity("TabOrderJSON", professor.getKey());
		    courseTab.setProperty("tabOrder", tabOrderJSON);
		    courseTab.setProperty("webId", webId);
			DatastoreAPI.persistEntity(courseTab);
		  }
		  
		  //O/w return one... dam duplications -.- 
		  return courseTab;
	}
	
	

}

