package nl.qnh.qforce.presentation.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class SwapiPersonDto {
    private String name;
    @JsonProperty("birth_year")
    private String birthYear;
    private String gender;
    private String height;
    private String mass;
    @JsonProperty("films")
    private List<String> films;
    private String url;
}
