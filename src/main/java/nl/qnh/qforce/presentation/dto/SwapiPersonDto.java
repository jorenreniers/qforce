package nl.qnh.qforce.presentation.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 *A Data Transfer Object that is used to represent a person from the SWAPI.
 * <p>
 * This class will change the JSON response from SWAPI into java objects.
 * Unknown properties are ignored. These are extra fields that are returned by the API.
 * </p>
 *
 * The Lombok annotations {@link Getter}, {@link Setter}, {@link NoArgsConstructor} and {@link AllArgsConstructor} are used so to create getters and setters for all the fields
 * and the necessary constructors to operate without writing the extra code.
 */
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
