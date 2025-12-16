package nl.qnh.qforce.domain.movie;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieImpl implements Movie{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("episode_id")
    private int episode;

    @JsonProperty("director")
    private String director;

    @JsonProperty("release_date")
    private LocalDate releaseDate;


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getEpisode() {
        return episode;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

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
