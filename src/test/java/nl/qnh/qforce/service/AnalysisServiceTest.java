package nl.qnh.qforce.service;

import nl.qnh.qforce.persistence.entity.AnalysisEntity;
import nl.qnh.qforce.persistence.repository.AnalysisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * test for the {@link AnalysisServiceImpl}
 */

@ExtendWith(MockitoExtension.class)
public class AnalysisServiceTest {

    @Mock
    AnalysisRepository analysisRepository;

    @InjectMocks
    AnalysisServiceImpl analysisServiceImpl;

    /**
     * Tests that the {@link AnalysisServiceImpl#logEndPoint(String)} method
     *Uses the repository's logEndPoint() 1 time when logging.
     */
    @Test
    public void testLogEndPoint() {

        String endpoint = "/persons?q=Luke";

        analysisServiceImpl.logEndPoint(endpoint);

        verify(analysisRepository, times(1)).save(any(AnalysisEntity.class));
    }


}
