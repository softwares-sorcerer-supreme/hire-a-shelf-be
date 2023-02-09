package com.example.shelve.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Configuration
public class SwaggerConfig {

    @Component
    @Profile("local")
    @OpenAPIDefinition(servers = @Server(url = "http://localhost:8080"))
    public static class LocalOpenAPIDefinitionConfiguration {
    }

    @Component
    @Profile("prd")
    @OpenAPIDefinition(servers = @Server(url = "http://hireashelf-env.eba-3mhfziwn.ap-northeast-1.elasticbeanstalk.com/"))
    public static class PrdOpenAPIDefinitionConfiguration {
    }


}
