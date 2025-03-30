package dev.jerome.baret.eipdemo;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootApplication
@EnableMethodSecurity
public class EipDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EipDemoApplication.class, args);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public OpenAPI apiDocConfig(OAuth2ResourceServerProperties properties) {
        String issuerUri = properties.getJwt().getIssuerUri();
        URI issuer = URI.create(issuerUri);
        String oidcUri = UriComponentsBuilder.fromUri(issuer)
                .replacePath(issuer.getPath() + ".well-known/openid-configuration").toUriString();
        return new OpenAPI()
                .info(new Info().title("EIP demo").version("1"))
                .components(new Components().addSecuritySchemes("keycloak", new SecurityScheme()
                        .type(SecurityScheme.Type.OPENIDCONNECT)
                        .openIdConnectUrl(oidcUri)));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
