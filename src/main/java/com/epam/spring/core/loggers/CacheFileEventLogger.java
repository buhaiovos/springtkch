package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;

import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;

    public void setCache(List<Event> cache) {
        this.cache = cache;
    }

    private List<Event> cache;

    public CacheFileEventLogger(int cacheSize, String filename) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event e : cache) {
            super.logEvent(e);
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

}
