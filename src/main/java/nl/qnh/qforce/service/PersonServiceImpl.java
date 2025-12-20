package nl.qnh.qforce.service;


import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.external.swapi.SwapiClient;
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

    private final SwapiClient swapiClient;

    private PersonServiceImpl(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    @Override
    public List<Person> search(String query) {
        return swapiClient.searchPersons(query).stream()
                .filter(person -> person.getName().toLowerCase().contains(query.toLowerCase()))
                .map(dto -> PersonConverter.convertToPerson(dto, swapiClient))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> get(long id) {

        SwapiPersonDto dto = swapiClient.getPersonById(id);

        if (dto == null) {
            return Optional.empty();
        }

        return Optional.of(
                PersonConverter.convertToPerson(dto, swapiClient)
        );
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
