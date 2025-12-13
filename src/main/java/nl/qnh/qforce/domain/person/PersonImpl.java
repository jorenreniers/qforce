package nl.qnh.qforce.domain.person;

import nl.qnh.qforce.domain.enums.Gender;
import nl.qnh.qforce.domain.movie.Movie;

import java.util.List;

public class PersonImpl implements Person {
    private Long id;
    private String name;
    private String birthYear;
    private Gender gender;
    private int height;
    private int mass;
    private List<Movie> movies;

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getBirthYear() {
        return "";
    }

    @Override
    public Gender getGender() {
        return null;
    }

    @Override
    public Integer getHeight() {
        return 0;
    }

    @Override
    public Integer getWeight() {
        return 0;
    }

    @Override
    public List<Movie> getMovies() {
        return List.of();
    }
}
