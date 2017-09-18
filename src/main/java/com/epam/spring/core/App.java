package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public App() {
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String... args) throws InterruptedException {
        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = (Event) ctx.getBean("event");
        System.out.println(event);
        app.logEvent(event);

        Thread.sleep(1000);

        event = (Event) ctx.getBean("event");
        System.out.println(event);
        app.logEvent(event);

        ctx.close();
    }

    private void logEvent(Event event) {
        eventLogger.logEvent(event);
    }

}
