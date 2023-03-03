package nl.qnh.qforce.service;

import nl.qnh.qforce.domain.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class for MovieService interface
 *
 * @author Ketrina
 */

@Service
public class MovieServiceImpl implements MovieService {

    /**
     * Implementation of getMovies() to get all movies from list of APIs
     *
     * @param movieApis the query string
     * @return a list of movies
     */

    @Override
    public List<Movie> getMovies(final List<String> movieApis) {
        final RestTemplate restTemplate = new RestTemplate();
        final List<Movie> movies = new ArrayList<>();
        movieApis.forEach(movieAPI -> {
            final Movie movie = restTemplate.getForObject(movieAPI, Movie.class);
            movies.add(movie);
        });

        return movies;
    }
}
