package nl.qnh.qforce.controller;

import nl.qnh.qforce.domain.enums.Gender;
import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.movie.MovieImpl;
import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.domain.person.PersonImpl;
import nl.qnh.qforce.persistence.entity.AnalysisEntity;
import nl.qnh.qforce.persistence.repository.AnalysisRepository;
import nl.qnh.qforce.presentation.controller.PersonController;
import nl.qnh.qforce.service.AnalysisService;
import nl.qnh.qforce.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * unit tests for{@link PersonController} using {@link MockMvc}.
 * Tests are for the endpoint for searching persons
 */
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     *mockbean
     * replaces the bean from the in the controllers with a mock object
     */
    @MockBean
    private PersonService personService;
    @MockBean
    private AnalysisService analysisService;

    @MockBean
    private AnalysisRepository analysisRepository;

    Movie movie1 = new MovieImpl(1L, "The Empire Strikes Back", 5, "Irvin Kershner", LocalDate.of(1980, 5, 17));

    Person person1 = PersonImpl.builder()
            .id(1L)
            .name("Luke Skywalker")
            .birthYear("19BBY")
            .gender(Gender.MALE)
            .height(172)
            .weight(77)
            .movies(List.of(movie1))
            .build();


    /**
     * Tests the /persons endpoint with a search query.
     * Verifies that the response status is 200 OK and that the returned JSON contains the expected person name and birth year.
     */
    @Test
    void getPersonsAsAList() throws Exception {
        when(personService.search("Luke")).thenReturn(List.of(person1));

        System.out.println(person1.getMovies());
        mockMvc.perform(get("/persons").param("q", "Luke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Luke Skywalker"))
                .andExpect(jsonPath("$[0].birth_year").value("19BBY"));
    }

    /**
     *Tests the same but with an identifier.
     */
    @Test
    void getPersonById() throws Exception {
        when(personService.get(1L)).thenReturn(Optional.of(person1));

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    /**
     * Tests the JSON serialization for a person object.
     * Ensures that snake_case fields "birth_year" exists in the output
     */

    @Test
    void jacksonTest() throws Exception {
        System.out.println(person1.toString());
        System.out.println(person1.getMovies().toString());

        when(personService.search("Luke")).thenReturn(List.of(person1));


        mockMvc.perform(get("/persons").param("q", "Luke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].birth_year").exists())
                .andExpect(jsonPath("$[0].birthYear").doesNotExist());
    }

    /**
     * Tests when the personservice searches a person and returns it a log of analysis is performed.
     * When you search for luke and the status is 200.
     * the log should be /personssearchluke
     */

    @Test
    void getBySearch_shouldLogAnalysis() throws Exception {

        when(personService.search(anyString())).thenReturn(List.of(person1));
        doNothing().when(analysisService).logEndPoint(anyString());

        mockMvc.perform(get("/persons").param("q", "luke"))
                .andExpect(status().isOk());

        verify(analysisService, times(1)).logEndPoint("/personssearchluke");
    }

}
