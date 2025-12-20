package nl.qnh.qforce.presentation.controller;

import lombok.RequiredArgsConstructor;
import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.service.AnalysisService;
import nl.qnh.qforce.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;
    private final AnalysisService analysisService;


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

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable long id) {

        analysisService.logEndPoint("/persons/" + id);


        return personService.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
