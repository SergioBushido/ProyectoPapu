package es.comicon.comic.config;

import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springcomic-public")
                .pathsToMatch("/api/**")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }
}
