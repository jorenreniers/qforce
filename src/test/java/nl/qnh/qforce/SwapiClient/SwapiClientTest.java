package nl.qnh.qforce.SwapiClient;

import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.external.swapi.SwapiClient;
import nl.qnh.qforce.presentation.dto.SwapiMovieDto;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;
import nl.qnh.qforce.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SwapiClientTest{

    @Mock
    private SwapiClient swapiClient;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void testSearchPersons() {
        SwapiPersonDto mockPersonDto = new SwapiPersonDto();
        mockPersonDto.setName("Luke Skywalker");
        mockPersonDto.setBirthYear("19BBY");
        mockPersonDto.setGender("male");
        mockPersonDto.setHeight("172");
        mockPersonDto.setMass("77");
        mockPersonDto.setUrl("https://swapi.dev/api/people/1/");
        mockPersonDto.setFilms(Arrays.asList("https://swapi.dev/api/films/1/"));

        SwapiPersonDto[] mockPersons = { mockPersonDto };

        when(swapiClient.searchPersons("Luke")).thenReturn(List.of(mockPersons));


        SwapiMovieDto mockMovieDto = new SwapiMovieDto();
        mockMovieDto.setTitle("A New Hope");
        mockMovieDto.setEpisode(4);
        mockMovieDto.setDirector("George Lucas");
        mockMovieDto.setReleaseDate("1977-05-25");

        when(swapiClient.getMovieByUrl(anyString()))
                .thenReturn(mockMovieDto);

        List<Person> result = personService.search("Luke");

        assertEquals(1, result.size());
        assertEquals("Luke Skywalker", result.get(0).getName());
        assertEquals(1, result.get(0).getMovies().size());
        assertEquals("A New Hope", result.get(0).getMovies().get(0).getTitle());
    }
}
