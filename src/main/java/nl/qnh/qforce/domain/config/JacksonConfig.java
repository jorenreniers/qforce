package nl.qnh.qforce.domain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Jackson
 * <p>
 * Allows customization for JSON serialization and its behavior in the app.
 * </P>
 *
 * <p>
 *     this bean will be used in all the app
 * </p>
 * **/
@Configuration
public class JacksonConfig {

    /**
     * {@link JavaTimeModule} ensures the serialization and deserialization of date and time objects.
     *
     * <p>
     *  {@link PropertyNamingStrategies#SNAKE_CASE} converts field names to snake_case for an example  {@code birthYear} to {@code birth_year} in JSON output
     * </p>
     * **/

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .build();
    }
}



