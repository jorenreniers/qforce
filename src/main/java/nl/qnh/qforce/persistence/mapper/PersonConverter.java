package nl.qnh.qforce.persistence.mapper;

import nl.qnh.qforce.domain.person.Person;
import nl.qnh.qforce.domain.person.PersonImpl;
import nl.qnh.qforce.persistence.entity.PersonEntity;

import java.util.Collections;
import java.util.stream.Collectors;

public abstract class PersonConverter {

    public static Person toPerson(PersonEntity entity) {
        if (entity == null){
            throw new IllegalArgumentException("PersonEntity cannot be null");
        }
        return PersonImpl.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthYear(entity.getBirthYear())
                .gender(entity.getGender())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .movies(entity.getMovies() != null
                        ? entity.getMovies().stream()
                        .map(MovieConverter::toMovie)
                        .toList()
                        :Collections.emptyList())
                .build();

    }


    public static PersonEntity toPersonEntity(Person person) {
        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .birthYear(person.getBirthYear())
                .gender(person.getGender())
                .height(person.getHeight())
                .weight(person.getWeight())
                .build();
    }
}
