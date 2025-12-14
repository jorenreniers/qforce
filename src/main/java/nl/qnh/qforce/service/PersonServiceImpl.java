package nl.qnh.qforce.service;

import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.domain.person.PersonRepository;
import nl.qnh.qforce.persistence.entity.PersonEntity;
import nl.qnh.qforce.persistence.mapper.PersonConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> search(String query) {
        List<PersonEntity> personEntities = personRepository.findByName(query);
        return personEntities.stream()
                .map(PersonConverter::toPerson)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> get(long id) {
        return Optional.empty();
    }
}
