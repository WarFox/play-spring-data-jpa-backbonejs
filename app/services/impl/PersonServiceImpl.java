package services.impl;

import models.Person;
import repositories.PersonRepository;
import services.PersonService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Service class for handling person class. We make it Singleton.
 */
@Named
@Singleton
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    // We are using constructor injection to receive a repository to support our desire for immutability.
    @Inject
    public PersonServiceImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person findOne(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public List<Person> findAll() {
        return iterableToList(personRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        personRepository.delete(personRepository.findOne(id));
    }

    private List<Person> iterableToList(Iterable<Person> iterable) {
        List<Person> list = new ArrayList<Person>();
        Iterator<Person> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
