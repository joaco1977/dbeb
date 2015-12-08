package com.asimplemodule.dbeb;

import static spark.Spark.*;
import com.asimplemodule.dbeb.models.*;
import com.asimplemodule.dbeb.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.hibernate.Session;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;

public class App {

    public static void main(final String[] args) {
        setPort(8080);
        externalStaticFileLocation("public"); // Static files

        get("/", (request, response) -> {
            response.redirect("/index.html");
            return "";
        });

        
        get("dbeb/users", "application/json", (request, response) -> {
            List<User> objs = HibernateUtil.getSession().createCriteria(User.class).list();
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
        
        get("dbeb/recommendations", "application/json", (request, response) -> {
            List<Recommendation> objs = HibernateUtil.getSession().createCriteria(Recommendation.class).list();
            return objs;
        }, new JsonTransformer());

        get("dbeb/recommendations/:id", "application/json", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            Recommendation obj = (Recommendation)HibernateUtil.getSession().get(Recommendation.class, id);
            if (obj == null) halt(404);
            return obj;
        }, new JsonTransformer());

        post("dbeb/recommendations", "application/json", (request, response) -> {
            Recommendation obj = JacksonUtil.readValue(request.body(), Recommendation.class);
            HibernateUtil.getSession().saveOrUpdate(obj);
            response.status(201);
            return obj;
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
        
        get("dbeb/companies", "application/json", (request, response) -> {
            List<Company> objs = HibernateUtil.getSession().createCriteria(Company.class).list();
            return objs;
        }, new JsonTransformer());

        get("dbeb/companies/:id", "application/json", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            Company obj = (Company)HibernateUtil.getSession().get(Company.class, id);
            if (obj == null) halt(404);
            return obj;
        }, new JsonTransformer());

        post("dbeb/companies", "application/json", (request, response) -> {
            Company obj = JacksonUtil.readValue(request.body(), Company.class);
            HibernateUtil.getSession().saveOrUpdate(obj);
            response.status(201);
            return obj;
        }, new JsonTransformer());

        put("dbeb/companies/:id", "application/json", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            Company obj = (Company)HibernateUtil.getSession().get(Company.class, id);
            if (obj == null) halt(404);
            obj = JacksonUtil.readValue(request.body(), Company.class);
            obj = (Company)HibernateUtil.getSession().merge(obj);
            return obj;
        }, new JsonTransformer());

        delete("dbeb/companies/:id", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            Company obj = (Company)HibernateUtil.getSession().get(Company.class, id);
            if (obj == null) halt(404);
            HibernateUtil.getSession().delete(obj);
            response.status(204);
            return "";
        });
        
        get("dbeb/locations", "application/json", (request, response) -> {
            List<Location> objs = HibernateUtil.getSession().createCriteria(Location.class).list();
            return objs;
        }, new JsonTransformer());

        get("dbeb/locations/:id", "application/json", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            Location obj = (Location)HibernateUtil.getSession().get(Location.class, id);
            if (obj == null) halt(404);
            return obj;
        }, new JsonTransformer());

        post("dbeb/locations", "application/json", (request, response) -> {
            Location obj = JacksonUtil.readValue(request.body(), Location.class);
            HibernateUtil.getSession().saveOrUpdate(obj);
            response.status(201);
            return obj;
        }, new JsonTransformer());

        put("dbeb/locations/:id", "application/json", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            Location obj = (Location)HibernateUtil.getSession().get(Location.class, id);
            if (obj == null) halt(404);
            obj = JacksonUtil.readValue(request.body(), Location.class);
            obj = (Location)HibernateUtil.getSession().merge(obj);
            return obj;
        }, new JsonTransformer());

        delete("dbeb/locations/:id", (request, response) -> {
            long id = Long.parseLong(request.params(":id"));
            Location obj = (Location)HibernateUtil.getSession().get(Location.class, id);
            if (obj == null) halt(404);
            HibernateUtil.getSession().delete(obj);
            response.status(204);
            return "";
        });
        

        before((request, response) -> {
            HibernateUtil.getSession().beginTransaction();
        });

        after((request, response) -> {
            HibernateUtil.getSession().getTransaction().commit();
            HibernateUtil.closeSession();
        });

        exception(Exception.class, (e, request, response) -> {
            HibernateUtil.getSession().getTransaction().rollback();
            HibernateUtil.closeSession();
            response.status(500);
        });
    }
}
