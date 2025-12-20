package nl.qnh.qforce.external.swapi;

import lombok.Data;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;

import java.util.List;

@Data
public class SwapiPeopleResponse {

    private int count;
    private List<SwapiPersonDto> results;
}