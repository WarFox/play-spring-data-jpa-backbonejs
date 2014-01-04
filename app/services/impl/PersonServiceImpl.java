package services.impl;

import models.Person;
import repositories.PersonRepository;
import services.PersonService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 */
@Named
@Singleton
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

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
}
