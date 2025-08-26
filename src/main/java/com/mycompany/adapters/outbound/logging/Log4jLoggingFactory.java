package com.mycompany.adapters.outbound.logging;

import com.mycompany.core.ports.outbound.*;
import org.apache.logging.log4j.*;

public class Log4jLoggingFactory implements ForLoggingFactory {
    public ForLogging create(Object loggerKey) {
        return new Log4jLogger(LogManager.getLogger(loggerKey));
    }
}
