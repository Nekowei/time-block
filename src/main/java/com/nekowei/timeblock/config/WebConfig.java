package com.nekowei.timeblock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

@Configuration
@ComponentScan("com.nekowei.timeblock")
public class WebConfig {
    @Bean
    public SimpleUrlHandlerMapping sampleServletMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MAX_VALUE - 2);
        return mapping;
    }
}
