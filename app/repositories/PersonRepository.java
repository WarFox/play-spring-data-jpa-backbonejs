package repositories;

import models.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Provides CRUD functionality for accessing people. Spring Data auto-magically takes care of many standard
 * operations here.
 */
@Named
@Singleton
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}