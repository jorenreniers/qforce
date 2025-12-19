package nl.qnh.qforce.persistence.repository;

import nl.qnh.qforce.persistence.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    MovieEntity findByTitle(String title);
}
