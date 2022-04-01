package systems.boos.traindelays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TimetablesServiceConfig {

    @Bean
    RestTemplate timetablesRestTemplate(@Value("${provider.port:8085}") int port) {
        return new RestTemplateBuilder()
                .rootUri(String.format("http://localhost:%d/timetables/v1", port))
                .build();
    }
}
