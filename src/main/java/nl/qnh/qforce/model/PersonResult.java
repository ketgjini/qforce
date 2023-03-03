package nl.qnh.qforce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import nl.qnh.qforce.domain.Gender;

import java.util.List;

/**
 * Class for Person api result data
 */
@JsonDeserialize
public class PersonResult {
    private String name;
    @JsonProperty("birth_year")
    private String birthYear;
    private Gender gender;
    private Integer height;
    @JsonProperty("mass")
    private Integer weight;
    @JsonProperty("films")
    private List<String> movies;

    public String getName() {
        return name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public List<String> getMovies() {
        return movies;
    }
}
