package nl.qnh.qforce.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiMovieDto {
    private String title;
    @JsonProperty("episode_id")
    private Integer episode;
    private String director;
    @JsonProperty("release_date")
    private String releaseDate;
}
