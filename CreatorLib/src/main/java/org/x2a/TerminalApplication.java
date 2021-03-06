package org.x2a;


import org.apache.log4j.*;
import org.x2a.picture.ImagePack;
import org.x2a.picture.Pallet;
import org.x2a.picture.Picture;
import org.x2a.picture.PngPicture;

import java.io.File;
import java.io.IOException;

/**
 * Created by ethan on 7/11/17.
 */


public class TerminalApplication {
    private static final PatternLayout fileLayout = new PatternLayout("[%-5p] %d, %c - %m%n");
    private static final PatternLayout consoleLayout = new PatternLayout("[%-5p] %c{5} - %m%n");

    private static final Logger log = Logger.getLogger(TerminalApplication.class);

    public static void main(String[] args) throws IOException {
        System.setProperty("java.awt.headless", "true");
        try {
            configureLogging();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void configureLogging() throws IOException {
        Logger.getRootLogger().addAppender(new FileAppender(fileLayout, "log.log"));
        ConsoleAppender appender = new ConsoleAppender(consoleLayout);
        appender.setThreshold(Level.INFO);
        Logger.getRootLogger().addAppender(appender);

        log.debug("logging configured");
    }

}
