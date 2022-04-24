package systems.boos.traindelays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TimetablesServiceConfig {

    @Bean
    RestTemplate timetablesRestTemplate(@Value("${provider.baseUrl:http://localhost:9000/timetables/v1}") String baseUrl) {
        return new RestTemplateBuilder()
                .rootUri(baseUrl)
                .build();
    }
}
