package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private String filename;
    private File file;

    public FileEventLogger() {
    }

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init() throws IOException {
        this.file = new File(filename);
        /*if (file.canWrite()) {
            throw new RuntimeException("Write access denied");
        }*/
    }

    public void logEvent(Event event) {
        try {
            FileUtils
                    .writeStringToFile(new File(filename),
                                       event.toString(),
                                      true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
