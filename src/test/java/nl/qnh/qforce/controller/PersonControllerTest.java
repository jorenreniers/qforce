package nl.qnh.qforce.controller;

import nl.qnh.qforce.domain.enums.Gender;
import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.movie.MovieImpl;
import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.domain.person.PersonImpl;
import nl.qnh.qforce.presentation.controller.PersonController;
import nl.qnh.qforce.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    // Test Movie
    Movie movie1 = new MovieImpl(1L, "The Empire Strikes Back", 5, "Irvin Kershner", LocalDate.of(1980, 5, 17));

    // Test persons
    Person person1 = PersonImpl.builder()
            .id(1L)
            .name("Luke Skywalker")
            .birthYear("19BBY")
            .gender(Gender.MALE)
            .height(172)
            .weight(77)
            .movies(List.of(movie1))
            .build();

    @Test
    void getPersonsAsAList() throws Exception {
        when(personService.search("Luke")).thenReturn(List.of(person1));

        mockMvc.perform(get("/persons").param("q", "Luke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Luke Skywalker"));
    }


    @Test
    void getPersonsAsAListShouldFail() throws Exception {
        when(personService.search("Luke")).thenReturn(List.of(person1));

        mockMvc.perform(get("/persons").param("q", "Luke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("R2"));
    }

    @Test
    void getPersonById() throws Exception {
        when(personService.get(1L)).thenReturn(Optional.of(person1));

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }


}
