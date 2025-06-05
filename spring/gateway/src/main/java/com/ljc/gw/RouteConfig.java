package com.ljc.gw;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Configuration
public class RouteConfig {

    /**
     * rewrite path, 将xxx/bing/yyyy重写为xxx/yyyy
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator route1(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("redirect bing.com", r -> r
                        .path("/bing/**")
                        .filters(f -> f.rewritePath("/bing/(?<remaining>.*)", "/${remaining}")
                                .filter(customHeaderFilter()))
                        .uri("https://www.bing.com"))
                .build();
    }

    @Bean
    public GatewayFilter customHeaderFilter() {
        return ((exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            if (!headers.containsKey("A")) {
                // Header "A" is not present, return JSON with code 800
                return Mono.defer(() -> {
                    exchange.getResponse().setStatusCode(HttpStatus.EXPECTATION_FAILED);
                    exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    return exchange.getResponse().writeWith(Mono.just(
                            exchange.getResponse().bufferFactory().wrap("{\"code\": 800}".getBytes())));
                });
            }
            return chain.filter(exchange);
        });
    }

    @Bean
    public RouteLocator route2(RouteLocatorBuilder builder) {

        return builder
                .routes()
                .route("call api", r -> r
                        .path("/callapi/**")
                        .filters(f -> f.rewritePath("/callapi/(?<remaining>.*)", "/${remaining}")
                                .filter(callApiFilter()))
                        .uri("https://test.com/index.php"))
                .build();
    }

    @Bean
    public GatewayFilter callApiFilter() {
        return ((exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            if (headers.containsKey("call_api")) {
                String apiUrl = "testUrl";
                String requestBody = "test";
                WebClient webClient = WebClient.create(apiUrl);
                Object resp;
                return webClient.post()
                        .uri(apiUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(requestBody)
                        .retrieve()
                        .onStatus(
                                HttpStatus::is4xxClientError,
                                response -> Mono.error(new ResponseStatusException(response.statusCode()))
                        ).bodyToMono(String.class)
                        .flatMap(apiResponse -> {
                            if (apiResponse.contains("success")) {
                                return Mono.defer(() -> {
                                    exchange.getResponse().setStatusCode(HttpStatus.EXPECTATION_FAILED);
                                    exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                                    return exchange.getResponse().writeWith(Mono.just(
                                            exchange.getResponse().bufferFactory().wrap(apiResponse.getBytes())));
                                });
                            } else {
                                return Mono.defer(() -> {
                                    exchange.getResponse().setStatusCode(HttpStatus.EXPECTATION_FAILED);
                                    exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                                    return exchange.getResponse().writeWith(Mono.just(
                                            exchange.getResponse().bufferFactory().wrap("{\"code\": 500}".getBytes())));
                                });
                            }
                        });
            } else {
                return chain.filter(exchange);
            }
        });
    }

}
