package nl.qnh.qforce.external.swapi;

import nl.qnh.qforce.presentation.dto.SwapiMovieDto;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 **This component is used to communicated with the SWAPI
 *
 * The layer acts to separate the HTTP calls, specific for the API, from the rest of the code, and ask the data of people and movies from the API
 *
 * <p>
 *
 * </p>
 **/

@Component
public class SwapiClient {

    /**
     * Both {@link WebClient} and {@link RestTemplate} are used:
     * {@link WebClient} for non-blocking REST calls</li>
     * {@link RestTemplate} for simple blocking calls to dynamic URLs</li>
     **/
    private final WebClient webClient;
    private final RestTemplate restTemplate;

    /**constructs a new {@code SwapiClient}**/
    public SwapiClient(WebClient.Builder builder, RestTemplate restTemplate) {

        /**
         * With the baseurl
         **/
        this.webClient = builder
                .baseUrl("https://swapi.info/api")
                .build();
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves a single person from swapi
     * @param id the unique identifier from the person we are looking for
     * @returna {@link SwapiPersonDto} representing the person,
     *          or {@code null} if the person does not exist
     */

    public SwapiPersonDto getPersonById(long id) {
        try {
            return webClient.get()
                    .uri("/people/{id}", id)
                    .retrieve()
                    .bodyToMono(SwapiPersonDto.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            return null;
        }
    }


    /**
     * Searches for a person based on a query string in the SWAPI data
     * @param query is the term what we are looking for, as an example Anakin or Vader
     * @return a list of matching persons in a {@link SwapiPersonDto} or an empty list
     */

    public List<SwapiPersonDto> searchPersons(String query) {
        String url = "/people/?search=" + query;
        SwapiPersonDto[] response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(SwapiPersonDto[].class)
                .block();
        return response != null ? Arrays.asList(response) : Collections.emptyList();
    }

    /**
     * retrieves a movie from a URL, movies are stored in film in the database and are given to a person with a URL.
     * So we have to retrieve it separately
     *
     * @param url
     * @return {@link SwapiMovieDto} representing the movie,
     *         or {@code null} if the request fails
     */
    public SwapiMovieDto getMovieByUrl(String url) {

        System.out.println("Fetching movie from URL In SwapiClient: " + url);


        return restTemplate.getForObject(url, SwapiMovieDto.class);
    }
}
