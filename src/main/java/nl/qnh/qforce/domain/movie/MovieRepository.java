package nl.qnh.qforce.domain.movie;

import nl.qnh.qforce.persistence.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    MovieEntity findByName(String name);
}
