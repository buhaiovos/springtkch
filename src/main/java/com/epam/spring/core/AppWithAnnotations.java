package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.beans.EventType;
import com.epam.spring.core.config.AppConfig;
import com.epam.spring.core.config.LoggersConfig;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("appAnno")
public class AppWithAnnotations {

    private Client client;
    private EventLogger defaultLogger;

    @Autowired
    public void setClient(Client client) {
        this.client = client;
    }

    @Autowired
    @Qualifier("consoleEventLogger")
    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public AppWithAnnotations() {
    }



    public static void main(String... args) throws InterruptedException {
        AnnotationConfigApplicationContext ctx=
                new AnnotationConfigApplicationContext(AppConfig.class, LoggersConfig.class);
        ctx.scan("com.epam.spring.core");
        //ctx.refresh();

        AppWithAnnotations app = (AppWithAnnotations) ctx.getBean("appAnno");
        Event event = (Event) ctx.getBean("event");
        event.setMsg("INFO");
        app.logEvent(event);

        Thread.sleep(1000);

        event = (Event) ctx.getBean("event");
        event.setMsg("ERROR");
        app.logEvent(event);

        event = (Event) ctx.getBean("event");
        event.setMsg("OTHER");
        app.logEvent(event);

        ctx.close();
    }

    private void logEvent(Event event) {
        defaultLogger.logEvent(event);
    }
}
