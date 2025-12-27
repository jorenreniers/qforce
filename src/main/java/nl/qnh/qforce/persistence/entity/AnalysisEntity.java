package nl.qnh.qforce.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * The database Entity Analysis to keep track of the API calls in the application. Stored in the H2 database.
 * JPA annotations are used {@link Entity}, {@link Table}, {@link Id} and {@link Column} to create a table in the database
 *
 * The Lombok annotations {@link Getter}, {@link Setter}, {@link NoArgsConstructor} and {@link AllArgsConstructor} are used so to create getters and setters for all the fields
 * and the necessary constructors to operate without writing the extra code.
 */
@Entity
@Table(name = "analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisEntity {

    /**
     * Unique identifier of the analysis record and created automatically
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Which endpoint is accessed
     */

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    /**
     * The requested timestamp when the endpoint was accessed
     */

    @Column(name = "timestamp" , nullable = false)
    private LocalDateTime timestamp;


}
