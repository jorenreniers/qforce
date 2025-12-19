package nl.qnh.qforce.service;


import nl.qnh.qforce.persistence.entity.AnalysisEntity;
import nl.qnh.qforce.persistence.repository.AnalysisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AnalysisServiceTest {

    @Mock
    AnalysisRepository analysisRepository;

    @InjectMocks
    AnalysisServiceImpl analysisServiceImpl;
    @Test
    public void testLogEndPoint() {

        String endpoint = "/persons?q=Luke";

        analysisServiceImpl.logEndPoint(endpoint);

        verify(analysisRepository, times(1)).save(any(AnalysisEntity.class));
    }


}
