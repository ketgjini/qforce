package nl.qnh.qforce.model.entities;

import lombok.*;

import javax.persistence.*;

/**
 * Analytics entity class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Analytics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "get_api")
    private int getApiCount;
    @Column(name = "search_api")
    private int searchApiCount;
}
