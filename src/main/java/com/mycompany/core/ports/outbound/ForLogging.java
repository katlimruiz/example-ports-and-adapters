package com.mycompany.core.ports.outbound;

/**
 * Logger Port.
 * Specific for "{}" formatting, and heavily leaning towards Log4j.
 * 
 * Implementations will need to stick to this because the entire program will
 * use {} for formatting arguments.
 */
public interface ForLogging {
    void debug(String message, Object... args);

    void info(String message, Object... args);

    void warn(String message, Object... args);

    void error(java.lang.Exception error);

    void error(String message, Object... args);
}
