package com.asimplemodule.dbeb.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.asimplemodule.dbeb.models.User;
import com.asimplemodule.dbeb.util.HibernateUtil;
import com.asimplemodule.dbeb.util.JacksonUtil;
import com.asimplemodule.dbeb.util.JsonTransformer;

public class UserController {
	
	public UserController() {
		
get("dbeb/users", "application/json", (request, response) -> {
        	
        	String email = request.queryParams("facebookId");
        	
            List<User> objs = HibernateUtil.getSession().createCriteria(User.class)
            		.add(Restrictions.eq("facebookId",email)).list();
            
            return objs;
        }, new JsonTransformer());

        get("dbeb/users/:id", "application/json", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            User obj = (User)HibernateUtil.getSession().get(User.class, id);
            if (obj == null) halt(404);
            return obj;
        }, new JsonTransformer());

        post("dbeb/users", "application/json", (request, response) -> {
            User obj = JacksonUtil.readValue(request.body(), User.class);
            HibernateUtil.getSession().saveOrUpdate(obj);
            response.status(201);
            return obj;
        }, new JsonTransformer());

        put("dbeb/users/:id", "application/json", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            User obj = (User)HibernateUtil.getSession().get(User.class, id);
            if (obj == null) halt(404);
            obj = JacksonUtil.readValue(request.body(), User.class);
            obj = (User)HibernateUtil.getSession().merge(obj);
            return obj;
        }, new JsonTransformer());

        delete("dbeb/users/:id", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            User obj = (User)HibernateUtil.getSession().get(User.class, id);
            if (obj == null) halt(404);
            HibernateUtil.getSession().delete(obj);
            response.status(204);
            return "";
        });
	}
}



