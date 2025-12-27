package nl.qnh.qforce.persistence.converter;

import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.movie.MovieImpl;
import nl.qnh.qforce.presentation.dto.SwapiMovieDto;

import java.time.LocalDate;

/**
 * The abstract class provides static methods to convert {@link SwapiMovieDto} objecten to objects from the interface {@link Movie} and to implementation of the interface
 * The SwapimovieDto objects are from the SWAPI API
 */

public abstract class MovieConverter {
    /**
     * Converts a SwapiMovieDto to a MovieImpl domain object
     * I used a builder().build() because it is easy to use and to read
     * @param dto {@link SwapiMovieDto}
     * @return a new Movie created with the data from the dto
     * {@code LocalDate.parse()} is used to parse the releasedate from the dto  in the right formate
     */

    public static Movie toMovie(SwapiMovieDto dto) {

        /**
         * System out print was used in debugging
         */
        System.out.println("Converting SwapiMovieDto to Movie: " + dto);

        return MovieImpl.builder()
                .title(dto.getTitle())
                .episode(dto.getEpisode())
                .director(dto.getDirector())
                .releaseDate(LocalDate.parse(dto.getReleaseDate()))
                .build();
    }

}


