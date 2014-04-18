package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * The main set of web services.
 */
@Named
@Singleton
public class Application extends Controller {

    public static Result index() {
        return ok(views.html.people.index.render("play-spring-data-jpa-backbonejs"));
    }
}
