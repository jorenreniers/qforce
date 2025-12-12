package nl.qnh.qforce.presentation.controller;

import lombok.RequiredArgsConstructor;
import nl.qnh.qforce.domain.person.PersonImpl;
import nl.qnh.qforce.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;


    @GetMapping
    Public<ResponseEntity<List<PersonImpl>> getPersons(@RequestParam(required = false) String name){

    }
}
