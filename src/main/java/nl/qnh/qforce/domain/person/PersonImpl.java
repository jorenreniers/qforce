package nl.qnh.qforce.domain.person;

import lombok.*;
import nl.qnh.qforce.domain.enums.Gender;
import nl.qnh.qforce.domain.movie.Movie;
import nl.qnh.qforce.domain.movie.MovieImpl;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PersonImpl implements Person {
    private Long id;
    private String name;
    private String birthYear;
    private Gender gender;
    private int height;
    private int weight;
    private List<Movie> movies;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBirthYear() {
        return birthYear;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public List<Movie> getMovies() {
        return movies;
    }
}
