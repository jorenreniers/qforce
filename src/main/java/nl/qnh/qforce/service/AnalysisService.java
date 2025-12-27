package nl.qnh.qforce.service;

/**
 * The analysis service used to log the endpoint when a search performed.
 */
public interface AnalysisService {

    void logEndPoint(String endpoint);
}
