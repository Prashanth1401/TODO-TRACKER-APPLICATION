package com.todo.config;

import com.todo.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class APIConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p
                        .path("/api/v1/**")
                        .uri("http://localhost:8081/"))
                .route(p->p
                        .path("/api/imp/**")
                        .uri("http://localhost:8082/"))
                .route(p->p
                        .path("/api/v2/**")
                        .uri("http://localhost:8083/"))
                .route(p->p
                        .path("/api/Archieve/**")
                        .uri("http://localhost:8086/"))
                .build();
    }

    @Bean
    public FilterRegistrationBean jwtFilterBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((Filter) new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/**");
        return filterRegistrationBean;
    }
}
