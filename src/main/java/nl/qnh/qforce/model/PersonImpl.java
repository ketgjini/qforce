package nl.qnh.qforce.model;

import lombok.*;
import nl.qnh.qforce.domain.Gender;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;

import java.util.List;

/**
 * Class implementation of Person interface
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonImpl implements Person {
    private long id;
    private String name;
    private String birthYear;
    private Gender gender;
    private Integer height;
    private Integer weight;
    private List<Movie> movies;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBirthYear() {
        return birthYear;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public List<Movie> getMovies() {
        return movies;
    }
}
