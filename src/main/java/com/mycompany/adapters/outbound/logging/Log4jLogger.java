package com.mycompany.adapters.outbound.logging;

import org.apache.logging.log4j.*;

public class Log4jLogger implements com.mycompany.core.ports.outbound.ForLogging {
    private final Logger logger;

    Log4jLogger(Logger logger) {
        this.logger = logger;
    }

    public void debug(String message, Object... args) {
        this.logger.debug(message, args);
    }

    public void info(String message, Object... args) {
        this.logger.info(message, args);
    }

    public void warn(String message, Object... args) {
        this.logger.warn(message, args);
    }

    public void error(java.lang.Exception error) {
        this.logger.error(error);
    }

    public void error(String message, Object... args) {
        this.logger.error(message, args);
    }
}
