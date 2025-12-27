package nl.qnh.qforce.service;

import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.external.swapi.SwapiClient;
import nl.qnh.qforce.persistence.converter.PersonConverter;
import nl.qnh.qforce.presentation.dto.SwapiPersonDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The implementation of the {@link PersonService} interface.
 * <p>
 * {@link SwapiClient} is used for the communication with the API
 * </p>
 */
@Service
public class PersonServiceImpl implements PersonService {


    private final SwapiClient swapiClient;

    private PersonServiceImpl(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    /**
     * The search function, using the provided query. Results are filtered where getName() contains the query
     *
     * @param query the query string
     * @return a list of matching results or an empty list.
     */
    @Override
    public List<Person> search(String query) {
        return swapiClient.searchPersons(query).stream()
                .filter(person -> person.getName().toLowerCase().contains(query.toLowerCase()))
                .map(dto -> PersonConverter.convertToPerson(dto, swapiClient))
                .collect(Collectors.toList());
    }

    /**
     * Searches a person by its unique identifier. If no person is found for the given id, an empty {@link Optional} is returned.
     *
     * @param id the unique identifier
     * @return an {@link Optional} containing the {@link Person} if found or an empty Optional
     */
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

}
