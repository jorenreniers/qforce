package nl.qnh.qforce.persistence.converter;

import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.movie.MovieImpl;
import nl.qnh.qforce.persistence.entity.MovieEntity;
import nl.qnh.qforce.presentation.dto.SwapiMovieDto;

import java.time.LocalDate;

public abstract class MovieConverter {

    public static Movie toMovie(MovieEntity entity) {
        if (entity == null) {
            return null;
        }

        return MovieImpl.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .episode(entity.getEpisode())
                .director(entity.getDirector())
                .releaseDate(entity.getReleaseDate())
                .build();
    }

    public static MovieEntity toMovieEntity(Movie movie) {
        return MovieEntity.builder()
                .title(movie.getTitle())
                .episode(movie.getEpisode())
                .director(movie.getDirector())
                .releaseDate(movie.getReleaseDate())
                .build();
    }

    public static Movie toMovie(SwapiMovieDto dto) {
        System.out.println("Converting SwapiMovieDto to Movie: " + dto);

        return MovieImpl.builder()
                .title(dto.getTitle())
                .episode(dto.getEpisode())
                .director(dto.getDirector())
                .releaseDate(LocalDate.parse(dto.getReleaseDate()))
                .build();
    }

    private static long extractId(String url) {
        String[] parts = url.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}


