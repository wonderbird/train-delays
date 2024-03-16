package systems.boos.traindelays;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "Query train departure time", version = "v1", description = "Find out when the next train will leave the station Rösrath-Stümpen."),
        servers = { @Server(url = "https://train-delays-lvnrwcqd7q-ew.a.run.app/", description = "Production server") }
)
public class OpenApiConfig {
}
