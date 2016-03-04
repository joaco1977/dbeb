package com.asimplemodule.dbeb;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.SparkBase.externalStaticFileLocation;
import static spark.SparkBase.setPort;

import com.asimplemodule.dbeb.controller.RecoController;
import com.asimplemodule.dbeb.controller.TagController;
import com.asimplemodule.dbeb.controller.UserController;
import com.asimplemodule.dbeb.util.HibernateUtil;

public class App {

	
    public static void main(final String[] args) {
        setPort(50845);
        externalStaticFileLocation("public"); // Static files
        
        new RecoController();
        new UserController();
        new TagController();
        
        get("/", (request, response) -> {
            response.redirect("/index.html");
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
