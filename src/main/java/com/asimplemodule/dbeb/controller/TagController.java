package com.asimplemodule.dbeb.controller;

import static spark.Spark.get;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.asimplemodule.dbeb.models.Tag;
import com.asimplemodule.dbeb.util.HibernateUtil;
import com.asimplemodule.dbeb.util.JsonTransformer;

public class TagController {

	
	public TagController() {
		 
	    
	    get("dbeb/tags", "application/json", (request, response) -> {
	        String name = request.queryParams("name");
	        List<Tag> objs = HibernateUtil.getSession().createCriteria(Tag.class).add(
	                Restrictions.ilike("name",name,MatchMode.START)).list();
	        return objs;
	    }, new JsonTransformer());
	}

    
   
}
