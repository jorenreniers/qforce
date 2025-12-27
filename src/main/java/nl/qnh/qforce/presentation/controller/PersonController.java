package nl.qnh.qforce.presentation.controller;

import lombok.RequiredArgsConstructor;
import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.service.AnalysisService;
import nl.qnh.qforce.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A REST controller used for handling the HTTP requests for the persons.
 * With a search option for looking up a specific name or containing a part of it.
 * Or searching for a specific id.
 * These searches are logged with the {@link AnalysisService}
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    /**
     * PersonService has the option to search() or to search with a specific id the get().
     */
    private final PersonService personService;

    /**
     * AnalysisService is used to log the eindpoint used in the H2 database.
     */
    private final AnalysisService analysisService;

    /**
     * Searches for persons with the given query. If no persons are found, a {@code 404 Not Found} response is returned.
     * @param query the search query
     * @return a {@link ResponseEntity} containing a list of {@link Person}
     */
    @GetMapping
    public ResponseEntity<List<Person>> search(@RequestParam(name = "q") String query) {

       analysisService.logEndPoint("/persons" + "search" + query);

        var result = personService.search(query);
        var persons = result.stream()
                .toList();
        if (persons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else  {
            return ResponseEntity.ok(persons);
        }
    }

    /**
     * Retrieves a person by its unique identifier.
     * The request is logged for analytical purposes. If the person does not exist, a {@code 404 Not Found} response is returned.
     * @param id the unique identifier of the person
     * @return a {@link ResponseEntity} containing the {@link Person} if found
     */

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable long id) {

        analysisService.logEndPoint("/persons/" + id);


        return personService.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
