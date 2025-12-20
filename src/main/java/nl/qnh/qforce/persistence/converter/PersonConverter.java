package nl.qnh.qforce.persistence.converter;

import nl.qnh.qforce.domain.enums.Gender;
import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.movie.MovieImpl;
import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.domain.person.PersonImpl;
import nl.qnh.qforce.external.swapi.SwapiClient;
import nl.qnh.qforce.persistence.entity.PersonEntity;
import nl.qnh.qforce.presentation.dto.SwapiMovieDto;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public abstract class PersonConverter {

    public static PersonImpl toPerson(PersonEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("PersonEntity cannot be null");
        }
        return PersonImpl.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthYear(entity.getBirthYear())
                .gender(entity.getGender())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .movies(entity.getMovies() != null
                        ? entity.getMovies().stream()
                        .map(MovieConverter::toMovie)
                        .toList()
                        : Collections.emptyList())
                .build();

    }


    public static PersonEntity toPersonEntity(Person person) {
        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .birthYear(person.getBirthYear())
                .gender(person.getGender())
                .height(person.getHeight())
                .weight(person.getWeight())
                .build();
    }

    public static Person convertToPerson(SwapiPersonDto swapiPerson, RestTemplate restTemplate) {
        PersonImpl person = new PersonImpl();
        person.setId(extractIdFromUrl(swapiPerson.getUrl()));
        person.setName(swapiPerson.getName());
        person.setBirthYear(swapiPerson.getBirthYear());
        person.setGender(mapGender(swapiPerson.getGender()));
        person.setHeight(mapHeight(swapiPerson.getHeight()));
        person.setWeight(mapWeight(swapiPerson.getMass()));
        List<String> movieUrls = Optional.ofNullable(swapiPerson.getFilms()).orElse(Collections.emptyList());
        person.setMovies(getMovies(movieUrls, restTemplate));
        return person;
    }

    public static Person convertToPerson(SwapiPersonDto dto, SwapiClient swapiClient) {
        System.out.println("Films URLs in converToPerson: " + dto.getFilms());
        List<Movie> movies = Optional.ofNullable(dto.getFilms())
                .orElse(Collections.emptyList())
                .stream()
                .map(filmUrl -> {
                    SwapiMovieDto movieDto = swapiClient.getMovieByUrl(filmUrl);
                    return MovieConverter.toMovie(movieDto);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return PersonImpl.builder()
                .id(extractIdFromUrl(dto.getUrl()))
                .name(dto.getName())
                .birthYear(dto.getBirthYear())
                .gender(mapGender(dto.getGender()))
                .height(mapHeight(dto.getHeight()))
                .weight(mapWeight(dto.getMass()))
                .movies(movies)
                .build();
    }


    private static long extractIdFromUrl(String url) {
        String[] parts = url.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }

    private static Gender mapGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            return Gender.MALE;
        } else if (gender.equalsIgnoreCase("female")) {
            return Gender.FEMALE;
        } else {
            return Gender.UNKNOWN;
        }
    }

    private static Integer mapHeight(String height) {
        try {
            return Integer.parseInt(height);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Integer mapWeight(String mass) {
        try {
            return Integer.parseInt(mass);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private static List<Movie> getMovies(List<String> movieUrls, RestTemplate restTemplate) {
        List<Movie> movies = new ArrayList<>();
        for (String movieUrl : movieUrls) {
            SwapiMovieDto swapiMovie = restTemplate.getForObject(movieUrl, SwapiMovieDto.class);
            MovieImpl movie = new MovieImpl();
            movie.setTitle(swapiMovie.getTitle());
            movie.setEpisode(swapiMovie.getEpisode());
            movie.setDirector(swapiMovie.getDirector());
            movie.setReleaseDate(mapReleaseDate(swapiMovie.getReleaseDate()));
            movies.add(movie);
        }
        return movies;
    }

    private static LocalDate mapReleaseDate(String releaseDate) {
        return LocalDate.parse(releaseDate);
    }


}
