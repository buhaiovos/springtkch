package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.beans.EventType;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    Client client;
    EventLogger defaultLogger;
    Map<EventType, EventLogger> loggers;

    public App() {
    }

    public App(Client client, EventLogger eventLogger,
               Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String... args) throws InterruptedException {
        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = (Event) ctx.getBean("event");
        event.setMsg("INFO");
        app.logEvent(EventType.INFO, event);

        Thread.sleep(1000);

        event = (Event) ctx.getBean("event");
        event.setMsg("ERROR");
        app.logEvent(EventType.ERROR, event);

        event = (Event) ctx.getBean("event");
        event.setMsg("OTHER");
        app.logEvent(null, event);

        ctx.close();
    }

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

}
