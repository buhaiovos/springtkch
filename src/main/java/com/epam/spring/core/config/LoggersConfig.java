package com.epam.spring.core.config;

import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.ConsoleEventLogger;
import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.loggers.FileEventLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggersConfig {

    @Bean(name="consoleEventLogger")
    public EventLogger getConsoleLogger() {
        return new ConsoleEventLogger();
    }

    @Bean(name="consoleEventLogger")
    public EventLogger getFileLogger(@Value("out.txt")String str) {
        return new FileEventLogger(str);
    }

}
