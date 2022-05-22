package systems.boos.traindelays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import systems.boos.traindelays.model.Timetable;

@Service
public class TimetablesService {
    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TimetablesService.class);

    @Autowired
    public TimetablesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Timetable fetchChanges() {
        Timetable result = new Timetable();

        try {
            result = restTemplate.<Timetable>exchange("/fchg/8005143",
                    HttpMethod.GET,
                    getRequestEntity(),
                    new ParameterizedTypeReference<>() {
                    }).getBody();
        } catch (RestClientException e) {
            logger.error("Error fetching changes from timetables API: {}", e.getMessage());
        }

        return result;
    }

    private HttpEntity<String> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, generateAuthToken());
        return new HttpEntity<>(headers);
    }

    private String generateAuthToken() {
        return "Bearer " + System.getenv("API_KEY");
    }
}
