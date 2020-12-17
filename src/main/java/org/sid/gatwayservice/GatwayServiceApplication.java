package org.sid.gatwayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatwayServiceApplication.class, args);
	}
//	@Bean
	RouteLocator gatewayRoutesStatic(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r-> r.path("/customers/**").uri("lb://customer-service").id("r1"))
				.route(r->r.path("/products/**").uri("lb://product-service").id("r2"))
				.build();
	}

	@Bean
	DiscoveryClientRouteDefinitionLocator gatewayRouteDynamic(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties ldp){
		return new DiscoveryClientRouteDefinitionLocator(rdc,ldp);
	}
}
