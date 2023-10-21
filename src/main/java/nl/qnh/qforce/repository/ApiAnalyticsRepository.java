package nl.qnh.qforce.repository;

import nl.qnh.qforce.model.entities.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Api Analytics table
 */
@Repository
public interface ApiAnalyticsRepository extends JpaRepository<Analytics, Integer> {

    /**
     * Method to find Api Analytics by ID
     * @param analyticsId
     * @return
     */
    Optional<Analytics> findById(int analyticsId);
}
