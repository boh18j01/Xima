package com.ecommerce.apigateway.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    private static final String[] WHITELIST = {
            "/eureka/**",
            "/docs",
            "api/test",
            "/v2/api-docs/**", "/swagger-resources/**", "/swagger-resources/**", "/configuration/ui/**",
            "/configuration/security/**", "/swagger-ui.html/**", "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**", "/swagger-ui/**",
    };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.csrf().disable();
        serverHttpSecurity
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(WHITELIST)
                                .permitAll()
                                .anyExchange()
                                .authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        return serverHttpSecurity.build();
    }



    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsWebFilter corsWebFilter(GlobalCorsProperties prop){
       final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       prop.getCorsConfigurations().forEach((source::registerCorsConfiguration));
        return new CorsWebFilter(source);

    }



}
