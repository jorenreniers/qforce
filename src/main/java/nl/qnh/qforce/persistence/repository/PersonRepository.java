package nl.qnh.qforce.persistence.repository;

import nl.qnh.qforce.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByName(String name);
}