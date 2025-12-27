package nl.qnh.qforce.domain.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import nl.qnh.qforce.domain.enums.Gender;
import nl.qnh.qforce.domain.movie.Movie;


import java.util.List;


/**
 *The implementation of the {@link Person} interface
 * <p>
 *  {@link Getter} en {@link Setter} are from lombok so all the properties have getters and setters without writing codelines
 *</p>
 * Also, there are No and All arguments constructors these provide automatic constructors with no arguments and all arguments (with all fields) that can be used in the app
 *<p>
 *    {@link JsonInclude } {@link JsonInclude.Include.NON_NULL} ensure that field with value null are excluded from JSON output
 *</p>
 *
 * <p>
 *     {@link JsonProperty} Jackson annotations maps Java field names to custom JSON property names for an example {@code episode_id}, {@code release_date}
 * </p>
 **/

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonImpl implements Person {

    /***
     * Unique identifier of a person
     * */
    @JsonProperty("id")
    private long id;

    /**
     * Name of a person
     **/
    @JsonProperty("name")
    private String name;

    /**
     * Birthyear of a person
     * */
    @JsonProperty("birth_year")
    private String birthYear;

    /**
     * Gender of a person. Uses the ENUM {@link Gender}
     * */

    @JsonProperty("gender")
    private Gender gender;

    /**
     * Height of a person
     * */
    @JsonProperty("height")
    private Integer height;

    /**
     * Weight of a person
     * */
    @JsonProperty("weight")
    private Integer weight;

    /**
     * A list of the movies where a person appears in
     * */
    @JsonProperty("movies")
    private List<Movie> movies;

    /**
     * Return of a string of the movie was used for debugging and logging.
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender=" + gender +
                ", height=" + height +
                ", weight=" + weight + '\''+
                "movies=" + movies +
                '}';
    }
}
