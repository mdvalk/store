package nl.jumbo.store.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        var server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        var contact = new Contact();
        contact.setName("Melvin de Valk");
        contact.setEmail("melvin.de.valk@outlook.com");

        var information = new Info()
                .title("Jumbo Store API")
                .version("1.0")
                .description("This API exposes endpoints to manage stores.")
                .contact(contact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}