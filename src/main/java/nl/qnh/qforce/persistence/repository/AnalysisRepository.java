package nl.qnh.qforce.persistence.repository;

import nl.qnh.qforce.persistence.entity.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Repository Interface for the {@link AnalysisEntity}
 *
 * Provides the Create, Read, Delete and update operations. Thanks to the JpaRepository we can now use for example
 * save() findById() and findAll()
 */
@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Long>{

}