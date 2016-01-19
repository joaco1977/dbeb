package com.asimplemodule.dbeb;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.SparkBase.externalStaticFileLocation;
import static spark.SparkBase.setPort;

import java.util.List;

import com.asimplemodule.dbeb.models.Company;
import com.asimplemodule.dbeb.models.Tag;
import com.asimplemodule.dbeb.models.Location;
import com.asimplemodule.dbeb.models.Recommendation;
import com.asimplemodule.dbeb.models.User;
import com.asimplemodule.dbeb.util.HibernateUtil;
import com.asimplemodule.dbeb.util.JacksonUtil;
import com.asimplemodule.dbeb.util.JsonTransformer;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class App {

	
    public static void main(final String[] args) {
        setPort(50845);
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
            Recommendation reco = JacksonUtil.readValue(request.body(), Recommendation.class);
            
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
        
        get("dbeb/tags", "application/json", (request, response) -> {
            String name = request.queryParams("name");
            List<Tag> objs = HibernateUtil.getSession().createCriteria(Tag.class).add(
                    Restrictions.ilike("name",name,MatchMode.START)).list();
            return objs;
        }, new JsonTransformer());

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
