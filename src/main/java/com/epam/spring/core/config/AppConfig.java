package com.epam.spring.core.config;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Client client(@Value("${client.id}")String id, @Value("${client.name}")String name) {
        return new Client(id, name);
    }

    @Bean
    @Scope(scopeName = "prototype")
    public Event event() {
        return new Event(new Date(), DateFormat.getDateTimeInstance());
    }

}
