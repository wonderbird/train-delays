package systems.boos.traindelays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimetablesService {
    private final RestTemplate restTemplate;

    @Autowired
    public TimetablesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Timetable fetchChanges() {
        return restTemplate.<Timetable>exchange("/fchg/8005143",
                HttpMethod.GET,
                getRequestEntity(),
                new ParameterizedTypeReference<>() {
                }).getBody();
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
