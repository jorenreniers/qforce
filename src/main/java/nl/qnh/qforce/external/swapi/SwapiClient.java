package nl.qnh.qforce.external.swapi;

import nl.qnh.qforce.presentation.dto.SwapiMovieDto;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Component
public class SwapiClient {
    private final WebClient webClient;
    private final RestTemplate restTemplate;

    public SwapiClient(WebClient.Builder builder, RestTemplate restTemplate) {
        this.webClient = builder
                .baseUrl("https://swapi.info/api")
                .build();
        this.restTemplate = restTemplate;
    }

    public SwapiPersonDto getPersonById(long id) {
        return webClient.get()
                .uri("/people/{id}", id)
                .retrieve()
                .bodyToMono(SwapiPersonDto.class)
                .block();
    }

    public List<SwapiPersonDto> searchPersons(String query) {
        String url = "/people/?search=" + query;
        SwapiPersonDto[] response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(SwapiPersonDto[].class)
                .block();
        return response != null ? Arrays.asList(response) : Collections.emptyList();
    }

    public SwapiMovieDto getMovieByUrl(String url) {

        System.out.println("Fetching movie from URL In SwapiClient: " + url);


        return restTemplate.getForObject(url, SwapiMovieDto.class);
    }
}
