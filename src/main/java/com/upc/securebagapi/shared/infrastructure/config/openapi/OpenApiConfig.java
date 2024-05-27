package com.upc.securebagapi.shared.infrastructure.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI secureBagOpenApi() {

        final String securitySchemeName = "Bearer Token Authentication";

        Info apiInfo = new Info()
                .title("SecureBAG - API RESTful")
                .description("BackEnd - SecureBAG application RESTful API documentation")
                .version("1.0.0")
                .license(new License().name("Apache 2.0").url("https://springdoc.org"));

        ExternalDocumentation externalDocs = new ExternalDocumentation()
                .description("SecureBAG Wiki Documentation")
                .url("https://github.com/WilverAR/securebag-api/wiki");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(securitySchemeName);

        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Components components = new Components().addSecuritySchemes(securitySchemeName, securityScheme);

        return new OpenAPI()
                .info(apiInfo)
                .externalDocs(externalDocs)
                .addSecurityItem(securityRequirement)
                .components(components);
        /*
        return new OpenAPI()
                .info(new Info()
                        .title("SecureBAG - API")
                        .description("BackEnd - SecureBAG application REST API documentation.")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SecureBAG Wiki Documentation")
                        .url("https://github.com/WilverAR/securebag-api/wiki"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
         */
    }
}
