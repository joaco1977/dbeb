package com.asimplemodule.dbeb.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Date;
import java.util.List;

import com.asimplemodule.dbeb.models.Recommendation;
import com.asimplemodule.dbeb.util.HibernateUtil;
import com.asimplemodule.dbeb.util.JacksonUtil;
import com.asimplemodule.dbeb.util.JsonTransformer;

public class RecoController {
	
	public static final Integer QUANTITY = 4;
	public RecoController() {
		
		get("dbeb/recommendations", "application/json", (request, response) -> {
			
			int from = 0;
			
			String offset = request.queryParams("offset");
			
			if(offset != null ){
				from = Integer.valueOf(offset);
			}
			
			List<Recommendation> objs = HibernateUtil.getSession().createCriteria(Recommendation.class)
					.setFirstResult(from).setMaxResults(QUANTITY).list();
			
	        return objs;
	    }, new JsonTransformer());

	    get("dbeb/recommendations/:id", "application/json", (request, response) -> {
	        long id = Long.parseLong(request.params(":id"));
	        Recommendation obj = (Recommendation)HibernateUtil.getSession().get(Recommendation.class, id);
	        if (obj == null) halt(404);
	        return obj;
	    }, new JsonTransformer());

	    post("dbeb/recommendations", "application/json", (request, response) -> {
	        Recommendation reco = JacksonUtil.readValue(request.body(), Recommendation.class);
	        
	        reco.setRecodate(new Date());
	        HibernateUtil.getSession().save(reco);
	        
	        reco.addAutomaticTags();
	        
	        response.status(201);
	        return reco;
	    }, new JsonTransformer());

	    put("dbeb/recommendations/:id", "application/json", (request, response) -> {
	        long id = Long.parseLong(request.params(":id"));
	        Recommendation obj = (Recommendation)HibernateUtil.getSession().get(Recommendation.class, id);
	        if (obj == null) halt(404);
	        obj = JacksonUtil.readValue(request.body(), Recommendation.class);
	        obj = (Recommendation)HibernateUtil.getSession().merge(obj);
	        return obj;
	    }, new JsonTransformer());

	    delete("dbeb/recommendations/:id", (request, response) -> {
	        long id = Long.parseLong(request.params(":id"));
	        Recommendation obj = (Recommendation)HibernateUtil.getSession().get(Recommendation.class, id);
	        if (obj == null) halt(404);
	        HibernateUtil.getSession().delete(obj);
	        response.status(204);
	        return "";
	    });
	}

	
	
}
