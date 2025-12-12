package nl.qnh.qforce.domain.movie;

import java.time.LocalDate;

public class MovieImpl implements Movie{
    private String title;
    private int episodeNumber;
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
