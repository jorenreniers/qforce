package nl.qnh.qforce.persistence.repository;

import nl.qnh.qforce.persistence.entity.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Long>{

}