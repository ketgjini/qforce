package nl.qnh.qforce.controller;


import nl.qnh.qforce.QforceApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = QforceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testRetrievePersonById() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/people/1"),
                HttpMethod.GET, entity, String.class);

        String expected = "{" +
                "    \"id\": 1," +
                "    \"name\": \"Luke Skywalker\"," +
                "    \"birth_year\": \"19BBY\"," +
                "    \"gender\": \"male\"," +
                "    \"height\": 172," +
                "    \"weight\": 77," +
                "    \"movies\": [" +
                "        {" +
                "            \"title\": \"A New Hope\"," +
                "            \"director\": \"George Lucas\"," +
                "            \"episode_id\": 4," +
                "            \"release_date\": \"1977-05-25\"" +
                "        }," +
                "        {" +
                "            \"title\": \"The Empire Strikes Back\"," +
                "            \"director\": \"Irvin Kershner\"," +
                "            \"episode_id\": 5," +
                "            \"release_date\": \"1980-05-17\"" +
                "        }," +
                "        {" +
                "            \"title\": \"Return of the Jedi\"," +
                "            \"director\": \"Richard Marquand\"," +
                "            \"episode_id\": 6," +
                "            \"release_date\": \"1983-05-25\"" +
                "        }," +
                "        {" +
                "            \"title\": \"Revenge of the Sith\"," +
                "            \"director\": \"George Lucas\"," +
                "            \"episode_id\": 3," +
                "            \"release_date\": \"2005-05-19\"" +
                "        }" +
                "    ]" +
                "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
