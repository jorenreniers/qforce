package nl.qnh.qforce.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MovieImpl implements Movie{
    private Long id;
    private String title;
    private int episode;
    private String director;
    private LocalDate release_date;


    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public Integer getEpisode() {
        return 0;
    }

    @Override
    public String getDirector() {
        return "";
    }

    @Override
    public LocalDate getReleaseDate() {
        return null;
    }
}
