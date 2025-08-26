package com.mycompany.core.ports.outbound;

/**
 * Logger Factory Port.
 * Creates a ForLogging port associated to the given logger key.
 * 
 * Similar to ForLogging, this is heavily leaning towards Log4j.
 */
public interface ForLoggingFactory {
    ForLogging create(Object loggerKey);
}
