package nl.qnh.qforce.service;

import nl.qnh.qforce.persistence.entity.AnalysisEntity;
import nl.qnh.qforce.persistence.repository.AnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The implementation of the {@link AnalysisService} interface. Responsible for logging the endpoint.
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    AnalysisRepository analysisRepository;


    public AnalysisServiceImpl(AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }

    /**
     * Logs an endpoint by saving the endpoint path and the current time in the database.
     * @param endpoint the endpoint path that was accessed
     */
    @Override
    public void logEndPoint(String endpoint) {
        AnalysisEntity analysis = new AnalysisEntity();
        analysis.setEndpoint(endpoint);
        analysis.setTimestamp(LocalDateTime.now());
        analysisRepository.save(analysis);
    }
}
