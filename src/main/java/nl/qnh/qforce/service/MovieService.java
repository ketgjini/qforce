package nl.qnh.qforce.service;


import nl.qnh.qforce.domain.Movie;

import java.util.List;

/**
 * The movie service to search movies via api.
 *
 * @author Ketrina
 */
public interface MovieService {

    /**
     * Retrieval of movies from list of APIs.
     *
     * @param movieApis the query string
     * @return a list of movies
     */
    List<Movie> getMovies(List<String> movieApis);
}
