package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Person;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
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
public class PersonController extends Controller {

    // We are using constructor injection to receive a service to support our desire for immutability.
    private final PersonService personService;

    @Inject
    public PersonController(PersonService personService) {
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
        if (personForm.hasErrors()) {
            Logger.info("has errors" + personForm.errorsAsJson());
        }
        Person person = personForm.get();
        person = personService.save(person);

        JsonNode jsonNode = Json.toJson(person);
        ObjectNode result = Json.newObject();
        result.put("data", jsonNode);
        result.put("status", "OK");
        return created(result);
    }

    /**
     * Update person with given id
     *
     * @param id Person id
     */
    public Result updatePerson(Long id) {
        Form<Person> personForm = Form.form(Person.class).bindFromRequest();
        if (personForm.hasErrors()) {
            Logger.info("has errors" + personForm.errorsAsJson());
        }
        Person person = personForm.get();
        person.id = id;
        person = personService.save(person);

        JsonNode jsonNode = Json.toJson(person);
        ObjectNode result = Json.newObject();
        result.put("data", jsonNode);
        result.put("status", "OK");

        return ok(result);
    }

    /**
     * Delete person with given id
     *
     * @param id Person id
     */
    public Result delete(Long id) {
        personService.delete(id);
        return ok();
    }

}
