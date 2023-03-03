package nl.qnh.qforce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.qnh.qforce.domain.Movie;

import java.time.LocalDate;

/**
 * Class implementation of Movie interface
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieImpl implements Movie {
    private String title;
    @JsonProperty("episode_id")
    private Integer episode;
    private String director;
    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getEpisode() {
        return episode;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
