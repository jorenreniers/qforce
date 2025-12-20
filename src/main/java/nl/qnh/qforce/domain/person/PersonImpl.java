package nl.qnh.qforce.domain.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import nl.qnh.qforce.domain.enums.Gender;
import nl.qnh.qforce.domain.movie.Movie;


import java.util.List;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonImpl implements Person {
    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private String birthYear;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("movies")
    private List<Movie> movies;

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
