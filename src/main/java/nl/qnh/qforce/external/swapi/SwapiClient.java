package nl.qnh.qforce.external.swapi;

import nl.qnh.qforce.presentation.dto.SwapiMovieDto;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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

    public SwapiPeopleResponse searchPersons(String query) {
        return restTemplate.getForObject(
                "https://swapi.info/api/people/?search={query}",
                SwapiPeopleResponse.class,
                query
        );
    }

    public SwapiMovieDto getMovieByUrl(String url) {
        return restTemplate.getForObject(url, SwapiMovieDto.class);
    }
}
