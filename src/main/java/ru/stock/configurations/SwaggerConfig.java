package ru.stock.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Stock Service")
                .description("Stock Service")
                .version("v0.0.1"))
                .servers(List.of(new Server().url("http://localhost:8085")
                        .description("Stock service (склад сервис)")));
    }
}
