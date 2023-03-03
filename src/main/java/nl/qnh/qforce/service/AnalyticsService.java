package nl.qnh.qforce.service;

import nl.qnh.qforce.model.AnalyticsDTO;

/**
 * API analytics service to find number of calls
 *
 * @author Ketrina
 */
public interface AnalyticsService {

    public void createAnalytics(AnalyticsDTO analyticsDTO);
    public void updateAnalytics(int id, AnalyticsDTO analyticsUpdateDTO);
    public AnalyticsDTO findById(int id);
}
