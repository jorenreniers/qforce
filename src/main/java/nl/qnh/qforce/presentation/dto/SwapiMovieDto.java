package nl.qnh.qforce.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *A Data Transfer Object that is used to represent a movie from the SWAPI.
 * <p>
 * This class will change the JSON response from SWAPI into java objects.
 * Unknown properties are ignored. These are extra fields that are returned by the API.
 * </p>
 *
 * The Lombok annotations {@link Getter}, {@link Setter}, {@link NoArgsConstructor} and {@link AllArgsConstructor} are used so to create getters and setters for all the fields
 * and the necessary constructors to operate without writing the extra code.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiMovieDto {

    /**
     * The title of the movie
     */
    @JsonProperty("title")
    private String title;

    /**
     * The episode number of the movie.
     * Mapped from the JSON property {@code episode_id}.
     */
    @JsonProperty("episode_id")
    private Integer episode;

    /**
     * Name of the Director
     */
    @JsonProperty("director")
    private String director;

    /**
     * The release date of the movie.
     * Provided by SWAPI as a String.
     */
    @JsonProperty("release_date")
    private String releaseDate;
}
