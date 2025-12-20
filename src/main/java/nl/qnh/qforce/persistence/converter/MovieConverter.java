package nl.qnh.qforce.persistence.converter;

import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.movie.MovieImpl;
import nl.qnh.qforce.persistence.entity.MovieEntity;
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
}


