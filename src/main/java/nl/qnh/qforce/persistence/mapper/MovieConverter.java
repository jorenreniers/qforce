package nl.qnh.qforce.persistence.mapper;

import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.movie.MovieImpl;
import nl.qnh.qforce.persistence.entity.MovieEntity;

public abstract class MovieConverter {


    public static Movie toMovie(MovieEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("MovieEntity cannot be null");
        }

        return MovieImpl.builder()
                .title(entity.getTitle())
                .episode(entity.getEpisode())
                .director(entity.getDirector())
                .releaseDate(entity.getRelease_date())
                .build();
    }


    public static MovieEntity toMovieEntity(MovieImpl movie) {
        if (movie == null) {
            throw new IllegalArgumentException("MovieImpl cannot be null");
        }

        return MovieEntity.builder()
                .title(movie.getTitle())
                .episode(movie.getEpisode())
                .director(movie.getDirector())
                .release_date(movie.getReleaseDate())
                .build();


    }
}

