package nl.qnh.qforce.service;

import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.model.AnalyticsDTO;
import nl.qnh.qforce.model.PeopleList;
import nl.qnh.qforce.model.PersonImpl;
import nl.qnh.qforce.model.PersonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Implementation class for PersonService interface
 *
 * @author Ketrina
 */

@Service
public class PersonServiceImpl implements PersonService {

    /**
     * Variable for Movie service
     */
    private final MovieService movieService;

    /**
     * Variable for Analytics service
     */
    private final AnalyticsService analyticsService;

    /**
     * RestTemplate variable
     */
    private final RestTemplate restTemplate;

    /**
     * GENERAL API URL to make calls to
     */
    private final static String API_URL = "https://swapi.dev/api/people/";

    /**
     * Constructor
     *
     * @param movieService
     * @param analyticsService
     * @param restTemplate
     */
    public PersonServiceImpl(final MovieService movieService, final AnalyticsService analyticsService, final RestTemplate restTemplate) {
        this.movieService = movieService;
        this.analyticsService = analyticsService;
        this.restTemplate = restTemplate;
    }

    /**
     *
     * @param query the query string to search
     * @return List of people found
     */
    @Override
    public List<Person> search(final String query) {
        final List<Person> people = new ArrayList<>();
        try {
            final PeopleList response = getPeopleList(query);
            final List<PersonResult> peopleFound = response.getResults();
            final List<PersonImpl> peopleCreated = new ArrayList<>();
            int countId = 0;
            for(final PersonResult p : peopleFound) {
                countId++;
                final List<Movie> movies = movieService.getMovies(p.getMovies());
                final PersonImpl personData = createPerson(p, countId, movies);
                peopleCreated.add(personData);
            }

            people.addAll(peopleCreated);

            updateAnalyticsForApiCall("SEARCH");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }

    /**
     *
     * @param personId the personId of the person
     * @return Optional<Person> from the API
     */
    @Override
    public Optional<Person> get(final long personId) {
        Optional<Person> person = Optional.empty();
        try {
            final PersonResult personFromUrl = getPersonResult(personId);
            final List<String> moviesFromUrl = personFromUrl.getMovies();
            final List<Movie> movies = movieService.getMovies(moviesFromUrl);
            final PersonImpl personData = createPerson(personFromUrl, personId, movies);
            person = Optional.ofNullable(personData);

            updateAnalyticsForApiCall("GET");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    /**
     * Api call to get List of people
     * @param query
     * @return PeopleList
     */
    private PeopleList getPeopleList(final String query) {
        return restTemplate.getForObject(API_URL+query, PeopleList.class);
    }

    /**
     * Api call to get person with ID
     * @param personId
     * @return PersonResult
     */
    private PersonResult getPersonResult(final long personId) {
        return restTemplate.getForObject(API_URL+personId, PersonResult.class);
    }

    /**
     * Method to change api calls analytics
     * @param apiCall
     */
    private void updateAnalyticsForApiCall(final String apiCall) {
        final AnalyticsDTO analytics = analyticsService.findById(1);

        if(analytics != null) {
            updateAnalytics(apiCall, analytics);
        } else {
            createAnalytics(apiCall);
        }
    }

    /**
     * Updates analytics data based on API call
     * @param apiCall
     * @param analytics
     */
    private void updateAnalytics(final String apiCall, final AnalyticsDTO analytics) {
        AnalyticsDTO updatedValues = new AnalyticsDTO();
        if("SEARCH".equals(apiCall)) {
            updatedValues = AnalyticsDTO.builder()
                    .getApiCount(analytics.getGetApiCount())
                    .searchApiCount(analytics.getSearchApiCount() + 1)
                    .build();
        }

        if("GET".equals(apiCall)) {
            updatedValues = AnalyticsDTO.builder()
                    .getApiCount(analytics.getGetApiCount() + 1)
                    .searchApiCount(analytics.getSearchApiCount())
                    .build();
        }
        analyticsService.updateAnalytics(1, updatedValues);
    }

    /**
     * Creates Analytics based on API call if it does not exist
     * @param apiCall
     */
    private void createAnalytics(final String apiCall) {
        AnalyticsDTO initialValues = new AnalyticsDTO();
        if("SEARCH".equals(apiCall)) {
            initialValues = AnalyticsDTO.builder()
                    .getApiCount(0)
                    .searchApiCount(1)
                    .build();
        }

        if("GET".equals(apiCall)) {
            initialValues = AnalyticsDTO.builder()
                    .getApiCount(1)
                    .searchApiCount(0)
                    .build();
        }
        analyticsService.createAnalytics(initialValues);
    }

    /**
     *
     * @param personFromUrl holds the data from the API
     * @param personId the id of the person we are getting
     * @param movies the list of movies this person is part of
     * @return object of type PersonImpl which implements the Person interface
     */
    private PersonImpl createPerson(final PersonResult personFromUrl, final long personId, final List<Movie> movies) {
        return PersonImpl.builder()
                .id(personId)
                .name(personFromUrl.getName())
                .birthYear(personFromUrl.getBirthYear())
                .gender(personFromUrl.getGender())
                .height(personFromUrl.getHeight())
                .weight(personFromUrl.getWeight())
                .movies(movies)
                .build();
    }
}
