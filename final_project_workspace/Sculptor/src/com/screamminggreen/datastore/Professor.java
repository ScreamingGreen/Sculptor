package com.screamminggreen.datastore;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;


/**
 * This class handles all the CRUD operations related to
 * Professor entity.
 *
 */
public class Professor {

  /**
   * Update the Professor
   * @param name: name of the Professor
   * @param email : email address
   * @return  updated Professor
   */
  public static void createOrUpdateProfessor(String webId, String email, String password) {
    Entity Professor = getProfessor(webId);
  	if (Professor == null) {
  	  Professor = new Entity("Professor", webId);
  	  Professor.setProperty("email", email);
  	  Professor.setProperty("password", password);
  	} else {
  	  Professor.setProperty("password", password);
  	}
  	DatastoreAPI.persistEntity(Professor);
  }

  /**
   * Retrun all the Professors
   * @param kind : of kind Professor
   * @return  Professors
   */
  public static Iterable<Entity> getAllProfessors(String kind) {
    return DatastoreAPI.listEntities(kind, null, null);
  }

  /**
   * Get Professor entity
   * @param name : name of the Professor
   * @return: Professor entity
   */
  public static Entity getProfessor(String webId) {
  	Key key = KeyFactory.createKey("Professor",webId);
  	return DatastoreAPI.findEntity(key);
  }

  /**
   * Get all details for a Professor
   * @param name: name of the Professor
   * @return list of items
   */
  
  public static List<Entity> getItems(String name) {
	  	Query query = new Query();
	  	Key parentKey = KeyFactory.createKey("Professor", name);
	  	Filter filter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.GREATER_THAN, parentKey);
	  	
	  	query.setAncestor(parentKey);	  	
	  	query.setFilter(filter);
	  	
	  	List<Entity> results = DatastoreAPI.getDatastoreServiceInstance()
	  				.prepare(query).asList(FetchOptions.Builder.withDefaults());
	  	return results;
	}
  
  /**
   * Delete Professor entity
   * @param ProfessorKey: Professor to be deleted
   * @return status string
   */
  public static String deleteProfessor(String ProfessorKey)
  {
	  Key key = KeyFactory.createKey("Professor",ProfessorKey);	   
	  
	  List<Entity> items = getItems(ProfessorKey);	  
	  if (!items.isEmpty()){
	      return "Cannot delete, as there are items associated with this Professor.";	      
	    }	    
	  DatastoreAPI.deleteEntity(key);
	  return "Professor deleted successfully";
	  
  }
}

