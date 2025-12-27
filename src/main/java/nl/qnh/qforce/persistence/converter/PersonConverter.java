package nl.qnh.qforce.persistence.converter;

import nl.qnh.qforce.domain.enums.Gender;
import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.domain.person.PersonImpl;
import nl.qnh.qforce.external.swapi.SwapiClient;
import nl.qnh.qforce.presentation.dto.SwapiMovieDto;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This abstract class is used to convert {@link SwapiPersonDto} objects
 * retrieved from the SWAPI into domain {@link Person} objects
 */

public abstract class PersonConverter {

    /**
     * Converts a {@link SwapiPersonDto} retrieved from SWAPI into {@link Person} domain object through the Implementation class
     * @param dto from SWAPI
     * @param swapiClient used to retrieve the data
     * @return a new domain object
     * I used the builder().build() method because it is easy to use and to read.
     */

    public static Person convertToPerson(SwapiPersonDto dto, SwapiClient swapiClient) {

        /**
         * the System out print was used in the debugging
         */
        System.out.println("Films URLs in converToPerson: " + dto.getFilms());

        /**
         * this List is used for retrieving the movies a person appears in, through the getFilms().
         * in to a {@link SwapiPersonDto}
         * then the retrieved films are converted in the {@link MovieConverter}.
         */
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

    /**
     * Extracts the unique identifier from a person from the URL.
     * The identifier is needed because the URL format is {@code https://swapi.co/api/people/{id}/},
     * @param url
     * @return
     */

    private static long extractIdFromUrl(String url) {
        String[] parts = url.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }

    /**
     * The method is used to map the Gender from the API to the enum {@link Gender}
     * because the API delivers the field as a String value
     * @param gender the field gender
     * @return the gender value as a {@link Gender} enum value
     */

    private static Gender mapGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            return Gender.MALE;
        } else if (gender.equalsIgnoreCase("female")) {
            return Gender.FEMALE;

        } else {
            return Gender.UNKNOWN;
        }
    }

    /**
     * converts the height of a person to an Integer because the value from the API is delivered as a String
     * @param height the field height
     * @return a parsed string value to an Integer
     */

    private static Integer mapHeight(String height) {
        try {
            return Integer.parseInt(height);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     *  converts the weight of a person to an Integer because the value from the API is delivered as a String
     * @param weight the field weight
     * @return a parsed string value to an Integer
     */

    private static Integer mapWeight(String weight) {
        try {
            return Integer.parseInt(weight);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
