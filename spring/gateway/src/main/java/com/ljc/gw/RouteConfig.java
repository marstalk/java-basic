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
                        .uri("https://usertracking.chinawayltd.com/index.php?idSite=65&module=API&method=VisitsSummary.get&format=json&date=2023-05-07&period=day&token_auth=69deb5f74b1cbc0c5b0a7fc720e416d9&filter_limit=100"))
                .build();
    }

    @Bean
    public GatewayFilter callApiFilter() {
        return ((exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            if (headers.containsKey("call_api")) {
                String apiUrl = "https://usertracking.huoyunren.com/piwik.php";
                String requestBody = "{\"requests\":[\"?idsite=150070&send_image=0&res=1440x3200&ua=Dalvik%2F2.1.0%20%28Linux%3B%20U%3B%20Android%2013%3B%2022127RK46C%20Build%2FTKQ1.220905.001%29&url=https%3A%2F%2Fcom.gxt.ydt.driver%2Fcom.gxt.ydt.library.fragment.WaybillListFragment&rand=24603&rec=1&apiv=1&uid=579780346623561728&cdt=2023-09-26%2018%3A52%3A05%2B0800&action_name=WaybillListFragment&_id=2e68fbf757c54c5b&lang=zh\",\"?idsite=150070&send_image=0&res=1440x3200&ua=Dalvik%2F2.1.0%20%28Linux%3B%20U%3B%20Android%2013%3B%2022127RK46C%20Build%2FTKQ1.220905.001%29&url=https%3A%2F%2Fcom.gxt.ydt.driver%2Fcom.gxt.ydt.fragment.DriverWaybillTabFragment&rand=17126&rec=1&apiv=1&uid=579780346623561728&cdt=2023-09-26%2018%3A52%3A05%2B0800&action_name=%E8%AE%A2%E5%8D%95&_id=2e68fbf757c54c5b&lang=zh\",\"?_viewts=1695725214&idsite=150070&send_image=0&res=1440x3200&_idvc=14&ua=Dalvik%2F2.1.0%20%28Linux%3B%20U%3B%20Android%2013%3B%2022127RK46C%20Build%2FTKQ1.220905.001%29&url=https%3A%2F%2Fcom.gxt.ydt.driver%2Fcom.gxt.ydt.library.activity.WelcomeActivity&rand=20458&rec=1&apiv=1&_idts=1695722423&cdt=2023-09-26%2018%3A52%3A01%2B0800&action_name=56888%E4%B8%80%E7%82%B9%E9%80%9A%E5%8F%B8%E6%9C%BA&_id=2e68fbf757c54c5b&new_visit=1&lang=zh\",\"?idsite=150070&send_image=0&res=1440x3200&ua=Dalvik%2F2.1.0%20%28Linux%3B%20U%3B%20Android%2013%3B%2022127RK46C%20Build%2FTKQ1.220905.001%29&url=https%3A%2F%2Fcom.gxt.ydt.driver%2Fcom.gxt.ydt.activity.DriverActivity&rand=49313&rec=1&apiv=1&uid=579780346623561728&cdt=2023-09-26%2018%3A52%3A03%2B0800&action_name=56888%E4%B8%80%E7%82%B9%E9%80%9A%E5%8F%B8%E6%9C%BA&_id=2e68fbf757c54c5b&lang=zh\",\"?idsite=150070&send_image=0&res=1440x3200&ua=Dalvik%2F2.1.0%20%28Linux%3B%20U%3B%20Android%2013%3B%2022127RK46C%20Build%2FTKQ1.220905.001%29&url=https%3A%2F%2Fcom.gxt.ydt.driver%2Fcom.gxt.ydt.library.fragment.OrderListFragment&rand=16964&rec=1&apiv=1&uid=579780346623561728&cdt=2023-09-26%2018%3A52%3A03%2B0800&action_name=OrderListFragment&_id=2e68fbf757c54c5b&lang=zh\",\"?idsite=150070&send_image=0&res=1440x3200&ua=Dalvik%2F2.1.0%20%28Linux%3B%20U%3B%20Android%2013%3B%2022127RK46C%20Build%2FTKQ1.220905.001%29&url=https%3A%2F%2Fcom.gxt.ydt.driver%2Fcom.gxt.ydt.library.fragment.OrderFragment&rand=95751&rec=1&apiv=1&uid=579780346623561728&cdt=2023-09-26%2018%3A52%3A03%2B0800&action_name=%E8%B4%A7%E6%BA%90&_id=2e68fbf757c54c5b&lang=zh\"]}";
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
