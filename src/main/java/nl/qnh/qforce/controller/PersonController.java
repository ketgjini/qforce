package nl.qnh.qforce.controller;

import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.service.PersonService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for api call with id
 * @author Ketrina
 */
@RestController
@RequestMapping("/people")
public class PersonController {

    /**
     * Variable for Person Service
     */
    private final PersonService personService;

    /**
     * Constructor
     * @param personService
     */
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    /**
     * Method to find person by id
     * @param personId
     * @return
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findPersonById(@PathVariable final int personId) {
        final Optional<Person> person = personService.get(personId);

        return person.isPresent()
                ? ResponseEntity.ok(person.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Person with id " + personId + " was not found");
    }

    /**
     * Method to search people by name
     * @param search
     * @return
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> searchPeopleByName(@RequestParam(name = "search", required = true) final String search) {
    	final String query = "?search="+search;
        final List<Person> people = personService.search(query);

        return people.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Person/s with name " + search + " was/were not found")
                : ResponseEntity.ok(people);
    }
}
