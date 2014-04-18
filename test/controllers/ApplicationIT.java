package controllers;

import models.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.GlobalSettings;
import play.mvc.Result;
import play.test.WithApplication;
import repositories.PersonRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static play.test.Helpers.*;

/**
 * An integration test focused on testing our routes configuration and interactions with our controller.
 * However we can mock repository interactions here so we don't need a real db.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationIT extends WithApplication {

    private static final Long SOME_ID = 1L;

    private PersonController app;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void setUp() throws Exception {
        app = new PersonController(personRepository);

        final GlobalSettings global = new GlobalSettings() {
            @Override
            public <A> A getControllerInstance(Class<A> aClass) {
                return (A) app;
            }
        };

        start(fakeApplication(global));
    }

    @Test
    public void indexSavesDataAndReturnsId() {
        final Person person = new Person();
        person.id = SOME_ID;
        when(personRepository.save(any(Person.class))).thenReturn(person);
        when(personRepository.findOne(SOME_ID)).thenReturn(person);

        final Result result = route(fakeRequest(GET, "/people"));

        assertEquals(OK, status(result));
        assertTrue(contentAsString(result).contains("data"));
        assertTrue(contentAsString(result).contains("status"));
    }

}
