package nl.qnh.qforce.service;


import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.persistence.converter.PersonConverter;
import nl.qnh.qforce.persistence.repository.PersonRepository;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final RestTemplate restTemplate;
    private final String SWAPI_BASE_URL = "https://swapi.info/api/";

    @Autowired
    public PersonServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Person> search(String query) {
        String url = SWAPI_BASE_URL + "people/?search=" + query;
        SwapiPersonDto[] swapiResult = restTemplate.getForObject(url, SwapiPersonDto[].class);

        if (swapiResult != null) {
            return Arrays.stream(swapiResult)
                    .filter(person -> person.getName().toLowerCase().contains(query.toLowerCase()))
                    .map(swapiPerson -> PersonConverter.convertToPerson(swapiPerson, restTemplate))
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    @Override
    public Optional<Person> get(long id) {
        String url = SWAPI_BASE_URL + "people/" + id + "/";
        SwapiPersonDto swapiPerson = restTemplate.getForObject(url, SwapiPersonDto.class);
        if (swapiPerson != null) {
            return Optional.of(PersonConverter.convertToPerson(swapiPerson, restTemplate));
        } else {
            return Optional.empty();
        }
    }


//
//    @Override
//    public List<Person> search(String query) {
//        List<PersonEntity> personEntities = personRepository.findByNameContaining(query);
//        return personEntities.stream()
//                .map(PersonConverter::toPerson)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<Person> get(long id) {
//        return personRepository.findById(id)
//                .map(PersonConverter::toPerson);
//    }
}
