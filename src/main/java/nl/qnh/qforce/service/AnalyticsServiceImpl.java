package nl.qnh.qforce.service;

import nl.qnh.qforce.model.AnalyticsDTO;
import nl.qnh.qforce.model.entities.Analytics;
import nl.qnh.qforce.repository.ApiAnalyticsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation class for Analytics
 */
@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    /**
     * Variable for Analytics Repository
     */
    private final ApiAnalyticsRepository analyticsRepo;

    /**
     * Constructor
     * @param analyticsRepo
     */
    public AnalyticsServiceImpl(final ApiAnalyticsRepository analyticsRepo) {
        this.analyticsRepo = analyticsRepo;
    }

    /**
     * Method to create analytics in db
     * @param analyticsDTO
     */
    @Override
    public void createAnalytics(final AnalyticsDTO analyticsDTO) {
        final Analytics analytics = new Analytics();

        analytics.setGetApiCount(analyticsDTO.getGetApiCount());
        analytics.setSearchApiCount(analyticsDTO.getSearchApiCount());
        analyticsRepo.save(analytics);
    }

    /**
     * Method to update get/post api call values
     * @param analyticsId
     * @param analyticsDTO
     */
    @Override
    public void updateAnalytics(final int analyticsId, final AnalyticsDTO analyticsDTO) {
        final Optional<Analytics> analytics = analyticsRepo.findById(analyticsId);
        if (analytics.isPresent()){
            final Analytics existingAnalytic = analytics.get();

            existingAnalytic.setGetApiCount(analyticsDTO.getGetApiCount());
            existingAnalytic.setSearchApiCount(analyticsDTO.getSearchApiCount());

            analyticsRepo.save(existingAnalytic);
        }
    }

    /**
     * Method to find analytics in db by ID
     * @param analyticsId
     * @return
     */
    @Override
    public AnalyticsDTO findById(final int analyticsId) {
        AnalyticsDTO values = null;
        final Optional<Analytics> analytics = analyticsRepo.findById(analyticsId);
        if (analytics.isPresent()) {
            final Analytics existingAnalytic = analytics.get();
            values = AnalyticsDTO.builder()
                    .getApiCount(existingAnalytic.getGetApiCount())
                    .searchApiCount(existingAnalytic.getSearchApiCount())
                    .build();
        }
        return values;
    }
}
