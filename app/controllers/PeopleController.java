package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Person;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.data.Form;
import play.mvc.Controller;
import services.PersonService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

/**
 * Controller responds with JSON
 */
@Named
@Singleton
public class PeopleController extends Controller {

    // We are using constructor injection to receive a service to support our desire for immutability.
    private final PersonService personService;

    @Inject
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    public Result list() {
        List<Person> people = personService.findAll();
        ObjectNode result = Json.newObject();
        result.put("data", Json.toJson(people));
        result.put("status", "OK");
        return ok(result);
    }

    public Result get(Long id) {
        JsonNode jsonNode = Json.toJson(personService.findOne(id));
        ObjectNode result = Json.newObject();
        result.put("data", jsonNode);
        result.put("status", "OK");
        return ok(result);
    }

    /**
     * Save a new person
     */
    public Result newPerson() {
        Form<Person> personForm = Form.form(Person.class).bindFromRequest();
        if(personForm.hasErrors()) {
            Logger.info("has errors" + personForm.errorsAsJson());
        }
        Person person = personForm.get();
        person = personService.save(person);

        JsonNode jsonNode = Json.toJson(person);
        ObjectNode result = Json.newObject();
        result.put("data", jsonNode);
        result.put("status", "OK");
        return ok(result);
    }

}
