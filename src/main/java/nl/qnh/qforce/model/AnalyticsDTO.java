package nl.qnh.qforce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Analytics DTO class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalyticsDTO {
    private int getApiCount;
    private int searchApiCount;
}
