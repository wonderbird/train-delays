package systems.boos.traindelays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        ParameterizedTypeReference<Timetable> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<Timetable> exchange = restTemplate.exchange("/fchg/8005143",
                HttpMethod.GET,
                getRequestEntity(),
                responseType);
        return exchange.getBody();
    }

    private HttpEntity<String> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, generateAuthToken());
        return new HttpEntity<>(headers);
    }

    private String generateAuthToken() {
        return "Bearer " + System.getenv("TRAIN_DELAYS_API_KEY");
    }
}
