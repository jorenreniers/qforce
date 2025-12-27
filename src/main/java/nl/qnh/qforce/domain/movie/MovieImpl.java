package nl.qnh.qforce.domain.movie;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

/**
 *The implementation of the {@link Movie} interface
 * <p>
 *  {@link Getter} en {@link Setter} are from lombok so all the properties have getters and setters without writing codelines
 *</p>
 * Also, there are No and All arguments constructors these provide automatic constructors with no arguments and all arguments (with all fields) that can be used in the app
 *<p>
 *    {@link JsonInclude } {@link JsonInclude.Include.NON_NULL} ensure that field with value null are excluded from JSON output
 *</p>
 *
 * <p>
 *     {@link JsonProperty}: Maps Java field names to custom JSON property names for an example {@code episode_id}, {@code release_date}
 * </p>
 **/

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieImpl implements Movie{

    /**
     * Unique identifier
     * **/

    @JsonProperty("id")
    private Long id;

    /**
     * Title of the movie.
     */

    @JsonProperty("title")
    private String title;

    /**
     * Episode number of the movie.
     */

    @JsonProperty("episode_id")
    private Integer episode;

    /**
     * Director of the movie.
     */
    @JsonProperty("director")
    private String director;

    /**
     * Release date of the movie.
     */
    @JsonProperty("release_date")
    private LocalDate releaseDate;

    /**
     * Return of a string of the movie was used in debugging and logging.
     */

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", episode=" + episode +
                ", director='" + director + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
