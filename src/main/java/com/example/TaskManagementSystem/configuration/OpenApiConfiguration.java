package com.example.TaskManagementSystem.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPIDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("Local env");

        Server prodServer = new Server();
        prodServer.setUrl("http://some.production.url");
        prodServer.setDescription("Production env");

        Contact contact = new Contact();
        contact.setName("Evgeny Zhulpa");
        contact.setEmail("zhenyazhulpa@gmail.com");
        contact.setUrl("http://github.com/evgenyzhulpa");

        License license = new License().name("GNU AGPLv3")
                .url("http://choosealicense.com/licenses/agpl-3.0");
        Info info = new Info()
                .title("Task Management System API")
                .version("1.0")
                .contact(contact)
                .description("API for Task Management System")
                .license(license);
        return new OpenAPI().info(info).servers(List.of(localhostServer, prodServer));
    }
}
